package com;

import javax.swing.*;
import java.awt.*;

public class ErrorMessage extends JDialog{
    protected static JDialog errorDialog = new JDialog(GUICom.frame, "Внимание!", true);
    protected static String errorMessage = "";

    public static void setErrorMessage(String errorMessage) {
        ErrorMessage.errorMessage = errorMessage;
    }

    public static void setErrorDialogShow(){
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
