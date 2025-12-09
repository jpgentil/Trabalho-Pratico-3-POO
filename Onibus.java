/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author jpmun
 */
public class Onibus {
    private int assentosDisponiveis = 5;

    // PARTE II – BLOCO SINCRONIZADO (MUTEX)
    public void reservarAssentoSeguro(String agente) {
        synchronized (this) {
            if (assentosDisponiveis > 0) {
                System.out.println(agente + " verificou disponibilidade...");

                try { Thread.sleep(100); } catch (Exception e) {}

                assentosDisponiveis--;
                System.out.println(agente + " realizou a compra! Assentos restantes: " + assentosDisponiveis);
            } else {
                System.out.println(agente + " tentou comprar, mas esta lotado.");
            }
        }
    }

    // PARTE III – WAIT / NOTIFY
    public void reservarComEspera(String agente) {
        synchronized (this) {
            while (assentosDisponiveis == 0) {
                try {
                    System.out.println(agente + " encontrou lotado. Entrando em espera...");
                    wait();
                } catch (InterruptedException e) {}
            }

            assentosDisponiveis--;
            System.out.println(agente + " conseguiu comprar apos espera. Assentos restantes: " + assentosDisponiveis);
        }
    }

    public void cancelarPassagem() {
        synchronized (this) {
            assentosDisponiveis++;
            System.out.println("PASSAGEM CANCELADA. Assentos agora: " + assentosDisponiveis);
            notifyAll();
        }
    }
}
