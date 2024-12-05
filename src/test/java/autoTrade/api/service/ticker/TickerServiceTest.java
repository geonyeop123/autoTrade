package autoTrade.api.service.ticker;

import autoTrade.api.service.ticker.response.TickerCreateResponse;
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

@ActiveProfiles("test")
@SpringBootTest
class TickerServiceTest {

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
        // given // when
        TickerCreateResponse byCurrentTicker = tickerService.createByCurrentTicker();

        // then
        List<Ticker> tickers = tickerRepository.findAll();
        assertThat(tickers).hasSize(3);
    }

}