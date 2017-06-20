package br.com.gs.kartrank.model;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

public class CorridaLogTest {

    @Test
    public void build() {
	String linhaDoArquivo = "23:49:08.277 	  038 – F.MASSA		  	  1		1:02.852 			44,275";
	LocalTime horaChegada = LocalTime.parse("23:49:08.277");
	String nomePiloto = "F.MASSA";
	int numeroPiloto = 38;
	int numeroDaVolta = 1;
	LocalTime tempoDaVolta = LocalTime.parse("00:01:02.852");
	double velocidadeMediaDaVolta = Double.parseDouble("44,275".replace(",", "."));
	
	CorridaLog resultado = new CorridaLog.Builder(linhaDoArquivo).build();
	
	assertEquals(horaChegada, resultado.getHora());
	assertEquals(numeroPiloto, resultado.getPiloto().getNumero());
	assertEquals(nomePiloto, resultado.getPiloto().getNome());
	assertEquals(numeroDaVolta, resultado.getNumeroVolta());
	assertEquals(tempoDaVolta, resultado.getTempoVolta());
	assertEquals(velocidadeMediaDaVolta, resultado.getVelocidadeMediaVolta(), velocidadeMediaDaVolta);
	
    }
    
    @Test
    public void buildComArquivoNaoFormatado() {
	String linhaDoArquivo = "LINHA NAO FORMATADA";
	CorridaLog resultado = new CorridaLog.Builder(linhaDoArquivo).build();
	
	assertEquals(resultado.getHora(), null);
    }
    
    @Test(expected = NullPointerException.class)
    public void buildComArquivoNulo() {
	new CorridaLog.Builder(null).build();
    }

}
