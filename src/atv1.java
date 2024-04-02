import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class atv1 {


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

            // Botões
            JButton botao1 = new JButton("Adicionar Oeste");
            botao1.setBounds(largTela-750, altTela-100, 150, 30);
            frame.add(botao1);

            JButton botao2 = new JButton("Adicionar Leste");
            botao2.setBounds(largTela-200, altTela-100, 150, 30);
            frame.add(botao2);

            frame.setVisible(true);



    }
}