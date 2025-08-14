package com.wifi.tariff.model;

import java.math.BigDecimal;

public record Pricing(
        BigDecimal basePrice,
        String currency,
        BillingType billingType
) { }
