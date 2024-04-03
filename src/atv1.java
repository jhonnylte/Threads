import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class atv1 {


        public static void main(String[] args) {
            int largTela = 800;
            int altTela = 600;


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


            // Céu
            JPanel ceu = new JPanel();
            ceu.setBackground(Color.CYAN);
            ceu.setPreferredSize(new Dimension(100, 200));
            ceu.setBounds(0, 0, largTela, altTela/2);
            frame.add(ceu);


            /*   Verificando se a imagem existe
            File file = new File("src/ponte.png");
            System.out.println(file.exists());*/

            // Botões
            JButton botao1 = new JButton("Adicionar Oeste");
            botao1.setBounds(largTela-750, altTela-100, 150, 30);
            frame.add(botao1);
            botao1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Carro car = new Carro(Carro.origem.oeste);
                    frame.getContentPane().remove(ceu);
                    frame.add(car.panel);
                    frame.add(ceu);
                    car.start();
                }
            });


            JButton botao2 = new JButton("Adicionar Leste");
            botao2.setBounds(largTela-200, altTela-100, 150, 30);
            frame.add(botao2);
            botao2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Carro car = new Carro(Carro.origem.leste);
                    frame.getContentPane().remove(ceu);
                    frame.add(car.panel);
                    frame.add(ceu);
                    car.start();
                }
            });

            frame.setVisible(true);



    }
}