package br.com.gs.kartrank.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.gs.kartrank.model.CorridaLog;
import br.com.gs.kartrank.model.Piloto;
import br.com.gs.kartrank.model.Resultado;
import br.com.gs.kartrank.service.CorridaService;

public class CorridaServiceTest {

    static CorridaService corridaService;

    @BeforeClass
    public static void inicializacao() {
	corridaService = new CorridaService();
    }

    @Test
    public void carregarListaDePilotos() {
	List<Piloto> resultado = corridaService.carregarListaDePilotos();
	int quantidadeTotalDePilotos = 6;
	
	assertEquals(quantidadeTotalDePilotos, resultado.size());
    }

    @Test
    public void carregarLogsPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	List<CorridaLog> resultado = corridaService.carregarLogsPorPiloto(piloto);
	int numeroFMassa = 38;

	assertEquals(numeroFMassa, resultado.get(0).getPiloto().getNumero());
    }

    @Test
    public void carregarLogsPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	List<CorridaLog> resultado = corridaService.carregarLogsPorPiloto(piloto);

	assertEquals(0, resultado.size());
    }

    @Test(expected = NullPointerException.class)
    public void carregarLogsPorPilotoNulo() {
	corridaService.carregarLogsPorPiloto(null);
    }

    @Test
    public void voltasCompletadasPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	int resultado = corridaService.voltasCompletadasPorPiloto(piloto);
	int voltasCompletadasFMassa = 4;

	assertEquals(voltasCompletadasFMassa, resultado);
    }

    @Test
    public void voltasCompletadasPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	int resultado = corridaService.voltasCompletadasPorPiloto(piloto);

	assertEquals(0, resultado);
    }

    @Test(expected = NullPointerException.class)
    public void voltasCompletadasPorPilotoNulo() {
	corridaService.voltasCompletadasPorPiloto(null);
    }

    @Test
    public void tempoTotalDeProvaPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	LocalTime resultado = corridaService.tempoTotalDeProvaPorPiloto(piloto);
	LocalTime resultadoEsperado = LocalTime.parse("00:04:11.578");

	assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void tempoTotalDeProvaPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	LocalTime resultado = corridaService.tempoTotalDeProvaPorPiloto(piloto);
	LocalTime resultadoEsperado = LocalTime.parse("00:00");

	assertEquals(resultado, resultadoEsperado);
    }

    @Test(expected = NullPointerException.class)
    public void tempoTotalDeProvaPorPilotoNulo() {
	corridaService.tempoTotalDeProvaPorPiloto(null);
    }

    @Test
    public void menorTempoDeVoltaPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	LocalTime resultado = corridaService.menorTempoDeVoltaPorPiloto(piloto);
	LocalTime resultadoEsperado = LocalTime.parse("00:01:02.769");

	assertEquals(resultado, resultadoEsperado);
    }

    @Test
    public void menorTempoDeVoltaPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	LocalTime resultado = corridaService.menorTempoDeVoltaPorPiloto(piloto);
	LocalTime resultadoEsperado = LocalTime.parse("00:00");

	assertEquals(resultado, resultadoEsperado);
    }

    @Test(expected = NullPointerException.class)
    public void menorTempoDeVoltaPorPilotoNulo() {
	corridaService.menorTempoDeVoltaPorPiloto(null);
    }

    @Test
    public void melhorVoltaPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	int resultado = corridaService.melhorVoltaPorPiloto(piloto);
	int melhorVoltaFMassa = 3;

	assertEquals(melhorVoltaFMassa, resultado);
    }

    @Test
    public void melhorVoltaPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	int resultado = corridaService.melhorVoltaPorPiloto(piloto);

	assertEquals(0, resultado);
    }

    @Test(expected = NullPointerException.class)
    public void melhorVoltaPorPilotoNulo() {
	corridaService.melhorVoltaPorPiloto(null);
    }

    @Test
    public void velocidadeMediaPorPiloto() {
	Piloto piloto = new Piloto(38, "F.MASSA");
	double resultado = corridaService.velocidadeMediaPorPiloto(piloto);
	double velocidadeMediaFMassa = 44.24575;

	assertEquals(velocidadeMediaFMassa, resultado, velocidadeMediaFMassa);
    }

    @Test
    public void velocidadeMediaPorPilotoDesconhecido() {
	Piloto piloto = new Piloto(1000, "Piloto Desconhecido");
	double resultado = corridaService.velocidadeMediaPorPiloto(piloto);

	assertEquals(0.0, resultado, 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void velocidadeMediaPorPilotoNulo() {
	corridaService.velocidadeMediaPorPiloto(null);
    }

    @Test
    public void melhorVoltaDaCorrida() {
	CorridaLog resultado = corridaService.melhorVoltaDaCorrida();
	LocalTime tempoEsperado = LocalTime.parse("00:01:02.769");
	Piloto pilotoEsperado = new Piloto(38, "F.MASSA");
	
	assertEquals(pilotoEsperado, resultado.getPiloto());
	assertEquals(tempoEsperado, resultado.getTempoVolta());
    }

    @Test
    public void carregarDadosDaCorrida() throws IOException {
	List<CorridaLog> logs = corridaService.carregarDadosDaCorrida(CorridaService.ARQUIVO_LOG);
	int numeroEsperadoDeLinhasDoLog = 23;

	assertEquals(numeroEsperadoDeLinhasDoLog, logs.size());
    }

    @Test
    public void carregarDadosDaCorridaCaminhoDoArquivoErraodo() {
	List<CorridaLog> resultado = corridaService.carregarDadosDaCorrida("/caminho/arquivo/falso/log.txt");

	assertEquals(0, resultado.size());
    }

    @Test(expected = NullPointerException.class)
    public void carregarDadosDaCorridaCaminhoDoArquivoNulo() {
	corridaService.carregarDadosDaCorrida(null);
    }

    @Test
    public void gerarResultado() {
	List<Resultado> resultado = corridaService.gerarResultado();
	int tamanhoEsperadoDaLista = 6;
	int numeroEsperadoDoVencedor = 38;
	int numeroEsperadoDoUltimoLugar = 11;

	assertEquals(tamanhoEsperadoDaLista, resultado.size());
	assertEquals(numeroEsperadoDoVencedor, resultado.stream().findFirst().get().getPiloto().getNumero());
	assertEquals(numeroEsperadoDoUltimoLugar, resultado.get(5).getPiloto().getNumero());
    }

}
