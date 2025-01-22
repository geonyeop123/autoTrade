package autoTrade.domain.exchangeRate;

import autoTrade.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExchangeRate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_rate_id")
    private Long id;

    private double price;

    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private CreateType createType;

    private ExchangeRate(Long id, double price, LocalDateTime datetime) {
        this(id, price, datetime, CreateType.AUTO);
    }

    @Builder
    private ExchangeRate(Long id, double price, LocalDateTime datetime, CreateType createType) {
        this.id = id;
        this.price = price;
        this.datetime = datetime;
        this.createType = createType;
    }

    public static ExchangeRate of(double price) {
        return of(price, CreateType.AUTO);
    }

    public static ExchangeRate of(double price, CreateType createType) {
        return ExchangeRate.builder()
                .price(price)
                .datetime(LocalDateTime.now())
                .build();
    }
}
