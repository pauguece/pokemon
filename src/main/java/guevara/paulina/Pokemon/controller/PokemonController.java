package guevara.paulina.Pokemon.controller;

import guevara.paulina.Pokemon.model.Pokemon;
import guevara.paulina.Pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> obtenerTodos() {
        return ResponseEntity.ok(pokemonService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pokemon> crear(@RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.crear(pokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarCompleto(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.actualizarCompleto(id, pokemon));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.actualizarParcial(id, pokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pokemonService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}