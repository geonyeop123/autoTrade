package autoTrade.domain.ticker;

import autoTrade.domain.ExchangeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@ActiveProfiles("test")
class TickerRepositoryTest {

    @Autowired
    private TickerRepository tickerRepository;

    @AfterEach
    void tearDown() {
        tickerRepository.deleteAllInBatch();
    }

    @DisplayName("모든 ticker의 거래소 유형 및 심볼 타입에 따라 최신의 값을 조회한다.")
    @Test
    void test() throws InterruptedException{
        // given
        Ticker ticker1 = Ticker.builder().exchangeType(ExchangeType.BITHUMB).symbol("KRW-BTC").price("145000000").build();
        Ticker ticker2 = Ticker.builder().exchangeType(ExchangeType.BITHUMB).symbol("KRW-USDT").price("1401").build();
        Ticker ticker3 = Ticker.builder().exchangeType(ExchangeType.BINANCE).symbol("BTCUSDT").price("100300").build();

        tickerRepository.saveAll(List.of(ticker1, ticker2, ticker3));

        Thread.sleep(1);
        Ticker ticker4 = Ticker.builder().exchangeType(ExchangeType.BITHUMB).symbol("KRW-BTC").price("143000000").build();
        Ticker ticker5 = Ticker.builder().exchangeType(ExchangeType.BITHUMB).symbol("KRW-USDT").price("1400").build();

        tickerRepository.saveAll(List.of(ticker4, ticker5));
        // when

        List<Ticker> latestTickersByType = tickerRepository.findLatestTickersByType();

        // then
        assertThat(latestTickersByType)
                .hasSize(3)
                .extracting("exchangeType", "symbol", "price")
                .containsExactlyInAnyOrder(
                    tuple(ExchangeType.BITHUMB, "KRW-BTC", "143000000")
                        , tuple(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                        , tuple(ExchangeType.BINANCE, "BTCUSDT", "100300")
            );

    }

}