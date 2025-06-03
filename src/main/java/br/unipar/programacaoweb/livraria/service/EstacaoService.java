package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Estacao;
import br.unipar.programacaoweb.livraria.model.Leitura;
import br.unipar.programacaoweb.livraria.model.Sensor;
import br.unipar.programacaoweb.livraria.repository.EstacaoRepository;
import br.unipar.programacaoweb.livraria.repository.LeituraRepository;
import br.unipar.programacaoweb.livraria.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class EstacaoService {

    private final EstacaoRepository estacaoRepository;
    private LeituraRepository leituraRepository;

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
        estacao.setNome("ST1");
        estacao.setStatus("ATIVA");
        estacao.setData_instalacao("01/02/2000");
        estacao.setLatitude("0");
        estacao.setLongitude("0");
        estacaoRepository.save(estacao);
    }

    public void verificarEstaçãoInativa() {
        List<Estacao> estacao = listarTodos();
        for (Estacao estacao1 : estacao) {
            //verifca se está offline
            if (estacao1.getStatus() == null) {
                //mandar mensagem de aviso
                System.out.println("Estação " + estacao1.getId() + " inativa há 2 minutos ISSO É INACEITÁVEL!: Aviso encaminhado para" +
                        "Suporte@EcoTrack.com.br");
            }
        }
    }

    public void inativarEstacoesAleatoriamente() {
        List<Estacao> estacoes = listarTodos();
        Random random = new Random();

        for (Estacao estacao : estacoes) {
            boolean inativar = random.nextBoolean(); // true ou false aleatoriamente

            if (inativar) {
                estacao.setStatus("INATIVA");
                estacaoRepository.save(estacao);
                System.out.println("Estação " + estacao.getId() + " foi inativada.");
            }
        }
    }


    @Autowired
    public EstacaoService(EstacaoRepository estacaoRepository, LeituraRepository leituraRepository) {
        this.estacaoRepository = estacaoRepository;
        this.leituraRepository = leituraRepository;
    }

    public Double mediaLeituraPorEstacao(Long estacaoId) {
        return leituraRepository.mediaLeiturasPorEstacao(estacaoId);
    }



}
