package autoTrade.util;


import autoTrade.domain.ExchangeType;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApiUtil {

    private final HashMap<ExchangeType, WebClient> clients;

    public ApiUtil(){
        this.clients = new HashMap<>();

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        for(ExchangeType exchangeType : ExchangeType.exchangeTypesAll()) {
            clients.put(exchangeType,
                    WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .baseUrl(exchangeType.getBaseUrl())
                        .build());
        }
    }

    public List<LinkedHashMap<String, Object>> getTickers(ExchangeType exchangeType, String[] symbols) throws Exception{

        WebClient webClient = clients.get(exchangeType);
        List<LinkedHashMap<String, Object>> listResponse = new ArrayList<>();

        Object response = webClient.get()
                .uri(exchangeType.getTickerUri() + StringUtils.join(symbols))
                .retrieve().bodyToMono(Object.class).block();

        if( response instanceof List){
            listResponse = (List<LinkedHashMap<String, Object>>)response;
        }else {
            listResponse.add((LinkedHashMap<String, Object>)response);
        }

        return listResponse;
    }


}
