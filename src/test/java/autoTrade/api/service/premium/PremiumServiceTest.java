package autoTrade.api.service.premium;

import autoTrade.api.service.premium.response.PremiumCreateResponse;
import autoTrade.domain.ExchangeType;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.domain.premium.Premium;
import autoTrade.domain.premium.PremiumRepository;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PremiumServiceTest {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private PremiumRepository premiumRepository;

    @Autowired
    private PremiumService premiumService;

    @AfterEach
    void tearDown() {
        premiumRepository.deleteAllInBatch();
        tickerRepository.deleteAllInBatch();
        exchangeRateRepository.deleteAllInBatch();
    }

    @DisplayName("현재 시세와 환율에 따른 프리미엄을 생성한다.")
    @Test
    void createPremium() {
        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "100300")));
        exchangeRateRepository.save(ExchangeRate.of(1400));

        // when
        PremiumCreateResponse premium = premiumService.createPremium();
        List<Premium> premiums = premiumRepository.findAll();

        // then
        assertThat(premiums).hasSize(1);
    }

    @DisplayName("현재 시세와 환율에 따른 프리미엄을 생성할 때 조회된 시세가 없으면 만들 수 없다.")
    @Test
    void createPremiumThanEmptyBinanceTicker() {
        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")));
        exchangeRateRepository.save(ExchangeRate.of(1400));

        // when // then
        assertThatThrownBy(() -> premiumService.createPremium())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("조회된 시세가 없습니다.");
    }

    @DisplayName("현재 시세와 환율에 따른 프리미엄을 생성할 때 조회된 환율이 없으면 만들 수 없다.")
    @Test
    void createPremiumThanEmptyExchangeRate() {
        // given
        tickerRepository.saveAll(List.of(
                makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "100300")));

        // when // then
        assertThatThrownBy(() -> premiumService.createPremium())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("조회된 환율이 없습니다.");
    }

    private Ticker makeTicker(ExchangeType type, String symbol, String price) {
        return Ticker.builder().exchangeType(type).symbol(symbol).price(price).build();
    }

}