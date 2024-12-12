package autoTrade.api.service.ticker;


import autoTrade.api.client.TickerClient;
import autoTrade.api.service.ticker.response.TickerCreateResponse;
import autoTrade.domain.ExchangeType;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TickerService {

    private final TickerRepository tickerRepository;

    private final TickerClient tickerClient;

    @Transactional
    public TickerCreateResponse createByCurrentTicker() throws Exception {

        HashMap<ExchangeType, String[]> tickersRequestMap = new HashMap<>();

        tickersRequestMap.put(ExchangeType.BITHUMB, new String[]{"KRW-BTC, KRW-USDT"});
        tickersRequestMap.put(ExchangeType.BINANCE, new String[]{"BTCUSDT"});

        List<Ticker> tickers = tickerClient.getTickers(tickersRequestMap);



        List<Ticker> savedTickers = tickerRepository.saveAll(tickers);

        return TickerCreateResponse.of(savedTickers);
    }

}
