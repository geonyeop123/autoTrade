package autoTrade.api.service.member;

import autoTrade.domain.member.Member;
import autoTrade.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("회원을 조회할 수 있다.")
    @Test
    void test() {
        // given
        Member member = Member.builder().name("hi").build();
        Member save = memberRepository.save(member);


        // when
        Member findIdMember = memberService.findById(save.getId());

        // then

        assertThat(findIdMember).isNotNull();
    }
}