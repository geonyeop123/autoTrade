package autoTrade.util;

import autoTrade.domain.ExchangeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApiUtilTest {

    @DisplayName("현재가를 조회를 요청하여 값을 반환받을 수 있다.")
    @Test
    void getTickers() throws Exception{


        // given
        ApiUtil apiUtil = new ApiUtil();
        List<LinkedHashMap<String, Object>> listMap = apiUtil.getTickers(ExchangeType.BITHUMB, new String[]{"KRW-BTC", "KRW-USDT"});

        // then
        assertThat(listMap).hasSize(2);
    }

}