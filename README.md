깃허브 사용자 API 가져오기
====

### 2021 미림테크톡 해커톤 APP 분야

<br/><br/>

## 실행화면 
<img src="https://user-images.githubusercontent.com/80630432/134389081-208e181e-7a20-48cb-a155-3c287bed4c19.jpg" width="250px">   <img src="https://user-images.githubusercontent.com/80630432/134389066-15a8a6f5-168b-41dd-92b1-9cbb276cee7e.jpg" width="250px" margin="30px">   <img src="https://user-images.githubusercontent.com/80630432/134389076-11261d6c-586f-4de0-996e-b083547f95c9.jpg" width="250px" margin="30px">
<hr/>

## 사용기술
* Android(java)
* Retrofit2
* RESTful API      


<hr/>


## 코드
### MainActivity
* retroFit 생성 - baseUrl - HTTP를 요청할 주소

```
Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```

* 받아온 api를 위한 인터페이스 연결
```
final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
```

* 인터페이스 내의 정보를 객체에 저장 - 입력된 아이디를 매개변수로 넘긴다
```
Call<User> call = jsonPlaceHolderApi.getPosts(edtSearch.getText().toString());
```

### JsonPlaceHolderApi
* 매개변수로 받은 아이디의 api를 HTTP로부터 GET 요청.
```
@GET("/users/{user}")
    Call<User> getPosts(@Path("user") String user);
```

### (참고) 링크로 이미지뷰 설정하기
```
Glide.with(getApplicationContext()).load("url").into(ImageView);
```
<hr/>

## 참고자료
* https://jsonplaceholder.typicode.com/ 사이트를 이용한 예제!
* https://www.youtube.com/watch?v=4JGvDUlfk7Y
    * 동영상을 따라 실습해보면 retroFit의 작동방식에 대해 이해하기 더 쉽다!!
* https://leveloper.tistory.com/165 - 코틀린으로 Github Api 가져오기
