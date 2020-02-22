[![Build Status](https://travis-ci.org/crizin/korean-romanizer.svg?branch=master)](https://travis-ci.org/crizin/korean-romanizer)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/72ed8965fd6e4e9c8faa9a0b3090a045)](https://www.codacy.com/app/crizin/korean-romanizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=crizin/korean-romanizer&amp;utm_campaign=Badge_Grade)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Korean Romanizer

## 소개

한국어를 입력하면 로마자로 변환해주는 Java 라이브러리.
[국립국어원 로마자 표기법](https://www.korean.go.kr/front/page/pageView.do?page_id=P000148&mn_id=99)을 기반으로 구현되었고
많은 부분이 커버 가능하지만 한국어의 특성상 단어별 사전 데이터가 존재하지 않으면 100% 구현이 어렵기 때문에 완벽하지는 않다.

## Maven

```xml
  <dependency>
    <groupId>net.crizin</groupId>
    <artifactId>korean-romanizer</artifactId>
    <version>2.0.1</version>
  </dependency>
```

## 온라인 데모

[Online Demo](http://unply.com/@/koreanRomanizer/)

## 클래스 설명

### `KoreanCharacter`

#### 로마자 변환 옵션

##### `KoreanCharacter.ConsonantAssimilation`

자음 동화가 일어나는 부분에서 역행 동화, 순행 동화 모두 가능할 때 어느쪽을 선택할지 지정할 수 있는 옵션.

  - `ConsonantAssimilation.Progressive`: 순행 동화
  - `ConsonantAssimilation.Regressive`: 역행 동화

```java
KoreanRomanizer.romanize("신라면", KoreanCharacter.ConsonantAssimilation.Progressive);
// => Sinnamyeon

KoreanRomanizer.romanize("신라면", KoreanCharacter.ConsonantAssimilation.Regressive);
// => Sillamyeon
```
##### `KoreanCharacter.Type`

단어의 성격별로 예외적인 처리를 하기 위해 단어의 타입을 지정할 수 있는 옵션.

##### `Type.Substantives`

체언에서 'ㄱ, ㄷ, ㅂ' 뒤에 'ㅎ'이 따를 때 'ㅎ'을 밝혀 적기 위해 주는 옵션.

```java
KoreanRomanizer.romanize("묵호");
// => Muko

KoreanRomanizer.romanize("묵호", KoreanCharacter.Type.Substantives);
// => Mukho
```

##### `Type.Compound`

합성어에서 앞 단어의 끝이 자음이고 뒤 단어의 첫 음절 발음이 y 또는 i로 시작되는 경우 'ㄴ'을 첨가하는 옵션. 

```java
KoreanRomanizer.romanize("학여울");
// => Hagyeoul

KoreanRomanizer.romanize("학여울", KoreanCharacter.Type.Compound);
// => Hangnyeoul
```

##### `Type.District`

지명을 올바르게 표기하기 위한 옵션.

```java
KoreanRomanizer.romanize("제주도");
// => Jejudo
KoreanRomanizer.romanize("효자로73번길")
// => Hyojaro73Beon-gil

KoreanRomanizer.romanize("제주도", KoreanCharacter.Type.District);
// => Jeju-do
KoreanRomanizer.romanize("효자로73번길", KoreanCharacter.Type.District)
// => Hyoja-ro 73beon-gil
```

##### `Type.Name`

인명을 올바르게 표기하기 위한 옵션

```java
KoreanRomanizer.romanize("제갈공명")
// => Jegalgongmyeong

KoreanRomanizer.romanize("제갈공명", KoreanCharacter.Type.Name)
// => Jegal Gongmyeong
```

##### `Type.NameTypical`

`Type.Name`과 같지만 흔히 사용하는 성씨 표기법을 따르는 옵션

```java
KoreanRomanizer.romanize("박보검", KoreanCharacter.Type.Name)
// => Bak Bogeom

KoreanRomanizer.romanize("박보검", KoreanCharacter.Type.NameTypical)
// => Park Bogeom
```

##### `Type.Typical`

위 옵션들을 지정하지 않았을 때의 기본 값.

#### 사용법

```java
new KoreanCharacter('A').getRomanizedString();
// => A

new KoreanCharacter('고').getRomanizedString();
// => go

new KoreanCharacter('고').getRomanizedString(new KoreanCharacter('닭'), null, ConsonantAssimilation.Regressive, Type.Typical);
// '고' 앞에 '닭'이 있을 때 '고'의 발음
// => kko

new KoreanCharacter('신').getRomanizedString(null, new KoreanCharacter('라'), KoreanCharacter.ConsonantAssimilation.Progressive, KoreanCharacter.Type.Typical)
// '신' 뒤에 '라'가 있고 순행 동화를 적용했을 때의 발음
// => sin
```

### `KoreanRomanizer`

#### `KoreanRomanizer.romanize`

  - `KoreanRomanizer.romanize(String)`
  - `KoreanRomanizer.romanize(String, ConsonantAssimilation)`
  - `KoreanRomanizer.romanize(String, Type)`
  - `KoreanRomanizer.romanize(String, Type, ConsonantAssimilation)`

입력 받은 문자열을 로마자로 변환한다. 변환시 옵션을 추가할 수 있는 Overloading 메소드들도 존재한다.

`type`, `consonantAssimilation` 값을 생략했을 때는 `Type.Typical`, `ConsonantAssimilation.Regressive`가 기본값.

## 라이선스

[MIT license](https://opensource.org/licenses/MIT)
