import javax.swing.*;
import java.awt.*;

public class Carro extends Thread{
    public JPanel panel = new JPanel();
    public int Xfinal;
    public int speed = 0;
    public enum origem {oeste, leste};

    public Carro(origem o) {
        if(o == origem.oeste) {
            speed = 10;
            Xfinal = 720;
            this.panel.setBackground(Color.YELLOW);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(10, 230, 30, 15);

        } else if(o == origem.leste){
            speed = -10;
            Xfinal = 0;
            this.panel.setBackground(Color.BLUE);
            this.panel.setPreferredSize(new Dimension(10, 10));
            this.panel.setBounds(700, 230, 30, 15);
        }
    }

    public void run(){
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
