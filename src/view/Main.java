package view;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Racing 2D");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1200,800);
        f.add(new Road());
        ImageIcon icon = new ImageIcon("src/res/img.jpg");
        f.setIconImage(icon.getImage());

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
