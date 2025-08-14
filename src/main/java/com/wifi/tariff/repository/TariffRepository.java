package com.wifi.tariff.repository;

import com.wifi.tariff.model.BillingType;
import com.wifi.tariff.model.Features;
import com.wifi.tariff.model.Pricing;
import com.wifi.tariff.model.Tariff;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class TariffRepository {

    public TariffRepository() {
        // Create some test data
        Features basicFeatures = new Features(25, 10, 2, 2);
        Pricing basicPricing = new Pricing(new BigDecimal("5.99"), "USD", BillingType.ONE_TIME);
        Tariff basicTariff = new Tariff(1, "Basic 2-Hour WiFi", basicFeatures, basicPricing);

        tariffs.put(1, basicTariff);
        nextId = 2; // Next available ID
    }
    private final Map<Integer, Tariff> tariffs = new HashMap<>();
    Integer nextId = 1;

    //add/update a tariff
    public Tariff save(Tariff tariff) {
        if (tariff.id() == null) {
            // Create new tariff with auto-generated ID
            Tariff newTariff = new Tariff(nextId++, tariff.name(), tariff.features(), tariff.pricing());
            tariffs.put(newTariff.id(), newTariff);
            return newTariff;
        } else {
            // Update existing tariff
            tariffs.put(tariff.id(), tariff);
            return tariff;
        }
    }

    //get tariff by ID
    public Optional<Tariff> findById(Integer id) {
        return Optional.ofNullable(tariffs.get(id));
    }

    //get all tariffs
    public List<Tariff> findAll() {
        return new ArrayList<>(tariffs.values());
    }
    //remove a tariff
    public void deleteById(Integer id) {
        tariffs.remove(id);
    }
}
