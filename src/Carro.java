import javax.swing.*;
import java.awt.*;

public class Carro extends Thread{
    public JPanel panel = new JPanel();
    public int Xfinal = 800;
    public int Xinicio = 0;
    public int passo = 0;
    public enum origem {oeste, leste}
    public origem o;
    public Carro(origem o) {
        this.o = o;
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
                // IDA
                while (panel.getX() < Xfinal) {
                    currentX += passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(10);

                }

                // VOLTA
                while (panel.getX() > Xinicio) {
                    currentX -= passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(10);
                }

            }

            if(this.o == origem.leste) {
                // IDA
                while (panel.getX() > Xinicio) {
                    currentX -= passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(10);
                }

                // VOLTA
                while (panel.getX() < Xfinal) {
                    currentX += passo;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());

                    // Velocidade
                    Thread.sleep(10);

                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
