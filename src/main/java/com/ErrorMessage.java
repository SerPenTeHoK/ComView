package com;

import javax.swing.*;
import java.awt.*;

class ErrorMessage extends JDialog{
    JLabel message = new JLabel("Порт занят. Выберите другой порт.");

    ErrorMessage(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        message.setFont(new Font("Arial", Font.PLAIN, 14));
        message.setHorizontalAlignment(SwingConstants.CENTER);

        super.getContentPane().add(message);
        super.setPreferredSize(new Dimension(320,100));
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.pack();
    }

    void ErrMsgShow(){super.setVisible(true);}
}
