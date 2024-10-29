package autoTrade.api.client;

import autoTrade.domain.ExchangeType;
import autoTrade.domain.ticker.Ticker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;

import static autoTrade.domain.ExchangeType.BITHUMB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@SpringBootTest
class TickerClientTest {

    @Autowired
    private TickerClient tickerClient;

    @DisplayName("요청한 가상회폐의 현재가를 조회할 수 있다.")
    @Test
    void getTickers() throws Exception{
        // given
        HashMap<ExchangeType, String[]> tickersRequestMap = new HashMap<>();
        tickersRequestMap.put(BITHUMB, new String[]{"KRW-BTC", "KRW-USDT"});

        // when
        List<Ticker> tickers = tickerClient.getTickers(tickersRequestMap);

        // then

        assertThat(tickers).hasSize(2)
                .extracting("exchangeType", "symbol")
                .containsExactlyInAnyOrder(
                        tuple(BITHUMB, "KRW-BTC")
                        , tuple(BITHUMB, "KRW-USDT")
                );
    }

}