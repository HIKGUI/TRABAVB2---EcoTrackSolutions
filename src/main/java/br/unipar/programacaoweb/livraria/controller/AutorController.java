package br.unipar.programacaoweb.livraria.controller;

import br.unipar.programacaoweb.livraria.model.Autor;
import br.unipar.programacaoweb.livraria.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autors = autorService.listarTodos();
        if(autors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(autors);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if(autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorService.salvar(autor);

        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if(autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable Long id,
                                                @RequestBody Autor autor) {
        Autor autorEditado = autorService.editar(id, autor);
        if(autorEditado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autorEditado);
    }

}
