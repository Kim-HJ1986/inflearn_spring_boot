package com.spring.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//     다형성에 의해 구현체는 MemoryMemberRepository가 되며,
//     아래에서 오버라이드한 메서드 모두 MemoryMemberRepository의 메서드가 실행된다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//  --------------------------------------------------------------------------------------------------------

    // 여기에서 직접 구현체를 생성하지 않고 Config에게 위임한다.
    // 아래와 같이 변경함으로써 MemberServiceImpl는 DIP를 철저히 지키게 된다. (구현체, 구체화에 대해 전혀 모르고 있다)
    // 생성자 주입 방식
    private final MemberRepository memberRepository;

    @Autowired // 마치 ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
