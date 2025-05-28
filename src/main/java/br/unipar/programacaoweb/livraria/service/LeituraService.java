package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Leitura;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.repository.LeituraRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LeituraService {

    private final LeituraRepository leituraRepository;

    public LeituraService(LeituraRepository leituraRepository) {
        this.leituraRepository = leituraRepository;
    }

    public Leitura salvar(Leitura leitura) {
        return leituraRepository.save(leitura);
    }

    public void excluir(Long id) {
        leituraRepository.deleteById(id);
    }

    public Leitura buscarPorId(Long id) {
        return leituraRepository.findById(id).orElse(null);
    }

//    public List<Leitura> buscarPorTipo(String tipo) {
//        return leituraRepository.findByTipoContainingIgnoreCase((tipo));
//    }

    public List<Leitura> listarTodos() {
        return leituraRepository.findAll();
    }


    public void atualizarLeituraAleatoriamente() {
        List<Leitura> leitura = listarTodos();
        for (Leitura leitura1 : leitura) {
            int leituraAleatorio = (int) (Math.random() * 500) + 1; // Gera um número aleatório entre 1 e 1000
            leitura1.setLeitura(leituraAleatorio);
            System.out.println("Leitura: " + leitura1.getId() + " - atualizado para: " + leituraAleatorio);
            salvar(leitura1);
        }
    }

    public void criarNovLeituraAleatoria() {
        Leitura leitura = new Leitura();
        leitura.setLeitura((int) (Math.random() * 500) + 1);
        leitura.setTimestamp(new Timestamp(System.currentTimeMillis()));

        leituraRepository.save(leitura);
    }

//    public void verificarSensoresOffline() {
//        List<Sensor> sensor = listarTodos();
//        for (Sensor sensor1 : sensor) {
//            //verifca se está offline
//            if (sensor1.getStatus() == null) {
//               //mandar mensagem de aviso
//                System.out.println("Sensor: " + sensor1.getId() + " está offline.");
//            }
//        }
//    }
}
