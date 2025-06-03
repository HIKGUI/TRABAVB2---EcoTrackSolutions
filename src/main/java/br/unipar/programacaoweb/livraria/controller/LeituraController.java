package br.unipar.programacaoweb.livraria.controller;

import br.unipar.programacaoweb.livraria.model.Autor;
import br.unipar.programacaoweb.livraria.model.Leitura;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.service.LeituraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leitura")
public class LeituraController {

    private LeituraService leituraService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    @Operation(summary = "Listar todos os sensores")
    @GetMapping("/listar")
    public ResponseEntity<List<Leitura>> listarLeitura() {
        List<Leitura> leitura = leituraService.listarTodos();
        if(leitura.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(leitura);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Leitura> salvarLeitura(@Valid @RequestBody Leitura leitura) {
        Leitura leituraSalvo = leituraService.salvar(leitura);

        return ResponseEntity.status(HttpStatus.CREATED).body(leituraSalvo);
    }

    @Operation(summary = "Buscar leitura por ID")
    @Parameter(name = "id", description = "ID da leitura a ser buscado")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Leitura> buscarLeituraPorId(@PathVariable Long id) {
        Leitura leitura = leituraService.buscarPorId(id);
        if(leitura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(leitura);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirLeitura(@PathVariable Long id) {
        Leitura leitura = leituraService.buscarPorId(id);
        if(leitura == null) {
            return ResponseEntity.notFound().build();
        }
        leituraService.excluir(id);

        return ResponseEntity.noContent().build();
    }





}
