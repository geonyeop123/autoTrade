package autoTrade.api.controller.exchangeRate;

import autoTrade.api.controller.exchangeRate.request.ExchangeRateCreateRequest;
import autoTrade.api.service.exchangeRate.ExchangeRateService;
import autoTrade.api.service.exchangeRate.request.ExchangeRateServiceCreateRequest;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PostMapping("/api/v1/exchangeRate/save")
    public ExchangeRateCreateResponse saveExchangeRate(@RequestBody ExchangeRateCreateRequest exchangeRateCreateRequest) {
        return exchangeRateService.createExchangeRate(ExchangeRateServiceCreateRequest.builder()
                        .date(exchangeRateCreateRequest.getDate())
                .build());
    }

}
