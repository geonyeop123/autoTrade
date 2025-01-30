package autoTrade.api.service.position;

import autoTrade.api.service.position.request.PositionCreateRequest;
import autoTrade.api.service.position.response.PositionCreateResponse;
import autoTrade.domain.member.Member;
import autoTrade.domain.member.MemberRepository;
import autoTrade.domain.position.Position;
import autoTrade.domain.position.PositionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PositionServiceTest {


    @Autowired
    PositionService positionService;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        positionRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("포지션을 생성할 수 있다.")
    @Test
    void test() {
        // given
        Member member = Member. builder()
                .name("이건엽")
                .build();
        memberRepository.save(member);
        PositionCreateRequest request =
                PositionCreateRequest.builder()
                        .member(member)
                        .symbol("BTC")
                        .krPrice("146529000")
                        .krQuantity(0.123)
                        .usPrice("99653.3")
                        .usQuantity(0.123)
                        .exchangeRate(1440.8)
                        .build();


        // when
        PositionCreateResponse response = positionService.createPosition(request);
        List<Position> positions = positionRepository.findAll();

        // then
        assertThat(positions).hasSize(1);
        Position position = positions.get(0);
        assertThat(position.getMember().getName()).isEqualTo("이건엽");
        assertThat(position.getExchangeRate().getPrice()).isEqualTo(1440.8);
        assertThat(position.getKrTicker().getPrice()).isEqualTo("146529000");
        assertThat(position.getUsTicker().getPrice()).isEqualTo("99653.3");
        assertThat(position.getKrTicker().getQuantity()).isEqualTo(0.123);
        assertThat(position.getUsTicker().getQuantity()).isEqualTo(0.123);

    }
}