package autoTrade.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public static void parseJson(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // 원하는 데이터 경로를 설정 (구조를 분석한 후 수정 필요)
            JsonNode itemsNode = rootNode.get("country");
            if (itemsNode != null && itemsNode.isArray()) {
                String exchangeRate = String.valueOf(itemsNode.get(1).get("value").asText());
                double rate = Double.parseDouble(exchangeRate.replaceAll(",", ""));
                System.out.println(rate);
            } else {
                System.out.println("환율 데이터를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
