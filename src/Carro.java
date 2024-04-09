import javax.swing.*;
import java.awt.*;

public class Carro extends Thread{
    public JPanel panel = new JPanel();
    public int Xfinal;
    public int Xinicio;
    public int speed = 0;
    public enum origem {oeste, leste}
    public origem o;
    public Carro(origem o) {
        this.o = o;
        if(this.o == origem.oeste) {
            speed = 10;
            Xfinal = 720;
            this.panel.setBackground(Color.YELLOW);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(10, 230, 30, 15);

        } else if(this.o == origem.leste){
            speed = 10;
            Xinicio = 10;
            this.panel.setBackground(Color.BLUE);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(750, 230, 30, 15);
        }
    }

    public void run(){
        int currentX = panel.getX();

        try {
            if(this.o == origem.oeste)
                while (panel.getX() < Xfinal){
                    currentX += speed;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());
                    Thread.sleep(250);
                }
            if(this.o == origem.leste)
                while (panel.getX() > Xinicio){
                    currentX -= speed;
                    int finalCurrentX = currentX;
                    SwingUtilities.invokeLater(() -> panel.setLocation(finalCurrentX, panel.getY()));
                    System.out.println(panel.getX());
                    Thread.sleep(250);
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
