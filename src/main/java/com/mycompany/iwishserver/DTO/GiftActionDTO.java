package com.mycompany.iwishserver.DTO;

import java.math.BigDecimal;

public class GiftActionDTO {
    private int giftId;
    private int ownerUserId;
    private String giftName;
    private BigDecimal price;
    private String status;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getGiftId() {
        return giftId;
    }
    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }
    public int getOwnerUserId() {
        return ownerUserId;
    }
    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }
    public String getGiftName() {
        return giftName;
    }
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
