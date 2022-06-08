package com.spring.core;

import com.spring.core.member.MemberRepository;
import com.spring.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 현재 컴포넌트 스캔을 하기 위해, 이 전에 만든 AppConfig를 스캔 대상에서 제외시켜주는 것
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 등록(중복된 이름) : 이 경우 수동으로 등록한 빈이 자동으로 등록된 빈을 오버라이딩하여 덮어쓴다.
    // 단 스프링 부트에서는 이를 디폴트로 오류를 뱉어내도록 결정했다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
