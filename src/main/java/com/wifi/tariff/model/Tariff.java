package com.wifi.tariff.model;

public record Tariff(
        String id,
        String name,
        Features features,
        Pricing pricing
) { }
