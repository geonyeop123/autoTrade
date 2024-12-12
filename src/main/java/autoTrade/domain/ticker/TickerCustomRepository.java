package autoTrade.domain.ticker;

import java.util.List;

public interface TickerCustomRepository {
    List<Ticker> findLatestTickersByType();
}
