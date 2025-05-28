package br.unipar.programacaoweb.livraria.controller;

import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sensor")
public class SensorController {

    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Operation(summary = "Listar todos os sensores")
    @GetMapping("/listar")
    public ResponseEntity<List<Sensor>> listarSensor() {
        List<Sensor> sensor = sensorService.listarTodos();
        if(sensor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sensor);
    }

    @Operation(summary = "Buscar sensor por ID")
    @Parameter(name = "id", description = "ID do sensor a ser buscado")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Sensor> buscarSensorPorId(@PathVariable Long id) {
        Sensor sensor = sensorService.buscarPorId(id);
        if(sensor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<List<Sensor>> buscarSensorPorTitulo(@PathVariable String tipo) {
        List<Sensor> sensor = sensorService.buscarPorTipo(tipo);
        if(sensor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Sensor> salvarSensor(@Valid @RequestBody Sensor sensor) {
        Sensor sensorSalvo = sensorService.salvar(sensor);

        return ResponseEntity.status(HttpStatus.CREATED).body(sensorSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirSensor(@PathVariable Long id) {
        Sensor sensor = sensorService.buscarPorId(id);
        if(sensor == null) {
            return ResponseEntity.notFound().build();
        }
        sensorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Sensor> editarSensor(@PathVariable Long id,
                                                @RequestBody Sensor sensor) {
        Sensor sensorAtual = sensorService.buscarPorId(id);
        if(sensorAtual == null) {
            return ResponseEntity.notFound().build();
        }

        sensorAtual.setTipo(sensor.getTipo());
        sensorAtual.setData_instalacao(sensor.getData_instalacao());
        sensorAtual.setStatus(sensor.getStatus());

        return ResponseEntity.ok(sensorService.salvar(sensorAtual));
    }

}
