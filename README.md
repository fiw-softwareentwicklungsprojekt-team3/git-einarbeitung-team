# Einarbeitung in Git

Test-Repo für Einarbeitungstask (Git-Workflow üben):

https://github.com/fiw-softwareentwicklungsprojekt-team3/projekt-backend/issues/15#

Zur Einarbeitung in Git sollen folgende Aufgaben von jedem Teammitglied ausgeführt werden:

- Alle haben user.name (Vor- und Nachname) und user.email (HTW-Adresse) auf allen genutzten Geräten gesetzt; GitHub-Handle und E-Mail sind an die Betreuenden geschickt
- Alle klonen das gemeinsame Test-Repo, erstellen einen Branch nach Namenskonvention (z. B. feature/E0.4-vorname) und committen eine eigene Datei (z. B. team/vorname.md)
- Commit-Nachrichten folgen der vereinbarten Konvention
- Jede Person hat mindestens einen Pull Request, der von einer anderen Person reviewt und gemergt wurde; der PR ist mit dem Ticket verknüpft (mit "Related to fiw-softwareentwicklungsprojekt-team3/projekt-backend#15"). Bitte anschließend auf keinen Fall das Ticket schließen!
- Mindestens einmal wird absichtlich ein Merge-Konflikt erzeugt und gemeinsam gelöst. Dazu können z.B. zwei von euch in ihren Branches gleichzeitig die README.md bearbeiten und ihren Namen an derselben Stelle eintragen. Wenn die erste Person ihren PR mergt, bekommt die zweite Person einen Merge-Konflikt und ihr könnt ihn gemeinsam auflösen.
- Alle haben mindestens einmal pull, commit, push und merge selbst ausgeführt

**Übung: Merge-Konflikt**

Tragt alle euren Namen auf dieser Zeile ein:

---

# Spring-Boot-Einarbeitung: Vorname-Endpoint

## Ziel der Übung

In dieser Übung soll ein minimales Spring-Boot-Projekt aufgebaut werden, das einen einfachen REST-Endpunkt bereitstellt. Dazu soll jede Teilnehmerin auf einem eigenen Branch eine eigene Controller-Klasse und einen dazugehörigen Test erstellen. Ziel ist es, die Grundlagen von Spring Boot, REST-Controllern und MockMvc-Testen selbst zu verstehen und lokal zu verifizieren.

---

## Ausgangslage

Das Projekt liegt auf dem `main`-Branch als minimales Spring-Boot-Grundgerüst vor. Es enthält nur die nötigen Abhängigkeiten:

- `spring-boot-starter-web`
- `spring-boot-starter-test`

Zusätzlich ist die Anwendung auf Port `8081` konfiguriert:

```properties
server.port=8081
```

Damit kann die Anwendung lokal gestartet werden, ohne das echte Backend des Hauptprojekts zu beeinflussen.

---

## Warum wird ein Controller erstellt?

Ein `Controller` ist die Stelle, an der HTTP-Anfragen verarbeitet werden. In Spring Boot werden Endpunkte mit Annotationen wie `@RestController`, `@RequestMapping` und `@GetMapping` definiert.

Ein eigener Controller wird benötigt, damit die Anwendung auf eine bestimmte URL reagieren kann, z. B.:

```text
GET /api/uebung/vorname/{name}
```

Der Controller nimmt den Wert aus der URL, z. B. `Patricia`, und gibt eine JSON-Antwort zurück.

Beispiel:

```java
package com.example.demo.uebung;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uebung")
public class PatriciaController {

    @GetMapping("/vorname/{name}")
    public Map<String, String> greet(@PathVariable String name) {
        return Map.of(
            "autorin", "Patricia",
            "nachricht", "Hallo " + name + ", mein Spring-Boot-Endpunkt läuft!"
        );
    }
}
```

Wichtig ist hier:

- `@RestController` macht die Klasse zu einem REST-Controller.
- `@RequestMapping("/api/uebung")` definiert den Basis-Pfad.
- `@GetMapping("/vorname/{name}")` legt die konkrete URL fest.
- `@PathVariable String name` liest den Wert aus der URI aus.

Das ist der Kern der Übung: Der Controller übersetzt die URL in eine Antwort, die als JSON an den Client zurückgegeben wird.

---

## Warum wird ein Test erstellt?

Ein Test ist nötig, um sicherzustellen, dass der Endpunkt wirklich funktioniert. Mit `MockMvc` kann man HTTP-Anfragen simulieren, ohne den Server manuell im Browser zu benutzen.

Der Test prüft, ob eine Anfrage auf den Endpoint erfolgreich verarbeitet wird und ob die Antwort den erwarteten Status und Inhalt liefert.

Beispiel:

```java
package com.example.demo.uebung;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatriciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnGreeting() throws Exception {
        mockMvc.perform(get("/api/uebung/vorname/Test"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.autorin").exists());
    }
}
```

Dieser Test prüft, dass:

- die URL erreichbar ist,
- der Endpoint mit `200 OK` antwortet,
- die JSON-Antwort ein Feld wie `autorin` enthält.

Damit wird getestet, was der Controller tatsächlich leisten soll.

---

## Ablauf für jede Person

### 1) Eigenen Branch erzeugen

```bash
git checkout -b feature/E0.2-vorname
```

### 2) Eigene Controller-Klasse erstellen

Jede Person erstellt eine eigene Klasse, z. B.:

```text
src/main/java/com/example/demo/uebung/VornameController.java
```

Der Controller enthält einen `GET`-Endpunkt mit dem Muster:

```text
GET /api/uebung/vorname/{name}
```

### 3) Passenden Test anlegen

Zusätzlich wird eine Testklasse erstellt, z. B.:

```text
src/test/java/com/example/demo/uebung/VornameControllerTest.java
```

Der Test nutzt `MockMvc`, um den Endpoint mit einer Anfrage zu prüfen und `200 OK` zu erwarten.

### 4) Anwendung lokal starten

```bash
./mvnw spring-boot:run
```

Wenn alles funktioniert, startet die Anwendung auf Port `8081`.

### 5) URL im Browser testen

```text
http://localhost:8081/api/uebung/vorname/Patricia
```

Erwartete Antwort:

```json
{"autorin":"Patricia","nachricht":"Hallo, ich bin Patricia, mein Spring-Boot-Endpunkt läuft!"}
```

### 6) Debugger-Check durchführen

- Anwendung über die IDE starten
- Breakpoint im eigenen Controller setzen
- URL im Browser aufrufen
- überprüfen, dass der Breakpoint ausgelöst wird
- damit bestätigen, dass die Anfrage wirklich den Controller erreicht

### 7) Tests ausführen

```bash
./mvnw test
```

Der Test muss grün durchlaufen.

---

## Pull Request und Ticket-Verknüpfung

Nach erfolgreichem Test und Debugger-Check:

- Änderungen committen
- auf den eigenen Branch pushen
- Pull Request auf `main` erstellen
- Pull Request mit dem Ticket verknüpfen
- Änderungen in `main` mergen





