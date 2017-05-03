package com;

import javax.swing.*;
import java.awt.*;

class ErrorMessage extends JDialog{
    private static JDialog errorDialog = new JDialog(GUICom.frame, "Внимание!", true);
    private static String errorMessage = "";

    static void setErrorMessage(String errorMessage) {
        ErrorMessage.errorMessage = errorMessage;
    }

    static void setErrorDialogShow(){
        JLabel message = new JLabel(errorMessage);
        message.setFont(new Font("Arial", Font.PLAIN, 14));
        message.setHorizontalAlignment(SwingConstants.CENTER);

        errorDialog.getContentPane().add(message);
        errorDialog.setPreferredSize(new Dimension(320,100));
        errorDialog.setResizable(false);
        errorDialog.setLocationRelativeTo(null);
        errorDialog.pack();
        errorDialog.setVisible(true);
    }
}
