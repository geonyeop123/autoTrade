package autoTrade.domain.ticker;

import autoTrade.domain.ExchangeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpBitTicker {

    private final ExchangeType exchangeType = ExchangeType.UPBIT;
    private String market;
    private Long trade_price;

    @Builder
    private UpBitTicker(String market, Long trade_price) {
        this.market = market;
        this.trade_price = trade_price;
    }

    public static UpBitTicker of(HashMap<String, Object> map) {
        return UpBitTicker.builder()
                .market(String.valueOf(map.get("market")))
                .trade_price(Long.parseLong(Double.toString(Double.parseDouble(String.valueOf(map.get("trade_price"))))))
                .build();
    }

    public static Ticker toEntity(UpBitTicker upbitTicker) {
        return Ticker.builder()
                .exchangeType(upbitTicker.exchangeType)
                .symbol(upbitTicker.market)
                .price(String.valueOf(upbitTicker.trade_price))
                .build();
    }
}
