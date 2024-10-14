package autoTrade.domain.ticker;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.ExchangeType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Ticker extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;

    private String symbol;

    private Long price;

}
