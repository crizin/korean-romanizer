package net.crizin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KoreanRomanizerTest {
	private KoreanRomanizer koreanRomanizer;

	@Before
	public void setUp() {
		koreanRomanizer = new KoreanRomanizer();
	}

	@Test(expected = NullPointerException.class)
	public void testException() {
		koreanRomanizer.romanize(null);
	}

	@Test
	public void testNormal() {
		assertEquals("", koreanRomanizer.romanize(""));
		assertEquals("Seoraksan", koreanRomanizer.romanize("설악산"));
	}

	@Test
	public void testNormalUseHyphen() {
		assertEquals("Hae-undae", koreanRomanizer.romanize("해운대"));

		koreanRomanizer.setUseHyphenWhenVowelConfused(false);
		assertEquals("Haeundae", koreanRomanizer.romanize("해운대"));
	}

	@Test
	public void testCapitalize() {
		assertEquals("Beullumbeogeu: FTC, Gugeulgwa MotorolaUi FRAND Namyong-e Daehae Josa Chaksu",
				koreanRomanizer.romanize("블룸버그: FTC, 구글과 Motorola의 FRAND 남용에 대해 조사 착수"));

		koreanRomanizer.setCapitalizeOnFirstLetter(false);
		assertEquals("beullumbeogeu: FTC, gugeulgwa Motorolaui FRAND namyong-e daehae josa chaksu",
				koreanRomanizer.romanize("블룸버그: FTC, 구글과 Motorola의 FRAND 남용에 대해 조사 착수"));
	}
}