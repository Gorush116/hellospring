package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long orderId;
    private String currency;

    // Double, float(부동소수점) 사용시 계산에 오차가 생길 수 있음 -> 정확한 계산을 위한 BigDecimal 사용
    private BigDecimal foreignCurrencyAmount;   // 외국통화기준금액
    private BigDecimal exRate;                  // 환율
    private BigDecimal convertedAmount;         // 환산금액
    private LocalDateTime validUntil;           // 유효기간

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
