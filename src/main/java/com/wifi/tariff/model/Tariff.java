package com.wifi.tariff.model;

import com.wifi.tariff.validation.ValidationGroups;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Null;


@Entity
@Table(name = "tariffs")
@Data
@NoArgsConstructor
public class Tariff {
    @Null(message = "ID must not be provided when creating a new tariff", groups = ValidationGroups.Create.class)
    @NotNull(message = "ID is required when updating a tariff", groups = ValidationGroups.Update.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tariff name is required")
    @Size(min = 3, max = 100, message = "Tariff name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Download speed is required")
    @Min(value = 1, message = "Download speed must be at least 1 Mbps")
    @Column(name = "download_mbps", nullable = false)
    private Integer downloadMbps;

    @NotNull(message = "Upload speed is required")
    @Min(value = 1, message = "Upload speed must be at least 1 Mbps")
    @Column(name = "upload_mbps", nullable = false)
    private Integer uploadMbps;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours;

    @NotNull(message = "Maximum devices is required")
    @Min(value = 1, message = "Must allow at least 1 device")
    @Column(name = "max_devices", nullable = false)
    private Integer maxDevices;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price cannot be negative")
    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @NotNull(message = "Currency is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @NotNull(message = "Billing type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "billing_type", nullable = false)
    private BillingType billingType;
}