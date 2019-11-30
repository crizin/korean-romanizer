package net.crizin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Java library that converts Korean into Roman characters.
 * It is implemented based on the National Korean Language Romanization and can be covered a lot,
 * but it is not perfect because it is difficult to implement 100% if there is no word dictionary data due to the nature of Korean.
 */
public class KoreanRomanizer {
	private static final Pattern doubleSurnames = Pattern.compile("^(\\s*)(강전|남궁|독고|동방|등정|망절|무본|사공|서문|선우|소봉|어금|장곡|제갈|황목|황보)(.{1,10})$");
	private static final Pattern districtPostfixes = Pattern.compile("^(.{1,20}?)(특별자치도|특별자치시|특별시|광역시|대로|구|군|도|동|리|면|시|읍|가|길|로)(\\s*)$");
	private static final Pattern districtPostfixesWithNumbers1 = Pattern.compile("^(.{0,20}?)(\\d+)(\\s*)(가길|가|번길|로|단지|동)(\\s*)$");
	private static final Pattern districtPostfixesWithNumbers2 = Pattern.compile("^(.{0,20}?)(대?로)\\s*(\\d+[가번]?)(길)(\\s*)$");
	private static final Map<String, String> typicalSurenameRules = new HashMap<String, String>() {
		{
			put("가", "Ka");
			put("간", "Kan");
			put("갈", "Kal");
			put("감", "Kam");
			put("강", "Kang");
			put("강전", "Kangjun");
			put("견", "Kyun");
			put("경", "Kyung");
			put("계", "Kye");
			put("고", "Ko");
			put("공", "Kong");
			put("곽", "Kwak");
			put("구", "Koo");
			put("국", "Kook");
			put("군", "Kun");
			put("궁", "Koong");
			put("궉", "Kwok");
			put("권", "Kwon");
			put("근", "Keun");
			put("금", "Keum");
			put("기", "Ki");
			put("길", "Kil");
			put("김", "Kim");
			put("노", "Noh");
			put("두", "Doo");
			put("란", "Lan");
			put("뢰", "Loi");
			put("루", "Lu");
			put("망절", "Mangjul");
			put("명", "Myung");
			put("문", "Moon");
			put("박", "Park");
			put("변", "Byun");
			put("부", "Boo");
			put("선", "Sun");
			put("선우", "Sunwoo");
			put("성", "Sung");
			put("순", "Soon");
			put("신", "Shin");
			put("심", "Shim");
			put("아", "Ah");
			put("어금", "Eokum");
			put("오", "Oh");
			put("우", "Woo");
			put("운", "Woon");
			put("유", "Yoo");
			put("윤", "Yoon");
			put("이", "Lee");
			put("임", "Lim");
			put("정", "Jung");
			put("조", "Cho");
			put("주", "Joo");
			put("준", "June");
			put("즙", "Chup");
			put("최", "Choi");
			put("편", "Pyun");
			put("평", "Pyung");
			put("풍", "Poong");
			put("현", "Hyun");
			put("형", "Hyung");
			put("흥", "Hong");
		}
	};

	public KoreanRomanizer() {
		super();
	}

	/**
	 * Romanize string.
	 *
	 * @param string
	 * 		the string to convert to roman string.
	 * @return the romanized string.
	 * @throws NullPointerException
	 * 		if argument string is null
	 */
	public static String romanize(String string) {
		return romanize(string, null, null);
	}

	/**
	 * Romanize string with consonant assimilation option.
	 *
	 * @param string
	 * 		the string to convert to roman string.
	 * @param consonantAssimilation
	 * 		the consonant assimilation type.
	 * @return the romanized string.
	 * @throws NullPointerException
	 * 		if argument string is null
	 */
	public static String romanize(String string, KoreanCharacter.ConsonantAssimilation consonantAssimilation) {
		return romanize(string, null, consonantAssimilation);
	}

	/**
	 * Romanize string with type option.
	 *
	 * @param string
	 * 		the string to convert to roman string.
	 * @param type
	 * 		the type of word
	 * @return the romanized string.
	 * @throws NullPointerException
	 * 		if argument string is null
	 */
	public static String romanize(String string, KoreanCharacter.Type type) {
		return romanize(string, type, null);
	}

