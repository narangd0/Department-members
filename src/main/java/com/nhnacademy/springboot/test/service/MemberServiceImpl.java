package com.nhnacademy.springboot.test.service;

import com.nhnacademy.springboot.test.entity.Member;
import com.nhnacademy.springboot.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
