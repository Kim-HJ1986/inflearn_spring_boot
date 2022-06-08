package com.spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A: A사용자가 10,000원 주문
        statefulService1.order("userA", 10000);

        // Thread B: B사용자가 20,000원 주문
        statefulService2.order("userB", 20000);

        // Thread A: 사용자A가 주문 금액 조회
        // expexted = 10,000 < - > but 20,000 (자원을 공유해버림)
        int price1 = statefulService1.getPrice();
        System.out.println("price1 = " + price1);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}