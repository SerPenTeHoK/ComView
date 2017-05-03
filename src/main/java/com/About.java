package com;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * GUI for About
 */
class About extends JDialog{
    private static final JDialog about = new JDialog(Settings.settings, "О программе", true);

    static void showAbout(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        JLabel labelName = new JLabel("<html><b>Wise Control version 1.0</b></html>");
        labelName.setFont(new Font("Arial", Font.ITALIC, 16));
        labelName.setForeground(Color.BLUE);
        panel.add(labelName, "wrap, center");

        JLabel labelAuthor = new JLabel("Автор и разработчик  (Author and Developer)");
        labelAuthor.setFont(new Font("Arial", Font.ITALIC,14));
        //labelAuthor.setForeground(Color.BLUE);
        panel.add(labelAuthor, "wrap, center");

        JLabel labelMyName = new JLabel("Сергей Наумов  (Sergey Naumov)");
        labelMyName.setFont(new Font("Arial", Font.ITALIC,14));
        //labelMyName.setForeground(Color.BLUE);
        panel.add(labelMyName, "wrap, center");

        JLabel labelMail = new JLabel("Электронная почта  (E - mail)");
        labelMail.setFont(new Font("Arial", Font.ITALIC,14));
        //labelMail.setForeground(Color.BLUE);
        panel.add(labelMail, "wrap, center");

        JTextField textEmail = new JTextField(15);
        textEmail.setText("wisess@mail.ru");
        textEmail.setEditable(false);
        textEmail.setBorder(new EmptyBorder(0,0,0,0));
        textEmail.setFont(new Font("Arial", Font.ITALIC,14));
        textEmail.setHorizontalAlignment(JTextField.CENTER);
        textEmail.setForeground(Color.BLUE);
        panel.add(textEmail, "wrap, center");

        about.setContentPane(panel);
        about.setPreferredSize(new Dimension(330,150));
        about.setResizable(false);
        about.setLocationRelativeTo(null);
        about.pack();
        about.setVisible(true);
    }
}
