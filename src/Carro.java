import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class Carro extends Thread{

    public JPanel panel = new JPanel();
    public int Xfinal = 800;
    public int Xinicio = 0;
    public int passo = 0;
    public int tempoTravessia;
    public int tempoPermanencia;
    public enum origem {oeste, leste}
    public origem o;
    public Semaphore semaEsquerda;
    public Semaphore semaDireita;
    public Semaphore waitLiberar;
    public static int ponteCount;
    public static int oesteCount = 0;
    public static int lesteCount = 0;
    public Carro(origem o,
                 int tempoTravessia,
                 int tempoPermanencia,
                 Semaphore semaEsquerda,
                 Semaphore semaDireita,
                 Semaphore waitLiberar
                 ) {
        this.waitLiberar = waitLiberar;
        this.semaEsquerda = semaEsquerda;
        this.semaDireita = semaDireita;
        this.o = o;
        this.tempoTravessia = (int) Math.round((float) (tempoTravessia * 1000) /80) ;
        this.tempoPermanencia = tempoPermanencia;
        if(this.o == origem.oeste) {
            passo = 10;
            this.panel.setBackground(Color.YELLOW);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(10, 230, 30, 15);

        } else if(this.o == origem.leste){
            passo = 10;
            this.panel.setBackground(Color.BLUE);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(750, 230, 30, 15);
        }
    }

    public void run(){
        int currentX = panel.getX();

        try {

            if(this.o == origem.oeste) {

                semaEsquerda.acquire();
                ponteCount++;

                // IDA
                while (panel.getX() < Xfinal) {
                    currentX += passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    // Velocidade
                    Thread.sleep(tempoTravessia);

                }
                ponteCount--;
                lesteCount++;
                System.out.println("Carros na ponte: "+ponteCount);
                System.out.println("Carros no leste: "+lesteCount);
                Thread.sleep(tempoPermanencia);
                if(ponteCount == 0)
                    for(int i = 0; i < lesteCount; i++)
                        semaDireita.release();

                for(int i = lesteCount; i > 0; i--) {
                    semaDireita.acquire();
                    sleep(100);
                    ponteCount++;
                    // VOLTA
                    while (panel.getX() > Xinicio) {
                        currentX -= passo;
                        int finalCurrentX = currentX;
                        SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                        //System.out.println(panel.getX());
                        // Velocidade
                        Thread.sleep(tempoTravessia);
                    }
                    ponteCount--;
                    lesteCount--;
                    System.out.println("Carros no leste: "+lesteCount);
                }


            }



            if(this.o == origem.leste) {
                semaDireita.acquire();
                // IDA
                while (panel.getX() > Xinicio) {
                    currentX -= passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(tempoTravessia);
                }
                semaDireita.release();
                Thread.sleep(tempoPermanencia);
                semaEsquerda.acquire();
                // VOLTA
                while (panel.getX() < Xfinal) {
                    currentX += passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(tempoTravessia);

                }
                semaEsquerda.release();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
