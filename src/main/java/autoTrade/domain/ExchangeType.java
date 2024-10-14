package autoTrade.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExchangeType {

    BITHUMB("빗썸"),
    UPBIT("업비트"),
    BINANCE("바이낸스");

    private final String text;
}
