package net.crizin;

import java.io.Serializable;

/**
 * A library that handling Hangul characters in syllable units.
 */
public class KoreanCharacter implements Serializable, Comparable<KoreanCharacter> {
	/**
	 * Required for serialization support.
	 *
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -2081434254504406150L;

	/**
	 * When consonant assimilation is ambiguous how it should occur,
	 * it designates one of progressive assimilation or regressive assimilation.
	 */
	public enum ConsonantAssimilation {
		/**
		 * Set progressive assimilation.
		 */
		Progressive,

		/**
		 * Set regressive assimilation.
		 */
		Regressive
	}

	/**
	 * Options for applying special rules depending on the type of word.
	 */
	public enum Type {
		/**
		 * Noun like a substantives.
		 */
		Substantives,

		/**
		 * Compound words.
		 */
		Compound,

		/**
		 * Addresses, locations.
		 */
		District,

		/**
		 * Person's full name.
		 */
		Name,

		/**
		 * Common words.
		 */
		Typical
	}

	/**
	 * The consonant used as the final syllable of Hangul, which is called "Jongsung".
	 */
	public enum Chosung {
		ㄱ("g") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄺ:
					case ㄻ:
					case ㄼ:
					case ㄽ:
					case ㄾ:
					case ㄿ:
					case ㅀ:
						return "kk";
					case ㅎ:
						return "k";
					default:
						return defaultPronunciation;
				}
			}

			protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
				return prevCharacterPronunciation.endsWith("n");
			}
		},
		ㄲ("kk"),
		ㄴ("n") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄹ:
					case ㅀ:
						return "l";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄷ("d") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄾ:
						return "tt";
					case ㄶ:
					case ㅎ:
						return "t";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄸ("tt"),
		ㄹ("r") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄱ:
					case ㄲ:
					case ㄳ:
					case ㄺ:
					case ㄼ:
					case ㄿ:
					case ㅁ:
					case ㅂ:
					case ㅄ:
					case ㅇ:
					case ㅋ:
					case ㅍ:
						return "n";
					case ㄴ:
					case ㄷ:
					case ㄵ:
					case ㄶ:
					case ㅅ:
					case ㅆ:
					case ㅈ:
					case ㅊ:
					case ㅎ:
						switch (consonantAssimilation) {
							case Progressive:
								return "n";
							default:
								return "l";
						}
					case ㄹ:
					case ㄻ:
					case ㄽ:
					case ㄾ:
					case ㅀ:
					case ㅌ:
						return "l";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅁ("m"),
		ㅂ("b") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄾ:
						return "pp";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅃ("pp"),
		ㅅ("s"),
		ㅆ("ss"),
		ㅇ("") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄱ:
						if (type == Type.Compound && currentCharacter.getJungsung().isInducePalatalization()) {
							return "n";
						} else {
							return "g";
						}
					case ㄺ:
						return "g";
					case ㄲ:
						return "kk";
					case ㄳ:
					case ㄽ:
					case ㅄ:
					case ㅅ:
						return "s";
					case ㅇ:
						if (type == Type.Compound && currentCharacter.getJungsung().isInducePalatalization()) {
							return "n";
						} else {
							return defaultPronunciation;
						}
					case ㄴ:
					case ㄶ:
						return "n";
					case ㄵ:
					case ㅈ:
						return "j";
					case ㄷ:
						return currentCharacter.getJungsung().isInducePalatalization() ? "j" : "d";
					case ㄹ:
					case ㅀ:
						if (type == Type.Compound && currentCharacter.getJungsung().isInducePalatalization()) {
							return "l";
						} else {
							return "r";
						}
					case ㄻ:
					case ㅁ:
						return "m";
					case ㄼ:
					case ㅂ:
						return "b";
					case ㄾ:
					case ㅌ:
						return currentCharacter.getJungsung().isInducePalatalization() ? "ch" : "t";
					case ㄿ:
					case ㅍ:
						return "p";
					case ㅆ:
						return "ss";
					case ㅊ:
						return "ch";
					case ㅋ:
						return "k";
					default:
						return defaultPronunciation;
				}
			}

			protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
				return prevCharacterPronunciation.endsWith("ng") && currentCharacterPronunciation.isEmpty();
			}
		},
		ㅈ("j") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㅎ:
						return "ch";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅉ("jj"),
		ㅊ("ch"),
		ㅋ("k"),
		ㅌ("t") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㅈ:
					case ㅊ:
						return currentCharacter.getJungsung().isInducePalatalization() ? "ch" : "t";
					default:
						return defaultPronunciation;
				}
			}

			protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
				return prevCharacterPronunciation.endsWith("t");
			}
		},
		ㅍ("p") {
			protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
				return prevCharacterPronunciation.endsWith("p");
			}
		},
		ㅎ("h") {
			protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (prevCharacter.getJongsung()) {
					case ㄱ:
						if (type == Type.Substantives) {
							return defaultPronunciation;
						} else {
							return "";
						}
					case ㄲ:
						return "kk";
					case ㄷ:
						if (type == Type.Substantives) {
							return defaultPronunciation;
						} else {
							return currentCharacter.getJungsung().isInducePalatalization() ? "ch" : "t";
						}
					case ㄾ:
					case ㅅ:
					case ㅆ:
					case ㅈ:
					case ㅊ:
					case ㅌ:
						return currentCharacter.getJungsung().isInducePalatalization() ? "ch" : "t";
					case ㄺ:
						return "k";
					case ㄼ:
						return "p";
					case ㄽ:
						return "s";
					case ㅀ:
						return "r";
					case ㅂ:
						if (type == Type.Substantives) {
							return defaultPronunciation;
						} else {
							return "p";
						}
					default:
						return defaultPronunciation;
				}
			}

			protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
				return !currentCharacterPronunciation.isEmpty() && prevCharacterPronunciation.endsWith(String.valueOf(currentCharacterPronunciation.charAt(0)));
			}
		};

		protected final String defaultPronunciation;

		Chosung(String defaultPronunciation) {
			this.defaultPronunciation = defaultPronunciation;
		}

		public String getPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
			if (prevCharacter == null || !prevCharacter.isKoreanCharacter()) {
				return defaultPronunciation;
			} else {
				String complexPronunciation = getComplexPronunciation(prevCharacter, currentCharacter, consonantAssimilation, type);
				return isNeedHyphen(prevCharacter.getRomanizedString(null, currentCharacter, consonantAssimilation, type), complexPronunciation) ? "-" + complexPronunciation : complexPronunciation;
			}
		}

		protected String getComplexPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
			return defaultPronunciation;
		}

		protected boolean isNeedHyphen(String prevCharacterPronunciation, String currentCharacterPronunciation) {
			return false;
		}
	}

	/**
	 * The vowel used as the middle syllable of Hangul, which is called "Jungsung".
	 */
	public enum Jungsung {
		ㅏ("a", false),
		ㅐ("ae", false),
		ㅑ("ya", true),
		ㅒ("yae", true),
		ㅓ("eo", false),
		ㅔ("e", false),
		ㅕ("yeo", true),
		ㅖ("ye", true),
		ㅗ("o", false),
		ㅘ("wa", false),
		ㅙ("wae", false),
		ㅚ("oe", false),
		ㅛ("yo", true),
		ㅜ("u", false),
		ㅝ("wo", false),
		ㅞ("we", false),
		ㅟ("wi", false),
		ㅠ("yu", true),
		ㅡ("eu", false),
		ㅢ("ui", false),
		ㅣ("i", true);

		private final String defaultPronunciation;
		private final boolean inducePalatalization;

		Jungsung(String defaultPronunciation, boolean inducePalatalization) {
			this.defaultPronunciation = defaultPronunciation;
			this.inducePalatalization = inducePalatalization;
		}

		public String getPronunciation(KoreanCharacter prevCharacter, KoreanCharacter currentCharacter) {
			boolean insertHyphen = false;

			if (prevCharacter != null && prevCharacter.isKoreanCharacter() && prevCharacter.getJongsung() == Jongsung.NONE && currentCharacter.getChosung() == Chosung.ㅇ) {
				switch (prevCharacter.getJungsung().defaultPronunciation.charAt(prevCharacter.getJungsung().defaultPronunciation.length() - 1)) {
					case 'a':
						switch (defaultPronunciation.charAt(0)) {
							case 'a':
							case 'e':
								insertHyphen = true;
						}
						break;
					case 'e':
						switch (defaultPronunciation.charAt(0)) {
							case 'a':
							case 'e':
							case 'o':
							case 'u':
								insertHyphen = true;
						}
						break;
				}
			}

			return insertHyphen ? "-" + defaultPronunciation : defaultPronunciation;
		}

		public boolean isInducePalatalization() {
			return inducePalatalization;
		}
	}

	/**
	 * The consonant used as the final syllable of Hangul, which is called "Jongsung".
	 */
	public enum Jongsung {
		NONE(""),
		ㄱ("k") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄲ:
					case ㅋ:
						return "";
					case ㅇ:
						if (type == Type.Compound && nextCharacter.jungsung.isInducePalatalization()) {
							return "ng";
						} else {
							return "";
						}
					case ㄴ:
					case ㅁ:
					case ㄹ:
						return "ng";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄲ("k") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄲ:
					case ㅋ:
					case ㅇ:
					case ㅎ:
						return "";
					case ㄴ:
					case ㅁ:
					case ㄹ:
						return "ng";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄳ("k") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄲ:
					case ㅋ:
						return "";
					case ㄴ:
					case ㅁ:
					case ㄹ:
						return "ng";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄴ("n") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄹ:
						switch (consonantAssimilation) {
							case Regressive:
								return "l";
							default:
								return "n";
						}
					case ㅇ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄵ("n") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄹ:
						switch (consonantAssimilation) {
							case Regressive:
								return "l";
							default:
								return "n";
						}
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄶ("n") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				return ㄴ.getComplexPronunciation(nextCharacter, consonantAssimilation, type);
			}
		},
		ㄷ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㅁ:
						return "n";
					case ㄸ:
					case ㅇ:
					case ㅌ:
					case ㅎ:
						if (type == Type.Substantives) {
							return defaultPronunciation;
						} else {
							return "";
						}
					case ㄹ:
						switch (consonantAssimilation) {
							case Regressive:
								return "l";
							default:
								return "n";
						}
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄹ("l") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㅇ:
						if (type == Type.Compound && nextCharacter.getJungsung().isInducePalatalization()) {
							return defaultPronunciation;
						} else {
							return "";
						}
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄺ("k") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄱ:
					case ㄲ:
					case ㅇ:
					case ㅎ:
						return "l";
					case ㄴ:
					case ㄹ:
					case ㅁ:
						return "ng";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄻ("m") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄹ:
					case ㅁ:
					case ㅇ:
						return "l";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄼ("l") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㄹ:
						return "m";
					case ㄷ:
					case ㄸ:
					case ㅂ:
					case ㅅ:
					case ㅆ:
					case ㅈ:
					case ㅉ:
					case ㅊ:
					case ㅋ:
					case ㅌ:
					case ㅎ:
						return "p";
					case ㅃ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㄽ("l"),
		ㄾ("l"),
		ㄿ("l") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㄹ:
						return "m";
					case ㄷ:
					case ㄸ:
					case ㅂ:
					case ㅅ:
					case ㅆ:
					case ㅈ:
					case ㅉ:
					case ㅊ:
					case ㅋ:
					case ㅌ:
					case ㅎ:
						return "p";
					case ㅃ:
					case ㅍ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅀ("l") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㅎ:
						return "";
					case ㅇ:
						if (type == Type.Compound && nextCharacter.getJungsung().isInducePalatalization()) {
							return defaultPronunciation;
						} else {
							return "";
						}
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅁ("m") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㅇ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅂ("p") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㄹ:
					case ㅁ:
						return "m";
					case ㅃ:
					case ㅇ:
						return "";
					case ㅎ:
						if (type == Type.Substantives) {
							return defaultPronunciation;
						} else {
							return "";
						}
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅄ("p") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㄹ:
					case ㅁ:
						return "m";
					case ㅃ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅅ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				return ㄷ.getComplexPronunciation(nextCharacter, consonantAssimilation, type);
			}
		},
		ㅆ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				return ㄷ.getComplexPronunciation(nextCharacter, consonantAssimilation, type);
			}
		},
		ㅇ("ng"),
		ㅈ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				return ㄷ.getComplexPronunciation(nextCharacter, consonantAssimilation, type);
			}
		},
		ㅊ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				return ㄷ.getComplexPronunciation(nextCharacter, consonantAssimilation, type);
			}
		},
		ㅋ("k") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄲ:
					case ㅇ:
						return "";
					case ㄴ:
					case ㅁ:
					case ㄹ:
						return "ng";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅌ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄴ:
					case ㅁ:
						return "n";
					case ㄸ:
					case ㅇ:
					case ㅎ:
						return "";
					case ㄹ:
						return "l";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅍ("p") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㅃ:
					case ㅇ:
						return "";
					default:
						return defaultPronunciation;
				}
			}
		},
		ㅎ("t") {
			protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
				switch (nextCharacter.getChosung()) {
					case ㄱ:
					case ㄲ:
					case ㄷ:
					case ㄸ:
					case ㅇ:
					case ㅈ:
					case ㅉ:
					case ㅊ:
					case ㅋ:
					case ㅌ:
					case ㅍ:
					case ㅎ:
						return "";
					case ㄴ:
					case ㅁ:
						return "n";
					case ㄹ:
						switch (consonantAssimilation) {
							case Regressive:
								return "l";
							default:
								return "n";
						}
					default:
						return defaultPronunciation;
				}
			}
		};

		protected final String defaultPronunciation;

		Jongsung(String defaultPronunciation) {
			this.defaultPronunciation = defaultPronunciation;
		}

		public String getPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
			return (nextCharacter == null || !nextCharacter.isKoreanCharacter()) ? defaultPronunciation : getComplexPronunciation(nextCharacter, consonantAssimilation, type);
		}

		protected String getComplexPronunciation(KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
			return defaultPronunciation;
		}
	}

	/**
	 * First character code point in Hangul Syllables in Unicode table ({@code 가}).
	 */
	public final static int KoreanLowerValue = 0xAC00;

	/**
	 * Last character code point in Hangul Syllables in Unicode table ({@code 힣}).
	 */
	public final static int KoreanUpperValue = 0xD7A3;

	/**
	 * The original character from constructor's argument.
	 */
	private final char character;

	/**
	 * Disassembled initial syllable of Hangul.
	 */
	private Chosung chosung;

	/**
	 * Disassembled middle syllable of Hangul.
	 */
	private Jungsung jungsung;

	/**
	 * Disassembled final syllable of Hangul.
	 */
	private Jongsung jongsung;

	/**
	 * Constructor
	 *
	 * @param koreanCharacter
	 * 		the Hangul or other character
	 */
	public KoreanCharacter(char koreanCharacter) {
		character = koreanCharacter;

		if (isKoreanCharacter(character)) {
			int value = character - KoreanLowerValue;
			chosung = Chosung.values()[value / (21 * 28)];
			jungsung = Jungsung.values()[value % (21 * 28) / 28];
			jongsung = Jongsung.values()[value % 28];
		}
	}

	/**
	 * Constructor with Hangul object with each syllables.
	 *
	 * @param chosung
	 * 		the consonant used as the initial syllable of Hangul.
	 * @param jungsung
	 * 		the vowel used as the middle syllable of Hangul.
	 * @param jongsung
	 * 		the consonant used as the final syllable of Hangul.
	 * @throws NullPointerException
	 * 		if any arguments is null.
	 */
	public KoreanCharacter(Chosung chosung, Jungsung jungsung, Jongsung jongsung) {
		if (chosung == null || jungsung == null || jongsung == null) {
			throw new NullPointerException("All parameters must not be null.");
		}

		this.chosung = chosung;
		this.jungsung = jungsung;
		this.jongsung = jongsung;

		this.character = (char) ((chosung.ordinal() * 21 * 28 + jungsung.ordinal() * 28 + jongsung.ordinal()) + KoreanLowerValue);
	}

	/**
	 * Whether or not the character of this object is Hangul.
	 *
	 * @return Whether all syllables exist to complete Hangul character.
	 */
	public boolean isKoreanCharacter() {
		return chosung != null && jungsung != null && jongsung != null;
	}

	/**
	 * @return the initial syllable if object has Hangul character, and null if not.
	 */
	public Chosung getChosung() {
		return chosung;
	}

	/**
	 * @return the middle syllable if object has Hangul character, and null if not.
	 */
	public Jungsung getJungsung() {
		return jungsung;
	}

	/**
	 * @return the final syllable if object has Hangul character, and null if not.
	 */
	public Jongsung getJongsung() {
		return jongsung;
	}

	/**
	 * @return the character that this object has.
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * @return the romanized string of the character this object has.
	 */
	public String getRomanizedString() {
		return getRomanizedString(null, null, ConsonantAssimilation.Progressive, Type.Typical);
	}

	/**
	 * @param prevCharacter
	 * 		the character preceding this character in the sentence.
	 * @param nextCharacter
	 * 		the character after this character in the sentence.
	 * @param consonantAssimilation
	 * 		the consonant assimilation type.
	 * @param type
	 * 		the type of word
	 * @return the romanized string of the character this object has.
	 */
	public String getRomanizedString(KoreanCharacter prevCharacter, KoreanCharacter nextCharacter, ConsonantAssimilation consonantAssimilation, Type type) {
		if (!isKoreanCharacter()) {
			return toString();
		}

		if (type == Type.Name) {
			prevCharacter = null;
			nextCharacter = null;
		}

		return chosung.getPronunciation(prevCharacter, this, consonantAssimilation, type)
				+ jungsung.getPronunciation(prevCharacter, this)
				+ jongsung.getPronunciation(nextCharacter, consonantAssimilation, type);
	}

	/**
	 * To check if character is in the Hangul Syllable of Unicode table.
	 *
	 * @param character
	 * 		the character to check.
	 * @return true if the character is Hangul
	 */
	public static boolean isKoreanCharacter(char character) {
		return (KoreanLowerValue <= character && character <= KoreanUpperValue);
	}

	/**
	 * Compares this object to another in ascending order.
	 *
	 * @param other
	 * 		the other object to compare to.
	 * @return the value of {@link Character#compareTo}.
	 */
	@Override
	public int compareTo(KoreanCharacter other) {
		return Character.compare(character, other.character);
	}

	/**
	 * Compares this object to another to test if they are equal.
	 *
	 * @param other
	 * 		the other object to compare to.
	 * @return true if this object is equal.
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		return character == ((KoreanCharacter) other).character;
	}

	/**
	 * Return the hash code for this character.
	 *
	 * @return the value of {@link Character#hashCode()}
	 */
	@Override
	public int hashCode() {
		return Character.hashCode(character);
	}

	/**
	 * Returns a {@link String} object representing this character's value.
	 *
	 * @return a string representation of this character.
	 */
	@Override
	public String toString() {
		return String.valueOf(character);
	}
}