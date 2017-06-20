package br.com.gs.kartrank.model;

import java.time.LocalTime;

public class CorridaLog {

    private Piloto piloto;

    private int numeroVolta;

    private LocalTime tempoVolta;

    private LocalTime hora;

    private double velocidadeMediaVolta;

    public CorridaLog(Builder builder) {
	this.piloto = builder.piloto;
	this.numeroVolta = builder.numeroVolta;
	this.tempoVolta = builder.tempoVolta;
	this.hora = builder.hora;
	this.velocidadeMediaVolta = builder.velocidadeMediaVolta;
    }

    public Piloto getPiloto() {
	return piloto;
    }

    public void setPiloto(Piloto piloto) {
	this.piloto = piloto;
    }

    public int getNumeroVolta() {
	return numeroVolta;
    }

    public void setNumeroVolta(int numeroVolta) {
	this.numeroVolta = numeroVolta;
    }

    public LocalTime getTempoVolta() {
	return tempoVolta;
    }

    public void setTempoVolta(LocalTime tempoVolta) {
	this.tempoVolta = tempoVolta;
    }

    public LocalTime getHora() {
	return hora;
    }

    public void setHora(LocalTime hora) {
	this.hora = hora;
    }

    public double getVelocidadeMediaVolta() {
	return velocidadeMediaVolta;
    }

    public void setVelocidadeMediaVolta(double velocidadeMediaVolta) {
	this.velocidadeMediaVolta = velocidadeMediaVolta;
    }

    public static class Builder {

	private Piloto piloto;

	private int numeroVolta;

	private LocalTime tempoVolta;

	private LocalTime hora;

	private double velocidadeMediaVolta;

	public Builder(String linha) {

	    if (linha.isEmpty()) {
		return;
	    }

	    try {
		String[] obj = linha.replace("\u2013", " ").replaceAll("\\s+", " ").split(" ");

		this.hora = LocalTime.parse(obj[0]);
		this.piloto = new Piloto(Integer.parseInt(obj[1]), obj[2]);
		this.numeroVolta = Integer.parseInt(obj[3]);
		this.tempoVolta = LocalTime.parse("00:0" + obj[4]);
		this.velocidadeMediaVolta = Double.parseDouble(obj[5].replace(",", "."));
	    } catch (Exception e) {
		return;
	    }

	}

	public CorridaLog build() {
	    return new CorridaLog(this);
	}

    }

}
