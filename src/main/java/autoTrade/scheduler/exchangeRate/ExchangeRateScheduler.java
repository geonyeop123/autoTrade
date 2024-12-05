package autoTrade.scheduler.exchangeRate;

import autoTrade.api.controller.exchangeRate.ExchangeRateController;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateScheduler {

    private final ExchangeRateController exchangeRateController;

    @Scheduled(fixedDelay = 5000) // 5초 이후 실행
    public void getExchangeRate() {
        ExchangeRateCreateResponse exchangeRateCreateResponse = exchangeRateController.saveExchangeRate().getData();
        log.info("get rate : {}", exchangeRateCreateResponse.getPrice());

    }

}
