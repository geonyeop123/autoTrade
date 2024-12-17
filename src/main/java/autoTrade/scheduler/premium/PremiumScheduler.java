package autoTrade.scheduler.premium;

import autoTrade.api.ApiResponse;
import autoTrade.api.controller.exchangeRate.ExchangeRateController;
import autoTrade.api.controller.premium.PremiumController;
import autoTrade.api.controller.ticker.TickerController;
import autoTrade.api.service.premium.response.PremiumCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class PremiumScheduler {

    private final PremiumController premiumController;
    private final TickerController tickerController;
    private final ExchangeRateController exchangeRateController;

//    @Scheduled(fixedDelay = 5500) // 5초 이후 실행
//    public void getExchangeRate() throws Exception{
//        ApiResponse<PremiumCreateResponse> premium = premiumController.createPremium();
//        PremiumCreateResponse data = premium.getData();
//
//        log.info("premiumRate : {}", data.getPremiumRate());
//    }

    @Scheduled(fixedDelay = 5000) // 5초마다 실행
    public void startTasks() {
        CompletableFuture.runAsync(() -> {
            try {
                tickerController.createTicker();
                exchangeRateController.saveExchangeRate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).thenRun(() -> {
            ApiResponse<PremiumCreateResponse> premium = premiumController.createPremium();
            PremiumCreateResponse data = premium.getData();
            log.info("Premium Info : {}", data.getInfo());
        });
    }
}
