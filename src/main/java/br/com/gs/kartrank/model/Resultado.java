package br.com.gs.kartrank.model;

import java.time.LocalTime;

public class Resultado {

    private Piloto piloto;

    private int quantidadeVoltasCompletadas;

    private LocalTime tempoTotal;

    private int numeroDaMelhorVolta;

    private double velocidadeMedia;

    private LocalTime diferencaDeTempoEntreVencedor;

    public Resultado() {
    }

    public Piloto getPiloto() {
	return piloto;
    }

    public void setPiloto(Piloto piloto) {
	this.piloto = piloto;
    }

    public int getQuantidadeVoltasCompletadas() {
	return quantidadeVoltasCompletadas;
    }

    public void setQuantidadeVoltasCompletadas(int quantidadeVoltasCompletadas) {
	this.quantidadeVoltasCompletadas = quantidadeVoltasCompletadas;
    }

    public LocalTime getTempoTotal() {
	return tempoTotal;
    }

    public void setTempoTotal(LocalTime tempoTotal) {
	this.tempoTotal = tempoTotal;
    }

    public int getNumeroDaMelhorVolta() {
	return numeroDaMelhorVolta;
    }

    public void setNumeroDaMelhorVolta(int numeroDaMelhorVolta) {
	this.numeroDaMelhorVolta = numeroDaMelhorVolta;
    }

    public double getVelocidadeMedia() {
	return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
	this.velocidadeMedia = velocidadeMedia;
    }

    public LocalTime getDiferencaDeTempoEntreVencedor() {
	return diferencaDeTempoEntreVencedor;
    }

    public void setDiferencaDeTempoEntreVencedor(LocalTime diferencaDeTempoEntreVencedor) {
	this.diferencaDeTempoEntreVencedor = diferencaDeTempoEntreVencedor;
    }

}
