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

}
