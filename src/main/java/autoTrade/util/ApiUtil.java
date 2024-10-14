package autoTrade.util;


import autoTrade.domain.ExchangeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

public class ApiUtil {

    private WebClient webClient;
    private String baseUrl;
    private String tickerUri;

    @Builder
    private ApiUtil(ExchangeType exchangeType) {

        if (exchangeType == ExchangeType.BITHUMB) {
            this.baseUrl = "https://api.bithumb.com/";
            this.tickerUri = "v1/ticker?markets=KRW-BTC,KRW-USDT";
        } else if(exchangeType == ExchangeType.UPBIT){
            this.baseUrl = "https://api.upbit.com/";
            this.tickerUri = "v1/ticker?markets=KRW-BTC,KRW-USDT";
        }else {
            this.baseUrl = "https://fapi.binance.com/";
            this.tickerUri = "fapi/v1/ticker/price?symbol=BTCUSDT";
        }

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void getTicker() {
        
    }


}
