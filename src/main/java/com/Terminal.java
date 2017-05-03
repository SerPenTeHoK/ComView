package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 *  GUI для терминала
 */
class Terminal {
    private static JFrame term = new JFrame("Терминал");
    private static JTextArea textArea = new JTextArea(10,25);
    private static JScrollPane scrollPane = new JScrollPane(textArea);
    private static JPopupMenu contextMenu = new JPopupMenu();
    private static JCheckBoxMenuItem alwaysOnTop = new JCheckBoxMenuItem("Всегда сверху");

    static void showTerminal(){
        //******************************* Общее для фрейма Терминал **********************************************
        term.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        term.getContentPane().add(scrollPane);
        term.setPreferredSize(new Dimension(320, 240));
        term.setMinimumSize(new Dimension(320, 240));
        term.setBounds(100,100,100,100);
        term.pack();
        try {
            URL resource = term.getClass().getResource("/images/bt3.png");
            BufferedImage image = ImageIO.read(resource);
            term.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        term.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               GUICom.clickTerminal();
            }
        });

        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3){
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        contextMenu.add(alwaysOnTop);
        alwaysOnTop.addActionListener(e -> {
                if (alwaysOnTop.isSelected()){term.setAlwaysOnTop(true);}
                else {term.setAlwaysOnTop(false);}
        });

        JMenuItem clear = new JMenuItem("Очистить");
        contextMenu.add(clear);
        clear.addActionListener(e -> textArea.setText(""));

        term.setVisible(true);
    }

    static void hideTerminal(){
        term.setVisible(false);
    }

    static void writeTerminal(String inputData){
        textArea.append(inputData);
    }
}
