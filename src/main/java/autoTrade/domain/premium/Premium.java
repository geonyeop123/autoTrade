package autoTrade.domain.premium;

import autoTrade.domain.BaseEntity;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.ticker.Ticker;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Premium extends BaseEntity {

    @Id
    @Column(name = "premium_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "exchange_rate_id")
    private ExchangeRate exchangeRate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "kr_btc_ticker_id")
    private Ticker krBtcTicker;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "usdt_ticker_id")
    private Ticker usdtTicker;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "us_btc_ticker_id")
    private Ticker usBtcTicker;

    private double premiumRate;

    @Builder
    private Premium(ExchangeRate exchangeRate, Ticker krBtcTicker, Ticker usdtTicker, Ticker usBtcTicker) {
        this.exchangeRate = exchangeRate;
        this.krBtcTicker = krBtcTicker;
        this.usdtTicker = usdtTicker;
        this.usBtcTicker = usBtcTicker;
        this.premiumRate = calculatePremiumRate();
    }

    private double calculatePremiumRate() {

        BigDecimal krPrice = new BigDecimal(krBtcTicker.getPrice());
        BigDecimal binancePrice = new BigDecimal(usBtcTicker.getPrice());
        BigDecimal exchangeRatePrice = BigDecimal.valueOf(exchangeRate.getPrice());
        BigDecimal krBinancePrice = binancePrice.multiply(exchangeRatePrice);

        // (한국 가격 - 외국 가격) / 외국 가격 * 100
        return krPrice.subtract(krBinancePrice)
                .divide(krBinancePrice, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();
    }
}
