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

    @Builder
    private ExchangeRate(Long id, double price, LocalDateTime datetime) {
        this.id = id;
        this.price = price;
        this.datetime = datetime;
    }

    public static ExchangeRate of(double price) {
        return ExchangeRate.builder()
                .price(price)
                .datetime(LocalDateTime.now())
                .build();
    }
}
