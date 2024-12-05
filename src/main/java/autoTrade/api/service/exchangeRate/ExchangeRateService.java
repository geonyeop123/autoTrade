package autoTrade.api.service.exchangeRate;


import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.util.ApiUtilRequest;
import autoTrade.util.ExchangeRateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Transactional
    public ExchangeRateCreateResponse createExchangeRate() {

        double exchangeRate = getExchangeRate();
        ExchangeRate exchangeRateEntity = ExchangeRate.of(exchangeRate);

        ExchangeRate savedExchangeRate = exchangeRateRepository.save(exchangeRateEntity);

        return ExchangeRateCreateResponse.of(savedExchangeRate);
    }

    private double getExchangeRate() {
        ApiUtilRequest request = makeApiRequest();

        ExchangeRateUtil exchangeRateUtil = new ExchangeRateUtil();

        return exchangeRateUtil.getData(request);
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
