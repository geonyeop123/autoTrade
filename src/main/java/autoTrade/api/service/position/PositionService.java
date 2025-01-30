package autoTrade.api.service.position;

import autoTrade.api.service.exchangeRate.ExchangeRateService;
import autoTrade.api.service.position.request.PositionCreateRequest;
import autoTrade.api.service.position.response.PositionCreateResponse;
import autoTrade.domain.ExchangeType;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.position.Position;
import autoTrade.domain.position.PositionRepository;
import autoTrade.domain.positionTicker.PositionTicker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final ExchangeRateService exchangeRateService;

    public PositionCreateResponse createPosition(PositionCreateRequest request) {
        PositionTicker krTicker = makePositionTicker(ExchangeType.BITHUMB, request.getKrPrice(), request.getKrQuantity(), request.getSymbol());
        PositionTicker usTicker = makePositionTicker(ExchangeType.BINANCE, request.getUsPrice(), request.getUsQuantity(), request.getSymbol());
        ExchangeRate exchangeRate = exchangeRateService.createExchangeRate(request.getExchangeRate());
        Position position = Position.builder()
                .member(request.getMember())
                .krTicker(krTicker)
                .usTicker(usTicker)
                .exchangeRate(exchangeRate)
                .build();
        Position savedPosition = positionRepository.save(position);

        return PositionCreateResponse.of(savedPosition);
    }

    private PositionTicker makePositionTicker(ExchangeType exchangeType, String price, double quantity, String symbol) {
        return PositionTicker.builder()
                .exchangeType(exchangeType)
                .price(price)
                .quantity(quantity)
                .symbol(symbol)
                .build();
    }

}
