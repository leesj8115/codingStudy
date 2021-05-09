<style>
  /* 결과 항목 박스 border 설정 */
  .result-div {
    border: 1px solid #595959;
  }
  /* input type="image"의 크기 조정 */
  #input-image {
    width: 100px;
  }
</style>
color
# input-type
각 type별 코드와 실행 결과를 보여주고 있습니다.
form 태그에서 사용됐을 때의 결과를 보고싶다면, [input-type.html](./input-type.html)을 확인해주세요.  
(submit 실행시 결과, email의 검증이나 tel에서 pattern을 사용했을 때의 검증 등은 여기서 확인할 수 없습니다.)

## text
```html
<!-- 한 줄을 입력받는 기본 형태. -->
<input type="text" name="txt" value="텍스트"/>
```
<div class="result-div">
  결과 = <input type="text" name="txt" value="텍스트"/>
</div>

## password
```html
<!-- 값이 특수문자 형태로 가려진다. -->
<input type="password" name="pw" value="패스워드"/>
```
<div class="result-div">
  결과 = <input type="password" name="pw" value="패스워드"/>
</div>

## number
```html
<!-- html5에서 추가. 숫자값만 입력받음 -->
<input type="number" name="num" value="1234567"/>
```
<div class="result-div">
  결과 = <input type="number" name="num" value="1234567"/>
</div>

## email
```html
<!-- html5에서 추가. submit시 *@* 형태인지 검증해준다. -->
<input type="email" name="email" value="test@test.com"/>
```
<div class="result-div">
  결과 = <input type="email" name="email" value="test@test.com"/>
</div>

## search
```html
<!-- html5에서 추가. text와 유사하되 'X' 추가 등 일부 차이가 있다. -->
<input type="search" name="search" value="검색"/>
```
<div class="result-div">
  결과 = <input type="search" name="search" value="검색"/>
</div>

## tel
```html
<!-- html5에서 추가. 자체 검증기능은 없어서 주로 pattern과 함께 사용한다. -->
<input type="tel" name="tel" pattern="(010)-\d{3,4}-\3{4}" value="010-1111-2222"/>
```
<div class="result-div">
  결과 = <input type="tel" name="tel" pattern="(010)-\d{3,4}-\3{4}" value="010-1111-2222"/>
</div>

## url
```html
<!-- html5에서 추가. 'http://'가 들어가는지 검증해준다. -->
<input type="url" name="url" value="http://www.naver.com"/>
```
<div class="result-div">
  결과 = <input type="url" name="url" value="http://www.naver.com"/>
</div>

## file
```html
<!-- 탐색기를 통해 파일을 입력받도록 해준다. -->
<input type="file" name="file" />
```
<div class="result-div">
  결과 = <input type="file" name="file" />
</div>

## radio
```html
<!-- name 그룹으로 묶어서 id 1개만 선택할 수 있다. -->
<label>남자</label><input type="radio" id="male" name="gender"/>
<label>여자</label><input type="radio" id="female" name="gender"/>
<label>기타</label><input type="radio" id="other" name="gender"/>
```
<div class="result-div">
  결과 = 
  <label>남자</label><input type="radio" id="male" name="gender"/>
  <label>여자</label><input type="radio" id="female" name="gender"/>
  <label>기타</label><input type="radio" id="other" name="gender"/>
</div>

## checkbox
```html
<!-- name 그룹을 통해 value 여러 개를 입력 받을 수 있다. -->
<label>개</label><input type="checkbox" name="like" value="dog"/>
<label>고양이</label><input type="checkbox" name="like" value="cat"/>
<label>새</label><input type="checkbox" name="like" value="bird"/>
```
<div class="result-div">
  결과 = 
  <label>개</label><input type="checkbox" name="like" value="dog"/>
  <label>고양이</label><input type="checkbox" name="like" value="cat"/>
  <label>새</label><input type="checkbox" name="like" value="bird"/>
</div>

## date
```html
<!-- html5에서 추가. 년, 월, 일을 입력받는다. yyyy-MM-dd -->
<input type="date" name="date" value="2021-05-08"/>
```
<div class="result-div">
  결과 = <input type="date" name="date" value="2021-05-08"/>
</div>

## month
```html
<!-- html5에서 추가. 년, 월을 입력받는다. yyyy-MM -->
<input type="month" name="month" value="2021-05"/>
```
<div class="result-div">
  결과 = <input type="month" name="month" value="2021-05"/>
</div>

## week
```html
<!-- html5에서 추가. 년, 주차를 입력받는다. yyyyWww -->
<input type="week" name="week" value="2021-W18"/>
```
<div class="result-div">
  결과 = <input type="week" name="week" value="2021-W18"/>
</div>

## time
```html
<!-- html5에서 추가. 시, 분을 입력받는다. hh:mm -->
<input type="time" name="time" value="22:09"/>
```
<div class="result-div">
  결과 = <input type="time" name="time" value="22:09"/>
</div>

## datetime-local
```html
<!-- datetime 도 있었으나, 사라질 예정이다. -->
<!-- html5에서 추가. 시간 전체를 입력받는다 yyyy-MM-ddThh:mm -->
<input type="datetime-local" name="datetime" value="2021-05-08T22:15"/>
```
<div class="result-div">
  결과 = <input type="datetime-local" name="datetime" value="2021-05-08T22:15"/>
</div>

## range
```html
<!-- html5에서 추가. 값의 범위를 지정하여 입력받을 수 있다. -->
<input type="range" name="range" min="0" max="10" value="5"/>
```
<div class="result-div">
  결과 = <input type="range" name="range" min="0" max="10" value="5"/>
</div>

## button
```html
<!-- 버튼에 따른 이벤트를 정의하여 처리할 수 있다. -->
<input type="button" name="button" value="버튼"/></td>
```
<div class="result-div">
  결과 = <input type="button" name="button" value="버튼"/></td>
</div>

## submit
```html
<!-- form의 데이터를 action에 전송한다. -->
<input type="submit" value="제출"/>
```
<div class="result-div">
  결과 = <input type="submit" value="제출"/>
</div>

## image
```html
<!-- sumbit과 동일하지만, 이미지 버튼으로 만들어준다. -->
<input id="input-image" type="image" src="./sample.png" alt="submit과 동일" />
```
<div class="result-div">
  결과 = <input id="input-image" type="image" src="./sample.png" alt="submit과 동일" />
</div>

## hidden
```html
<!-- 사용자에게 노출되지 않는다. 주로 토큰 값이나, 데이터베이스의 레코드를 저장한다. -->
<input type="hidden" value="비밀"/>
```
<div class="result-div">
  결과 = <input type="hidden" value="비밀"/>
</div>
