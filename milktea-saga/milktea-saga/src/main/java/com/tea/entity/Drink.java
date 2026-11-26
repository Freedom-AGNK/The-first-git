package com.tea.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class Drink {
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer stock;
    private Integer isActive;
    private LocalDateTime createdAt;
    @Override
    public String toString(){
        return String.format("[%d] %-12s ¥%-6s 库存 %d",
                id, name, price.toPlainString(), stock);
    }
}
