import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.AWTEventMulticaster.add;

public class atv1 {

    public JTextField tempoTravessiaLeste;
    public JTextField tempodePermanciaLeste;
    public JTextField tempoTravessiaOeste;
    public JTextField tempodePermanciaOeste;

        public static void main(String[] args) {
            int largTela = 800;
            int altTela = 600;

            int xOeste = 10;
            int xLeste = 750;


            JFrame frame = new JFrame("Ponte de Carros");
            frame.setSize(new Dimension(largTela, altTela));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);

            // Ponte
            ImageIcon ponte = new ImageIcon("src/ponte800.png");
            JLabel label = new JLabel(ponte);
            label.setBounds(-1, 150, 800, 198);
            frame.add(label);


            // Carro
            JPanel carro = new JPanel();
            carro.setBackground(Color.YELLOW);
            carro.setPreferredSize(new Dimension(10, 10));
            carro.setBounds(10, 230, 30, 15);
            frame.add(carro);
            Carro car = new Carro(carro);
            car.start();

            // Carro2
            JPanel carro2 = new JPanel();
            carro2.setBackground(Color.BLUE);
            carro2.setPreferredSize(new Dimension(10, 10));
            carro2.setBounds(750, 230, 30, 15);
            frame.add(carro2);

            // Céu
            JPanel retangulo = new JPanel();
            retangulo.setBackground(Color.CYAN);
            retangulo.setPreferredSize(new Dimension(100, 200));
            retangulo.setBounds(0, 0, largTela, altTela/2);
            frame.add(retangulo);

            /*   Verificando se a imagem existe
            File file = new File("src/ponte.png");
            System.out.println(file.exists());*/


            //Lado Oeste
            JTextField tempoTravessiaOeste = new JTextField(10);
            tempoTravessiaOeste.setBounds(largTela-750, altTela-180, 150, 30);
            frame.add(tempoTravessiaOeste);

            JTextField tempodePermanciaOeste = new JTextField(10);
            tempodePermanciaOeste.setBounds(largTela-750, altTela-140, 150, 30);
            frame.add(tempodePermanciaOeste);

            JButton botaoOeste = new JButton("Adicionar Oeste");
            botaoOeste.setBounds(largTela-750, altTela-100, 150, 30);
            frame.add(botaoOeste);


            //Lado Leste
            JTextField tempoTravessiaLeste = new JTextField(10);
            tempoTravessiaLeste.setBounds(largTela-200, altTela-180, 150, 30);
            frame.add(tempoTravessiaLeste);

            JTextField tempodePermanciaLeste = new JTextField(10);
            tempodePermanciaLeste.setBounds(largTela-200, altTela-140, 150, 30);
            frame.add(tempodePermanciaLeste);

            JButton botaoLeste = new JButton("Adicionar Leste");
            botaoLeste.setBounds(largTela-200, altTela-100, 150, 30);
            frame.add(botaoLeste);

            frame.setVisible(true);



    }
}