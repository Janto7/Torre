package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

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

	// Método mover.
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		// La dirección no puede ser nula
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		// Los pasos han de ser positivos
		if (pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}

		switch (direccion) {
		case ARRIBA:
			/*
			 * Desplazamos la torre por el tablero dependiendo de su color, capturando no se
			 * salga del tablero.
			 */
			try {

				if (color == Color.BLANCO) {
					// Si es torre blanca los pasar a dar seran positivos, solo se desplaza la fila,
					// la columna sigue siendo la misma.
					setPosicion(new Posicion(posicion.getFila() + pasos, (char) (posicion.getColumna())));
				} else {
					// Si es torre negra los pasos a dar seran negativos
					setPosicion(new Posicion(posicion.getFila() - pasos, (char) (posicion.getColumna())));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		case ABAJO:
			try {
				if (color == Color.BLANCO) {
					// Si es torre blanca los pasos a dar seran negativos, solo se desplaza la fila,
					// La columna sigue siendo la misma.
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
					// Ambos colores se mueven en el mismo sentido, las filas siguen siendo la misma
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() - pasos)));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		case DERECHA:
			try {
				if (color == Color.BLANCO) {
					// Ambos colores se meuven en el mismo sentido, las filas siguen siendo las
					// mismas.
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));
				} else {
					setPosicion(new Posicion(posicion.getFila(), (char) (posicion.getColumna() + pasos)));

				}
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
			}
			break;
		default:
			break;
		}

	}

	public void enrocar(Direccion direccion) throws OperationNotSupportedException {
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}

		int fila;
		char columna;

		switch (direccion) {
		case ENROQUE_CORTO:
			fila = posicion.getFila();
			columna = posicion.getColumna();
			/*
			 * Solo tenemos dos tipos de enroques, cortos y largos, solo se pueden dar ambos
			 * estando en una columna determinada, h y a, solo debemos controlar que nos
			 * encontramos en esas columnas y filas para hacer un enroque, controlando si el
			 * color es negro o blanco y posicionarlos en la nueva columna tras realizar el
			 * enroque.
			 */
			if (color == Color.NEGRO) {
				if (fila == 8 && columna == 'h') {
					// Los posicionamos en la nueva columna
					posicion = new Posicion(8, 'f');
				} else {
					// Si la operación solicitada no es compatible lo imprimos en consola.
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			} else {
				if (fila == 1 && columna == 'h') {
					posicion = new Posicion(1, 'f');
				} else {
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			}

			break;
		// Mismo procedimiento para enroque largo, solo que partimos de la columna a
		// hacia la columna d.
		case ENROQUE_LARGO:
			fila = posicion.getFila();
			columna = posicion.getColumna();
			if (color == Color.NEGRO) {
				if (fila == 8 && columna == 'a') {
					posicion = new Posicion(8, 'd');
				} else {
					throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
				}
			} else {
				if (fila == 1 && columna == 'a') {
					posicion = new Posicion(1, 'd');
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
