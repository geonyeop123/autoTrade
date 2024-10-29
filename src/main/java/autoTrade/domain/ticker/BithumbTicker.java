package autoTrade.domain.ticker;

import autoTrade.domain.ExchangeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BithumbTicker {

    private final ExchangeType exchangeType = ExchangeType.BITHUMB;
    private String market;
    private String trade_price;

    @Builder
    private BithumbTicker(String market, String trade_price) {
        this.market = market;
        this.trade_price = trade_price;
    }

    public static BithumbTicker of(HashMap<String, Object> map) {
        return BithumbTicker.builder()
                .market(String.valueOf(map.get("market")))
                .trade_price(String.valueOf(map.get("trade_price")))
                .build();
    }

    public static Ticker toEntity(BithumbTicker bithumbTicker) {
        return Ticker.builder()
                .exchangeType(bithumbTicker.exchangeType)
                .symbol(bithumbTicker.market)
                .price(bithumbTicker.trade_price)
                .build();
    }

    public static Ticker toEntityBy(HashMap<String, Object> map){
        return Ticker.builder()
                .exchangeType(ExchangeType.BITHUMB)
                .symbol(String.valueOf(map.get("market")))
                .price(String.valueOf(map.get("trade_price")))
                .build();
    }
}
