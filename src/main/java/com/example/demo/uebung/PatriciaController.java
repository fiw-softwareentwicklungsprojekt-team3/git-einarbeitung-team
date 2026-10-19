package com.example.demo.uebung;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uebung")
public class PatriciaController {

    // Dieser Endpunkt verarbeitet eine GET-Anfrage unter /api/uebung/vorname/{name}.
    // Der Name aus der URL wird als Pfadvariable übernommen und in einer JSON-Antwort zurückgegeben.
    @GetMapping("/vorname/{name}")
    public Map<String, String> greet(@PathVariable String name) {
        return Map.of(
            "autorin", "Patricia",
            "nachricht", "Hallo, ich bin " + name + ", mein Spring-Boot-Endpunkt läuft!"
        );
    }
}
