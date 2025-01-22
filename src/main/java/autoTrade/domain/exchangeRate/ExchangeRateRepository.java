package autoTrade.domain.exchangeRate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("SELECT e FROM ExchangeRate e WHERE e.createType = 'AUTO' ORDER BY e.datetime DESC LIMIT 1")
    ExchangeRate findLatestExchangeRate();
}
