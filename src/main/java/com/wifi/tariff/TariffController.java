package com.wifi.tariff;

import com.wifi.tariff.model.Tariff;
import com.wifi.tariff.repository.TariffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
public class TariffController {
    private final TariffRepository tariffRepository;

    //Constructor injection
    public TariffController(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @GetMapping("/{id}")
    public Tariff getTariff(@PathVariable Integer id) {
        return tariffRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found!"));
    }

    @GetMapping
    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Tariff> createTariff(@RequestBody Tariff tariff) {
        Tariff savedTariff = tariffRepository.save(tariff);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTariff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTariff(@PathVariable Integer id) {
        tariffRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
