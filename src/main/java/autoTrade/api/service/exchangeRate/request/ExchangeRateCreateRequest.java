package autoTrade.api.service.exchangeRate.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExchangeRateCreateRequest {

    LocalDate date;

    @Builder
    private ExchangeRateCreateRequest(LocalDate date) {
        this.date = date;
    }
}
