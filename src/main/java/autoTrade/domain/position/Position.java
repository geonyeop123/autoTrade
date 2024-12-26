package autoTrade.domain.position;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.positionTicker.PositionTicker;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Position extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @JoinColumn(name = "kr_position_ticker_id")
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    private PositionTicker krTicker;

    @JoinColumn(name = "us_position_ticker_id")
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    private PositionTicker usTicker;

    @JoinColumn(name = "exchange_rate_id")
    @ManyToOne(fetch = LAZY)
    private ExchangeRate exchangeRate;

    private boolean sellYn;

    private boolean alertYn;

    @Builder
    private Position(PositionTicker krTicker, PositionTicker usTicker, ExchangeRate exchangeRate, boolean sellYn, boolean alertYn) {
        this.krTicker = krTicker;
        this.usTicker = usTicker;
        this.exchangeRate = exchangeRate;
        this.sellYn = sellYn;
        this.alertYn = alertYn;
    }
}
