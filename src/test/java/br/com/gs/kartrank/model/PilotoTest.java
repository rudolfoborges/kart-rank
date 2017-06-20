package br.com.gs.kartrank.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PilotoTest {
	
	Piloto piloto;
	
	@Before
	public void inicializacao() {
		this.piloto = new Piloto(100, "Jo�o");
	}

	@Test
	public void getNumero() {
		assertEquals(100, this.piloto.getNumero());
	}
	
	@Test
	public void getNome() {
		assertEquals("Jo�o", this.piloto.getNome());
	}
	
}
