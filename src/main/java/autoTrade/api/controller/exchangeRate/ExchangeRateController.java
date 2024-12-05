package autoTrade.api.controller.exchangeRate;

import autoTrade.api.ApiResponse;
import autoTrade.api.service.exchangeRate.ExchangeRateService;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PostMapping("/api/v1/exchangeRate/save")
    public ApiResponse<ExchangeRateCreateResponse> saveExchangeRate() {
        log.info("saveExchangeRate");
        return ApiResponse.ok(exchangeRateService.createExchangeRate());
    }

}
