package br.com.gs.kartrank.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import br.com.gs.kartrank.model.CorridaLog;
import br.com.gs.kartrank.model.Piloto;
import br.com.gs.kartrank.model.Resultado;

@Component
public class CorridaService {

    public static final String ARQUIVO_LOG = "src/main/resources/log.txt";
    
    private List<CorridaLog> logs = new ArrayList<CorridaLog>();
    

    public CorridaService() {
	logs = carregarDadosDaCorrida(ARQUIVO_LOG);
    }

    public List<Piloto> carregarListaDePilotos() {
	return logs.stream()
		.map(x -> x.getPiloto()).distinct()
		.collect(Collectors.toList());
    }

    public List<CorridaLog> carregarLogsPorPiloto(Piloto piloto) {
	return logs.stream()
		.filter(x -> piloto.getNumero() == x.getPiloto().getNumero())
		.collect(Collectors.toList());
    }

    public int voltasCompletadasPorPiloto(Piloto piloto) {
	return carregarLogsPorPiloto(piloto).stream()
		.mapToInt(x -> x.getNumeroVolta())
		.max()
		.orElse(0);
    }

    public LocalTime tempoTotalDeProvaPorPiloto(Piloto piloto) {
	double tempoTotalDeProva = carregarLogsPorPiloto(piloto).stream()
		.mapToDouble(x -> x.getTempoVolta().toNanoOfDay())
		.sum();

	return LocalTime.ofNanoOfDay((long) tempoTotalDeProva);
    }

    public LocalTime menorTempoDeVoltaPorPiloto(Piloto piloto) {
	long menorTempoDeVoltaLong = carregarLogsPorPiloto(piloto).stream()
		.mapToLong(x -> x.getTempoVolta().toNanoOfDay())
		.min()
		.orElse(0L);

	return LocalTime.ofNanoOfDay(menorTempoDeVoltaLong);
    }

    public int melhorVoltaPorPiloto(Piloto piloto) {
	return carregarLogsPorPiloto(piloto).stream()
		.filter(x -> x.getTempoVolta().toNanoOfDay() == menorTempoDeVoltaPorPiloto(piloto).toNanoOfDay())
		.mapToInt(x -> x.getNumeroVolta())
		.findFirst() 
		.orElse(0);
		
    }

    public double velocidadeMediaPorPiloto(Piloto piloto) {
	return carregarLogsPorPiloto(piloto).stream()
		.mapToDouble(x -> x.getVelocidadeMediaVolta())
		.average()
		.orElse(0.0);
    }
    
    public CorridaLog melhorVoltaDaCorrida() {
	if(logs.isEmpty()) {
	   return null; 
	}
	
	 return logs.stream()
		 .sorted((p1, p2) -> p1.getTempoVolta().compareTo(p2.getTempoVolta()))
		 .findFirst()
		 .orElse(null);
    }

    private LocalTime subtrairDatas(LocalTime lc1, LocalTime lc2) {
	return lc1.minusNanos(lc2.toNanoOfDay());
    }

    @SuppressWarnings("resource")
    public List<CorridaLog> carregarDadosDaCorrida(String filePath) {
	
	List<CorridaLog> logs = new ArrayList<CorridaLog>();
	
	Path caminho = Paths.get(filePath);

	Stream<String> file = null;

	try {
	    file = Files.lines(caminho);
	} catch (IOException e) {
	    return logs;
	}

	file.forEach(l -> {
	    logs.add(new CorridaLog.Builder(l).build());
	});
	
	return logs;

    }
    
    public LocalTime horaChegadaDaUltimaVoltaPorPiloto(Piloto piloto) {
	
	long horaChegadaNano =  carregarLogsPorPiloto(piloto).stream()
		.mapToLong(x -> x.getHora().toNanoOfDay())
		.max().orElse(0L);
	
	return LocalTime.ofNanoOfDay(horaChegadaNano);
	
    }
    
    private List<Resultado> processarDados() {

   	List<Resultado> list = new ArrayList<Resultado>();

   	carregarListaDePilotos()
   		.forEach(piloto -> {
           	    Resultado resultado = new Resultado();
           	    resultado.setPiloto(piloto);
           	    resultado.setNumeroDaMelhorVolta(melhorVoltaPorPiloto(piloto));
           	    resultado.setQuantidadeVoltasCompletadas(voltasCompletadasPorPiloto(piloto));
           	    resultado.setTempoTotal(tempoTotalDeProvaPorPiloto(piloto));
           	    resultado.setVelocidadeMedia(velocidadeMediaPorPiloto(piloto));
           	    resultado.setHoraChegada(horaChegadaDaUltimaVoltaPorPiloto(piloto));
           	    list.add(resultado);
   		});

   	return list;

       }

       public List<Resultado> gerarResultado() {

   	List<Resultado> resultado = processarDados().stream()
   		.sorted((p1, p2) -> p1.getTempoTotal().compareTo(p2.getTempoTotal()))
   		.collect(Collectors.toList());

   	resultado.forEach(p -> {
   	    p.setDiferencaDeTempoEntreVencedor(
   		    subtrairDatas(p.getHoraChegada(), resultado.stream()
   			    .findFirst().get().getHoraChegada()));
   	});

   	return resultado;

       }

}
