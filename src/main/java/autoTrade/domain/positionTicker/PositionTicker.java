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

    private double quantity;

    @Builder
    private PositionTicker(ExchangeType exchangeType, String symbol, String price, double quantity) {
        this.exchangeType = exchangeType;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PositionTicker{" +
                "id=" + id +
                ", exchangeType=" + exchangeType +
                ", symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
