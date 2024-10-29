package autoTrade.util;


import autoTrade.domain.ExchangeType;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ApiUtil {

    private final HashMap<ExchangeType, WebClient> clients;

    public ApiUtil(){
        this.clients = new HashMap<>();
        for(ExchangeType exchangeType : ExchangeType.exchangeTypesAll()) {
            clients.put(exchangeType, WebClient.builder()
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
