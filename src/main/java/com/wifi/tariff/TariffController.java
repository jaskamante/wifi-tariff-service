package com.wifi.tariff;

import com.wifi.tariff.model.Tariff;
import com.wifi.tariff.repository.TariffRepository;
import com.wifi.tariff.validation.ValidationGroups;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
@Validated
public class TariffController {
    private final TariffRepository tariffRepository;

    //Constructor injection
    public TariffController(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @GetMapping("/{id}")
    public Tariff getTariff( @PathVariable
                                 @Min(value = 1, message = "Tariff ID must be a positive number")
                                 Integer id) {
        return tariffRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found!"));
    }

    @GetMapping
    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tariff> createTariff(@Validated(ValidationGroups.Create.class) @RequestBody Tariff tariff) {
        Tariff savedTariff = tariffRepository.save(tariff);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTariff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tariff> updateTariff(
            @PathVariable @Min(value = 1, message = "Tariff ID must be a positive number") Integer id,
            @Validated(ValidationGroups.Update.class) @RequestBody Tariff tariff) {

        if (!tariffRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found");
        }

        tariff.setId(id);
        Tariff savedTariff = tariffRepository.save(tariff);
        return ResponseEntity.ok(savedTariff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tariff> deleteTariff(@PathVariable
            @Min(value = 1, message = "Tariff ID must be a positive number")
            Integer id) {
        return tariffRepository.findById(id)
                .map(tariff -> {
                    tariffRepository.deleteById(id);
                    return ResponseEntity.ok(tariff);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found"));
    }
}
