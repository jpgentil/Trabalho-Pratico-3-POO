/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author jpmun
 */
public class Cancelamento extends Thread {
    private Onibus onibus;

    public Cancelamento(Onibus onibus) {
        this.onibus = onibus;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {}

        onibus.cancelarPassagem();
    }
}