	/**
	 * Romanize string with Consonant assimilation and type option.
	 *
	 * @param string
	 * 		the string to convert.
	 * @param type
	 * 		the type of word
	 * @param consonantAssimilation
	 * 		the consonant assimilation type.
	 * @return Romanized string
	 * @throws NullPointerException
	 * 		if string parameter is null
	 */
	public static String romanize(String string, KoreanCharacter.Type type, KoreanCharacter.ConsonantAssimilation consonantAssimilation) {
		if (string == null) {
			throw new NullPointerException("String should not be null.");
		}

		consonantAssimilation = (consonantAssimilation == null) ? KoreanCharacter.ConsonantAssimilation.Regressive : consonantAssimilation;
		type = (type == null) ? KoreanCharacter.Type.Typical : type;

		switch (type) {
			case Name:
			case NameTypical:
				string = normalizeName(string, type);
				break;
			case District:
				string = normalizeDistrict(string);
				break;
		}

		StringBuilder buffer = new StringBuilder(string.length() * 3);

		KoreanCharacter prevCharacter, currentCharacter = null, nextCharacter = null;

		for (int i = 0; i < string.length(); i++) {
			prevCharacter = currentCharacter;
			currentCharacter = (nextCharacter == null) ? new KoreanCharacter(string.charAt(i)) : nextCharacter;
			nextCharacter = (i < string.length() - 1) ? new KoreanCharacter(string.charAt(i + 1)) : null;

			if (currentCharacter.isKoreanCharacter()) {
				String pronunciation = currentCharacter.getRomanizedString(prevCharacter, nextCharacter, consonantAssimilation, type);

				if (prevCharacter == null || !prevCharacter.isKoreanCharacter()) {
					if (type == KoreanCharacter.Type.District && prevCharacter != null && (prevCharacter.toString().equals("-") || Character.isDigit(prevCharacter.getCharacter()))) {
						buffer.append(pronunciation);
					} else {
						buffer.append(Character.toUpperCase(pronunciation.charAt(0)));
						buffer.append(pronunciation.substring(1));
					}
				} else {
					buffer.append(pronunciation);
				}
			} else {
				buffer.append(currentCharacter);
			}
		}

		return buffer.toString();
	}

	/**
	 * The {@code main} method to convert string from the standard input.
	 *
	 * @param args
	 * 		first argument is {@link KoreanCharacter.Type} value,
	 * 		second argument is {@link KoreanCharacter.ConsonantAssimilation} value
	 * 		(Both arguments must be specified or none specified.)
	 */
	public static void main(String... args) {
		Scanner scanner = new Scanner(System.in);

		KoreanCharacter.Type type = null;
		KoreanCharacter.ConsonantAssimilation consonantAssimilation = null;

		if (args.length > 0) {
			try {
				type = KoreanCharacter.Type.valueOf(args[0]);
			} catch (IllegalArgumentException ignored) {
				System.err.println("Unknown type: " + args[0]);
			}
		}

		if (args.length > 1) {
			try {
				consonantAssimilation = KoreanCharacter.ConsonantAssimilation.valueOf(args[1]);
			} catch (IllegalArgumentException ignored) {
				System.err.println("Unknown consonantAssimilation: " + args[1]);
			}
		}

		while (scanner.hasNext()) {
			System.out.println(KoreanRomanizer.romanize(scanner.nextLine(), type, consonantAssimilation));
		}
	}

	/**
	 * @param string
	 * 		the name string to normalize.
	 * @param type
	 * 	 	the type of word
	 * @return the normalized name string.
	 */
	private static String normalizeName(String string, KoreanCharacter.Type type) {
		Matcher matcher = doubleSurnames.matcher(string);

		if (type == KoreanCharacter.Type.NameTypical) {
			if (matcher.find()) {
				return matcher.group(1) + typicalSurenameRules.getOrDefault(matcher.group(2), matcher.group(2)) + " " + matcher.group(3);
			} else {
				return typicalSurenameRules.getOrDefault(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0))) + " " + string.substring(1);
			}
		} else {
			if (matcher.find()) {
				return matcher.group(1) + matcher.group(2) + " " + matcher.group(3);
			} else {
				return string.charAt(0) + " " + string.substring(1);
			}
		}
	}

	/**
	 * @param string
	 * 		the district string to normalize.
	 * @return the normalized district string.
	 */
	private static String normalizeDistrict(String string) {
		Matcher matcher = districtPostfixesWithNumbers2.matcher(string);

		if (matcher.find()) {
			return matcher.group(1) + "-" + matcher.group(2) + " " + matcher.group(3) + "-" + matcher.group(4) + matcher.group(5);
		} else {
			matcher = districtPostfixesWithNumbers1.matcher(string);
			if (matcher.find()) {
				return matcher.group(1) + (matcher.group(1).endsWith(" ") ? "" : " ") + matcher.group(2) + "-" + matcher.group(3) + matcher.group(4);
			} else {
				matcher = districtPostfixes.matcher(string);
				if (matcher.find()) {
					return matcher.group(1) + "-" + matcher.group(2) + matcher.group(3);
				}
			}
		}

		return string;
	}
}