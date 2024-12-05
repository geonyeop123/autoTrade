package autoTrade.api.controller.ticker;

import autoTrade.api.service.ticker.TickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TickerController {

    private final TickerService tickerService;

    @PostMapping("/api/v1/ticker/create")
    public void createTicker() {

    }

}
