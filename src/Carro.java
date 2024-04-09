import javax.swing.*;
import java.awt.*;

public class Carro extends Thread{
    public JPanel panel;
    public int Xfinal;

    public Carro(JPanel panel) {
        this.panel = panel;
        Xfinal = 720;
    }

    public void run(){
        int speed = 5;
        int currentX = panel.getX();

        try {
            while (panel.getX() < Xfinal){
                currentX += speed;
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
