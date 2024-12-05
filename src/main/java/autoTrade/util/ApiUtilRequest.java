package autoTrade.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.MultiValueMap;

@Getter
public class ApiUtilRequest {

    private String host;
    private String path;
    private MultiValueMap<String, String> params;

    @Builder
    private ApiUtilRequest(String host, String path, MultiValueMap<String, String> params) {
        this.host = host;
        this.path = path;
        this.params = params;
    }
}
