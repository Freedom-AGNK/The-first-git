package com.tea.enity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class TeaOrder {
    private Long id;
    private Long memberId;
    private BigDecimal totalAmount=BigDecimal.valueOf(0);
    private String status;
    private LocalDateTime createdAt;
    public String toString() {
        return String.format("Order[%d] member=%s ¥%s %s",
                id, memberId, totalAmount.toPlainString(), status);
    }
}
