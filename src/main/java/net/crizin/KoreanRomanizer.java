package net.crizin;

public class KoreanRomanizer {
	private boolean capitalizeOnFirstLetter = true;
	private boolean useHyphenWhenVowelConfused = true;
	private static String[] chosungs = {"g", "kk", "n", "d", "tt", "r", "m", "b", "pp", "s", "ss", "", "j", "jj", "ch", "k", "t", "p", "h"};
	private static String[] jungsungs = {"a", "ae", "ya", "yae", "eo", "e", "yeo", "ye", "o", "wa", "wae", "oe", "yo", "u", "wo", "we", "wi", "yu", "eu", "ui", "i"};

	public static final int CHOSUNG_ㄱ = 0;
	public static final int CHOSUNG_ㄲ = 1;
	public static final int CHOSUNG_ㄴ = 2;
	public static final int CHOSUNG_ㄷ = 3;
	public static final int CHOSUNG_ㄸ = 4;
	public static final int CHOSUNG_ㄹ = 5;
	public static final int CHOSUNG_ㅁ = 6;
	public static final int CHOSUNG_ㅂ = 7;
	public static final int CHOSUNG_ㅃ = 8;
	public static final int CHOSUNG_ㅅ = 9;
	public static final int CHOSUNG_ㅆ = 10;
	public static final int CHOSUNG_ㅇ = 11;
	public static final int CHOSUNG_ㅈ = 12;
	public static final int CHOSUNG_ㅉ = 13;
	public static final int CHOSUNG_ㅊ = 14;
	public static final int CHOSUNG_ㅋ = 15;
	public static final int CHOSUNG_ㅌ = 16;
	public static final int CHOSUNG_ㅍ = 17;
	public static final int CHOSUNG_ㅎ = 18;

	public static final int JUNGSUNG_ㅏ = 0;
	public static final int JUNGSUNG_ㅐ = 1;
	public static final int JUNGSUNG_ㅑ = 2;
	public static final int JUNGSUNG_ㅒ = 3;
	public static final int JUNGSUNG_ㅓ = 4;
	public static final int JUNGSUNG_ㅔ = 5;
	public static final int JUNGSUNG_ㅕ = 6;
	public static final int JUNGSUNG_ㅖ = 7;
	public static final int JUNGSUNG_ㅗ = 8;
	public static final int JUNGSUNG_ㅘ = 9;
	public static final int JUNGSUNG_ㅙ = 10;
	public static final int JUNGSUNG_ㅚ = 11;
	public static final int JUNGSUNG_ㅛ = 12;
	public static final int JUNGSUNG_ㅜ = 13;
	public static final int JUNGSUNG_ㅝ = 14;
	public static final int JUNGSUNG_ㅞ = 15;
	public static final int JUNGSUNG_ㅟ = 16;
	public static final int JUNGSUNG_ㅠ = 17;
	public static final int JUNGSUNG_ㅡ = 18;
	public static final int JUNGSUNG_ㅢ = 19;
	public static final int JUNGSUNG_ㅣ = 20;

	public static final int JONGSUNG_NONE = 0;
	public static final int JONGSUNG_ㄱ = 1;
	public static final int JONGSUNG_ㄲ = 2;
	public static final int JONGSUNG_ㄳ = 3;
	public static final int JONGSUNG_ㄴ = 4;
	public static final int JONGSUNG_ㄵ = 5;
	public static final int JONGSUNG_ㄶ = 6;
	public static final int JONGSUNG_ㄷ = 7;
	public static final int JONGSUNG_ㄹ = 8;
	public static final int JONGSUNG_ㄺ = 9;
	public static final int JONGSUNG_ㄻ = 10;
	public static final int JONGSUNG_ㄼ = 11;
	public static final int JONGSUNG_ㄽ = 12;
	public static final int JONGSUNG_ㄾ = 13;
	public static final int JONGSUNG_ㄿ = 14;
	public static final int JONGSUNG_ㅀ = 15;
	public static final int JONGSUNG_ㅁ = 16;
	public static final int JONGSUNG_ㅂ = 17;
	public static final int JONGSUNG_ㅄ = 18;
	public static final int JONGSUNG_ㅅ = 19;
	public static final int JONGSUNG_ㅆ = 20;
	public static final int JONGSUNG_ㅇ = 21;
	public static final int JONGSUNG_ㅈ = 22;
	public static final int JONGSUNG_ㅊ = 23;
	public static final int JONGSUNG_ㅋ = 24;
	public static final int JONGSUNG_ㅌ = 25;
	public static final int JONGSUNG_ㅍ = 26;
	public static final int JONGSUNG_ㅎ = 27;

