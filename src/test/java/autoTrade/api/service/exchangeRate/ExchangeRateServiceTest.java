package autoTrade.api.service.exchangeRate;

import autoTrade.api.service.exchangeRate.request.ExchangeRateServiceCreateRequest;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.exception.DayOffException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ExchangeRateServiceTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @DisplayName("지정된 날짜의 환율을 DB에 저장한다.")
    @Test
    void createExchangeRate() {
        // given
        LocalDate date = LocalDate.of(2024, 10, 25);
        ExchangeRateServiceCreateRequest request =
                ExchangeRateServiceCreateRequest.builder()
                        .date(date)
                        .build();

        // when
        ExchangeRateCreateResponse response = exchangeRateService.createExchangeRate(request);
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();

        // then
        assertThat(exchangeRates).hasSize(1);
        assertThat(exchangeRates.get(0).getDate()).isEqualTo(response.getDate());
        assertThat(exchangeRates.get(0).getPrice()).isEqualTo(response.getPrice());
    }

    @DisplayName("휴일에 환율을 조회할 시 오류가 발생한다.")
    @Test
    void createExchangeRateWhenDayOff() {
        // given
        LocalDate dayOff = LocalDate.of(2024, 10, 27);
        ExchangeRateServiceCreateRequest request =
                ExchangeRateServiceCreateRequest.builder()
                        .date(dayOff)
                        .build();

        // when // then
        assertThatThrownBy(() -> exchangeRateService.createExchangeRate(request))
                .isInstanceOf(DayOffException.class)
                .hasMessage("휴일에는 환율을 조회할 수 없습니다.");
    }

}