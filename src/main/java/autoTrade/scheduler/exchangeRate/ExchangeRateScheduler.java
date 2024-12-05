package autoTrade.scheduler.exchangeRate;

import autoTrade.api.controller.exchangeRate.ExchangeRateController;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class ExchangeRateScheduler {

    private final ExchangeRateController exchangeRateController;

    @Scheduled(fixedDelay = 5000) // 5초 이후 실행
    public void getExchangeRate() {
        System.out.println("rate start");
        ExchangeRateCreateResponse exchangeRateCreateResponse = exchangeRateController.saveExchangeRate().getData();
        System.out.println(exchangeRateCreateResponse.getPrice());
        System.out.println(exchangeRateCreateResponse.getDate());
    }

}
