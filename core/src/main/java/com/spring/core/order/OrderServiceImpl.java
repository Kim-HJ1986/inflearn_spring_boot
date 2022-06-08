package com.spring.core.order;

import com.spring.core.discount.DiscountPolicy;
import com.spring.core.member.Member;
import com.spring.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    OCP, DIP 위반!!!!!!!!!!!!!!!
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//  ------------------------------------------------------------------------------------------------------


    // 여기에서 직접 구현체를 생성하지 않고 Config에게 위임한다.
    // 아래와 같이 변경함으로써 OrderServiceImpl는 DIP를 철저히 지키게 된다. (구현체, 구체화에 대해 전혀 모르고 있다)
    // 생성자 주입 방식
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
