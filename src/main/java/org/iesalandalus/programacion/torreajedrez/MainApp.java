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

}
