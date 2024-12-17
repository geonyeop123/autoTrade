package autoTrade.domain.position;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.PositionTicker;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Position extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @JoinColumn(name = "position_ticker_id")
    @OneToOne()
    private PositionTicker krTicker;

    @JoinColumn(name = "position_ticker_id")
    @OneToOne()
    private PositionTicker usTicker;

    private double premium_rate;

    private double benefit_rate;

    private Integer benefit_value;

    private double sell_benefit_rate;

    private Integer sell_benefit_value;

    private boolean sellYn;

}
