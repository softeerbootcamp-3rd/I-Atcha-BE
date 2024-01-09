# 프로젝트 개요

# 개발 규칙
## 커밋 컨벤션
- 🎉[FEAT] : 기능 추가
  - ex) 🎉[FEAT] : 로그인 기능 추가
- 📗[DOCS] : 문서 작성
  - ex) 📗[DOCS] : readme 수정
- 🎁[REFACTOR] : 리펙토링
  - ex) 🎁[REFACTOR] : 로그인 기능 리팩토링
- 🛠[FIX] : 오류 수정
  - ex) 🛠[FIX] : 로그인 기능 버그 수정

## 주석 규칙
- 함수 위에 /**/ 사용
- 필요시 함수 내에 추가 (//)

## 이슈 규칙
- title
  - 커밋 컨벤션 사용
- write
  - 설명
  - todo
- 라벨
  - bug : 버그 발견
  - docs : 문서 작성
  - feat : 기능 개발
  - fix : 버그 수정
  - refactor : 리팩토링

## 브랜치 전략 : Github Flow
- main 브랜치 : 릴리즈
- 기능 만들때마다 브랜치 추가
  - 브랜치 명 : {깃헙 아이디} - {기능 이름}
- 기능 합치기 전 PR

## 요청 성공/실패
- STATUS : OK
  - 데이터 추가
- STATUS : ERROR
  - 에러 메시지 추가

## API 문서 양식
- 스프레드시트로 작성

## 기타 사항
- 코드 컨벤션은 인텔리제이에 내장된 것으로 사용

testeststasfjhdslkfjadslkfjalsjfasl

### response 사용하기~ ✏️

```
// GET인데 성공인 경우(데이터를 return 하는 경우)
public ResponseEntity< ? > examController(){
        return ResponseEntity.ok().body(DataResponse.success(homeResponseDto));
}
```

```
// POST인데 성공인 경우
public ResponseEntity< ? > examController(){
    return ResponseEntity.ok().body(SingleResponse.success()); //아무것도 안넣어도 OK이로 나올꺼에요!
}
```

```
//ERROR 처리를 하고 싶으면 예외 처리하고 이런식으로 하면 될것 같아요! 아직은 예외처리 안하기로 했으니 일단 이렇게 적어놓겠습니다
public ResponseEntity< ? extends BasicResponse> testException(Exception e){
  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.error(e.getMessage()));
}
```
