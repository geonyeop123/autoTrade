package autoTrade.api.service.exchangeRate.response;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.exchangeRate.ExchangeRate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExchangeRateCreateResponse extends BaseEntity {

    private Long id;
    private double price;
    private LocalDate date;

    @Builder
    private ExchangeRateCreateResponse(Long id, double price, LocalDate date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    private static ExchangeRateCreateResponse of(ExchangeRate exchangeRate){
        return ExchangeRateCreateResponse.builder()
                .price(exchangeRate.getPrice())
                .date(exchangeRate.getDate())
                .build();
    }
}
