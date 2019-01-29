package net.crizin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KoreanRomanizerTest {
	@Test
	public void testKoreanCharacter() {
		KoreanCharacter c = new KoreanCharacter(' ');
		assertNull(c.getChosung());
		assertNull(c.getJungsung());
		assertNull(c.getJongsung());
		assertEquals(' ', c.getCharacter());
		assertEquals(" ", c.toString());
		assertEquals(" ", c.getRomanizedString());
		assertFalse(c.isKoreanCharacter());

		assertEquals('닭', new KoreanCharacter(KoreanCharacter.Chosung.ㄷ, KoreanCharacter.Jungsung.ㅏ, KoreanCharacter.Jongsung.ㄺ).getCharacter());
		assertEquals('소', new KoreanCharacter(KoreanCharacter.Chosung.ㅅ, KoreanCharacter.Jungsung.ㅗ, KoreanCharacter.Jongsung.NONE).getCharacter());

		KoreanCharacter ga = new KoreanCharacter('가');
		KoreanCharacter na = new KoreanCharacter('나');
		KoreanCharacter na2 = new KoreanCharacter('나');

		assertTrue(ga.compareTo(na) < 0);
		assertEquals(0, na.compareTo(na2));
		assertEquals(na, na2);
		assertEquals(Character.hashCode('가'), ga.hashCode());
		assertEquals("가", ga.toString());
	}

	@Test
	public void testException() {
		Throwable exception = assertThrows(NullPointerException.class, () -> KoreanRomanizer.romanize(null));
		assertEquals("String should not be null.", exception.getMessage());
		assertEquals("", KoreanRomanizer.romanize(""));
	}

	@Test
	public void testByConsonantAssimilation() {
		assertEquals("Baengno", KoreanRomanizer.romanize("백로", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Baengno", KoreanRomanizer.romanize("백로", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Digeullieul", KoreanRomanizer.romanize("디귿리을", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Digeunnieul", KoreanRomanizer.romanize("디귿리을", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Amnokgang", KoreanRomanizer.romanize("압록강", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Amnokgang", KoreanRomanizer.romanize("압록강", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Wangsimni", KoreanRomanizer.romanize("왕십리", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Wangsimni", KoreanRomanizer.romanize("왕십리", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Hyeomnyeok", KoreanRomanizer.romanize("협력", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Hyeomnyeok", KoreanRomanizer.romanize("협력", KoreanCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Seollal", KoreanRomanizer.romanize("설날", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Seollal", KoreanRomanizer.romanize("설날", KoreanCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Saengsallyang", KoreanRomanizer.romanize("생산량", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Saengsannyang", KoreanRomanizer.romanize("생산량", KoreanCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Sillamyeon", KoreanRomanizer.romanize("신라면", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Sinnamyeon", KoreanRomanizer.romanize("신라면", KoreanCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Wonsimnyeok", KoreanRomanizer.romanize("원심력", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Wonsimnyeok", KoreanRomanizer.romanize("원심력", KoreanCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Mangna", KoreanRomanizer.romanize("망라", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Mangna", KoreanRomanizer.romanize("망라", KoreanCharacter.ConsonantAssimilation.Progressive));
	}

	@Test
	public void testByType() {
		assertEquals("Gaka", KoreanRomanizer.romanize("각하", KoreanCharacter.Type.Typical));
		assertEquals("Gakha", KoreanRomanizer.romanize("각하", KoreanCharacter.Type.Substantives));
		assertEquals("Matang", KoreanRomanizer.romanize("맏항", KoreanCharacter.Type.Typical));
		assertEquals("Mathang", KoreanRomanizer.romanize("맏항", KoreanCharacter.Type.Substantives));
		assertEquals("Beopak", KoreanRomanizer.romanize("법학", KoreanCharacter.Type.Typical));
		assertEquals("Beophak", KoreanRomanizer.romanize("법학", KoreanCharacter.Type.Substantives));

		assertEquals("Saegyeonpil", KoreanRomanizer.romanize("색연필", KoreanCharacter.Type.Typical));
		assertEquals("Saengnyeonpil", KoreanRomanizer.romanize("색연필", KoreanCharacter.Type.Compound));
		assertEquals("Kong-yeot", KoreanRomanizer.romanize("콩엿", KoreanCharacter.Type.Typical));
		assertEquals("Kongnyeot", KoreanRomanizer.romanize("콩엿", KoreanCharacter.Type.Compound));

		assertEquals("Jongno2Ga", KoreanRomanizer.romanize("종로2가", KoreanCharacter.Type.Typical));
		assertEquals("Jongno 2-ga", KoreanRomanizer.romanize("종로2가", KoreanCharacter.Type.District));
		assertEquals("Seongnamdaero2Beon-gil", KoreanRomanizer.romanize("성남대로2번길", KoreanCharacter.Type.Typical));
		assertEquals("Seongnam-daero 2beon-gil", KoreanRomanizer.romanize("성남대로2번길", KoreanCharacter.Type.District));

		assertEquals("Ijieun", KoreanRomanizer.romanize("이지은", KoreanCharacter.Type.Typical));
		assertEquals("I Jieun", KoreanRomanizer.romanize("이지은", KoreanCharacter.Type.Name));
		assertEquals("Jegalgongmyeong", KoreanRomanizer.romanize("제갈공명", KoreanCharacter.Type.Typical));
		assertEquals("Jegal Gongmyeong", KoreanRomanizer.romanize("제갈공명", KoreanCharacter.Type.Name));
		assertEquals("Bakwayobi", KoreanRomanizer.romanize("박화요비", KoreanCharacter.Type.Typical));
		assertEquals("Bak Hwayobi", KoreanRomanizer.romanize("박화요비", KoreanCharacter.Type.Name));
	}

	@Test
	public void testMisc() {
		assertEquals("Anta", KoreanRomanizer.romanize("않다"));
	}

	@Test
	public void testSpec() {
		// 제2장 표기 일람

		// [붙임 1] ‘ㄱ, ㄷ, ㅂ’은 모음 앞에서는 ‘ｇ, ｄ, ｂ’로, 자음 앞이나 어말에서는 ‘ｋ, ｔ, p’로 적는다.
		assertEquals("Gumi", KoreanRomanizer.romanize("구미"));
		assertEquals("Yeongdong", KoreanRomanizer.romanize("영동"));
		assertEquals("Baegam", KoreanRomanizer.romanize("백암"));
		assertEquals("Okcheon", KoreanRomanizer.romanize("옥천"));
		assertEquals("Hapdeok", KoreanRomanizer.romanize("합덕"));
		assertEquals("Hobeop", KoreanRomanizer.romanize("호법"));
		assertEquals("Wolgot", KoreanRomanizer.romanize("월곶"));
		assertEquals("Beotkkot", KoreanRomanizer.romanize("벚꽃"));
		assertEquals("Hanbat", KoreanRomanizer.romanize("한밭"));

		// [붙임 2] ‘ㄹ’은 모음 앞에서는 ‘ｒ’로, 자음 앞이나 어말에서는 ‘ｌ’로 적는다. 단, ‘ㄹㄹ’은 ‘ll’로 적는다.
		assertEquals("Guri", KoreanRomanizer.romanize("구리"));
		assertEquals("Seorak", KoreanRomanizer.romanize("설악"));
		assertEquals("Chilgok", KoreanRomanizer.romanize("칠곡"));
		assertEquals("Imsil", KoreanRomanizer.romanize("임실"));
		assertEquals("Ulleung", KoreanRomanizer.romanize("울릉"));
		assertEquals("Daegwallyeong", KoreanRomanizer.romanize("대관령"));

		// 제3장 표기상의 유의점

		// 제1항 음운 변화가 일어날 때에는 변화의 결과에 따라 다음 각호와 같이 적는다.

		// 1. 자음 사이에서 동화 작용이 일어나는 경우
		assertEquals("Baengma", KoreanRomanizer.romanize("백마", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Sinmunno", KoreanRomanizer.romanize("신문로", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Jongno", KoreanRomanizer.romanize("종로", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Wangsimni", KoreanRomanizer.romanize("왕십리", KoreanCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Byeollae", KoreanRomanizer.romanize("별내", KoreanCharacter.ConsonantAssimilation.Progressive));
		assertEquals("Silla", KoreanRomanizer.romanize("신라", KoreanCharacter.ConsonantAssimilation.Regressive));

		// 2. ‘ㄴ, ㄹ’이 덧나는 경우
		assertEquals("Hangnyeoul", KoreanRomanizer.romanize("학여울", KoreanCharacter.Type.Compound));
		assertEquals("Allyak", KoreanRomanizer.romanize("알약", KoreanCharacter.Type.Compound));

		// 3. 구개음화가 되는 경우
		assertEquals("Haedoji", KoreanRomanizer.romanize("해돋이"));
		assertEquals("Gachi", KoreanRomanizer.romanize("같이"));
		assertEquals("Guchida", KoreanRomanizer.romanize("굳히다"));

		// 4. ‘ㄱ, ㄷ, ㅂ, ㅈ’이 ‘ㅎ’과 합하여 거센소리로 소리 나는 경우
		assertEquals("Joko", KoreanRomanizer.romanize("좋고"));
		assertEquals("Nota", KoreanRomanizer.romanize("놓다"));
		assertEquals("Japyeo", KoreanRomanizer.romanize("잡혀"));
		assertEquals("Nachi", KoreanRomanizer.romanize("낳지"));
		// 다만, 체언에서 ‘ㄱ, ㄷ, ㅂ’ 뒤에 ‘ㅎ’이 따를 때에는 ‘ㅎ’을 밝혀 적는다.
		assertEquals("Mukho", KoreanRomanizer.romanize("묵호", KoreanCharacter.Type.Substantives));
		assertEquals("Jiphyeonjeon", KoreanRomanizer.romanize("집현전", KoreanCharacter.Type.Substantives));
		// [붙임] 된소리되기는 표기에 반영하지 않는다
		assertEquals("Apgujeong", KoreanRomanizer.romanize("압구정"));
		assertEquals("Nakdonggang", KoreanRomanizer.romanize("낙동강"));
		assertEquals("Jukbyeon", KoreanRomanizer.romanize("죽변"));
		assertEquals("Nakseongdae", KoreanRomanizer.romanize("낙성대"));
		assertEquals("Hapjeong", KoreanRomanizer.romanize("합정"));
		assertEquals("Paldang", KoreanRomanizer.romanize("팔당"));
		assertEquals("Saetbyeol", KoreanRomanizer.romanize("샛별"));
		assertEquals("Ulsan", KoreanRomanizer.romanize("울산"));

		// 제2항 발음상 혼동의 우려가 있을 때에는 음절 사이에 붙임표(-)를 쓸 수 있다.
		assertEquals("Jung-ang", KoreanRomanizer.romanize("중앙"));
		assertEquals("Ban-gudae", KoreanRomanizer.romanize("반구대"));
		assertEquals("Se-un", KoreanRomanizer.romanize("세운"));
		assertEquals("Hae-undae", KoreanRomanizer.romanize("해운대"));

		// 제3항 고유 명사는 첫 글자를 대문자로 적는다.
		assertEquals("Busan", KoreanRomanizer.romanize("부산"));
		assertEquals("Sejong", KoreanRomanizer.romanize("세종"));

		// 제4항 인명은 성과 이름의 순서로 띄어 쓴다. 이름은 붙여 쓰는 것을 원칙으로 하되 음절 사이에 붙임표(-)를 쓰는 것을 허용한다.
		assertEquals("Min Yongha", KoreanRomanizer.romanize("민용하", KoreanCharacter.Type.Name));
		assertEquals("Song Nari", KoreanRomanizer.romanize("송나리", KoreanCharacter.Type.Name));

		// 1. 이름에서 일어나는 음운 변화는 표기에 반영하지 않는다.
		assertEquals("Han Boknam", KoreanRomanizer.romanize("한복남", KoreanCharacter.Type.Name));
		assertEquals("Hong Bitna", KoreanRomanizer.romanize("홍빛나", KoreanCharacter.Type.Name));

		// 2. 성의 표기는 따로 정한다.

		// 제5항 ‘도, 시, 군, 구, 읍, 면, 리, 동’의 행정 구역 단위와 ‘가’는 각각 ‘do, si, gun, gu, eup, myeon, ri, dong, ga’로 적고, 그 앞에는 붙임표(-)를 넣는다. 붙임표(-) 앞뒤에서 일어나는 음운 변화는 표기에 반영하지 않는다.
		assertEquals("Chungcheongbuk-do", KoreanRomanizer.romanize("충청북도", KoreanCharacter.Type.District));
		assertEquals("Jeju-do", KoreanRomanizer.romanize("제주도", KoreanCharacter.Type.District));
		assertEquals("Uijeongbu-si", KoreanRomanizer.romanize("의정부시", KoreanCharacter.Type.District));
		assertEquals("Yangju-gun", KoreanRomanizer.romanize("양주군", KoreanCharacter.Type.District));
		assertEquals("Dobong-gu", KoreanRomanizer.romanize("도봉구", KoreanCharacter.Type.District));
		assertEquals("Sinchang-eup", KoreanRomanizer.romanize("신창읍", KoreanCharacter.Type.District));
		assertEquals("Samjuk-myeon", KoreanRomanizer.romanize("삼죽면", KoreanCharacter.Type.District));
		assertEquals("Inwang-ri", KoreanRomanizer.romanize("인왕리", KoreanCharacter.Type.District));
		assertEquals("Dangsan-dong", KoreanRomanizer.romanize("당산동", KoreanCharacter.Type.District));
		assertEquals("Bongcheon 1-dong", KoreanRomanizer.romanize("봉천1동", KoreanCharacter.Type.District));
		assertEquals("Jongno 2-ga", KoreanRomanizer.romanize("종로 2가", KoreanCharacter.Type.District));
		assertEquals("Toegyero 3-ga", KoreanRomanizer.romanize("퇴계로 3가", KoreanCharacter.Type.District));

		// 제6항 자연 지물명, 문화재명, 인공 축조물명은 붙임표(-) 없이 붙여 쓴다.
		assertEquals("Namsan", KoreanRomanizer.romanize("남산", KoreanCharacter.Type.Substantives));
		assertEquals("Songnisan", KoreanRomanizer.romanize("속리산", KoreanCharacter.Type.Substantives));
		assertEquals("Geumgang", KoreanRomanizer.romanize("금강", KoreanCharacter.Type.Substantives));
		assertEquals("Dokdo", KoreanRomanizer.romanize("독도", KoreanCharacter.Type.Substantives));
		assertEquals("Gyeongbokgung", KoreanRomanizer.romanize("경복궁", KoreanCharacter.Type.Substantives));
		assertEquals("Muryangsujeon", KoreanRomanizer.romanize("무량수전", KoreanCharacter.Type.Substantives));
		assertEquals("Yeonhwagyo", KoreanRomanizer.romanize("연화교", KoreanCharacter.Type.Substantives));
		assertEquals("Geungnakjeon", KoreanRomanizer.romanize("극락전", KoreanCharacter.Type.Substantives));
		assertEquals("Anapji", KoreanRomanizer.romanize("안압지", KoreanCharacter.Type.Substantives));
		assertEquals("Namhansanseong", KoreanRomanizer.romanize("남한산성", KoreanCharacter.Type.Substantives));
		assertEquals("Hwarangdae", KoreanRomanizer.romanize("화랑대", KoreanCharacter.Type.Substantives));
		assertEquals("Bulguksa", KoreanRomanizer.romanize("불국사", KoreanCharacter.Type.Substantives));
		assertEquals("Hyeonchungsa", KoreanRomanizer.romanize("현충사", KoreanCharacter.Type.Substantives));
		assertEquals("Dongnimmun", KoreanRomanizer.romanize("독립문", KoreanCharacter.Type.Substantives));
		assertEquals("Ojukheon", KoreanRomanizer.romanize("오죽헌", KoreanCharacter.Type.Substantives));
		assertEquals("Chokseongnu", KoreanRomanizer.romanize("촉석루", KoreanCharacter.Type.Substantives));
		assertEquals("Jongmyo", KoreanRomanizer.romanize("종묘", KoreanCharacter.Type.Substantives));
		assertEquals("Dabotap", KoreanRomanizer.romanize("다보탑", KoreanCharacter.Type.Substantives));
	}

	@Test
	public void testLongText() {
		String koreanText = "여름장이란 애시당초에 글러서, 해는 아직 중천에 있건만 장판은 벌써 쓸쓸하고 더운 햇발이 벌여놓은 전 휘장 밑으로 등줄기를 훅훅 볶는다. 마을 사람들은 거지 반 돌아간 뒤요, 팔리지 못한 나무꾼 패가 길거리에 궁싯거리고들 있으나 석유병이나 받고 고깃마리나 사면 족할 이 축들을 바라고 언제까지든지 버티고 있을 법은 없다. 춥춥스럽게 날아드는 파리 떼도 장난꾼 각다귀들도 귀치않다. 얽둑배기요 왼손잡이인 드팀전의 허 생원은 기어코 동업의 조 선달에게 나꾸어 보았다.\n"
				+ "“그만 거둘까?”\n"
				+ "“잘 생각했네. 봉평 장에서 한번이나 흐붓하게 사본 일 있을까. 내일 대화 장에서가 한몫 벌어야겠네.”\n"
				+ "“오늘 밤은 밤을 새서 걸어야 될걸?”\n"
				+ "“달이 뜨렷다?”\n"
				+ "절렁절렁 소리를 내며 조 선달이 그날 번 돈을 따지는 것을 보고 허 생원은 말뚝에서 넓은 휘장을 걷고 벌여놓았던 물건을 거두기 시작하였다. 무명 필과 주단 바리가 두 고리짝에 꼭 찼다. 멍석 위에는 천 조각이 어수선하게 남았다. 다른 축들도 벌써 거진 전들을 걷고 있었다.\n"
				+ "약바르게 떠나는 패도 있었다. 어물장수도, 땜장이도, 엿장수도, 생강장수도 꼴들이 보이지 않았다. 내일은 진부와 대화에 장이 선다. 축들은 그 어느 쪽으로든지 밤을 새며 육칠십 리 밤길을 타박거리지 않으면 안 된다. 장판은 잔치 뒷마당같이 어수선하게 벌어지고, 술집에서는 싸움이 터져 있었다. 주정꾼 욕지거리에 섞여 계집의 앙칼진 목소리가 찢어졌다. 장날 저녁은 정해놓고 계집의 고함소리로 시작되는 것이다.\n"
				+ "“생원, 시침을 떼두 다 아네…. 충주집 말야.”\n"
				+ "계집 목소리로 문득 생각난 듯이 조 선달은 비죽이 웃는다. “화중지병이지. 연소패들을 적수로 하구야 대거리가 돼야 말이지.”\n"
				+ "“그렇지두 않을걸. 축들이 사족을 못 쓰는 것도 사실은 사실이나, 아무리 그렇다군 해두 왜 그 동이 말일세, 감쪽같이 충주집을 후린 눈치거든.”\n"
				+ "“무어 그 애숭이가? 물건 가지고 나꾸었나부지. 착실한 녀석인 줄 알았더니.”\n"
				+ "“그 길만은 알 수 있나… 궁리 말구 가보세나그려. 내 한턱 씀세.”\n"
				+ "그다지 마음이 당기지 않는 것을 쫓아갔다. 허 생원은 계집과는 연분이 멀었다. 얽둑배기 상판을 대어설 숫기도 없었으나 계집 편에서 정을 보낸 적도 없었고, 쓸쓸하고 뒤틀린 반생이었다. 충주집을 생각만 하여도 철없이 얼굴이 붉어지고 발 밑이 떨리고 그 자리에 소스라쳐버린다.\n"
				+ "충주집 대문에 들어서서 술좌석에서 짜장 동이를 만났을 때에는 어찌 된 서슬엔지 빨끈 화가 나버렸다. 상위에 붉은 얼굴을 쳐들고 제법 계집과 농탕 치는 것을 보고서야 견딜 수 없었던 것이다. 녀석이 제법 난질꾼인데 꼴 사납다. 머리에 피도 안 마른 녀석이 낮부터 술 처먹고 계집과 농탕이야. 장돌뱅이 망신만 시키고 돌아다니누나. 그 꼴에 우리들과 한몫 보자는 셈이지. 동이 앞에 막아서면서부터 책망이었다.\n"
				+ "걱정두 팔자요 하는 듯이 빤히 쳐다보는 상기된 눈망울에 부딪칠 때, 결김에 따귀를 하나 갈겨주지 않고는 배길 수 없었다. 동이도 화를 쓰고 팩하게 일어서기는 하였으나, 허 생원은 조금도 동색하는 법 없이 마음먹은 대로는 다 지껄였다.\n"
				+ "“어디서 주워먹은 선머슴인지는 모르겠으나, 네게도 아비 어미 있겠지. 그 사나운 꼴 보면 맘 좋겠다. 장사란 탐탁하게 해야 되지, 계집이 다 무어야. 나가거라, 냉큼 꼴 치워.”\n"
				+ "그러나 한마디도 대거리하지 않고 하염없이 나가는 꼴을 보려니, 도리어 측은히 여겨졌다. 아직두 서름서름한 사인데 너무 과하지 않았을까 하고 마음이 섬뜩해졌다. 주제도 넘지, 같은 술 손님이면서두 아무리 젊다고 자식 낫세 된 것을 붙들고 치고 닦아셀 것은 무어야 원. 충주집은 입술을 쭝긋하고 술 붓는 솜씨도 거칠었으나, 젊은 애들한테는 그것이 약이 된다고 하고 그 자리는 조 선달이 얼버무려 넘겼다.\n"
				+ "“너, 녀석한테 반했지? 애숭이를 빨면 죄 된다.”\n"
				+ "한참 법석을 친 후이다. 담도 생긴 데다가 웬일이지 흠뻑 취해보고 싶은 생각도 있어서 허 생원은 주는 술잔이면 거의 다 들이켰다. 거나해짐을 따라 계집 생각보다도 동이의 뒷일이 한결같이 궁금해졌다.\n"
				+ "내 꼴에 계집을 가로채서니 어떡헐 작정이었누 하고 어리석은 꼬락서니를 모질게 책망하는 마음도 한편에 있었다. 그렇기 때문에, 얼마나 지난 뒤인지 동이가 헐레벌떡거리며 황급히 부르러 왔을 때에는 마시던 잔을 그 자리에 던지고 정신없이 허덕이며 충주집을 뛰어나간 것이었다.\n"
				+ "“생원 당나귀가 바를 끊구 야단이에요.”\n"
				+ "“각다귀들 장난이지 필연코.”\n"
				+ "짐승도 짐승이려니와 동이의 마음씨가 가슴을 울렸다. 뒤를 따라 장판을 달음질하려니 거슴츠레한 눈이 뜨거워질 것 같다.\n"
				+ "“부락스런 녀석들이라 어쩌는 수 있어야죠.”\n"
				+ "“나귀를 몹시 구는 녀석들은 그냥 두지는 않을걸.”\n"
				+ "반평생을 같이 지내온 짐승이었다. 같은 주막에서 잠자고, 같은 달빛에 젖으면서 장에서 장으로 걸어 다니는 동안에 이십 년의 세월이 사람과 짐승을 함께 늙게 하였다. 가스러진 목뒤 털은 주인의 머리털과도 같이 바스러지고, 개진개진 젖은 눈은 주인의 눈과 같이 눈곱을 흘렸다.\n"
				+ "몽당비처럼 짧게 쓸리운 꼬리는, 파리를 쫓으려고 기껏 휘저어보아야 벌써 다리까지는 닿지 않았다. 닳아 없어진 굽을 몇번이나 도려내고 새 철을 신겼는지 모른다. 굽은 벌써 더 자라나기는 틀렸고 닳아버린 철 사이로는 피가 빼짓이 흘렀다. 냄새만 맡고도 주인을 분간하였다. 호소하는 목소리로 야단스럽게 울며 반겨한다.\n"
				+ "어린아이를 달래듯이 목덜미를 어루만져주니 나귀는 코를 벌름거리고 입을 투르르거렸다. 콧물이 튀었다. 허 생원은 짐승 때문에 속도 무던히는 썩였다. 아이들의 장난이 심한 눈치여서 땀 밴 몸뚱어리가 부들부들 떨리고 좀체 흥분이 식지 않는 모양이었다. 굴레가 벗어지고 안장도 떨어졌다. “요 몹쓸 자식들” 하고 허 생원은 호령을 하였으나 패들은 벌써 줄행랑을 논 뒤요 몇 남지 않은 아이들이 호령에 놀래 비슬비슬 멀어졌다.\n"
				+ "“우리들 장난이 아니우, 암놈을 보고 저 혼자 발광이지.”\n"
				+ "코흘리개 한 녀석이 멀리서 소리를 쳤다.\n"
				+ "“고 녀석 말투가….”\n"
				+ "“김 첨지 당나귀가 가버리니까 온통 흙을 차고 거품을 흘리면서 미친 소같이 날뛰는걸. 꼴이 우스워 우리는 보고만 있었다우. 배를 좀 보지.”\n"
				+ "아이는 앙토라진 투로 소리를 치며 깔깔 웃었다. 허 생원은 모르는 결에 낯이 뜨거워졌다. 뭇 시선을 막으려고 그는 짐승의 배 앞을 가리어서지 않으면 안되었다.\n"
				+ "“늙은 주제에 암샘을 내는 셈야. 저놈의 짐승이.”\n"
				+ "아이의 웃음소리에 허 생원은 주춤하면서 거어코 견딜 수 없어 채찍을 들더니 아이를 쫓았다.\n"
				+ "“쫓으려거든 쫓아보지. 왼손잡이가 사람을 때려.”\n"
				+ "줄달음에 달아나는 각다귀에는 당하는 재주가 없었다. 왼손잡이는 아이 하나도 후릴 수 없다. 그만 채찍을 던졌다. 술기도 돌아 몸이 유난스럽게 화끈거렸다.\n"
				+ "“그만 떠나세. 녀석들과 어울리다가는 한이 없어. 장판의 각다귀들이란 어른보다도 더 무서운 것들인 걸.”\n"
				+ "조 선달과 동이는 각각 제 나귀에 안장을 얹고 짐을 싣기 시작하였다. 해가 꽤 많이 기울어진 모양이었다.\n"
				+ "드팀전 장돌림을 시작한 지 이십 년이나 되어도 허 생원은 봉평 장을 빼논 적은 드물었다. 충주 제천 등의 이웃 군에도 가고, 멀리 영남 지방도 헤매기는 하였으나, 강릉쯤에 물건 하러 가는 외에는 처음부터 끝까지 군내를 돌아다녔다. 닷새 만큼씩의 장날에는 달보다도 확실하게 면에서 면으로 건너간다. 고향이 청주라고 자랑 삼아 말하였으나 고향에 돌보러 간 일도 있는 것 같지는 않았다.\n"
				+ "장에서 장으로 가는 길의 아름다운 강산이 그대로 그에게는 그리운 고향이었다. 반날 동안이나 뚜벅뚜벅 걷고 장터 있는 마을에 거지 반 가까왔을 때, 거친 나귀가 한바탕 우렁차게 울면, 더구나 그것이 저녁녘이어서 등불들이 어둠 속에 깜박거릴 무렵이면, 늘 당하는 것이건만 허 생원은 변치 않고 언제든지 가슴이 뛰놀았다.\n"
				+ "젊은 시절에는 알뜰하게 벌어 돈푼이나 모아둔 적도 있기는 있었으나, 읍내에 백중이 열린 해 호탕스럽게 놀고 투전을 하고 하여 사흘 동안에 다 털어버렸다. 나귀까지 팔게 된 판이었으나 애끊는 정분에 그것만은 이를 물고 단념하였다. 결국 도로아미타불로 장돌림을 다시 시작할 수밖에 없었다.\n"
				+ "짐승을 데리고 읍내를 도망해 나왔을 때에는 너를 팔지 않기 다행이었다고 길가에서 울면서 짐승의 등을 어루만졌던 것이었다. 빚을 지기 시작하니 재산을 모을 염은 당초에 틀리고 간신히 입에 풀칠을 하러 장에서 장으로 돌아다니게 되었다.\n"
				+ "호탕스럽게 놀았다고는 하여도 계집 하나 후려보지는 못하였다. 계집이란 쌀쌀하고 매정한 것이다. 평생 인연이 없는 것이라고 신세가 서글퍼졌다. 일신에 가까운 것이라고는 언제나 변함없는 한 필의 당나귀였다. 그렇다고 하여도 꼭 한번의 첫 일을 잊을 수는 없었다. 뒤에도 처음에도 없는 단 한번의 괴이한 인연! 봉평에 다니기 시작한 젊은 시절의 일이었으나 그것을 생각할 적만은 그도 산 보람을 느꼈다.\n"
				+ "“달밤이었으나 어떻게 해서 그렇게 됐는지 지금 생각해두 도무지 알 수 없어.”\n"
				+ "허 생원은 오늘 밤도 또 그 이야기를 끄집어내려는 것이다. 조 선달은 친구가 된 이래 귀에 못이 박히도록 들어왔다. 그렇다고 싫증은 낼 수도 없었으나 허 생원은 시치미를 떼고 되풀이할 대로는 되풀이하고야 말았다.\n"
				+ "“달밤에는 그런 이야기가 격에 맞거든.”\n"
				+ "조 선달 편을 바라는 보았으나 물론 미안해서가 아니라 달빛에 감동하여서였다.\n"
				+ "이지러는 졌으나 보름을 갓 지난 달은 부드러운 빛을 흔붓이 흘리고 있다. 대화까지는 팔십 리의 밤길, 고개를 둘이나 넘고 개울을 하나 건너고 벌판과 산길을 걸어야 된다. 길은 지금 긴 산허리에 걸려 있다.\n"
				+ "밤중을 지난 무렵인지 죽은 듯이 고요한 속에서 짐승 같은 달의 숨소리가 손에 잡힐 듯이 들리며, 콩 포기와 옥수수 잎새가 한층 달에 푸르게 젖었다. 산허리는 온통 메밀 밭이어서 피기 시작한 꽃이 소금을 뿌린 듯이 흐뭇한 달빛에 숨이 막힐 지경이다. 붉은 대공이 향기같이 애잔하고 나귀들의 걸음도 시원하다.\n"
				+ "길이 좁은 까닭에 세 사람은 나귀를 타고 외줄로 늘어섰다. 방울소리가 시원스럽게 딸랑딸랑 메밀 밭께로 흘러간다. 앞장선 허 생원의 이야기 소리는 꽁무니에 선 동이에게는 확적히는 안 들렸으나, 그는 그대로 개운한 제멋에 적적하지는 않았다.\n"
				+ "“장 선 꼭 이런 날 밤이었네. 객주집 토방이란 무더워서 잠이 들어야지. 밤중은 돼서 혼자 일어나 개울가에 목욕하러 나갔지. 봉평은 지금이나 그제나 마찬가지지. 보이는 곳마다 메밀 밭이어서 개울가가 어디 없이 하얀 꽃이야. 돌 밭에 벗어도 좋을 것을, 달이 너무나 밝은 까닭에 옷을 벗으러 물방앗간으로 들어가지 않았나. 이상한 일도 많지. 거기서 난데없는 성 서방네 처녀와 마주쳤단 말이네. 봉평서야 제일 가는 일색이었지- 팔자에 있었나부지.”\n"
				+ "아무렴 하고 응답하면서 말머리를 아끼는 듯이 한참이나 담배를 빨 뿐이었다. 구수한 자주빛 연기가 밤 기운 속에 흘러서는 녹았다.\n"
				+ "“날 기다린 것은 아니었으나 그렇다고 달리 기다리는 놈팽이가 있는 것두 아니었네. 처녀는 울고 있단 말야. 짐작은 대고 있으나 성 서방네는 한창 어려워서 들고날 판인 때였지, 한집안 일이니 딸에겐들 걱정이 없을 리 있겠나? 좋은 데만 있으면 시집도 보내련만 시집은 죽어도 싫다지….”\n"
				+ "“그러나 처녀란 울 때같이 정을 끄는 때가 있을까. 처음에는 놀라기도 한 눈치였으나 걱정 있을 때는 누그러지기도 쉬운 듯해서 이럭저럭 이야기가 되었네…. 생각하면 무섭고도 기막힌 밤이었어.”\n"
				+ "“제천인지로 줄행랑을 놓은 건 그 다음날이렷다.”\n"
				+ "“다음 장도막에는 벌써 온 집안이 사라진 뒤였네. 장판은 소문에 발끈 뒤집혀 고작해야 술집에 팔려가기가 상수라고 처녀의 뒷공론이 자자들 하단 말이야. 제천 장판을 몇 번이나 뒤졌겠나. 허나 처녀의 꼴은 꿩 궈먹은 자리야. 첫날밤이 마지막 밤이었지. 그때부터 봉평이 마음에 든 것이 반평생인들 잊을 수 있겠나.”\n"
				+ "“수 좋았지. 그렇게 신통한 일이란 쉽지 않어. 항용 못난 것 얻어 새끼 낳고, 걱정 늘고 생각만 해두 진저리나지- 그러나 늙으막바지까지 장돌뱅이로 지내기도 힘드는 노릇 아닌가? 난 가을까지만 하구 이 생계와두 하직하려네. 대화쯤에 조그만 전방이나 하나 벌이구 식구들을 부르겠어. 사시장천 뚜벅뚜벅 걷기란 여간이래야지.”\n"
				+ "“옛 처녀나 만나면 같이나 살까- 난 꺼꾸러질 때까지 이 길 걷고 저 달 볼 테야.”\n"
				+ "산길을 벗어나니 큰길로 틔어졌다. 꽁무니의 동이도 앞으로 나서 나귀들은 가로 늘어섰다.\n"
				+ "“총각두 젊겠다, 지금이 한창 시절이렸다. 충주집에서는 그만 실수를 해서 그 꼴이 되었으나 설게 생각 말게.”\n"
				+ "“처, 천만에요. 되려 부끄러워요. 계집이란 지금 웬 제격인가요. 자나깨나 어머니 생각뿐인데요.”\n"
				+ "허 생원의 이야기로 실심해 한 끝이라 동이의 어조는 한풀 수그러진 것이었다.\n"
				+ "“아비 어미란 말에 가슴이 터지는 것도 같았으나 제겐 아버지가 없어요. 피붙이라고는 어머니 하나뿐인 걸요.”\n"
				+ "“돌아가셨나?”\n"
				+ "“당초부터 없어요.”\n"
				+ "“그런 법이 세상에…”\n"
				+ "생원과 선달이 야단스럽게 껄껄들 웃으니, 동이는 정색하고 우길 수밖에는 없었다.\n"
				+ "“부끄러워서 말하지 않으랴 했으나 정말예요. 제천 촌에서 달도 차지 않은 아이를 낳고 어머니는 집을 쫓겨났죠. 우스운 이야기나, 그러기 때문에 지금까지 아버지 얼굴도 본 적 없고 있는 고장도 모르고 지내와요.”\n"
				+ "고개가 앞에 놓인 까닭에 세 사람은 나귀를 내렸다. 둔덕은 험하고 입을 벌리기도 대근하여 이야기는 한동안 끊졌다. 나귀는 건듯하면 미끄러졌다. 허 생원은 숨이 차 몇 번이고 다리를 쉬지 않으면 안되었다. 고개를 넘을 때마다 나이가 알렸다. 동이 같은 젊은 축이 그지없이 부러웠다. 땀이 등을 한바탕 쪽 씻어 내렸다.\n"
				+ "고개 너머는 바로 개울이었다. 장마에 흘러버린 널다리가 아직도 걸리지 않은 채로 있는 까닭에 벗고 건너야 되었다. 고의를 벗어 띠로 등에 얽어 매고 반 벌거숭이의 우스꽝스런 꼴로 물속에 뛰어들었다. 금방 땀을 흘린 뒤였으나 밤 물은 뼈를 찔렀다.\n"
				+ "“그래 대체 기르긴 누가 기르구?”\n"
				+ "“어머니는 하는 수 없이 의부를 얻어가서 술장사를 시작했죠. 술이 고주래서 의부라고 전망나니예요. 철 들어서부터 맞기 시작한 것이 하룬들 편한 날 있었을까. 어머니는 말리다가 채이고 맞고 칼부림을 당하고 하니 집 꼴이 무어겠소. 열 여덟 살 때 집을 뛰쳐나서부터 이 짓이죠.”\n"
				+ "“총각 낫세론 섬이 무던하다고 생각했더니 듣고 보니 딱한 신세로군.”\n"
				+ "물은 깊어 허리까지 찼다. 속 물살도 어지간히 센 데다가 발에 채이는 돌멩이도 미끄러워 금시에 훌칠 듯하였다. 나귀와 조 선달은 재빨리 거의 건넜으나 동이는 허 생원을 붙드느라고 두 사람은 훨씬 떨어졌다.\n"
				+ "“모친의 친정은 원래부터 제천이었던가?”\n"
				+ "“웬걸요. 시원스리 말은 안 해주나 봉평이라는 것만은 들었죠.”\n"
				+ "“봉평, 그래 그 아비 성은 무엇이구?”\n"
				+ "“알 수 있나요. 도무지 듣지를 못했으니까.”\n"
				+ "“그, 그렇겠지.” 하고 중얼거리며 흐려지는 눈을 까물까물하다가 허 생원은 경망하게도 발을 빗디디었다. 앞으로 고꾸라지기가 바쁘게 몸째 풍덩 빠져버렸다. 허위적거릴수록 몸을 걷잡을 수 없어 동이가 소리를 치며 가까이 왔을 때에는 벌써 퍽으나 흘렀었다. 옷째 쫄딱 젖으니 물에 젖은 개보다도 참혹한 꼴이었다. 동이는 물속에서 어른을 해깝게 업을 수 있었다. 젖었다고는 하여도 여윈 몸이라 장정 등에는 오히려 가벼웠다.\n"
				+ "“이렇게까지 해서 안됐네. 내 오늘은 정신이 빠진 모양이야.”\n"
				+ "“염려하실 것 없어요.”\n"
				+ "“그래 모친은 아비를 찾지는 않는 눈치지?”\n"
				+ "“늘 한번 만나고 싶다고는 하는데요.”\n"
				+ "“지금 어디 계신가?”\n"
				+ "“의부와도 갈라져 제천에 있죠. 가을에는 봉평에 모셔오려고 생각 중인데요. 이를 물고 벌면 이럭저럭 살아갈 수 있겠죠.”\n"
				+ "“아무렴, 기특한 생각이야. 가을이랬다?”\n"
				+ "동이의 탐탁한 등어리가 뼈에 사무쳐 따뜻하다. 물을 다 건넜을 때에는 도리어 서글픈 생각에 좀더 업혔으면도 하였다.\n"
				+ "“진종일 실수만 하니 웬일이요, 생원.”\n"
				+ "조 선달은 바라보며 기어코 웃음이 터졌다.\n"
				+ "“나귀야. 나귀 생각하다 실족을 했어. 말 안 했던가. 저 꼴에 제법 새끼를 얻었단 말이지. 읍내 강릉집 피마에게 말일세. 귀를 쭝긋 세우고 달랑달랑 뛰는 것이 나귀새끼같이 귀여운 것이 있을까. 그것 보러 나는 일부러 읍내를 도는 때가 있다네.”\n"
				+ "“사람을 물에 빠뜨릴 젠, 따는 대단한 나귀 새끼군.”\n"
				+ "허 생원은 젖은 옷을 웬만큼 짜서 입었다. 이가 덜덜 갈리고 가슴이 떨리며 몹시도 추웠으나 마음은 알 수 없이 둥실둥실 가벼웠다.\n"
				+ "“주막까지 부지런히들 가세나. 뜰에 불을 피우고 훗훗이 쉬어. 나귀에겐 더운 물을 끓여주고. 내일 대화장 보고는 제천이다.”\n"
				+ "“생원도 제천으로…?”\n"
				+ "“오래간만에 가보고 싶어. 동행하려나 동이?”\n"
				+ "나귀가 걷기 시작하였을 때, 동이의 채찍은 왼손에 있었다. 오랫동안 아둑시니같이 눈이 어둡던 허 생원도 요번만은 동이의 왼손잡이가 눈에 띄지 않을 수 없었다.\n"
				+ "걸음도 해깝고 방울소리가 밤 벌판에 한층 청청하게 울렸다.\n"
				+ "달이 어지간히 기울어졌다.";

		String romanizedText = "Yeoreumjang-iran Aesidangchoe Geulleoseo, Haeneun Ajik Jungcheone Itgeonman Jangpaneun Beolsseo Sseulsseulhago Deoun Haetbari Beoryeonoeun Jeon Hwijang Miteuro Deungjulgireul Hukuk Bongneunda. Ma-eul Saramdeureun Geoji Ban Doragan Dwiyo, Palliji Motan Namukkun Paega Gilgeorie Gungsitgeorigodeul Isseuna Seogyubyeong-ina Batgo Goginmarina Samyeon Jokal I Chukdeureul Barago Eonjekkajideunji Beotigo Isseul Beobeun Eopda. Chupchupseureopge Naradeuneun Pari Ttedo Jangnankkun Gakdagwideuldo Gwichianta. Eokdukbaegiyo Oensonjabiin Deutimjeonui Heo Saeng-woneun Gieoko Dong-eobui Jo Seondarege Nakkueo Boatda.\n"
				+ "“Geuman Geodulkka?”\n"
				+ "“Jal Saenggakaenne. Bongpyeong Jang-eseo Hanbeonina Heubutage Sabon Il Isseulkka. Naeil Daehwa Jang-eseoga Hanmok Beoreoyagenne.”\n"
				+ "“Oneul Bameun Bameul Saeseo Georeoya Doelgeol?”\n"
				+ "“Dari Tteuryeotda?”\n"
				+ "Jeolleongjeolleong Sorireul Naemyeo Jo Seondari Geunal Beon Doneul Ttajineun Geoseul Bogo Heo Saeng-woneun Malttugeseo Neolbeun Hwijang-eul Geotgo Beoryeonoatdeon Mulgeoneul Geodugi Sijakayeotda. Mumyeong Pilgwa Judan Bariga Du Gorijjage Kkok Chatda. Meongseok Wieneun Cheon Jogagi Eosuseonhage Namatda. Dareun Chukdeuldo Beolsseo Geojin Jeondeureul Geotgo Isseotda.\n"
				+ "Yakbareuge Tteonaneun Paedo Isseotda. Eomuljangsudo, Ttaemjang-ido, Yeotjangsudo, Saenggangjangsudo Kkoldeuri Boiji Anatda. Naeireun Jinbuwa Daehwa-e Jang-i Seonda. Chukdeureun Geu Eoneu Jjogeurodeunji Bameul Saemyeo Yukchilsip Ri Bamgireul Tabakgeoriji Aneumyeon An Doenda. Jangpaneun Janchi Dwinmadanggachi Eosuseonhage Beoreojigo, Suljibeseoneun Ssaumi Teojyeo Isseotda. Jujeongkkun Yokjigeorie Seokkyeo Gyejibui Angkaljin Moksoriga Jjijeojyeotda. Jangnal Jeonyeogeun Jeonghaenoko Gyejibui Gohamsoriro Sijakdoeneun Geosida.\n"
				+ "“Saeng-won, Sichimeul Ttedu Da Ane…. Chungjujip Marya.”\n"
				+ "Gyejip Moksoriro Mundeuk Saenggangnan Deusi Jo Seondareun Bijugi Unneunda. “Hwajungjibyeong-iji. Yeonsopaedeureul Jeoksuro Haguya Daegeoriga Dwaeya Mariji.”\n"
				+ "“Geureochidu Aneulgeol. Chukdeuri Sajogeul Mot Sseuneun Geotdo Sasireun Sasirina, Amuri Geureotagun Haedu Wae Geu Dong-i Marilse, Gamjjokgachi Chungjujibeul Hurin Nunchigeodeun.”\n"
				+ "“Mueo Geu Aesung-iga? Mulgeon Gajigo Nakkueonnabuji. Chaksilhan Nyeoseogin Jul Aratdeoni.”\n"
				+ "“Geu Gilmaneun Al Su Inna… Gungni Malgu Gabosenageuryeo. Nae Hanteok Sseumse.”\n"
				+ "Geudaji Ma-eumi Danggiji Anneun Geoseul Jjochagatda. Heo Saeng-woneun Gyejipgwaneun Yeonbuni Meoreotda. Eokdukbaegi Sangpaneul Dae-eoseol Sutgido Eopseosseuna Gyejip Pyeoneseo Jeong-eul Bonaen Jeokdo Eopseotgo, Sseulsseulhago Dwiteullin Bansaeng-ieotda. Chungjujibeul Saenggangman Hayeodo Cheoreopsi Eolguri Bulgeojigo Bal Michi Tteolligo Geu Jarie Soseurachyeobeorinda.\n"
				+ "Chungjujip Daemune Deureoseoseo Suljwaseogeseo Jjajang Dong-ireul Mannasseul Ttae-eneun Eojji Doen Seoseurenji Ppalkkeun Hwaga Nabeoryeotda. Sang-wie Bulgeun Eolgureul Chyeodeulgo Jebeop Gyejipgwa Nongtang Chineun Geoseul Bogoseoya Gyeondil Su Eopseotdeon Geosida. Nyeoseogi Jebeop Nanjilkkuninde Kkol Sanapda. Meorie Pido An Mareun Nyeoseogi Natbuteo Sul Cheomeokgo Gyejipgwa Nongtang-iya. Jangdolbaeng-i Mangsinman Sikigo Doradaninuna. Geu Kkore Urideulgwa Hanmok Bojaneun Semiji. Dong-i Ape Magaseomyeonseobuteo Chaengmang-ieotda.\n"
				+ "Geokjeongdu Paljayo Haneun Deusi Ppanhi Chyeodaboneun Sanggidoen Nunmang-ure Buditchil Ttae, Gyeolgime Ttagwireul Hana Galgyeojuji An-goneun Baegil Su Eopseotda. Dong-ido Hwareul Sseugo Paekage Ireoseogineun Hayeosseuna, Heo Saeng-woneun Jogeumdo Dongsaekaneun Beop Eopsi Ma-eummeogeun Daeroneun Da Jikkeoryeotda.\n"
				+ "“Eodiseo Juwomeogeun Seonmeoseuminjineun Moreugesseuna, Negedo Abi Eomi Itgetji. Geu Sanaun Kkol Bomyeon Mam Joketda. Jangsaran Tamtakage Haeya Doeji, Gyejibi Da Mueoya. Nagageora, Naengkeum Kkol Chiwo.”\n"
				+ "Geureona Hanmadido Daegeorihaji An-go Hayeomeopsi Naganeun Kkoreul Boryeoni, Dorieo Cheugeunhi Yeogyeojyeotda. Ajikdu Seoreumseoreumhan Sainde Neomu Gwahaji Anasseulkka Hago Ma-eumi Seomtteukaejyeotda. Jujedo Neomji, Gateun Sul Sonnimimyeonseodu Amuri Jeomdago Jasik Natse Doen Geoseul Butdeulgo Chigo Dakkasel Geoseun Mueoya Won. Chungjujibeun Ipsureul Jjunggeutago Sul Bunneun Somssido Geochireosseuna, Jeolmeun Aedeulhanteneun Geugeosi Yagi Doendago Hago Geu Jarineun Jo Seondari Eolbeomuryeo Neomgyeotda.\n"
				+ "“Neo, Nyeoseokante Banhaetji? Aesung-ireul Ppalmyeon Joe Doenda.”\n"
				+ "Hancham Beopseogeul Chin Huida. Damdo Saenggin Dedaga Weniriji Heumppeok Chwihaebogo Sipeun Saenggakdo Isseoseo Heo Saeng-woneun Juneun Suljanimyeon Geoui Da Deurikyeotda. Geonahaejimeul Ttara Gyejip Saenggakbodado Dong-iui Dwisiri Han-gyeolgachi Gunggeumhaejyeotda.\n"
				+ "Nae Kkore Gyejibeul Garochaeseoni Eotteokeol Jakjeong-ieonnu Hago Eoriseogeun Kkorakseonireul Mojilge Chaengmanghaneun Ma-eumdo Hanpyeone Isseotda. Geureoki Ttaemune, Eolmana Jinan Dwiinji Dong-iga Heollebeoltteokgeorimyeo Hwanggeupi Bureureo Wasseul Ttae-eneun Masideon Janeul Geu Jarie Deonjigo Jeongsineopsi Heodeogimyeo Chungjujibeul Ttwieonagan Geosieotda.\n"
				+ "“Saeng-won Dangnagwiga Bareul Kkeun-gu Yadanieyo.”\n"
				+ "“Gakdagwideul Jangnaniji Piryeonko.”\n"
				+ "Jimseungdo Jimseung-iryeoniwa Dong-iui Ma-eumssiga Gaseumeul Ullyeotda. Dwireul Ttara Jangpaneul Dareumjilharyeoni Geoseumcheurehan Nuni Tteugeowojil Geot Gatda.\n"
				+ "“Burakseureon Nyeoseokdeurira Eojjeoneun Su Isseoyajyo.”\n"
				+ "“Nagwireul Mopsi Guneun Nyeoseokdeureun Geunyang Dujineun Aneulgeol.”\n"
				+ "Banpyeongsaeng-eul Gachi Jinae-on Jimseung-ieotda. Gateun Jumageseo Jamjago, Gateun Dalbiche Jeojeumyeonseo Jang-eseo Jang-euro Georeo Danineun Dong-ane Isip Nyeonui Sewori Saramgwa Jimseung-eul Hamkke Neulkke Hayeotda. Gaseureojin Mokdwi Teoreun Juinui Meoriteolgwado Gachi Baseureojigo, Gaejin-gaejin Jeojeun Nuneun Juinui Nun-gwa Gachi Nun-gobeul Heullyeotda.\n"
				+ "Mongdangbicheoreom Jjalkke Sseulliun Kkorineun, Parireul Jjocheuryeogo Gikkeot Hwijeoeoboaya Beolsseo Darikkajineun Dachi Anatda. Dara Eopseojin Gubeul Myeotbeonina Doryeonaego Sae Cheoreul Sin-gyeonneunji Moreunda. Gubeun Beolsseo Deo Jaranagineun Teullyeotgo Darabeorin Cheol Saironeun Piga Ppaejisi Heulleotda. Naemsaeman Matgodo Juineul Bun-ganhayeotda. Hosohaneun Moksoriro Yadanseureopge Ulmyeo Ban-gyeohanda.\n"
				+ "Eorinaireul Dallaedeusi Mokdeolmireul Eorumanjyeojuni Nagwineun Koreul Beolleumgeorigo Ibeul Tureureugeoryeotda. Konmuri Twieotda. Heo Saeng-woneun Jimseung Ttaemune Sokdo Mudeonhineun Sseogyeotda. Aideurui Jangnani Simhan Nunchiyeoseo Ttam Baen Momttung-eoriga Budeulbudeul Tteolligo Jomche Heungbuni Sikji Anneun Moyang-ieotda. Gullega Beoseojigo Anjangdo Tteoreojyeotda. “Yo Mopsseul Jasikdeul” Hago Heo Saeng-woneun Horyeong-eul Hayeosseuna Paedeureun Beolsseo Julhaengnang-eul Non Dwiyo Myeot Namji Aneun Aideuri Horyeong-e Nollae Biseulbiseul Meoreojyeotda.\n"
				+ "“Urideul Jangnani Aniu, Amnomeul Bogo Jeo Honja Balgwang-iji.”\n"
				+ "Koheulligae Han Nyeoseogi Meolliseo Sorireul Chyeotda.\n"
				+ "“Go Nyeoseok Maltuga….”\n"
				+ "“Gim Cheomji Dangnagwiga Gabeorinikka Ontong Heulgeul Chago Geopumeul Heullimyeonseo Michin Sogachi Nalttwineun-geol. Kkori Useuwo Urineun Bogoman Isseotdau. Baereul Jom Boji.”\n"
				+ "Aineun Angtorajin Turo Sorireul Chimyeo Kkalkkal Useotda. Heo Saeng-woneun Moreuneun Gyeore Nachi Tteugeowojyeotda. Mut Siseoneul Mageuryeogo Geuneun Jimseung-ui Bae Apeul Garieoseoji Aneumyeon Andoe-eotda.\n"
				+ "“Neulgeun Juje-e Amsaemeul Naeneun Semya. Jeonomui Jimseung-i.”\n"
				+ "Aiui Useumsorie Heo Saeng-woneun Juchumhamyeonseo Geoeoko Gyeondil Su Eopseo Chaejjigeul Deuldeoni Aireul Jjochatda.\n"
				+ "“Jjocheuryeogeodeun Jjochaboji. Oensonjabiga Sarameul Ttaeryeo.”\n"
				+ "Juldareume Darananeun Gakdagwieneun Danghaneun Jaejuga Eopseotda. Oensonjabineun Ai Hanado Huril Su Eopda. Geuman Chaejjigeul Deonjyeotda. Sulgido Dora Momi Yunanseureopge Hwakkeun-georyeotda.\n"
				+ "“Geuman Tteonase. Nyeoseokdeulgwa Eoullidaganeun Hani Eopseo. Jangpanui Gakdagwideuriran Eoreunbodado Deo Museoun Geotdeurin Geol.”\n"
				+ "Jo Seondalgwa Dong-ineun Gakgak Je Nagwie Anjang-eul Eon-go Jimeul Sitgi Sijakayeotda. Haega Kkwae Mani Giureojin Moyang-ieotda.\n"
				+ "Deutimjeon Jangdollimeul Sijakan Ji Isip Nyeonina Doe-eodo Heo Saeng-woneun Bongpyeong Jang-eul Ppaenon Jeogeun Deumureotda. Chungju Jecheon Deung-ui Iut Gunedo Gago, Meolli Yeongnam Jibangdo Hemaegineun Hayeosseuna, Gangneungjjeume Mulgeon Hareo Ganeun Oe-eneun Cheoeumbuteo Kkeutkkaji Gunnaereul Doradanyeotda. Datsae Mankeumssigui Jangnareneun Dalbodado Hwaksilhage Myeoneseo Myeoneuro Geonneoganda. Gohyang-i Cheongjurago Jarang Sama Malhayeosseuna Gohyang-e Dolboreo Gan Ildo Inneun Geot Gatjineun Anatda.\n"
				+ "Jang-eseo Jang-euro Ganeun Girui Areumdaun Gangsani Geudaero Geuegeneun Geuriun Gohyang-ieotda. Bannal Dong-anina Ttubeokttubeok Geotgo Jangteo Inneun Ma-eure Geoji Ban Gakkawasseul Ttae, Geochin Nagwiga Hanbatang Ureongchage Ulmyeon, Deoguna Geugeosi Jeonyeongnyeokieoseo Deungbuldeuri Eodum Soge Kkambakgeoril Muryeobimyeon, Neul Danghaneun Geosigeonman Heo Saeng-woneun Byeonchi An-go Eonjedeunji Gaseumi Ttwinoratda.\n"
				+ "Jeolmeun Sijeoreneun Altteulhage Beoreo Donpunina Moadun Jeokdo Itgineun Isseosseuna, Eumnae-e Baekjung-i Yeollin Hae Hotangseureopge Nolgo Tujeoneul Hago Hayeo Saheul Dong-ane Da Teoreobeoryeotda. Nagwikkaji Palge Doen Panieosseuna Aekkeunneun Jeongbune Geugeonmaneun Ireul Mulgo Dannyeomhayeotda. Gyeolguk Doroamitabullo Jangdollimeul Dasi Sijakal Subakke Eopseotda.\n"
				+ "Jimseung-eul Derigo Eumnaereul Domanghae Nawasseul Ttae-eneun Neoreul Palji An-gi Dahaeng-ieotdago Gilga-eseo Ulmyeonseo Jimseung-ui Deung-eul Eorumanjyeotdeon Geosieotda. Bijeul Jigi Sijakani Jaesaneul Moeul Yeomeun Dangchoe Teulligo Gansinhi Ibe Pulchireul Hareo Jang-eseo Jang-euro Doradanige Doe-eotda.\n"
				+ "Hotangseureopge Noratdagoneun Hayeodo Gyejip Hana Huryeobojineun Motayeotda. Gyejibiran Ssalssalhago Maejeonghan Geosida. Pyeongsaeng Inyeoni Eomneun Geosirago Sinsega Seogeulpeojyeotda. Ilsine Gakkaun Geosiragoneun Eonjena Byeonhameomneun Han Pirui Dangnagwiyeotda. Geureotago Hayeodo Kkok Hanbeonui Cheot Ireul Ijeul Suneun Eopseotda. Dwiedo Cheoeumedo Eomneun Dan Hanbeonui Goeihan Inyeon! Bongpyeong-e Danigi Sijakan Jeolmeun Sijeorui Irieosseuna Geugeoseul Saenggakal Jeongmaneun Geudo San Borameul Neukkyeotda.\n"
				+ "“Dalbamieosseuna Eotteoke Haeseo Geureoke Dwaenneunji Jigeum Saenggakaedu Domuji Al Su Eopseo.”\n"
				+ "Heo Saeng-woneun Oneul Bamdo Tto Geu Iyagireul Kkeujibeonaeryeoneun Geosida. Jo Seondareun Chin-guga Doen Irae Gwie Mosi Bakidorok Deureowatda. Geureotago Siljeung-eun Nael Sudo Eopseosseuna Heo Saeng-woneun Sichimireul Ttego Doepurihal Daeroneun Doepurihagoya Maratda.\n"
				+ "“Dalbameneun Geureon Iyagiga Gyeoge Matgeodeun.”\n"
				+ "Jo Seondal Pyeoneul Baraneun Boasseuna Mullon Mianhaeseoga Anira Dalbiche Gamdonghayeoseoyeotda.\n"
				+ "Ijireoneun Jyeosseuna Boreumeul Gat Jinan Dareun Budeureoun Bicheul Heunbusi Heulligo Itda. Daehwakkajineun Palsip Riui Bamgil, Gogaereul Durina Neomgo Gae-ureul Hana Geonneogo Beolpan-gwa San-gireul Georeoya Doenda. Gireun Jigeum Gin Sanheorie Geollyeo Itda.\n"
				+ "Bamjung-eul Jinan Muryeobinji Jugeun Deusi Goyohan Sogeseo Jimseung Gateun Darui Sumsoriga Sone Japil Deusi Deullimyeo, Kong Pogiwa Oksusu Ipsaega Hancheung Dare Pureuge Jeojeotda. Sanheorineun Ontong Memil Bachieoseo Pigi Sijakan Kkochi Sogeumeul Ppurin Deusi Heumutan Dalbiche Sumi Makil Jigyeong-ida. Bulgeun Daegong-i Hyanggigachi Aejanhago Nagwideurui Georeumdo Siwonhada.\n"
				+ "Giri Jobeun Kkadalge Se Sarameun Nagwireul Tago Oejullo Neureoseotda. Bang-ulsoriga Siwonseureopge Ttallangttallang Memil Batkkero Heulleoganda. Apjangseon Heo Saeng-wonui Iyagi Sorineun Kkongmunie Seon Dong-iegeneun Hwakjeokineun An Deullyeosseuna, Geuneun Geudaero Gae-unhan Jemeose Jeokjeokajineun Anatda.\n"
				+ "“Jang Seon Kkok Ireon Nal Bamieonne. Gaekjujip Tobang-iran Mudeowoseo Jami Deureoyaji. Bamjung-eun Dwaeseo Honja Ireona Gae-ulga-e Mogyokareo Nagatji. Bongpyeong-eun Jigeumina Geujena Machan-gajiji. Boineun Gonmada Memil Bachieoseo Gae-ulgaga Eodi Eopsi Hayan Kkochiya. Dol Bate Beoseodo Joeul Geoseul, Dari Neomuna Balgeun Kkadalge Oseul Beoseureo Mulbang-atganeuro Deureogaji Ananna. Isanghan Ildo Manji. Geogiseo Nande-eomneun Seong Seobangne Cheonyeowa Majuchyeotdan Marine. Bongpyeongseoya Jeil Ganeun Ilsaegieotji- Palja-e Isseonnabuji.”\n"
				+ "Amuryeom Hago Eungdapamyeonseo Malmeorireul Akkineun Deusi Hanchamina Dambaereul Ppal Ppunieotda. Gusuhan Jajubit Yeon-giga Bam Giun Soge Heulleoseoneun Nogatda.\n"
				+ "“Nal Gidarin Geoseun Anieosseuna Geureotago Dalli Gidarineun Nompaeng-iga Inneun Geotdu Anieonne. Cheonyeoneun Ulgo Itdan Marya. Jimjageun Daego Isseuna Seong Seobangneneun Hanchang Eoryeowoseo Deulgonal Panin Ttaeyeotji, Hanjiban Irini Ttaregendeul Geokjeong-i Eopseul Ri Itgenna? Joeun Deman Isseumyeon Sijipdo Bonaeryeonman Sijibeun Jugeodo Sildaji….”\n"
				+ "“Geureona Cheonyeoran Ul Ttaegachi Jeong-eul Kkeuneun Ttaega Isseulkka. Cheoeumeneun Nollagido Han Nunchiyeosseuna Geokjeong Isseul Ttaeneun Nugeureojigido Swiun Deutaeseo Ireokjeoreok Iyagiga Doe-eonne…. Saenggakamyeon Museopgodo Gimakin Bamieosseo.”\n"
				+ "“Jecheoninjiro Julhaengnang-eul Noeun Geon Geu Da-eumnariryeotda.”\n"
				+ "“Da-eum Jangdomageneun Beolsseo On Jibani Sarajin Dwiyeonne. Jangpaneun Somune Balkkeun Dwijipyeo Gojakaeya Suljibe Pallyeogagiga Sangsurago Cheonyeoui Dwitgongnoni Jajadeul Hadan Mariya. Jecheon Jangpaneul Myeot Beonina Dwijyeotgenna. Heona Cheonyeoui Kkoreun Kkwong Gwomeogeun Jariya. Cheonnalbami Majimak Bamieotji. Geuttaebuteo Bongpyeong-i Ma-eume Deun Geosi Banpyeongsaeng-indeul Ijeul Su Itgenna.”\n"
				+ "“Su Joatji. Geureoke Sintonghan Iriran Swipji Aneo. Hang-yong Monnan Geot Eodeo Saekki Nako, Geokjeong Neulgo Saenggangman Haedu Jinjeorinaji- Geureona Neulgeumakbajikkaji Jangdolbaeng-iro Jinaegido Himdeuneun Noreut Anin-ga? Nan Ga-eulkkajiman Hagu I Saenggyewadu Hajikaryeone. Daehwajjeume Jogeuman Jeonbang-ina Hana Beorigu Sikgudeureul Bureugesseo. Sasijangcheon Ttubeokttubeok Geotgiran Yeoganiraeyaji.”\n"
				+ "“Yet Cheonyeona Mannamyeon Gachina Salkka- Nan Kkeokkureojil Ttaekkaji I Gil Geotgo Jeo Dal Bol Teya.”\n"
				+ "San-gireul Beoseonani Keun-gillo Tuieojyeotda. Kkongmuniui Dong-ido Apeuro Naseo Nagwideureun Garo Neureoseotda.\n"
				+ "“Chonggakdu Jeomkketda, Jigeumi Hanchang Sijeoriryeotda. Chungjujibeseoneun Geuman Silsureul Haeseo Geu Kkori Doe-eosseuna Seolge Saenggak Malge.”\n"
				+ "“Cheo, Cheonmaneyo. Doeryeo Bukkeureowoyo. Gyejibiran Jigeum Wen Jegyeogin-gayo. Janakkaena Eomeoni Saenggakppunindeyo.”\n"
				+ "Heo Saeng-wonui Iyagiro Silsimhae Han Kkeuchira Dong-iui Eojoneun Hanpul Sugeureojin Geosieotda.\n"
				+ "“Abi Eomiran Mare Gaseumi Teojineun Geotdo Gatasseuna Jegen Abeojiga Eopseoyo. Pibuchiragoneun Eomeoni Hanappunin Georyo.”\n"
				+ "“Doragasyeonna?”\n"
				+ "“Dangchobuteo Eopseoyo.”\n"
				+ "“Geureon Beobi Sesang-e…”\n"
				+ "Saeng-won-gwa Seondari Yadanseureopge Kkeolkkeoldeul Useuni, Dong-ineun Jeongsaekago Ugil Subakkeneun Eopseotda.\n"
				+ "“Bukkeureowoseo Malhaji Aneurya Haesseuna Jeongmaryeyo. Jecheon Choneseo Daldo Chaji Aneun Aireul Nako Eomeonineun Jibeul Jjotgyeonatjyo. Useuun Iyagina, Geureogi Ttaemune Jigeumkkaji Abeoji Eolguldo Bon Jeok Eopgo Inneun Gojangdo Moreugo Jinaewayo.”\n"
				+ "Gogaega Ape Noin Kkadalge Se Sarameun Nagwireul Naeryeotda. Dundeogeun Heomhago Ibeul Beolligido Daegeunhayeo Iyagineun Handong-an Kkeunjyeotda. Nagwineun Geondeutamyeon Mikkeureojyeotda. Heo Saeng-woneun Sumi Cha Myeot Beonigo Darireul Swiji Aneumyeon Andoe-eotda. Gogaereul Neomeul Ttaemada Naiga Allyeotda. Dong-i Gateun Jeolmeun Chugi Geujieopsi Bureowotda. Ttami Deung-eul Hanbatang Jjok Ssiseo Naeryeotda.\n"
				+ "Gogae Neomeoneun Baro Gae-urieotda. Jangma-e Heulleobeorin Neoldariga Ajikdo Geolliji Aneun Chaero Inneun Kkadalge Beotgo Geonneoya Doe-eotda. Gouireul Beoseo Ttiro Deung-e Eolgeo Maego Ban Beolgeosung-iui Useukkwangseureon Kkollo Mulsoge Ttwieodeureotda. Geumbang Ttameul Heullin Dwiyeosseuna Bam Mureun Ppyeoreul Jjilleotda.\n"
				+ "“Geurae Daeche Gireugin Nuga Gireugu?”\n"
				+ "“Eomeonineun Haneun Su Eopsi Uibureul Eodeogaseo Suljangsareul Sijakaetjyo. Suri Gojuraeseo Uiburago Jeonmangnaniyeyo. Cheol Deureoseobuteo Matgi Sijakan Geosi Harundeul Pyeonhan Nal Isseosseulkka. Eomeonineun Mallidaga Chaeigo Matgo Kalburimeul Danghago Hani Jip Kkori Mueogetso. Yeol Yeodeol Sal Ttae Jibeul Ttwichyeonaseobuteo I Jisijyo.”\n"
				+ "“Chonggak Natseron Seomi Mudeonhadago Saenggakaetdeoni Deutgo Boni Ttakan Sinserogun.”\n"
				+ "Mureun Gipeo Heorikkaji Chatda. Sok Mulsaldo Eojiganhi Sen Dedaga Bare Chaeineun Dolmeng-ido Mikkeureowo Geumsie Hulchil Deutayeotda. Nagwiwa Jo Seondareun Jaeppalli Geoui Geonneosseuna Dong-ineun Heo Saeng-woneul Butdeuneurago Du Sarameun Hwolssin Tteoreojyeotda.\n"
				+ "“Mochinui Chinjeong-eun Wollaebuteo Jecheonieotdeon-ga?”\n"
				+ "“Wen-georyo. Siwonseuri Mareun An Haejuna Bongpyeong-iraneun Geonmaneun Deureotjyo.”\n"
				+ "“Bongpyeong, Geurae Geu Abi Seong-eun Mueosigu?”\n"
				+ "“Al Su Innayo. Domuji Deutjireul Motaesseunikka.”\n"
				+ "“Geu, Geureoketji.” Hago Jung-eolgeorimyeo Heuryeojineun Nuneul Kkamulkkamulhadaga Heo Saeng-woneun Gyeongmanghagedo Bareul Bitdidieotda. Apeuro Gokkurajigiga Bappeuge Momjjae Pungdeong Ppajyeobeoryeotda. Heowijeokgeorilsurok Momeul Geotjabeul Su Eopseo Dong-iga Sorireul Chimyeo Gakkai Wasseul Ttae-eneun Beolsseo Peogeuna Heulleosseotda. Otjjae Jjolttak Jeojeuni Mure Jeojeun Gaebodado Chamhokan Kkorieotda. Dong-ineun Mulsogeseo Eoreuneul Haekkapge Eobeul Su Isseotda. Jeojeotdagoneun Hayeodo Yeowin Momira Jangjeong Deung-eneun Ohiryeo Gabyeowotda.\n"
				+ "“Ireokekkaji Haeseo Andwaenne. Nae Oneureun Jeongsini Ppajin Moyang-iya.”\n"
				+ "“Yeomnyeohasil Geot Eopseoyo.”\n"
				+ "“Geurae Mochineun Abireul Chatjineun Anneun Nunchiji?”\n"
				+ "“Neul Hanbeon Mannago Sipdagoneun Haneundeyo.”\n"
				+ "“Jigeum Eodi Gyesin-ga?”\n"
				+ "“Uibuwado Gallajyeo Jecheone Itjyo. Ga-eureneun Bongpyeong-e Mosyeooryeogo Saenggak Jung-indeyo. Ireul Mulgo Beolmyeon Ireokjeoreok Saragal Su Itgetjyo.”\n"
				+ "“Amuryeom, Giteukan Saenggagiya. Ga-euriraetda?”\n"
				+ "Dong-iui Tamtakan Deung-eoriga Ppyeoe Samuchyeo Ttatteutada. Mureul Da Geonneosseul Ttae-eneun Dorieo Seogeulpeun Saenggage Jomdeo Eopyeosseumyeondo Hayeotda.\n"
				+ "“Jinjong-il Silsuman Hani Weniriyo, Saeng-won.”\n"
				+ "Jo Seondareun Barabomyeo Gieoko Useumi Teojyeotda.\n"
				+ "“Nagwiya. Nagwi Saenggakada Siljogeul Haesseo. Mal An Haetdeon-ga. Jeo Kkore Jebeop Saekkireul Eodeotdan Mariji. Eumnae Gangneungjip Pima-ege Marilse. Gwireul Jjunggeut Se-ugo Dallangdallang Ttwineun Geosi Nagwisaekkigachi Gwiyeoun Geosi Isseulkka. Geugeot Boreo Naneun Ilbureo Eumnaereul Doneun Ttaega Itdane.”\n"
				+ "“Sarameul Mure Ppatteuril Jen, Ttaneun Daedanhan Nagwi Saekkigun.”\n"
				+ "Heo Saeng-woneun Jeojeun Oseul Wenmankeum Jjaseo Ibeotda. Iga Deoldeol Galligo Gaseumi Tteollimyeo Mopsido Chuwosseuna Ma-eumeun Al Su Eopsi Dungsildungsil Gabyeowotda.\n"
				+ "“Jumakkaji Bujireonhideul Gasena. Tteure Bureul Piugo Hutusi Swieo. Nagwiegen Deoun Mureul Kkeuryeojugo. Naeil Daehwajang Bogoneun Jecheonida.”\n"
				+ "“Saeng-wondo Jecheoneuro…?”\n"
				+ "“Oraeganmane Gabogo Sipeo. Donghaengharyeona Dong-i?”\n"
				+ "Nagwiga Geotgi Sijakayeosseul Ttae, Dong-iui Chaejjigeun Oensone Isseotda. Oraetdong-an Aduksinigachi Nuni Eodupdeon Heo Saeng-wondo Yobeonmaneun Dong-iui Oensonjabiga Nune Ttuiji Aneul Su Eopseotda.\n"
				+ "Georeumdo Haekkapgo Bang-ulsoriga Bam Beolpane Hancheung Cheongcheonghage Ullyeotda.\n"
				+ "Dari Eojiganhi Giureojyeotda.";

		assertEquals(romanizedText, KoreanRomanizer.romanize(koreanText));
	}
}