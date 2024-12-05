package autoTrade.api.service.ticker.response;


import autoTrade.domain.ticker.Ticker;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TickerCreateResponse {
    List<Ticker> tickers;

    @Builder
    public TickerCreateResponse(List<Ticker> tickers) {
        this.tickers = tickers;
    }

    public static TickerCreateResponse of(List<Ticker> tickers){
        return TickerCreateResponse.builder().tickers(tickers).build();
    }
}
