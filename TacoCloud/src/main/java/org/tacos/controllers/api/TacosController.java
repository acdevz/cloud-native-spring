package org.tacos.controllers.api;

import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tacos.models.Taco;
import org.tacos.repositories.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
public class TacosController {
    private final TacoRepository tacoRepository;

    public TacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public Iterable<Taco> allTacos(
            @RequestParam(required = false) Integer p
    ){
        Pageable pageable = PageRequest.of(p != null ? p : 0, 5, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(
            @PathVariable Long id
    ){
        Optional<Taco> tacos = tacoRepository.findById(id);
        return tacos
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(
            @RequestBody Taco taco
    ){
        tacoRepository.save(taco);
        return taco;
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTaco(
            @RequestBody Taco taco,
            @PathVariable Long id
    ){
        taco.setId(id);
        tacoRepository.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(
            @PathVariable Long id){
        tacoRepository.deleteById(id);
    }

}
