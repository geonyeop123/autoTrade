package autoTrade.domain.position;


import autoTrade.domain.ExchangeType;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.domain.positionTicker.PositionTicker;
import autoTrade.domain.positionTicker.PositionTickerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class PositionRepositoryTest {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private PositionTickerRepository positionTickerRepository;

    @AfterEach
    void tearDown() {
        positionRepository.deleteAllInBatch();
        exchangeRateRepository.deleteAllInBatch();
        positionTickerRepository.deleteAll();
    }

    @DisplayName("포지션을 생성할 수 있다.")
    @Test
    void createPosition() {
        // given
        ExchangeRate exchangeRate = ExchangeRate.of(1400.0);
        PositionTicker krPositionTicker = PositionTicker.builder()
                .exchangeType(ExchangeType.BITHUMB)
                .symbol("KRW-BTC")
                .price("140000000")
                .quantity(0.117)
                .build();

        PositionTicker usPositionTicker = PositionTicker.builder()
                .exchangeType(ExchangeType.BINANCE)
                .symbol("BTCUSDT")
                .price("96000")
                .quantity(0.117)
                .build();

        ExchangeRate saveExchangeRate = exchangeRateRepository.save(exchangeRate);

        // when
        Position position = Position.builder()
                .exchangeRate(saveExchangeRate)
                .krTicker(krPositionTicker)
                .usTicker(usPositionTicker)
                .build();

        positionRepository.save(position);
        List<Position> positions = positionRepository.findAll();
        Iterable<PositionTicker> positionTickers = positionTickerRepository.findAll();
        // then
        assertThat(positions).hasSize(1);
        assertThat(positionTickers).hasSize(2);
    }

}