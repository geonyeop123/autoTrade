package autoTrade.api.controller.exchangeRate.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExchangeRateCreateRequest {

    LocalDate date;

}