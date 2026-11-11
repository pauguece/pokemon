package guevara.paulina.Pokemon.service;

import guevara.paulina.Pokemon.model.Pokemon;
import guevara.paulina.Pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    // Obtener todos
    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    // Obtener por ID
    public Pokemon obtenerPorId(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokémon con id " + id + " no encontrado"));
    }

    // Crear
    public Pokemon crear(Pokemon pokemon) {
        pokemon.setId(null); // asegura que se genere un id nuevo
        return pokemonRepository.save(pokemon);
    }

    // Actualizar completo (PUT): reemplaza todos los atributos
    public Pokemon actualizarCompleto(Long id, Pokemon datos) {
        Pokemon existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setTipo(datos.getTipo());
        existente.setNivel(datos.getNivel());
        existente.setAtaque(datos.getAtaque());
        existente.setDefensa(datos.getDefensa());
        existente.setDescripcion(datos.getDescripcion());
        existente.setImagenUrl(datos.getImagenUrl());
        return pokemonRepository.save(existente);
    }

    public Pokemon actualizarParcial(Long id, Pokemon datos) {
        Pokemon existente = obtenerPorId(id);
        if (datos.getNombre() != null)      existente.setNombre(datos.getNombre());
        if (datos.getTipo() != null)        existente.setTipo(datos.getTipo());
        if (datos.getNivel() != null)       existente.setNivel(datos.getNivel());
        if (datos.getAtaque() != null)      existente.setAtaque(datos.getAtaque());
        if (datos.getDefensa() != null)     existente.setDefensa(datos.getDefensa());
        if (datos.getDescripcion() != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getImagenUrl() != null)   existente.setImagenUrl(datos.getImagenUrl());
        return pokemonRepository.save(existente);
    }

    // Eliminar
    public void eliminar(Long id) {
        Pokemon existente = obtenerPorId(id); // lanza 404 si no existe
        pokemonRepository.delete(existente);
    }
}
