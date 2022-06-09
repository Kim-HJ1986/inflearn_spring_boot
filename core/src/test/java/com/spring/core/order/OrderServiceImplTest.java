package com.spring.core.order;

import com.spring.core.discount.DiscountPolicy;
import com.spring.core.discount.RateDiscountPolicy;
import com.spring.core.member.Grade;
import com.spring.core.member.Member;
import com.spring.core.member.MemberRepository;
import com.spring.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        // 순수 자바 테스트에서
        // 생성자 주입의 경우, 아래와 같이 생성자 파라미터를 임의로 만들어서 넣어줄 수 있다.
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}