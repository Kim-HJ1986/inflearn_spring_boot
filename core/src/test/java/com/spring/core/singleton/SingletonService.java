package com.spring.core.singleton;

public class SingletonService {

    // 1. 자바가 실행되며 초기에 static 변수로 딱 한번 instance를 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 2. 생성한 instance를 가져올 수 있도록 instance를 리턴하는 static 메서드를 만든다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 외부에서 생성자에 접근하지 못하도록 private으로 접근 제어한다.
    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
