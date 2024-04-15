import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.Semaphore;

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


            /*  Verificando se a imagem existe*/
            //File file = new File("src/carLeste.png");
            //System.out.println(file.exists());



            //Lado Oeste
            JLabel tempoTrav = new JLabel();
            tempoTrav.setText("Tempo de travessia(s): ");
            tempoTrav.setBounds(50, 330, 200, 100);
            frame.add(tempoTrav);
            JTextField campoTempoTrav = new JTextField();
            campoTempoTrav.setBounds(largTela-750, 390, 150, 30);
            frame.add(campoTempoTrav);

            JLabel tempoPerma = new JLabel();
            tempoPerma.setText("Tempo de permanência(s): ");
            tempoPerma.setBounds(50, 390, 200, 100);
            frame.add(tempoPerma);
            JTextField campoTempoPerma = new JTextField();
            campoTempoPerma.setBounds(largTela-750, 450, 150, 30);
            frame.add(campoTempoPerma);

            JButton botao1 = new JButton("Adicionar Oeste");
            botao1.setBounds(largTela-750, altTela-100, 150, 30);
            frame.add(botao1);
            botao1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Carro car = new Carro("oeste",
                            Integer.parseInt(campoTempoTrav.getText()),
                            Integer.parseInt(campoTempoPerma.getText())*1000
                    );

                    frame.getContentPane().remove(ceu);
                    frame.add(car.panel);
                    frame.add(ceu);
                    car.start();
                }
            });



            //Lado Leste
            JLabel tempoTravDir = new JLabel();
            tempoTravDir.setText("Tempo de travessia: ");
            tempoTravDir.setBounds(largTela-200, 330, 200, 100);
            frame.add(tempoTravDir);
            JTextField tempoTravessiaLeste = new JTextField(10);
            tempoTravessiaLeste.setBounds(largTela-200, 390, 150, 30);
            frame.add(tempoTravessiaLeste);

            JLabel tempoPermaDir = new JLabel();
            tempoPermaDir.setText("Tempo de permanência: ");
            tempoPermaDir.setBounds(largTela-200, 390, 200, 100);
            frame.add(tempoPermaDir);
            JTextField tempodePermanciaLeste = new JTextField(10);
            tempodePermanciaLeste.setBounds(largTela-200, 450, 150, 30);
            frame.add(tempodePermanciaLeste);


            JButton botao2 = new JButton("Adicionar Leste");
            botao2.setBounds(largTela-200, altTela-100, 150, 30);
            frame.add(botao2);
            botao2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Carro car = new Carro("leste",
                            Integer.parseInt(tempoTravessiaLeste.getText()),
                            Integer.parseInt(tempodePermanciaLeste.getText())*1000
                            );
                    frame.getContentPane().remove(ceu);
                    frame.add(car.panel);
                    frame.add(ceu);
                    car.start();
                }
            });



            frame.setVisible(true);



    }
}