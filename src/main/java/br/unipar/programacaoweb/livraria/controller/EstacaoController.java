package br.unipar.programacaoweb.livraria.controller;

import br.unipar.programacaoweb.livraria.model.Estacao;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.service.EstacaoService;
import br.unipar.programacaoweb.livraria.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/estacao")
public class EstacaoController {

    private EstacaoService estacaoService;

    public EstacaoController(EstacaoService estacaoService) {
        this.estacaoService = estacaoService;
    }

    @Operation(summary = "Listar todas as estacoes")
    @GetMapping("/listar")
    public ResponseEntity<List<Estacao>> listarSensor() {
        List<Estacao> estacao = estacaoService.listarTodos();
        if(estacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estacao);
    }

    @Operation(summary = "Buscar estacao por ID")
    @Parameter(name = "id", description = "ID da estacao a ser buscado")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Estacao> buscarEstacaoPorId(@PathVariable Long id) {
        Estacao estacao = estacaoService.buscarPorId(id);
        if(estacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacao);
    }

    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<List<Estacao>> buscarEstacaoPorNome(@PathVariable String nome) {
        List<Estacao> estacao = estacaoService.buscarPorNome(nome);
        if(estacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacao);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Estacao> salvarEsacao(@Valid @RequestBody Estacao estacao) {
        Estacao estacaoSalvo = estacaoService.salvar(estacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(estacaoSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirEstacao(@PathVariable Long id) {
        Estacao estacao = estacaoService.buscarPorId(id);
        if(estacao == null) {
            return ResponseEntity.notFound().build();
        }
        estacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Estacao> editarSensor(@PathVariable Long id,
                                                @RequestBody Estacao estacao) {
        Estacao estacaoAtual = estacaoService.buscarPorId(id);
        if(estacaoAtual == null) {
            return ResponseEntity.notFound().build();
        }

        estacaoAtual.setNome(estacao.getNome());
        estacaoAtual.setStatus(estacao.getStatus());
        estacaoAtual.setLatitude(estacao.getLatitude());
        estacaoAtual.setLongitude(estacao.getLongitude());
        estacaoAtual.setData_instalacao(estacao.getData_instalacao());

        return ResponseEntity.ok(estacaoService.salvar(estacaoAtual));
    }

    @GetMapping("/media-leituras/{id}")
    public ResponseEntity<Double> mediaLeituraPorEstacao(@PathVariable Long id) {
        Double media = estacaoService.mediaLeituraPorEstacao(id);
        if (media == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(media);
    }

}
