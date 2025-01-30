package autoTrade.api.service.position.request;

import autoTrade.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PositionCreateRequest {

    private Member member;
    private String symbol;
    private String krPrice;
    private double krQuantity;
    private String usPrice;
    private double usQuantity;
    private double exchangeRate;


    @Builder
    private PositionCreateRequest(Member member, String symbol, String krPrice, double krQuantity, String usPrice, double usQuantity, double exchangeRate) {
        this.member = member;
        this.symbol = symbol;
        this.krPrice = krPrice;
        this.krQuantity = krQuantity;
        this.usPrice = usPrice;
        this.usQuantity = usQuantity;
        this.exchangeRate = exchangeRate;
    }
}
