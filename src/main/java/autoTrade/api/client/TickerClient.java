package autoTrade.api.client;

import autoTrade.domain.ExchangeType;
import autoTrade.domain.ticker.BinanceTicker;
import autoTrade.domain.ticker.BithumbTicker;
import autoTrade.domain.ticker.Ticker;
import autoTrade.util.ApiUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class TickerClient {

    public List<Ticker> getTickers(HashMap<ExchangeType, String[]> tickersRequestMap) throws Exception{

        List<Ticker> tickers = new ArrayList<>();
        ApiUtil apiUtil = new ApiUtil();

        for (ExchangeType exchangeType : tickersRequestMap.keySet()) {
            List<LinkedHashMap<String, Object>> response = apiUtil.getTickers(exchangeType, tickersRequestMap.get(exchangeType));
            tickers.addAll(makeTickers(exchangeType, response));
        }

        return tickers;
    }

    private List<Ticker> makeTickers(ExchangeType exchangeType, List<LinkedHashMap<String, Object>> tickersRequestMaps){

        if(ExchangeType.BITHUMB == exchangeType){
            return tickersRequestMaps.stream().map(BithumbTicker::toEntityBy).toList();
        }else if(ExchangeType.BINANCE == exchangeType){
            return tickersRequestMaps.stream().map(BinanceTicker::toEntityBy).toList();
        }

        return new ArrayList<>();
    }

}
