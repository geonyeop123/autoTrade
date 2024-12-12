package autoTrade.api.controller.ticker;

import autoTrade.api.ApiResponse;
import autoTrade.api.service.ticker.TickerService;
import autoTrade.api.service.ticker.response.TickerCreateResponse;
import autoTrade.domain.ExchangeType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class TickerController {

    private final TickerService tickerService;

    @PostMapping("/api/v1/ticker/create")
    public ApiResponse<TickerCreateResponse> createTicker() throws Exception {

        HashMap<ExchangeType, String[]> tickersRequestMap = new HashMap<>();

        tickersRequestMap.put(ExchangeType.BITHUMB, new String[]{"KRW-BTC, KRW-USDT"});
        tickersRequestMap.put(ExchangeType.BINANCE, new String[]{"BTCUSDT"});

        return ApiResponse.ok(tickerService.createByCurrentTicker(tickersRequestMap));
    }

}
