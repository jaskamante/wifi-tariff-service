package com.wifi.tariff.model;

public record Tariff(
        Integer id,
        String name,
        Features features,
        Pricing pricing
) { }
