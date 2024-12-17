package autoTrade.api.controller.premium;

import autoTrade.api.ApiResponse;
import autoTrade.api.service.premium.PremiumService;
import autoTrade.api.service.premium.response.PremiumCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PremiumController {

    private final PremiumService premiumService;

    @PostMapping("/api/v1/premium/create")
    public ApiResponse<PremiumCreateResponse> createPremium() {
        log.info("create Premium");
        return ApiResponse.ok(premiumService.createPremium());
    }
}
