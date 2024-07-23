package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.api.SimpleApiExcutor;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return runApiForExRate(url);

    }

    // 변경되지 않는 부분 시작
    private static BigDecimal runApiForExRate(String url) {
        // URI 준비, 예외처리 작업 코드
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // API 실행, 서버로부터 받은 응답 가져옴
        String response;
        try {
            response = new SimpleApiExcutor().execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // JSON 문자열 파싱, 필요한 환율정보 추출
        try {
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    // 변경되지 않는 부분 끝

    // 변경되는 부분 시작
    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
    // 변경되는 부분 끝
}
