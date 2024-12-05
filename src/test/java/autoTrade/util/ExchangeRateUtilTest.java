package autoTrade.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeRateUtilTest {

    @DisplayName("실시간 환율을 조회한다.")
    @Test
    void liveTimeGetExchangeRate() {
        // given
        ApiUtilRequest apiUtilRequest = makeApiRequest();
        ExchangeRateUtil exchangeRateUtil = new ExchangeRateUtil();

        // when
        double exchangeRate = exchangeRateUtil.getData(apiUtilRequest);

        // then
        assertThat(exchangeRate).isNotNaN();
    }



    private ApiUtilRequest makeApiRequest() {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("key", "calculator");
        params.add("pkid", "141");
        params.add("q", "환율");
        params.add("where", "m");
        params.add("u2", "1");
        params.add("u3", "USD");
        params.add("u4", "KRW");

        return ApiUtilRequest.builder()
                .host("m.search.naver.com")
                .path("/p/csearch/content/qapirender.nhn")
                .params(params)
                .build();
    }
}