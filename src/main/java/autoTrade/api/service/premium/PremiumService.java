package autoTrade.api.service.premium;

import autoTrade.api.service.premium.response.PremiumCreateResponse;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.domain.premium.Premium;
import autoTrade.domain.premium.PremiumRepository;
import autoTrade.domain.ticker.Ticker;
import autoTrade.domain.ticker.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PremiumService {

    private final PremiumRepository premiumRepository;
    private final TickerRepository tickerRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    @Transactional
    public PremiumCreateResponse createPremium() {

        List<Ticker> tickers = tickerRepository.findLatestTickersByType();
        ExchangeRate exchangeRate = exchangeRateRepository.findLatestExchangeRate();

        Premium premium = makePremium(tickers, exchangeRate);

        Premium savedPremium = premiumRepository.save(premium);

        return PremiumCreateResponse.builder()
                .id(savedPremium.getId())
                .krBtcTicker(premium.getKrBtcTicker())
                .usdtTicker(premium.getUsdtTicker())
                .usBtcTicker(premium.getUsBtcTicker())
                .exchangeRate(premium.getExchangeRate())
                .premiumRate(premium.getPremiumRate())
                .build();
    }

    private Premium makePremium(List<Ticker> tickers, ExchangeRate exchangeRate) {

        if(tickers.size() != 3){
            throw new IllegalArgumentException("조회된 시세가 없습니다.");
        }else if(exchangeRate == null){
            throw new IllegalArgumentException("조회된 환율이 없습니다.");
        }

        Map<String, List<Ticker>> tickerMap =
                tickers.stream()
                        .collect(Collectors.groupingBy(Ticker::getSymbol));

        return Premium.builder()
                .krBtcTicker(tickerMap.get("KRW-BTC").get(0))
                .usdtTicker(tickerMap.get("KRW-USDT").get(0))
                .usBtcTicker(tickerMap.get("BTCUSDT").get(0))
                .exchangeRate(exchangeRate)
                .build();
    }

}
