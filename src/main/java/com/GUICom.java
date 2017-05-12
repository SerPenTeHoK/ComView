package com;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * GUI for Main frame
 */
public class GUICom {
    private Connect com;
    private String selectedCom;
    private Terminal term = new Terminal("Терминал");
    private Settings settings = new Settings(null,"Настройки", true);
    private ErrorMessage errorMessage  = new ErrorMessage(null, "Внимание!", true);
    final static JFrame frame = new JFrame("Wise Control");
    private String textFieldLabel = "Введите команду";
    private JTextField textField = new JTextField(15);
    private JButton buttonSend = new JButton("Отправить");
    private String buttonConnectLabel = "Соединить";
    private String buttonDisconnectLabel = "Разъединить";
    private JButton buttonConnect = new JButton(buttonConnectLabel);
    private JButton buttonSettings = new JButton("Настройки");
    private JCheckBox checkBox = new JCheckBox("+\\r");
    private JCheckBox terminal = new JCheckBox("Терминал");
    private JPanel panel = new JPanel();
    private TitledBorder titledBorder = BorderFactory.createTitledBorder("Список доступных COM портов");

    private String getButtonConnectLabel() {
        return buttonConnectLabel;
    }
    private void setButtonConnectLabel(String buttonConnectLabel) {
        this.buttonConnectLabel = buttonConnectLabel;
    }
    private String getButtonDisconnectLabel() {
        return buttonDisconnectLabel;
    }
    private void setButtonDisconnectLabel(String buttonDisconnectLabel) {
        this.buttonDisconnectLabel = buttonDisconnectLabel;
    }
    private String getTextFieldLabel() {
        return textFieldLabel;
    }
    private void setTextFieldLabel(String textFieldLabel) {
        this.textFieldLabel = textFieldLabel;
    }

    private GUICom() {
        //******************************* Выпадающее меню ***********************************************
        JComboBox<String> comList = new JComboBox<>(new GetCom().getCom());
        comList.setFont(new Font("Arial", Font.BOLD, 12));
        comList.setBackground(Color.WHITE);
        comList.setSelectedIndex(-1);
        comList.addItemListener(e-> {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    selectedCom = (String) e.getItem();
                }
        });

        settings.langComboBox.addItemListener((e) -> {
            if (e.getStateChange()==ItemEvent.SELECTED){
                if (e.getItem().equals("English")){langSet(new Locale("en","US"));}
                else {langSet(new Locale("ru","RU"));}
            }
        });

        //******************************* Блок кнопок ****************************************************
        buttonSend.setMaximumSize(new Dimension(120,22));
        buttonSend.setMinimumSize(new Dimension(120,22));
        buttonSend.addActionListener(e-> {
                if (textField.getText().equals(getTextFieldLabel())||textField.getText().equals("")){
                    textField.setText(getTextFieldLabel());
                }
                else if (checkBox.isSelected()){
                    com.sendCom(textField.getText() + "\r");
                    textField.setText(getTextFieldLabel());}
                else {
                    com.sendCom(textField.getText());
                    textField.setText(getTextFieldLabel());}
        });

        buttonConnect.setMaximumSize(new Dimension(120,22));
        buttonConnect.setMinimumSize(new Dimension(120,22));
        buttonConnect.addActionListener(e-> {
                if (selectedCom.equals("")){
                    buttonConnect.setText(getButtonConnectLabel());
                }
                else if (buttonConnect.getText().equals(getButtonConnectLabel())){
                    com = new Connect(selectedCom);
                    com.openConnect();
                    if (Connect.getErrorFlag().equals("error")){
                        buttonConnect.setText(getButtonConnectLabel());
                        com.closeConnect();
                        errorMessage.ErrMsgShow();
                    }
                    else {buttonConnect.setText(getButtonDisconnectLabel());}
                    }
                else {
                    com.closeConnect();
                    buttonConnect.setText(getButtonConnectLabel());
                }
        });

        terminal.addActionListener(e-> {
                if (terminal.isSelected()){term.showTerminal();}
                else {term.hideTerminal();}
        });

        term.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (terminal.isSelected()){terminal.doClick();}
            }
        });

        buttonSettings.setMaximumSize(new Dimension(120,22));
        buttonSettings.setMinimumSize(new Dimension(120,22));
        buttonSettings.addActionListener(e-> settings.showSetting());



        //******************************* Текстовые поля **************************************************
        textField.setText("Введите команду");
        textField.setFont(new Font("Arial", Font.ITALIC, 12));
        textField.setMinimumSize(new Dimension(150,24));
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }
            public void focusLost(FocusEvent e) {}
        });
        textField.addActionListener(e-> {
                buttonSend.doClick();
                textField.setText("");
        });

        //******************************* Размещение на панели ********************************************
        panel.setLayout(new MigLayout());
        panel.setBorder(titledBorder);
        panel.add(comList, "span 1, growX, split");
        panel.add(buttonConnect, "wrap, pushX");
        panel.add(textField, "pushX, growX, split");
        panel.add(buttonSend, "wrap, pushX");
        panel.add(checkBox, "split");
        panel.add(terminal, "pushX, growX, right");
        panel.add(buttonSettings, "pushX, growX, right");

        //******************************* Компоновка фрейма ************************************************
        try {
            URL resource = frame.getClass().getResource("/images/bt3.png");
            BufferedImage image = ImageIO.read(resource);
            frame.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(320, 150));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void langSet(Locale currentLocale){
        ResourceBundle langChoice = ResourceBundle.getBundle("loc_data", currentLocale);
        buttonConnect.setText(langChoice.getString("buttonConnect"));
        setButtonConnectLabel(langChoice.getString("buttonConnect"));
        setButtonDisconnectLabel(langChoice.getString("buttonDisconnect"));
        buttonSend.setText(langChoice.getString("buttonSend"));
        buttonSettings.setText(langChoice.getString("buttonSettings"));
        terminal.setText(langChoice.getString("checkbox"));
        textField.setText(langChoice.getString("textField"));
        setTextFieldLabel(langChoice.getString("textField"));
        titledBorder.setTitle(langChoice.getString("comList")); // Баг
        panel.repaint();
        term.setTitle(langChoice.getString("termFrame"));
        term.setAlwaysOnTop(langChoice.getString("alwaysOnTop"));
        term.setClear(langChoice.getString("clear"));
        settings.setTitle(langChoice.getString("settingsFrame"));
        settings.setButtonAbout(langChoice.getString("buttonAbout"));
        settings.setButtonSave(langChoice.getString("buttonSave"));
        settings.setSpeedL(langChoice.getString("speedLabel"));
        settings.setDataL(langChoice.getString("frameSize"));
        settings.setStopBitsL(langChoice.getString("stopBits"));
        settings.setParityL(langChoice.getString("parity"));
        settings.setLangL(langChoice.getString("lang"));
        settings.setAbout(langChoice.getString("buttonAbout"));
        errorMessage.setTitle(langChoice.getString("errorDialog"));
        errorMessage.message.setText(langChoice.getString("errorMsg"));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GUICom());
    }
}