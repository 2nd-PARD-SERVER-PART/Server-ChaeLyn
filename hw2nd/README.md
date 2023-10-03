# 2nd homework
+ Product CRUD 파일 만들기
+ controller, dto, repository 사용

<br>

## 1️⃣ 물품 추가
<img width="1392" alt="add" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/1e673f76-6fd9-4094-881c-aa0cb1534148">

## 2️⃣ 리스트 출력 (전체, 해당 ID)
### 2-1 전체
<img width="1392" alt="findALL" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/5f64588c-9ea6-4c33-8b87-c1e3c9f72a7f">

### 2-2 해당 ID
<img width="1392" alt="findOne" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/a54a145a-bb6a-416c-b416-cc8875718318">

## 3️⃣ 물품 수정
<img width="1392" alt="update" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/7c57bc02-ae2c-4b07-8e45-227ffdd85590">
* 수정 후 확인
<img width="1392" alt="findAll_update" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/9b41fed1-3156-497f-97f7-3bb0a9e65173">

## 4️⃣ 물품 삭제
<img width="1392" alt="delete" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/58b175ac-b8d0-46cb-97aa-961c3ca9a711">
* 삭제 후 확인
<img width="1392" alt="findAll_delete" src="https://github.com/2nd-PARD-SERVER-PART/Server-chaelyn/assets/100327184/c7eda9bd-714b-4684-850d-e457a7c88e99">

---
+ ### repository(저장소) 역할
  + 클래스가 객체에 대한 저장, 검색, 업데이트 및 삭제 작업을 위한 메커니즘 제공
  + DB의 CRUD 명령을 실행하게 만드는 인터페이스

<br>

+ ### controller 역할
  + client의 요청을 처리한 후 지정된 뷰에 모델 객체를 넘겨줌
  + 뷰를 연결해 웹 상에 띄우고, 뷰에서 가져오는 데이터들을 어떻게 처리하는지 사용자가 지정해놓으면 그 역할에 맞춰 처리<br>
  + 웹의 모든 요청 컨트롤(말 그대로 컨트롤!)
