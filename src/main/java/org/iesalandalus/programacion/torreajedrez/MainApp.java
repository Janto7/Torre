package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

/**
 * @author José Antonio Del Rey Martínez
 *
 */

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	private static Torre torre;
	private static boolean salir;

	public static void main(String[] args) {
		System.out.println("PROGAMACIÓN");
		System.out.println("José Antonio Del Rey Martínez");
		System.out.println("TAREA ONLINE 3");
		System.out.println("------------------------------------------------------------------------");
		do {
			mostrarMenu();
			ejecutarOpcion(elegirOpcion());
			mostrarTorre();
		} while (!salir);
		System.out.println("Hasta luego !!!!");

	}

	// Metodo Mostrar Torre
	private static void mostrarTorre() {
		if (torre != null && !salir) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println(torre);
			System.out.println("------------------------------------------------------------------------");
		}
	}

	// Mostrar Menú
	private static void mostrarMenu() {
		System.out.println("1. Crear torre por defecto");
		System.out.println("2. Crear torre de un color");
		System.out.println("3. Crear torre de un color en una columna inicial dada");
		System.out.println("4. Mover torre");
		System.out.println("5. Salir");
	}

	// Elegir Opción
	private static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("Introduce una opción: ");
			opcion = Entrada.entero();

		} while (opcion < 1 || opcion > 5);
		return opcion;
	}

	// Elegir Color
	private static Color elegirColor() {
		int opcionColor;
		do {
			System.out.print("Introduce 1 si quieres color blanco o 2 si quieres color negro: ");
			opcionColor = Entrada.entero();
		} while (opcionColor < 1 && opcionColor > 2);

		if (opcionColor == 1) {
			return Color.BLANCO;
		} else {
			return Color.NEGRO;
		}
	}

	// Elegir columna inicial
	private static char elegirColumnaInicial() {
		char opcionColumnaInicial;
		do {
			System.out.print("Introduce la columna inicial (a o h): ");
			opcionColumnaInicial = Entrada.caracter();
		} while (opcionColumnaInicial != 'a' && opcionColumnaInicial != 'h');
		return opcionColumnaInicial;
	}

	// Elegir Mostrar menú direcciones
	private static void mostrarMenuDirecciones() {
		System.out.println("1. Arriba");
		System.out.println("2. Abajo");
		System.out.println("3. Izquierda");
		System.out.println("4. Derecha");
		System.out.println("5. Enroque Corto");
		System.out.println("6. Enroque Largo");
	}

	// Elegir dirección
	private static Direccion elegirDireccion() {
		int opcionDireccion;
		do {
			System.out.print("Introduce una opción: ");
			opcionDireccion = Entrada.entero();

		} while (opcionDireccion < 1 || opcionDireccion > 6);

		Direccion direccion = null;
		switch (opcionDireccion) {
		case 1:
			direccion = Direccion.ARRIBA;
			break;
		case 2:
			direccion = Direccion.ABAJO;
			break;
		case 3:
			direccion = Direccion.IZQUIERDA;
			break;
		case 4:
			direccion = Direccion.DERECHA;
			break;
		case 5:
			direccion = Direccion.ENROQUE_CORTO;
			break;
		case 6:
			direccion = Direccion.ENROQUE_LARGO;
			break;
		default:

		}
		return direccion;
	}

	// Crear torre por defecto
	private static void crearTorreDefecto() {
		torre = new Torre();
	}

	// Crear torre de un color
	private static void crearTorreColor() {
		Color color = elegirColor();
		torre = new Torre(color);
	}

	// Crear torre color columna
	private static void crearTorreColorColumna() {
		Color color = elegirColor();
		char columnaInicial = elegirColumnaInicial();
		torre = new Torre(color, columnaInicial);
	}

	// Mover
	private static void mover() {
		if (torre == null) {
			System.out.println("Debes crear una torre antes de moverla.");

			return;
		}
		mostrarMenuDirecciones();
		Direccion direccion = elegirDireccion();
		// Solos nos preguntara los pasos a dar cuando no usemos enroques
		if (direccion != Direccion.ENROQUE_CORTO && direccion != Direccion.ENROQUE_LARGO) {
			System.out.print("¿Cuántos pasos quieres que dé la torre?: ");
			int pasos = Entrada.entero();

			try {
				torre.mover(direccion, pasos);
			} catch (OperationNotSupportedException | NullPointerException e) {
				System.out.println(e.getMessage());
			}
			// si no vamos a mover, enrocamos
		} else {
			try {
				torre.enrocar(direccion);
				if (direccion == Direccion.ENROQUE_CORTO ) {
				System.out.print("El enroque corto ha sido un éxito, buena jugada ;)\n");
				} else {
					System.out.print("El enroque largo ha sido un éxito, buena jugada ;)\n");
				}
				
			} catch (OperationNotSupportedException | NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// Ejecutar opción
	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 1:

			crearTorreDefecto();
			break;
		case 2:

			crearTorreColor();
			break;
		case 3:

			crearTorreColorColumna();
			break;
		case 4:

			mover();
			break;
		case 5:

			salir = true;
			break;
		default:
			break;
		}
	}

}
