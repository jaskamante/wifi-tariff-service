package com.wifi.tariff;

import com.wifi.tariff.model.Tariff;
import com.wifi.tariff.repository.TariffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    @PutMapping("/{id}")
    public ResponseEntity<Tariff> updateTariff(@PathVariable Integer id, @RequestBody Tariff tariff) {
        Optional<Tariff> updatedTariff = tariffRepository.updateById(id, tariff);

        if (updatedTariff.isPresent()) {
            return ResponseEntity.ok(updatedTariff.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tariff> deleteTariff(@PathVariable Integer id) {
        Optional<Tariff> deletedTariff = tariffRepository.deleteById(id);

        if (deletedTariff.isPresent()) {
            return ResponseEntity.ok(deletedTariff.get());
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff not found!");
        }
    }
}
