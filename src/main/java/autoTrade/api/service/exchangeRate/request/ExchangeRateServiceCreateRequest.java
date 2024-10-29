package autoTrade.api.service.exchangeRate.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExchangeRateServiceCreateRequest {

    LocalDate date;

    @Builder
    private ExchangeRateServiceCreateRequest(LocalDate date) {
        this.date = date;
    }
}
