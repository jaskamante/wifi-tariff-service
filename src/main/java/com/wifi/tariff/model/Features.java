package com.wifi.tariff.model;

public record Features(
        Integer downloadMbps,
        Integer uploadMbps,
        Integer durationHours,
        Integer maxDevices
) { }
