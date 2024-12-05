package autoTrade.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class ExchangeRateUtil {

    public double getData(ApiUtilRequest request) {

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        // WebClient를 생성합니다.
        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).build();

        // WebClient를 사용하여 동기적으로 데이터를 요청하고, 바로 parseJson 함수를 호출합니다.
        String responseBody = webClient.get()
                .uri(builder -> builder
                        .scheme("https")
                        .host(request.getHost())
                        .path(request.getPath())
                        .queryParams(request.getParams())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기적으로 결과를 얻음
        return parseJson(responseBody);
    }

    private double parseJson(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 원하는 데이터 경로를 설정 (구조를 분석한 후 수정 필요)
            JsonNode itemsNode = rootNode.get("country");
            if (itemsNode != null && itemsNode.isArray()) {
                String exchangeRate = String.valueOf(itemsNode.get(1).get("value").asText());
                return Double.parseDouble(exchangeRate.replaceAll(",", ""));
            } else {
                System.out.println("환율 데이터를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
