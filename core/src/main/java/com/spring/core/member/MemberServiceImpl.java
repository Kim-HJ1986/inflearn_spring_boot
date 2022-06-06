package com.spring.core.member;

public class MemberServiceImpl implements MemberService{

    // 다형성에 의해 구현체는 MemoryMemberRepository가 되며,
    // 아래에서 오버라이드한 메서드 모두 MemoryMemberRepository의 메서드가 실행된다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
