[![Build Status](https://travis-ci.org/crizin/korean-romanizer.svg?branch=master)](https://travis-ci.org/crizin/korean-romanizer)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/72ed8965fd6e4e9c8faa9a0b3090a045)](https://www.codacy.com/app/crizin/korean-romanizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=crizin/korean-romanizer&amp;utm_campaign=Badge_Grade)

# 소개

한국어를 입력하면 로마자로 변환해주는 Java 라이브러리. 국립국어원 로마자 표기법을 기반으로 구현되었고 많은 부분이 커버 가능하지만 한국어의 특성상 단어별 사전 데이터가 존재하지 않으면 100% 구현이 어렵기 때문에 완벽하지는 않다.

# 온라인 데모

http://unply.com/@/koreanRomanizer/

# 메서드 요약

<table>
  <tr>
    <td>void</td>
    <td>setCapitalizeOnFirstLetter(boolean capitalizeOnFirstLetter)</td>
    <td>true로 설정하면 공백으로 구분된 각 단어의 첫 글자를 대문자로 출력한다 (기본값: true)
  </tr>
  <tr>
    <td>void
    <td>setUseHyphenWhenVowelConfused(boolean useHyphenWhenVowelConfused)
    <td>true로 설정하면 발음상 혼동의 우려가 있는 부분에 하이픈을 출력해준다 (기본값: true)
  </tr>
  <tr>
    <td>String
    <td>romanize(String string)
    <td>입력받은 문자열 중 한글 영역을 로마자로 변환해 리턴한다
  </tr>
</table>

패치 노트
====
- 1.0.1 모음이 연속되어 발음이 혼란스러운 모든 경우에 하이픈이 추가되도록 수정
- 1.0.2 Javadoc 추가

라이선스
====
http://www.apache.org/licenses/LICENSE-2.0