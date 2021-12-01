import GraphicGraph.Panel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        final int buttonWidth = 200, buttonHeight = 100;

        JFrame f = new JFrame("Grafi");
        f.setBounds(0, 0, 1000, 1000);
        f.getContentPane().setBackground(Color.GRAY);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel option = new JLabel("Nessuna opzione selezionata");
        option.setBounds(100, 50, 200, 50);

        JLabel dim = new JLabel("");
        dim.setBounds(100, 25, 200, 25);

        GraphicGraph.Panel bh = new Panel(option, dim);
        bh.setBounds(350, 0, 650, 1000);
        bh.addMouseListener(bh);

        JButton add = new JButton("Aggiungi nodo");
        add.setBounds(100, 100, buttonWidth, buttonHeight);
        add.addActionListener(bh);
        add.setActionCommand("add");

        JButton addArch = new JButton("Aggiungi arco");
        addArch.setBounds(100, 250, buttonWidth, buttonHeight);
        addArch.addActionListener(bh);
        addArch.setActionCommand("addArch");

        JButton start = new JButton("Partenza");
        start.setBounds(100, 400, buttonWidth, buttonHeight);
        start.addActionListener(bh);
        start.setActionCommand("start");

        JButton end = new JButton("Fine");
        end.setBounds(100, 550, buttonWidth, buttonHeight);
        end.addActionListener(bh);
        end.setActionCommand("end");

        JButton calc = new JButton("Calcola");
        calc.setBounds(100, 700, buttonWidth, buttonHeight);
        calc.addActionListener(bh);
        calc.setActionCommand("calc");

        f.add(add);
        f.add(addArch);
        f.add(start);
        f.add(start);
        f.add(end);
        f.add(calc);
        f.add(option);
        f.add(bh);
        f.add(dim);

        f.setVisible(true);

    }
}
