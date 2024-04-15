import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Carro extends Thread {

    public JPanel panel = new JPanel();
    public int Xfinal = 800;
    public int Xinicio = 0;
    public int passo = 0;
    public int tempoTravessia;
    public int tempoPermanencia;



    public String origem;
    public static Semaphore semEsq = new Semaphore(0); // Esperando ou atrav na esquerda
    public static Semaphore semDir = new Semaphore(0); // Esperando ou atrav na direita
    public static Semaphore mutex = new Semaphore(1);

    public enum ponte {vazia, esquerda, direita}

    // Sentido da ponte
    public static int numCarrosPonte;
    public static Carro.ponte statusPonte;

    public Carro(String origem,
                 int tempoTravessia,
                 int tempoPermanencia
    ) {

        this.origem = origem;
        this.tempoTravessia = (int) Math.round((float) (tempoTravessia * 1000) / 80);
        this.tempoPermanencia = tempoPermanencia;
        statusPonte = Carro.ponte.vazia;
        numCarrosPonte = 0;

        if (Objects.equals(this.origem, "oeste")) {
            passo = 10;
            this.panel.setBackground(Color.YELLOW);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(20, 230, 30, 15);


        } else if (Objects.equals(this.origem, "leste")) {
            passo = 10;
            this.panel.setBackground(Color.BLUE);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(750, 230, 30, 15);
            System.out.println("Carro leste criado");
        }
    }

    public void run() {
        int currentX = panel.getX();
        System.out.println("run");

        while (true) {
            try {

                if (Objects.equals(this.origem, "oeste")) {

                    if ((statusPonte == Carro.ponte.vazia) || (statusPonte == Carro.ponte.direita)) {
                        semEsq.release();
                        semEsq.acquire();
                        mutex.acquire();
                        numCarrosPonte++;
                        statusPonte = Carro.ponte.direita;
                        mutex.release();


                        // IDA
                        System.out.println("Atravessando");
                        while (panel.getX() < Xfinal) {
                            currentX += passo;
                            int finalCurrentX = currentX;
                            SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                            // Velocidade
                            Thread.sleep(tempoTravessia);

                        }

                        mutex.acquire();
                        numCarrosPonte--;
                        if (numCarrosPonte == 0)
                            statusPonte = Carro.ponte.vazia;
                        mutex.release();

                        this.origem = "leste";
                    }
                } else if (Objects.equals(this.origem, "leste")) {

                    if ((statusPonte == Carro.ponte.vazia) || (statusPonte == Carro.ponte.esquerda)) {
                        semDir.release();
                        semDir.acquire();

                        mutex.acquire();
                        numCarrosPonte++;
                        statusPonte = Carro.ponte.esquerda;
                        mutex.release();


                        // VOLTA
                        while (panel.getX() > Xinicio) {
                            currentX -= passo;
                            int finalCurrentX = currentX;
                            SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                            //System.out.println(panel.getX());
                            // Velocidade
                            Thread.sleep(tempoTravessia);
                        }

                        mutex.acquire();
                        numCarrosPonte--;
                        if (numCarrosPonte == 0)
                            statusPonte = Carro.ponte.vazia;
                        mutex.release();

                        this.origem = "oeste";

                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
