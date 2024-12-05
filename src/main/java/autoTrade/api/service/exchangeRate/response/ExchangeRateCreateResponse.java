package autoTrade.api.service.exchangeRate.response;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.exchangeRate.ExchangeRate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExchangeRateCreateResponse extends BaseEntity {

    private Long id;
    private double price;
    private LocalDateTime date;

    @Builder
    private ExchangeRateCreateResponse(Long id, double price, LocalDateTime date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public static ExchangeRateCreateResponse of(ExchangeRate exchangeRate){
        return ExchangeRateCreateResponse.builder()
                .id(exchangeRate.getId())
                .price(exchangeRate.getPrice())
                .date(exchangeRate.getDatetime())
                .build();
    }
}
