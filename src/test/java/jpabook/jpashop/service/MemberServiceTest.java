package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("Member를 등록 할 수 있다.")
    @Test
    void joinMember (){
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long id = memberService.join(member);

        //then
        assertEquals(member, memberService.findOne(id));
    }

    @DisplayName("이미 등록한 멤버를 다시 등록 할 수 없다.")
    @Test
    void duplicateTest(){
        //given
        Member member1 = new Member();
        member1.setName("Kim");
        memberService.join(member1);

        //when
        Member member2 = new Member();
        member2.setName("Kim");

        //then
        Assertions.assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> memberService.join(member2));
    }
}