package com.tea.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class TeaOrder {
    private Long id;
    private Long memberId;
    private BigDecimal totalAmount=BigDecimal.valueOf(0);
    private String status;
    private LocalDateTime createdAt;

    public TeaOrder(Long memberId, Long id, BigDecimal totalAmount, String status, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public TeaOrder() {

    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return String.format("Order[%d] member=%s ¥%s %s",
                id, memberId, totalAmount.toPlainString(), status);
    }
}
