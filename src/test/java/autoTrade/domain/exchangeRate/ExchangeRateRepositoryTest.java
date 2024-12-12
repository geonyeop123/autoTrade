package autoTrade.domain.exchangeRate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ExchangeRateRepositoryTest {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    @AfterEach
    void tearDown(){
        exchangeRateRepository.deleteAllInBatch();
    }

    @DisplayName("가장 최근의 저장된 환율을 조회한다.")
    @Test
    void findLatestExchangeRate() {
        // given
        ExchangeRate exchangeRate1 = ExchangeRate.of(1041.1);
        ExchangeRate exchangeRate2 = ExchangeRate.of(1041.2);
        exchangeRateRepository.save(exchangeRate1);
        ExchangeRate save = exchangeRateRepository.save(exchangeRate2);

        // when
        ExchangeRate findExchange = exchangeRateRepository.findLatestExchangeRate();

        // then
        assertThat(findExchange.getPrice()).isEqualTo(save.getPrice());
    }
}