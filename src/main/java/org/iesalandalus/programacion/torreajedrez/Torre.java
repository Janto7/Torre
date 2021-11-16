package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

/**
 /**
 * @author José Antonio Del Rey Martínez
 *
 */
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
		if (color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		setColor(color);
		if (color == Color.BLANCO) {
			posicion = new Posicion(1, 'h');
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(8, 'h');
		}

	}

	// Constructor que acepta como parámetros el color y la columna inicial
	public Torre(Color color, char columnaInicial) {
		if (color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}

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

	/** Método mover */
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		// Los pasos han de ser positivos
		if (pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}
		try {
			// Sumamos los pasos.
			switch (direccion) {
			case ARRIBA:
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila() + pasos, posicion.getColumna()));
				} else {
					setPosicion(new Posicion(posicion.getFila() - pasos, posicion.getColumna()));
				}
				break;
			case ABAJO:
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila() - pasos, posicion.getColumna()));
				} else {
					setPosicion(new Posicion(posicion.getFila() + pasos, posicion.getColumna()));
				}
				break;
			case IZQUIERDA:
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
				}
				break;
			case DERECHA:
				if (color == Color.BLANCO) {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
				}
				break;
			default:
			}
		} catch (IllegalArgumentException e) {
			throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
		}
	}

	public void enrocar(Direccion direccion) throws OperationNotSupportedException {
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}

		switch (direccion) {
		case ENROQUE_CORTO:

			/*
			 * Solo tenemos dos tipos de enroques, cortos y largos, solo se pueden dar ambos
			 * estando en una columna determinada, h y a, solo debemos controlar que nos
			 * encontramos en esas columnas y filas para hacer un enroque, controlando si el
			 * color es negro o blanco y posicionarlos en la nueva columna tras realizar el
			 * enroque.
			 */
			if (color == Color.NEGRO) {
				if ((posicion.getFila() == 8 && posicion.getColumna() == 'h')) {
					// Los posicionamos en la nueva columna
					setPosicion(new Posicion(posicion.getFila(), 'f'));
				} else {
					// Si la operación solicitada no es compatible lo imprimos en consola.
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			} else {
				if ((posicion.getFila() == 1 && posicion.getColumna() == 'h')) {
					setPosicion(new Posicion(posicion.getFila(), 'f'));
				} else {
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			}

			break;
		// Mismo procedimiento para enroque largo, solo que partimos de la columna a
		// hacia la columna d.
		case ENROQUE_LARGO:

			if (color == Color.NEGRO) {
				if (posicion.getFila() == 8 && posicion.getColumna() == 'a') {
					setPosicion(new Posicion(posicion.getFila(), 'd'));
				} else {
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			} else {
				if (posicion.getFila() == 1 && posicion.getColumna() == 'a') {
					setPosicion(new Posicion(posicion.getFila(), 'd'));
				} else {
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			}

			break;
		default:

		}
	}

	// Métodos hashCode y equals generados de forma automatica por IDE
	@Override
	public int hashCode() {
		return Objects.hash(color, posicion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torre other = (Torre) obj;
		return color == other.color && Objects.equals(posicion, other.posicion);
	}

	// Método toString generado de forma automatica y modificado para pasar la
	// cadena del test.
	@Override
	public String toString() {
		return posicion + ", " + "color=" + color;
	}

}
