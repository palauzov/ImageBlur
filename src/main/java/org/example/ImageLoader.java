package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageLoader {

    public ImageLoader(){}

    public void loadImage(BufferedImage Image){
        JFrame jFrame = new JFrame();
        JLabel jLabel = new JLabel();
        int width = Image.getWidth();
        int height = Image.getHeight();
        jFrame.setSize(width, height);
        jLabel.setIcon(new ImageIcon(Image));
        jFrame.getContentPane().add(jLabel, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
