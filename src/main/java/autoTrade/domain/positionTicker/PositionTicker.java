package autoTrade.domain.positionTicker;


import autoTrade.domain.ExchangeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionTicker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_ticker_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;

    private String symbol;

    private String price;

    @Builder
    private PositionTicker(ExchangeType exchangeType, String symbol, String price) {
        this.exchangeType = exchangeType;
        this.symbol = symbol;
        this.price = price;
    }
}
