package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Estacao;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.repository.EstacaoRepository;
import br.unipar.programacaoweb.livraria.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepositoryRepository) {
        this.sensorRepository = sensorRepositoryRepository;
    }

    public Sensor salvar(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public void excluir(Long id) {
        sensorRepository.deleteById(id);
    }

    public Sensor buscarPorId(Long id) {
        return sensorRepository.findById(id).orElse(null);
    }

    public List<Sensor> buscarPorTipo(String tipo) {
        return sensorRepository.findByTipoContainingIgnoreCase((tipo));
    }

    public List<Sensor> listarTodos() {
        return sensorRepository.findAll();
    }


//    public void atualizarLeituraAleatoriamente() {
//        List<Sensor> sensor = listarTodos();
//        for (Sensor sensor1 : sensor) {
//            int leituraAleatorio = (int) (Math.random() * 1000) + 1; // Gera um número aleatório entre 1 e 1000
//            sensor1.setLeitura(leituraAleatorio);
//            System.out.println("Sensor: " + sensor1.getId() + " - leitura atualizado para: " + leituraAleatorio);
//            salvar(sensor1);
//        }
//    }

    @Autowired
    private EstacaoRepository estacaoRepository;

    public void criarNovSensorAleatoria() {

        List<Estacao> estacao = estacaoRepository.findAll();

        if (estacao.isEmpty()) {
            throw new RuntimeException("Nenhum sensor cadastrado encontrado.");
        }

        // Seleciona um sensor aleatoriamente
        Estacao estacaoAleatorio = estacao.get((int) (Math.random() * estacao.size()));

        List<String> tipos = Arrays.asList("TEMPERATURA", "UMIDADE", "CO2", "RUÍDO");
        Collections.shuffle(tipos); // embaralha a lista
        Sensor sensor = new Sensor();
        sensor.setTipo(tipos.get(0));
        sensor.setStatus("ONLINE");
        sensor.setEstacao(estacaoAleatorio);

        sensorRepository.save(sensor);
    }

    public void verificarSensoresOffline() {
        List<Sensor> sensor = listarTodos();
        for (Sensor sensor1 : sensor) {
            //verifca se está offline
            if (sensor1.getStatus() == null) {
               //mandar mensagem de aviso
                System.out.println("Sensor: " + sensor1.getId() + " está offline.");
            }
        }
    }



}
