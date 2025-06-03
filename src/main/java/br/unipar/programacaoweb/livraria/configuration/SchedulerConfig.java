package br.unipar.programacaoweb.livraria.configuration;

import br.unipar.programacaoweb.livraria.service.EstacaoService;
import br.unipar.programacaoweb.livraria.service.LeituraService;
import br.unipar.programacaoweb.livraria.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    private final LeituraService leituraService;
    @Autowired
    private EstacaoService estacaoService;

    public SchedulerConfig(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    @Autowired
    private SensorService sensorService;


//    @Scheduled(fixedRate = 5000)
//    public void scheduleTask() {
//        System.out.println("Tarefa executada agora!");
//        estacaoService.criarNovEstacaoAleatoria();
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void scheduleTask() {
//        System.out.println("Tarefa executada agora!");
//        sensorService.criarNovSensorAleatoria();
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void scheduleTask() {
//        System.out.println("Tarefa executada agora!");
//        leituraService.criarNovLeituraAleatoria();
//    }
////
//    @Scheduled(fixedRate = 10000)
//    public void inativarEstacoesAleatoriamente() {
//        estacaoService.inativarEstacoesAleatoriamente();
//        //inativer uma estacao aleatoriamente
//    }
//
//    @Scheduled(fixedRate = 20000)
//    public void verificarSensoresInativos() {
//        // Verifica se algum sensor está inativo
//        sensorService.verificarSensoresOffline();
//    }
//
//    @Scheduled(fixedRate = 100000)
//    public void verificarEstaçãoInativa() {
//        // Verifica se algum sensor está inativo
//        estacaoService.verificarEstaçãoInativa();
//    }
}
