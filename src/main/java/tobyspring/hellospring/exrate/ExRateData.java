package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

// Json한테 다른 요소 들어있어도 무시
@JsonIgnoreProperties(ignoreUnknown=true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