	public void setCapitalizeOnFirstLetter(boolean capitalizeOnFirstLetter) {
		this.capitalizeOnFirstLetter = capitalizeOnFirstLetter;
	}

	public void setUseHyphenWhenVowelConfused(boolean useHyphenWhenVowelConfused) {
		this.useHyphenWhenVowelConfused = useHyphenWhenVowelConfused;
	}

	public String romanize(String string) throws NullPointerException {
		if (string == null) {
			throw new NullPointerException("String should not be null.");
		}

		int length = string.length();
		boolean isFirstLetter = true;
		boolean skipNextChosung = false;
		StringBuilder buffer = new StringBuilder(length * 3);

		for (int i = 0; i < length; i++) {
			char character = string.charAt(i);

			if (character < 0xAC00 || character > 0xD7A3) {
				buffer.append(character);
				isFirstLetter = true;
				continue;
			}

			character -= 0xAC00;

			if (!skipNextChosung) {
				String chosung = chosungs[character / (21 * 28)];

				if (capitalizeOnFirstLetter && isFirstLetter && chosung.length() > 0) {
					chosung = Character.toUpperCase(chosung.charAt(0)) + chosung.substring(1);
					isFirstLetter = false;
				}

				buffer.append(chosung);
			}

			skipNextChosung = false;

			String jungsung = jungsungs[character % (21 * 28) / 28];

			if (capitalizeOnFirstLetter && isFirstLetter && jungsung.length() > 0) {
				jungsung = Character.toUpperCase(jungsung.charAt(0)) + jungsung.substring(1);
				isFirstLetter = false;
			}

			buffer.append(jungsung);

			int nextChosung = -1;
			int nextJungsung = -1;

			if (i < length - 1) {
				char nextCharacter = string.charAt(i + 1);

				if (nextCharacter >= 0xAC00 && nextCharacter <= 0xD7A3) {
					nextChosung = (nextCharacter - 0xAC00) / (21 * 28);
					nextJungsung = (nextCharacter - 0xAC00) % (21 * 28) / 28;
				}
			}

			int jongsung = character % 28;

			if (useHyphenWhenVowelConfused && jongsung == JONGSUNG_NONE && nextChosung == CHOSUNG_ㅇ) {
				char nextJungsungChar = jungsungs[nextJungsung].charAt(0);
				boolean useHyphen = false;

				switch (jungsung.charAt(jungsung.length() - 1)) {
					case 'a':
						switch (nextJungsungChar) {
							case 'a':
							case 'e':
								useHyphen = true;
						}
						break;
					case 'e':
						switch (nextJungsungChar) {
							case 'a':
							case 'e':
							case 'o':
							case 'u':
								useHyphen = true;
						}
						break;
				}

				if (useHyphen) {
					buffer.append("-");
				}
			}

			skipNextChosung = false;

			switch (jongsung) {
				case JONGSUNG_ㄱ:
					switch (nextChosung) {
						case CHOSUNG_ㄲ:
						case CHOSUNG_ㅋ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("ng");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("g");
							break;
						default:
							buffer.append("k");
					}
					break;
				case JONGSUNG_ㄲ:
					switch (nextChosung) {
						case CHOSUNG_ㄲ:
						case CHOSUNG_ㅋ:
							break;
						case CHOSUNG_ㄱ:
							buffer.append("kg");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("ng");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("kk");
							break;
						case CHOSUNG_ㅎ:
							buffer.append("k");
							skipNextChosung = true;
							break;
						default:
							buffer.append("k");
					}
					break;
				case JONGSUNG_ㄳ:
					switch (nextChosung) {
						case CHOSUNG_ㄲ:
						case CHOSUNG_ㅋ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("ng");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("ks");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							buffer.append("k");
							skipNextChosung = true;
							break;
						default:
							buffer.append("k");
					}
					break;
				case JONGSUNG_ㄴ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("n");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							buffer.append("g");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						default:
							buffer.append("n");
					}
					break;
				case JONGSUNG_ㄵ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("n");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							buffer.append("g");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄷ:
							buffer.append("ntt");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
						case CHOSUNG_ㅎ:
							skipNextChosung = true;
						case CHOSUNG_ㅈ:
							buffer.append("nj");
							break;
						default:
							buffer.append("n");
					}
					break;
				case JONGSUNG_ㄶ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("n");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							buffer.append("g");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						default:
							buffer.append("n");
					}
					break;
				case JONGSUNG_ㄷ:
					switch (nextChosung) {
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							switch (nextJungsung) {
								case JUNGSUNG_ㅑ:
								case JUNGSUNG_ㅒ:
								case JUNGSUNG_ㅕ:
								case JUNGSUNG_ㅖ:
								case JUNGSUNG_ㅛ:
								case JUNGSUNG_ㅠ:
								case JUNGSUNG_ㅣ:
									buffer.append("j");
									break;
								default:
									buffer.append("d");
							}
							break;
						case CHOSUNG_ㅌ:
							buffer.append("t");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						case CHOSUNG_ㅎ:
							buffer.append("t");
							skipNextChosung = true;
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㄹ:
					switch (nextChosung) {
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							skipNextChosung = true;
						case CHOSUNG_ㅇ:
							buffer.append("r");
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㄺ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
						case CHOSUNG_ㄲ:
							buffer.append("lkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅂ:
							buffer.append("lpp");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅃ:
						case CHOSUNG_ㅊ:
						case CHOSUNG_ㅅ:
						case CHOSUNG_ㅋ:
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅍ:
						case CHOSUNG_ㄸ:
							buffer.append("l");
							break;
						case CHOSUNG_ㄷ:
							buffer.append("ltt");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅁ:
							buffer.append("ng");
							break;
						case CHOSUNG_ㅆ:
							buffer.append("lss");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("lg");
							break;
						case CHOSUNG_ㅈ:
						case CHOSUNG_ㅉ:
							buffer.append("ljj");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							buffer.append("lk");
							skipNextChosung = true;
							break;
						default:
							buffer.append("k");
					}
					break;
				case JONGSUNG_ㄻ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("mkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄷ:
							buffer.append("mtt");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅁ:
							buffer.append("l");
							break;
						case CHOSUNG_ㅂ:
							buffer.append("mpp");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅅ:
							buffer.append("mss");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("lm");
							break;
						case CHOSUNG_ㅈ:
							buffer.append("mjj");
							skipNextChosung = true;
							break;
						default:
							buffer.append("m");
					}
					break;
				case JONGSUNG_ㄼ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("lkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
							buffer.append("m");
							break;
						case CHOSUNG_ㅎ:
							skipNextChosung = true;
						case CHOSUNG_ㄷ:
						case CHOSUNG_ㅂ:
						case CHOSUNG_ㄸ:
						case CHOSUNG_ㅅ:
						case CHOSUNG_ㅆ:
						case CHOSUNG_ㅈ:
						case CHOSUNG_ㅉ:
						case CHOSUNG_ㅊ:
						case CHOSUNG_ㅋ:
						case CHOSUNG_ㅌ:
							buffer.append("p");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅃ:
							break;
						case CHOSUNG_ㅇ:
							buffer.append("lb");
							skipNextChosung = true;
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㄽ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("lkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄷ:
							buffer.append("ltt");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅂ:
							buffer.append("lpp");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
						case CHOSUNG_ㅎ:
							buffer.append("ls");
							skipNextChosung = true;
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㄾ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("lkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅂ:
							buffer.append("lp");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄷ:
						case CHOSUNG_ㅇ:
						case CHOSUNG_ㅎ:
							buffer.append("lt");
							skipNextChosung = true;
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㄿ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("lkk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
							buffer.append("m");
							break;
						case CHOSUNG_ㄷ:
						case CHOSUNG_ㅂ:
						case CHOSUNG_ㄸ:
						case CHOSUNG_ㅅ:
						case CHOSUNG_ㅆ:
						case CHOSUNG_ㅈ:
						case CHOSUNG_ㅉ:
						case CHOSUNG_ㅊ:
						case CHOSUNG_ㅋ:
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅎ:
							buffer.append("p");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅃ:
						case CHOSUNG_ㅍ:
							break;
						case CHOSUNG_ㅇ:
							buffer.append("lp");
							skipNextChosung = true;
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㅀ:
					switch (nextChosung) {
						case CHOSUNG_ㄱ:
							buffer.append("lk");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							skipNextChosung = true;
						case CHOSUNG_ㅇ:
							buffer.append("r");
							break;
						default:
							buffer.append("l");
					}
					break;
				case JONGSUNG_ㅁ:
					switch (nextChosung) {
						case CHOSUNG_ㄷ:
							buffer.append("mtt");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						default:
							buffer.append("m");
					}
					break;
				case JONGSUNG_ㅂ:
					switch (nextChosung) {
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅁ:
							buffer.append("m");
							break;
						case CHOSUNG_ㅃ:
							break;
						case CHOSUNG_ㅇ:
							buffer.append("b");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							buffer.append("p");
							break;
						case CHOSUNG_ㅍ:
							buffer.append("p");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						default:
							buffer.append("p");
					}
					break;
				case JONGSUNG_ㅄ:
					switch (nextChosung) {
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("m");
							break;
						case CHOSUNG_ㅃ:
							break;
						case CHOSUNG_ㅇ:
							buffer.append("ps");
							skipNextChosung = true;
							break;
						default:
							buffer.append("p");
					}
					break;
				case JONGSUNG_ㅅ:
					switch (nextChosung) {
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("s");
							break;
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅎ:
							buffer.append("t");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㅆ:
					switch (nextChosung) {
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("tss");
							break;
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅎ:
							buffer.append("t");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㅇ:
					switch (nextChosung) {
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("ng");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						default:
							buffer.append("ng");
					}
					break;
				case JONGSUNG_ㅈ:
					switch (nextChosung) {
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㅊ:
							buffer.append("t");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("j");
							break;
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅎ:
							switch (nextJungsung) {
								case JUNGSUNG_ㅑ:
								case JUNGSUNG_ㅒ:
								case JUNGSUNG_ㅕ:
								case JUNGSUNG_ㅖ:
								case JUNGSUNG_ㅛ:
								case JUNGSUNG_ㅠ:
								case JUNGSUNG_ㅣ:
									buffer.append("ch");
									skipNextChosung = true;
									break;
								default:
									buffer.append("t");
									if (useHyphenWhenVowelConfused) {
										buffer.append("-");
									}
							}
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㅊ:
					switch (nextChosung) {
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							buffer.append("ch");
							break;
						case CHOSUNG_ㅌ:
						case CHOSUNG_ㅎ:
							buffer.append("t");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㅋ:
					switch (nextChosung) {
						case CHOSUNG_ㄲ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("ng");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ngn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅎ:
							buffer.append("k");
							skipNextChosung = true;
							break;
						default:
							buffer.append("k");
					}
					break;
				case JONGSUNG_ㅌ:
					switch (nextChosung) {
						case CHOSUNG_ㄸ:
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("ll");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅇ:
							switch (nextJungsung) {
								case JUNGSUNG_ㅑ:
								case JUNGSUNG_ㅒ:
								case JUNGSUNG_ㅕ:
								case JUNGSUNG_ㅖ:
								case JUNGSUNG_ㅛ:
								case JUNGSUNG_ㅠ:
								case JUNGSUNG_ㅣ:
									buffer.append("ch");
									break;
								default:
									buffer.append("t");
							}
							break;
						case CHOSUNG_ㅌ:
							buffer.append("t");
							if (useHyphenWhenVowelConfused) {
								buffer.append("-");
							}
							break;
						case CHOSUNG_ㅎ:
							buffer.append("t");
							skipNextChosung = true;
							break;
						default:
							buffer.append("t");
					}
					break;
				case JONGSUNG_ㅍ:
					switch (nextChosung) {
						case CHOSUNG_ㄹ:
							buffer.append("mn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅁ:
							buffer.append("m");
							break;
						case CHOSUNG_ㅃ:
							break;
						case CHOSUNG_ㅇ:
							buffer.append("p");
							skipNextChosung = true;
							break;
						default:
							buffer.append("p");
					}
					break;
				case JONGSUNG_ㅎ:
					switch (nextChosung) {
						case CHOSUNG_ㅇ:
						case CHOSUNG_ㅎ:
							break;
						case CHOSUNG_ㄱ:
							buffer.append("k");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄷ:
							buffer.append("t");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㄴ:
						case CHOSUNG_ㅁ:
							buffer.append("n");
							break;
						case CHOSUNG_ㄹ:
							buffer.append("nn");
							skipNextChosung = true;
							break;
						case CHOSUNG_ㅈ:
							buffer.append("ch");
							skipNextChosung = true;
							break;
						default:
							buffer.append("t");
					}
					break;
			}
		}

		return buffer.toString();
	}
}