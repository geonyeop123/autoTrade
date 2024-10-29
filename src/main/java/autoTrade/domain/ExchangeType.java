package autoTrade.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ExchangeType {

    BITHUMB("빗썸",
        "https://api.bithumb.com/",
        "v1/ticker?markets="),
    UPBIT("업비트",
        "https://api.upbit.com/",
            "v1/ticker?markets="),
    BINANCE("바이낸스",
            "https://fapi.binance.com/",
            "fapi/v1/ticker/price?symbol=");

    private final String text;
    private final String baseUrl;
    private final String tickerUri;

    public static List<ExchangeType> exchangeTypesAll() {
        return List.of(BITHUMB
//                , UPBIT
                , BINANCE);
    }
}
