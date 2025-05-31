package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Estacao;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.repository.EstacaoRepository;
import br.unipar.programacaoweb.livraria.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EstacaoService {

    private final EstacaoRepository estacaoRepository;

    public EstacaoService(EstacaoRepository estacaoRepository) {
        this.estacaoRepository = estacaoRepository;
    }

    public Estacao salvar(Estacao estacao) {
        return estacaoRepository.save(estacao);
    }

    public void excluir(Long id) {
        estacaoRepository.deleteById(id);
    }

    public Estacao buscarPorId(Long id) {
        return estacaoRepository.findById(id).orElse(null);
    }

    public List<Estacao> buscarPorNome(String nome) { return estacaoRepository.findByNomeContainingIgnoreCase((nome));}

    public List<Estacao> listarTodos() {
        return estacaoRepository.findAll();
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

    public void criarNovEstacaoAleatoria() {

        Estacao estacao = new Estacao();
        estacao.setId((long) 0);
        List<String> nome = Arrays.asList("ST1", "ST2", "ST3", "ST4", "ST5");
        Collections.shuffle(nome); // embaralha a lista
        estacao.setNome(nome.get(0));
        List<String> status = Arrays.asList("ATIVA", "INATIVA", "MANUTENÇÃO");
        Collections.shuffle(status); // embaralha a lista
        estacao.setStatus(status.get(0));
        estacao.setLatitude("0");
        estacao.setLongitude("0");
        estacaoRepository.save(estacao);
    }

    public void verificarEstaçãoOffline() {
        List<Estacao> estacao = listarTodos();
        for (Estacao estacao1 : estacao) {
            //verifca se está offline
            if (estacao1.getStatus() == "MANUTENÇÃO") {
               //mandar mensagem de aviso
                System.out.println("Estação: " + estacao1.getId() + " está em " + estacao1.getStatus());
            }
        }
    }



}
