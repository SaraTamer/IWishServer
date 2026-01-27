package com.mycompany.iwishserver.DTO;

import java.math.BigDecimal;

public class UpdateContributionDTO {
    private int contributionId;
    private BigDecimal newPercentage;

    public int getContributionId() {
        return contributionId;
    }

    public BigDecimal getNewPercentage() {
        return newPercentage;
    }
}
