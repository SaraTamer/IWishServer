package com.mycompany.iwishserver.DTO;

import java.math.BigDecimal;

public class AddContributionDTO {
    private int giftId;
    private BigDecimal percentage;

    public int getGiftId() {
        return giftId;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }
}
