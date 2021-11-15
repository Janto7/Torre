package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

public class Torre {

	// La visibilidad adecuada es privada porque son atributos de clase
	private Color color;
	private Posicion posicion;

	// Constructor por Defecto
	public Torre() {
		color = Color.NEGRO;
		posicion = new Posicion(8, 'h');
	}

	// Constructor que acepta como parámetro el color
	public Torre(Color color) {
		setColor(color);
		if (color == Color.BLANCO) {
			posicion = new Posicion(1, 'h');
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(8, 'h');
		}
	}

	// Constructor que acepta como parámetros el color y la columna inicial
	public Torre(Color color, char columnaInicial) {
		setColor(color);

		if (columnaInicial != 'a' && columnaInicial != 'h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}
		if (color == Color.BLANCO) {
			posicion = new Posicion(1, columnaInicial);
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(8, columnaInicial);
		}
	}

	// Métodos set y get
	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		if (color == null) {
			throw new IllegalArgumentException("ERROR: No se puede asignar un color nulo.");
		}
		this.color = color;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	private void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	// Método mover.
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		// La dirección no puede ser nulo
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		// Los pasos han de ser positivos
		if (pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}

		switch (direccion) {
		case ARRIBA:
			// Desplazamos la torre por el tablero dependiendo de su color, capturando no se
			// salga del tablero. 
			try {

				if (color == Color.BLANCO) {

					setPosicion(new Posicion(posicion.getFila() + pasos, (char) (posicion.getColumna())));
				} else {
					setPosicion(new Posicion(posicion.getFila() - pasos, (char) (posicion.getColumna())));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		case ABAJO:
			try {
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila() - pasos, (char) (posicion.getColumna())));
				} else {
					setPosicion(new Posicion(posicion.getFila() + pasos, (char) (posicion.getColumna())));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		case IZQUIERDA:

			try {
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		case DERECHA:
			try {
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		default:
			break;
		}

	}
}
