package com.tea.entity;
import java.time.LocalDateTime;
public class Member {
    private Long id;
    private String name;
    private String phone;
    private Integer points;
    private LocalDateTime createdAt;

    public Member(Long id, String name, String phone, Integer points, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.points = points;
        this.createdAt = createdAt;
    }
    public Member(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Member(){}
    @Override
    public String toString() {
        return String.format("会员[%d] %s（%s）积分 %d",
                id, name, phone, points);
    }
}
