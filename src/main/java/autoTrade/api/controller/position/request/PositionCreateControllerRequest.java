package autoTrade.api.controller.position.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PositionCreateControllerRequest {

    @NotNull(message = "회원정보가 없습니다.")
    private Long memberId;
    @NotBlank(message = "코인 정보는 필수입니다 ")
    private String symbol;
    @NotNull(message = "한국 매수 가격은 필수입니다.")
    private String krPrice;
    @Positive(message = "한국 매수 개수는 양수입니다.")
    private double krQuantity;
    @NotNull(message = "외국 매수 가격은 필수입니다.")
    private String usPrice;
    @Positive(message = "외국 매수 개수는 양수입니다.")
    private double usQuantity;
    @NotNull(message = "환율은 필수입니다.")
    private double exchangeRate;

    @Builder
    private PositionCreateControllerRequest(Long memberId, String symbol, String krPrice, double krQuantity, String usPrice, double usQuantity, double exchangeRate) {
        this.memberId = memberId;
        this.symbol = symbol;
        this.krPrice = krPrice;
        this.krQuantity = krQuantity;
        this.usPrice = usPrice;
        this.usQuantity = usQuantity;
        this.exchangeRate = exchangeRate;
    }
}
