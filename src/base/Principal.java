package base;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author bittor
 *
 */
public class Principal {
	
	
	private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

	private static Scanner teclado = new Scanner(System.in);
	
	private static boolean permiso = false;
	
	private static boolean compuertasVerificadas = false;
	
	
	public static void main(String[] args) {

		System.out.println(
				"Este programa lee el nivel de agua de una presa y permite abrir compuertas si tenemos permiso (el nivel es superior a 50) y las compuertas est�n verificadas.");

		int nivel = leerNivelAgua();

		mostrarMenu(nivel);
		
		Handler fileHandler = null;
		
		try {
			
			fileHandler = new FileHandler("./selecciones.log");
			
			LOGGER.addHandler(fileHandler);
			
			LOGGER.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			
		}catch(IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}

	private static void mostrarMenu(int nivel) {
		int opcion = 0;
		do {
			System.out.println();
			System.out.println("Nivel del agua: " + nivel);
			System.out.println();
			System.out.println("ACCIONES: ");
			System.out.println();
			System.out.println("1. Nueva lectura del nivel de agua.");
			System.out.println("2. Abrir compuertas. Requiere:");
			System.out.println("	3. Solicitar permiso. Estado: " + (permiso ? "CONCEDIDO" : "NO CONCEDIDO"));
			System.out.println("	4. Verificar compuertas. Estado: " + (compuertasVerificadas ? "VERIFICADAS" : "NO VERIFICADAS"));
			System.out.println("5. Salir");
			System.out.println();
			System.out.print("Introduce opci�n: ");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				LOGGER.log(Level.FINE, "Opcion 1");
				nivel = leerNivelAgua();
				permiso = false;
				compuertasVerificadas = false;
				break;	
			case 2:
				LOGGER.log(Level.FINE, "Opcion 2");
				if(abrirCompuertas()) {
					System.out.println();
					System.out.print("�Compuertas abiertas!");
				}else {
					System.out.println();
					System.out.print("No se cumplen las condiciones para abrir compuertas.");
				}
				break;
			case 3:
				LOGGER.log(Level.FINE, "Opcion 3");
				permiso = solicitarPermiso(nivel);
				if(!permiso) {
					System.out.println();
					System.out.print("El permiso solamente se concede si el nivel del agua es superior a 50.");
				}
				break;	
			case 4:
				LOGGER.log(Level.FINE, "Opcion 4");
				compuertasVerificadas = verificarCompuertas();
				if(compuertasVerificadas) {
					System.out.println();
					System.out.print("�Compuertas verificadas!");
				}
				break;
			default:
				break;
			}
		} while (opcion != 5);
	}

	static int leerNivelAgua() {
		permiso = false;
		return (int) Math.round(Math.random() * 100);
	}

	static boolean abrirCompuertas() {
		if (permiso && compuertasVerificadas) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Controla dar el permiso en base al nivel de agua
	 * 
	 * @author bittor
	 * @param nivel tipo int
	 * @return Devuelve true si el nivel es mayor de 50, sino devuelve false
	 */
	public static boolean solicitarPermiso(int nivel) {
		if (nivel > 50) {
			return true;
		}else {
			return false;
		}
	}
	static boolean verificarCompuertas() {		
		return true;
	}

}
