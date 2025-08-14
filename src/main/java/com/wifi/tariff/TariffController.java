package com.wifi.tariff;

import com.wifi.tariff.model.Tariff;
import com.wifi.tariff.repository.TariffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
