package com.spring.core.discount;

import com.spring.core.annotation.MainDiscountPolicy;
import com.spring.core.member.Grade;
import com.spring.core.member.Member;
import org.springframework.stereotype.Component;


//@Primary
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy // 직접 만들어준 어노테이션
@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

