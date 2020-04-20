package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = false)
    public Long join (Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers (){
        return memberRepository.findAll();
    }

    public Member findOne (Long memberId){
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember (Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("같은 이름이 이미 입력되었습니다.");
        }
    }
}
