package autoTrade.api.controller.position;

import autoTrade.api.ApiResponse;
import autoTrade.api.controller.position.request.PositionCreateControllerRequest;
import autoTrade.api.service.member.MemberService;
import autoTrade.api.service.position.PositionService;
import autoTrade.api.service.position.request.PositionCreateRequest;
import autoTrade.api.service.position.response.PositionCreateResponse;
import autoTrade.domain.member.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PositionController {

    private final PositionService positionService;
    private final MemberService memberService;

    @PostMapping("/api/v1/position/save")
    public ApiResponse<PositionCreateResponse> createPosition(@Valid @RequestBody PositionCreateControllerRequest request){

        log.info("savePosition");

        Member findMember = memberService.findById(request.getMemberId());

        if(findMember == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        PositionCreateRequest serviceRequest = PositionCreateRequest.builder()
                .member(findMember)
                .exchangeRate(request.getExchangeRate())
                .krQuantity(request.getKrQuantity())
                .krPrice(request.getKrPrice())
                .symbol(request.getSymbol())
                .usQuantity(request.getUsQuantity())
                .usPrice(request.getUsPrice())
                .build();

        return ApiResponse.ok(positionService.createPosition(serviceRequest));
    }

}
