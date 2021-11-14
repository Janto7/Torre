package org.iesalandalus.programacion.torreajedrez;

public class Torre {

	// La visibilidad adecuada es privada porque son atributos de clase
	private Color color;
	private Posicion posicion;

	// Constructor por Defecto
	public Torre() {
		color = Color.NEGRO;
		posicion = new Posicion(8, 'h');
	}
	//Constructor que acepta como parámetro el color
	public Torre(Color color) {
		setColor(color);
		if (color == Color.BLANCO) {
			posicion = new Posicion(1, 'h');
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(8, 'h');
		}
	}

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

}
