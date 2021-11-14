package org.iesalandalus.programacion.torreajedrez;

public class Posicion {
	// Atributos privados como nos indica el diagrama
	private int fila;
	private char columna;

	// Constructor Posición
	public Posicion(int fila, char columna) {

		setFila(fila);
		setColumna(columna);
	}

	// Constructor copia con excepción adecuada
	public Posicion(Posicion posicion) {
		if (posicion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		}
		this.fila = posicion.getFila();
		this.columna = posicion.getColumna();
	}

	// Métodos get y set
	public int getFila() {
		return fila;
	}

	private void setFila(int fila) {
		if (fila < 1 || fila > 8) {
			throw new IllegalArgumentException("ERROR: Fila no válida.");

		} else {
			this.fila = fila;
		}
	}

	public char getColumna() {
		return columna;
	}

	private void setColumna(char columna) {
		if (columna < 'a' || columna > 'h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");

		} else {
			this.columna = columna;

		}
	}

}
