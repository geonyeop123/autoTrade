package autoTrade.domain.ticker;

import autoTrade.domain.ExchangeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BinanceTicker {

    private final ExchangeType exchangeType = ExchangeType.BINANCE;
    private String symbol;
    private String price;

    @Builder
    private BinanceTicker(String symbol, String price) {
        this.symbol = symbol;
        this.price = price;
    }

    public static BinanceTicker of(HashMap<String, Object> map) {
        return BinanceTicker.builder()
                .symbol(String.valueOf(map.get("symbol")))
                .price(String.valueOf(map.get("price")))
                .build();
    }

    public static Ticker toEntity(BinanceTicker binanceTicker) {
        return Ticker.builder()
                .exchangeType(binanceTicker.exchangeType)
                .symbol(binanceTicker.symbol)
                .price(binanceTicker.price)
                .build();
    }

    public static Ticker toEntityBy(HashMap<String, Object> map) {
        return Ticker.builder()
                .exchangeType(ExchangeType.BINANCE)
                .symbol(String.valueOf(map.get("symbol")))
                .price(String.valueOf(map.get("price")))
                .build();
    }
}
