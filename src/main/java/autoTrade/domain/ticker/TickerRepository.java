package autoTrade.domain.ticker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, Long>, TickerCustomRepository {

}
