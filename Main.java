/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package newpackage;
import java.util.concurrent.Semaphore;
/**
 *
 * @author jpmun
*/
public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("\nbloco sincronizado\n");
        Onibus onibus2 = new Onibus();
        for (int i = 1; i <= 7; i++)
            new AgenteVenda("Agente " + i, onibus2, 2).start();

        Thread.sleep(2500);

        System.out.println("\nwait/notify\n");
        Onibus onibus3 = new Onibus();
        for (int i = 1; i <= 7; i++)
            new AgenteVenda("Agente " + i, onibus3, 3).start();

        new Cancelamento(onibus3).start();

        Thread.sleep(5000);

        System.out.println("\nsemaforos\n");
        Onibus onibus4 = new Onibus();
        Semaphore semaforo = new Semaphore(3);

        for (int i = 1; i <= 7; i++)
            new AgenteVenda("Agente " + i, onibus4, 4, semaforo).start();
    }
}


