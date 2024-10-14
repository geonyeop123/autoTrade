package autoTrade.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class ApiUtilTest {

    @DisplayName("")
    @Test
    void getBithumb() {
        // given
        ApiUtil apiUtil = new ApiUtil();
        WebClient webClient = apiUtil.getWebClient("https://api.bithumb.com/");
        Object o = apiUtil.get(webClient, "v1/ticker?markets=KRW-BTC");
        Object o1 = apiUtil.get(webClient, "v1/ticker?markets=KRW-USDT");
        System.out.println(o);
        System.out.println(o1);


        // when


        // then
    }

    @DisplayName("")
    @Test
    void getUpbit() {
        // given
        ApiUtil apiUtil = new ApiUtil();
        WebClient webClient = apiUtil.getWebClient("https://api.upbit.com/");
        Object o = apiUtil.get(webClient, "v1/ticker?markets=KRW-BTC");
        System.out.println(o);

        // when


        // then
    }

    @DisplayName("")
    @Test
    void getBinance() {
        // given
        ApiUtil apiUtil = new ApiUtil();
        WebClient webClient = apiUtil.getWebClient("https://fapi.binance.com/");
        Object o = apiUtil.get(webClient, "fapi/v1/ticker/price?symbol=BTCUSDT");
        System.out.println(o);

        // when


        // then
    }

}