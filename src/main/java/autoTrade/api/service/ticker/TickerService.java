package autoTrade.api.service.ticker;


import autoTrade.api.client.TickerClient;
import autoTrade.api.service.ticker.response.TickerCreateResponse;
import autoTrade.domain.ExchangeType;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TickerService {

    private final TickerRepository tickerRepository;

    private final TickerClient tickerClient;

    @Transactional
    public TickerCreateResponse createByCurrentTicker(HashMap<ExchangeType, String[]> tickersRequestMap) throws Exception {

        List<Ticker> tickers = tickerClient.getTickers(tickersRequestMap);

        List<Ticker> savedTickers = saveTickers(tickers);

        return TickerCreateResponse.of(savedTickers);
    }

    private List<Ticker> saveTickers(List<Ticker> tickers) {

        List<Ticker> latestTickersByType = tickerRepository.findLatestTickersByType();

        List<Ticker> savedTickers = new ArrayList<>();
        List<Ticker> notDuplicateTickers = new ArrayList<>();

        for (Ticker ticker : tickers) {
            if(isContains(latestTickersByType, ticker)){
                savedTickers.add(ticker);
            }else{
                notDuplicateTickers.add(ticker);
            }
        }

        if(!notDuplicateTickers.isEmpty()){
            savedTickers.addAll(tickerRepository.saveAll(notDuplicateTickers));
        }

        return savedTickers;
    }

    private boolean isContains(List<Ticker> tickers, Ticker ticker) {
        return !tickers.stream().filter(ticker::equals).toList().isEmpty();
    }

}
