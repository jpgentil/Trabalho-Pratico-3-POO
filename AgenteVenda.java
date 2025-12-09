/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;
import java.util.concurrent.Semaphore;
/**
 *
 * @author jpmun
 */

public class AgenteVenda extends Thread {
    private Onibus onibus;
    private String nome;
    private int modo;
    private Semaphore semaforo;

    public AgenteVenda(String nome, Onibus onibus, int modo) {
        this.nome = nome;
        this.onibus = onibus;
        this.modo = modo;
    }

    public AgenteVenda(String nome, Onibus onibus, int modo, Semaphore semaforo) {
        this(nome, onibus, modo);
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            switch (modo) {
                case 2: // synchronized
                    onibus.reservarAssentoSeguro(nome);
                    break;

                case 3: // wait/notify
                    onibus.reservarComEspera(nome);
                    break;

                case 4: // semáforo
                    semaforo.acquire();
                    System.out.println(nome + " entrou no sistema (permissões restantes: " + semaforo.availablePermits() + ")");
                    onibus.reservarAssentoSeguro(nome);
                    semaforo.release();
                    System.out.println(nome + " saiu do sistema");
                    break;
            }
        } catch (Exception e) {}
    }
}
