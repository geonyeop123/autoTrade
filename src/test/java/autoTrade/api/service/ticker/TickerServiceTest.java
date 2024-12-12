package autoTrade.api.service.ticker;

import autoTrade.api.client.TickerClient;
import autoTrade.api.service.ticker.response.TickerCreateResponse;
import autoTrade.domain.ExchangeType;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@SpringBootTest
class TickerServiceTest {

    @MockBean
    private TickerClient tickerClient;

    @Autowired
    private TickerService tickerService;

    @Autowired
    private TickerRepository tickerRepository;

    @AfterEach
    void tearDown() {
        tickerRepository.deleteAllInBatch();
    }

    @DisplayName("현재 시점 가상화폐의 가격을 저장한다.")
    @Test
    void createByCurrentTicker() throws Exception {
        // given
        HashMap<ExchangeType, String[]> tickersRequestMap = makeTickerRequestMap();

        Mockito.when(tickerClient.getTickers(tickersRequestMap))
                .thenReturn(List.of(
                        makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                        , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                        , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "100300")
                ));
        // when
        TickerCreateResponse byCurrentTicker = tickerService.createByCurrentTicker(tickersRequestMap);

        // then
        List<Ticker> tickers = tickerRepository.findAll();
        assertThat(tickers)
                .hasSize(3)
                .extracting("exchangeType", "symbol", "price")
                .containsExactlyInAnyOrder(
                        tuple(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                        , tuple(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                        , tuple(ExchangeType.BINANCE, "BTCUSDT", "100300")
                );
    }

    private HashMap<ExchangeType, String[]> makeTickerRequestMap() {
        HashMap<ExchangeType, String[]> result = new HashMap<>();

        result.put(ExchangeType.BITHUMB, new String[]{"KRW-BTC, KRW-USDT"});
        result.put(ExchangeType.BINANCE, new String[]{"BTCUSDT"});

        return result;
    }

    private Ticker makeTicker(ExchangeType type, String symbol, String price) {
        return Ticker.builder().exchangeType(type).symbol(symbol).price(price).build();
    }

    @DisplayName("현재 시점의 가상화폐 가격이 가장 최신의 가상화폐 가격과 동일한 경우 저장하지 않는다.")
    @Test
    void createByCurrentTickerWithSamePrice() throws Exception {

        // given
        HashMap<ExchangeType, String[]> tickersRequestMap = makeTickerRequestMap();

        Mockito.when(tickerClient.getTickers(tickersRequestMap))
                .thenReturn(List.of(
                        makeTicker(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                        , makeTicker(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                        , makeTicker(ExchangeType.BINANCE, "BTCUSDT", "100300")
                ));

        // when
        TickerCreateResponse byCurrentTicker = tickerService.createByCurrentTicker(tickersRequestMap);
        TickerCreateResponse byCurrentTicker1 = tickerService.createByCurrentTicker(tickersRequestMap);

        // then
        List<Ticker> tickers = tickerRepository.findAll();
        assertThat(tickers)
                .hasSize(3)
                .extracting("exchangeType", "symbol", "price")
                .containsExactlyInAnyOrder(
                        tuple(ExchangeType.BITHUMB, "KRW-BTC", "140000000")
                        , tuple(ExchangeType.BITHUMB, "KRW-USDT", "1400")
                        , tuple(ExchangeType.BINANCE, "BTCUSDT", "100300")
                );
    }

}