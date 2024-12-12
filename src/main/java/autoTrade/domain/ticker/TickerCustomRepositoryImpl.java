package autoTrade.domain.ticker;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TickerCustomRepositoryImpl implements TickerCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Ticker> findLatestTickersByType() {
        QTicker ticker = QTicker.ticker;

        // 타입별 최신 데이터 쿼리 작성
        var subquery = queryFactory
                .select(ticker.id.max().as("id"))
                .from(ticker)
                .groupBy(ticker.exchangeType, ticker.symbol);

        return queryFactory.selectFrom(ticker)
                .where(ticker.id.in(subquery))
                .fetch();
    }
}
