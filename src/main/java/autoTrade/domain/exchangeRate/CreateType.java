package autoTrade.domain.exchangeRate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CreateType {

    AUTO("자동")
    , MANUAL("수동");

    private final String text;
}
