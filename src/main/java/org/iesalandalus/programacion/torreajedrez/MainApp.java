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

	}

	// Metodo Mostrar Torre
	private static void mostrarTorre() {
		if (torre != null && !salir) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(torre);
			System.out.println("-------------------------------------------------------------------------");
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


}
