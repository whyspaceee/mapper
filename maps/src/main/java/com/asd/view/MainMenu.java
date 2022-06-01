package com.asd.view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.asd.logic.*;
import com.asd.repository.MapsApi;

public class MainMenu extends JFrame implements ActionListener {

    MST mst;
    MapsApi api;
    JLabel image;
    JLabel totalCostLabel;
    JButton button;
    JTextField[] textFields;
    JLabel path;

    public MainMenu(MST mst, MapsApi api) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.api = api;
        this.mst = mst;
        image = new JLabel();
        image.setPreferredSize(new Dimension(720, 480));
        path = new JLabel();
        path.setPreferredSize(new Dimension(360, 100));
        totalCostLabel = new JLabel();
        totalCostLabel.setPreferredSize(new Dimension(360, 100));
        button = new JButton("Submit");
        button.addActionListener(this);
        textFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(250, 40));
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Place 1"));
        this.add(textFields[0]);
        this.add(new JLabel("Place 2"));
        this.add(textFields[1]);
        this.add(new JLabel("Place 3"));
        this.add(textFields[2]);
        this.add(new JLabel("Place 4"));
        this.add(textFields[3]);
        this.add(new JLabel("Place 5"));
        this.add(textFields[4]);
        this.add(new JPanel());
        this.add(button);

        this.add(path);
        this.add(image);
        this.add(totalCostLabel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            ArrayList<String> arrayList = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
                if (textFields[i].getText().length() > 0) {
                    arrayList.add(textFields[i].getText());
                    System.out.println(textFields[i].getText());
                }
            }
            mst.create(arrayList.toArray(new String[0]));
            long totalCost = mst.Prim(0);
            long hours = totalCost / 3600;
            long minutes = totalCost % 3600 / 60;
            String paths = mst.getPath();
            Vertex[] vertices = mst.getVertices();
            ImageIcon img = new ImageIcon(api.getImage(vertices));
            totalCostLabel.setText(hours + " hours " + minutes + " minutes");
            path.setText(paths);
            image.setIcon(img);
            repaint();

        }
    }

}
