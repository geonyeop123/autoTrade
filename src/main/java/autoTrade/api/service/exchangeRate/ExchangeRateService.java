package autoTrade.api.service.exchangeRate;


import autoTrade.api.service.exchangeRate.request.ExchangeRateServiceCreateRequest;
import autoTrade.api.service.exchangeRate.response.ExchangeRateCreateResponse;
import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.exchangeRate.ExchangeRateRepository;
import autoTrade.exception.DayOffException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateCreateResponse createExchangeRate(ExchangeRateServiceCreateRequest exchangeRateServiceCreateRequest) {

        LocalDate date = exchangeRateServiceCreateRequest.getDate();

        if(isDayOff(date)){
            throw new DayOffException("휴일에는 환율을 조회할 수 없습니다.");
        }

        double exchangeRate1 = getExchangeRate(date);

        ExchangeRate exchangeRate =
                ExchangeRate.builder()
                        .price(exchangeRate1)
                        .date(date)
                        .build();

        ExchangeRate savedExchangeRate = exchangeRateRepository.save(exchangeRate);

        return ExchangeRateCreateResponse.builder()
                .id(savedExchangeRate.getId())
                .price(savedExchangeRate.getPrice())
                .date(savedExchangeRate.getDate())
                .build();
    }

    private boolean isDayOff(LocalDate date) {

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
                .contains(dayOfWeek);
    }

    private double getExchangeRate(LocalDate date) {
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.koreaexim.go.kr/")
                .build();

        List response = webClient.get().uri("site/program/financial/exchangeJSON?authkey=8KF4zaUdq5CgBTEKjQMKzkUe2sWzagG5&data=AP01&cur_unit=USD&searchdate="
                        + dateString)
                .retrieve().bodyToMono(List.class).block();

        String s = ((LinkedHashMap<String, String>)response.get(0)).get("deal_bas_r");
        s = s.replaceAll(",", "");

        return Double.parseDouble(s);
    }


}
