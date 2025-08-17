package com.wifi.tariff.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "tariffs")
@Data
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "download_mbps", nullable = false)
    private Integer downloadMbps;

    @Column(name = "upload_mbps", nullable = false)
    private Integer uploadMbps;

    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours;

    @Column(name = "max_devices", nullable = false)
    private Integer maxDevices;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "billing_type", nullable = false)
    private BillingType billingType;
}