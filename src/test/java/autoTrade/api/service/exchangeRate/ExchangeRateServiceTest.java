package autoTrade.api.service.exchangeRate;

import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExchangeRateServiceTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @AfterEach
    void tearDown() {
        exchangeRateRepository.deleteAllInBatch();
    }

    @DisplayName("지정된 날짜의 환율을 DB에 저장한다.")
    @Test
    void createExchangeRate() {

        // given // when
        ExchangeRateCreateResponse response = exchangeRateService.createExchangeRate();
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();

        // then
        assertThat(exchangeRates).hasSize(1);
        assertThat(exchangeRates.get(0).getDatetime()).isEqualTo(response.getDate());
        assertThat(exchangeRates.get(0).getPrice()).isEqualTo(response.getPrice());
    }

}