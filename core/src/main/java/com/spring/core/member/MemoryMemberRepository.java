package com.spring.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{


    //원래 동시성을 고려해서 멀티쓰레드에서도 문제 없는 ConcurrentHashMap을 사용하는게 맞다고 한다. -> 추후 공부!
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
