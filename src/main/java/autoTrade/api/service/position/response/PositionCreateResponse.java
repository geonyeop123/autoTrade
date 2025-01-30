package autoTrade.api.service.position.response;

import autoTrade.domain.exchangeRate.ExchangeRate;
import autoTrade.domain.member.Member;
import autoTrade.domain.position.Position;
import autoTrade.domain.positionTicker.PositionTicker;
import lombok.Builder;

public class PositionCreateResponse {

    private Long id;
    private Member member;
    private PositionTicker krTicker;
    private PositionTicker usTicker;
    private ExchangeRate exchangeRate;

    @Builder
    private PositionCreateResponse(Long id, Member member, PositionTicker krTicker, PositionTicker usTicker, ExchangeRate exchangeRate) {
        this.id = id;
        this.member = member;
        this.krTicker = krTicker;
        this.usTicker = usTicker;
        this.exchangeRate = exchangeRate;
    }

    public static PositionCreateResponse of(Position position){
        return PositionCreateResponse.builder()
                .id(position.getId())
                .member(position.getMember())
                .krTicker(position.getKrTicker())
                .usTicker(position.getUsTicker())
                .exchangeRate(position.getExchangeRate())
                .build();
    }
}
