package autoTrade.api.service.premium.response;

import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.ticker.Ticker;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PremiumCreateResponse {

    private Long id;

    private ExchangeRate exchangeRate;
    private Ticker krBtcTicker;
    private Ticker usdtTicker;
    private Ticker usBtcTicker;
    private double premiumRate;

    public String getInfo() {

        return  String.format("""
               
                KR Ticker : %,d
                US Ticker : %s
                exchange rate : %.1f
                premium rate : %s""", Integer.valueOf(krBtcTicker.getPrice()), usBtcTicker.getPrice(), exchangeRate.getPrice(), premiumRate);
    }

    @Builder
    private PremiumCreateResponse(Long id, ExchangeRate exchangeRate, Ticker krBtcTicker, Ticker usdtTicker, Ticker usBtcTicker, double premiumRate) {
        this.id = id;
        this.exchangeRate = exchangeRate;
        this.krBtcTicker = krBtcTicker;
        this.usdtTicker = usdtTicker;
        this.usBtcTicker = usBtcTicker;
        this.premiumRate = premiumRate;
    }
}
