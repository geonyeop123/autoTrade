package autoTrade.domain.ticker;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.ExchangeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticker extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticker_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;

    private String symbol;

    private String price;

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("symbol : ");
        sb.append(symbol);
        sb.append(", price : ");
        sb.append(price);
        return sb.toString();
    }

    @Builder
    private Ticker(ExchangeType exchangeType, String symbol, String price) {
        this.exchangeType = exchangeType;
        this.symbol = symbol;
        this.price = price;
    }
}
