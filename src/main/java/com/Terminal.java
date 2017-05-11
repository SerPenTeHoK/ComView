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
class Terminal extends JFrame{
    private static JTextArea textArea = new JTextArea(10,25);
    private static JScrollPane scrollPane = new JScrollPane(textArea);
    private static JPopupMenu contextMenu = new JPopupMenu();
    private JCheckBoxMenuItem alwaysOnTop = new JCheckBoxMenuItem("Всегда сверху");
    private JMenuItem clear = new JMenuItem("Очистить");

    public void setAlwaysOnTop(String title) {
        alwaysOnTop.setText(title);
    }

    public void setClear(String title) {
        clear.setText(title);
    }

    Terminal(String title) throws HeadlessException {
        super(title);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.getContentPane().add(scrollPane);
        super.setPreferredSize(new Dimension(320, 240));
        super.setMinimumSize(new Dimension(320, 240));
        super.setBounds(100,100,100,100);
        super.pack();
        try {
            URL resource = super.getClass().getResource("/images/bt3.png");
            BufferedImage image = ImageIO.read(resource);
            super.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                if (alwaysOnTop.isSelected()){super.setAlwaysOnTop(true);}
                else {super.setAlwaysOnTop(false);}
        });
        contextMenu.add(clear);
        clear.addActionListener(e -> textArea.setText(""));

    }

    void showTerminal(){super.setVisible(true);}

    void hideTerminal(){super.setVisible(false);}

    static void writeTerminal(String inputData){
        textArea.append(inputData);
    }
}
