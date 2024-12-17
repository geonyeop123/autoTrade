package autoTrade.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PositionTicker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_ticker_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;

    private String symbol;

    private String price;

}
