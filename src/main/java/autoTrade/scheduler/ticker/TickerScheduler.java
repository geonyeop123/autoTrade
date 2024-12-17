package autoTrade.scheduler.ticker;

import autoTrade.api.controller.ticker.TickerController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class TickerScheduler {

    private final TickerController tickerController;

//    @Scheduled(fixedDelay = 5000) // 5초 이후 실행
//    public void getExchangeRate() throws Exception{
//        ApiResponse<TickerCreateResponse> tickers = tickerController.createTicker();
//        TickerCreateResponse data = tickers.getData();
//        data.getTickers().forEach(ticker -> log.info("tickerInfo : {}", ticker.getInfo()));
//    }
}
