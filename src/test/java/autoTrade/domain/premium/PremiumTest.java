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
class PremiumTest {

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @AfterEach
    void tearDown() {
        tickerRepository.deleteAllInBatch();
        exchangeRateRepository.deleteAllInBatch();
    }

    @DisplayName("프리미엄을 계산할 수 있다.")
    @Test
    void calculatePremiumRate() {

        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "151250000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1000")
                , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "104981.3")));

        List<Ticker> tickers = tickerRepository.findLatestTickersByType();
        exchangeRateRepository.save(ExchangeRate.of(1437.5));

        Map<String, List<Ticker>> collect = tickers.stream().collect(Collectors.groupingBy(Ticker::getSymbol));
        ExchangeRate exchangeRate = exchangeRateRepository.findLatestExchangeRate();

        Premium premium = Premium.builder()
                .krBtcTicker(collect.get("KRW-BTC").get(0))
                .usdtTicker(collect.get("KRW-USDT").get(0))
                .usBtcTicker(collect.get("BTCUSDT").get(0))
                .exchangeRate(exchangeRate)
                .build();

        assertThat(premium.getPremiumRate()).isEqualTo(0.22);
    }

    @DisplayName("프리미엄을 계산할 수 있다.2")
    @Test
    void calculatePremiumRate2() {

        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "154875000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1448")
                , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "106826.5")));

        List<Ticker> tickers = tickerRepository.findLatestTickersByType();
        exchangeRateRepository.save(ExchangeRate.of(1435.3));

        Map<String, List<Ticker>> collect = tickers.stream().collect(Collectors.groupingBy(Ticker::getSymbol));
        ExchangeRate exchangeRate = exchangeRateRepository.findLatestExchangeRate();

        Premium premium = Premium.builder()
                .krBtcTicker(collect.get("KRW-BTC").get(0))
                .usdtTicker(collect.get("KRW-USDT").get(0))
                .usBtcTicker(collect.get("BTCUSDT").get(0))
                .exchangeRate(exchangeRate)
                .build();

        assertThat(premium.getPremiumRate()).isEqualTo(1.01);
    }

    private Ticker makeTicker(ExchangeType type, String symbol, String price) {
        return Ticker.builder().exchangeType(type).symbol(symbol).price(price).build();
    }

}