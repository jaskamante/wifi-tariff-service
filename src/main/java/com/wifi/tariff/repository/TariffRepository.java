package com.wifi.tariff.repository;

import com.wifi.tariff.model.BillingType;
import com.wifi.tariff.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
