[![Build Status](https://travis-ci.org/crizin/korean-romanizer.svg?branch=master)](https://travis-ci.org/crizin/korean-romanizer)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/72ed8965fd6e4e9c8faa9a0b3090a045)](https://www.codacy.com/app/crizin/korean-romanizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=crizin/korean-romanizer&amp;utm_campaign=Badge_Grade)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Korean Romanizer

## 소개

한국어를 입력하면 로마자로 변환해주는 Java 라이브러리. [국립국어원 로마자 표기법](https://www.korean.go.kr/front/page/pageView.do?page_id=P000148&mn_id=99)을 기반으로 구현되었고 많은 부분이 커버 가능하지만
한국어의 특성상 단어별 사전 데이터가 존재하지 않으면 100% 구현이 어렵기 때문에 완벽하지는 않다.

## Maven

```xml
  <dependency>
    <groupId>net.crizin</groupId>
    <artifactId>korean-romanizer</artifactId>
    <version>1.0.3</version>
  </dependency>
```

## 온라인 데모

[Online Demo](http://unply.com/@/koreanRomanizer/)

## 메서드 요약

<table>
  <tr>
    <td>void</td>
    <td>setCapitalizeOnFirstLetter(boolean capitalizeOnFirstLetter)</td>
    <td>true로 설정하면 공백으로 구분된 각 단어의 첫 글자를 대문자로 출력한다 (기본값: true)</td>
  </tr>
  <tr>
    <td>void</td>
    <td>setUseHyphenWhenVowelConfused(boolean useHyphenWhenVowelConfused)</td>
    <td>true로 설정하면 발음상 혼동의 우려가 있는 부분에 하이픈을 출력해준다 (기본값: true)</td>
  </tr>
  <tr>
    <td>String</td>
    <td>romanize(String string)</td>
    <td>입력받은 문자열 중 한글 영역을 로마자로 변환해 리턴한다</td>
  </tr>
</table>

## 라이선스

[MIT license](https://opensource.org/licenses/MIT)
