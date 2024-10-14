package autoTrade.api.service.ticker;


import autoTrade.domain.ticker.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TickerService {

    private final TickerRepository tickerRepository;

    public void saveByCurrentTickers() {

    }




}
