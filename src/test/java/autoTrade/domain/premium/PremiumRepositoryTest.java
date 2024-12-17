package autoTrade.domain.premium;

import autoTrade.domain.ExchangeType;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class PremiumRepositoryTest {

    @Autowired
    private PremiumRepository premiumRepository;

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @AfterEach
    void tearDown() {
        premiumRepository.deleteAllInBatch();
        tickerRepository.deleteAllInBatch();
        exchangeRateRepository.deleteAllInBatch();
    }

    @DisplayName("현재 프리미엄을 저장한다.")
    @Test
    void test() {
        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "100300")));

        List<Ticker> tickers = tickerRepository.findLatestTickersByType();
        exchangeRateRepository.save(ExchangeRate.of(1400));

        Map<String, List<Ticker>> collect = tickers.stream().collect(Collectors.groupingBy(Ticker::getSymbol));
        ExchangeRate exchangeRate = exchangeRateRepository.findLatestExchangeRate();

        Premium premium = Premium.builder()
                .krBtcTicker(collect.get("KRW-BTC").get(0))
                .usdtTicker(collect.get("KRW-USDT").get(0))
                .usBtcTicker(collect.get("BTCUSDT").get(0))
                .exchangeRate(exchangeRate)
                .build();

        // when
        premiumRepository.save(premium);

        // then
        List<Premium> premiums = premiumRepository.findAll();

        assertThat(premiums).hasSize(1);

    }

    private Ticker makeTicker(ExchangeType type, String symbol, String price) {
        return Ticker.builder().exchangeType(type).symbol(symbol).price(price).build();
    }
}