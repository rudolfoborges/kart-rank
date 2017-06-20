package br.com.gs.kartrank.model;

public class Piloto {

    private int numero;

    private String nome;

    public Piloto(int numero, String nome) {
	this.numero = numero;
	this.nome = nome;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + numero;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Piloto other = (Piloto) obj;
	if (numero != other.numero)
	    return false;
	return true;
    }

    public int getNumero() {
	return numero;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

}