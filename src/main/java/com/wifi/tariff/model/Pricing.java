package com.wifi.tariff.model;

import java.math.BigDecimal;

public record Pricing(
        BigDecimal basePrice,
        Currency currency,
        BillingType billingType
) { }
