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


/**
 * GUI for Main frame
 */
public class GUICom {
    private Connect com;
    private String selectedCom;
    static JFrame frame = new JFrame("Wise Control");
    private JTextField textField = new JTextField(15);
    private static JButton buttonSend = new JButton("Отправить");
    private static JButton buttonConnect = new JButton("Соединить");
    private static JButton buttonSettings = new JButton("Настройки");
    private JCheckBox checkBox = new JCheckBox("+\\r");
    private static JCheckBox terminal = new JCheckBox("Терминал");

    private GUICom() {

        //******************************* Выпадающее меню ***********************************************
        GetCom getCom = new GetCom();
        String[] comL = getCom.getCom();
        JComboBox comList = new JComboBox(comL);
        comList.setToolTipText("Выберите COM");
        comList.setFont(new Font("Arial", Font.BOLD, 12));
        comList.setBackground(Color.WHITE);
        comList.setSelectedIndex(-1); // индекс элемента JComboBox по умолчанию при запуске программы
        comList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    selectedCom = (String) e.getItem();
                }
            }
        });

        //******************************* Блок кнопок ****************************************************
        buttonSend.setMaximumSize(new Dimension(120,22));
        buttonSend.setMinimumSize(new Dimension(120,22));
        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("Введите команду")||textField.getText().equals("")){
                    textField.setText("Введите команду");
                }
                else if (checkBox.isSelected()){
                    com.sendCom(textField.getText() + "\r");
                    textField.setText("Введите команду");}
                else {
                    com.sendCom(textField.getText());
                    textField.setText("Введите команду");}
            }
        });

        buttonConnect.setMaximumSize(new Dimension(120,22));
        buttonConnect.setMinimumSize(new Dimension(120,22));
        buttonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedCom.equals("")){
                    buttonConnect.setText("Соединить");
                }
                else if (buttonConnect.getText().equals("Соединить")){
                    com = new Connect(selectedCom);
                    com.openConnect();
                    if (Connect.getErrorFlag().equals("error")){
                        buttonConnect.setText("Соединить");
                        com.closeConnect();
                    }
                    else {buttonConnect.setText("Разъединить");}
                    }
                else {
                    com.closeConnect();
                    buttonConnect.setText("Соединить");
                }
            }
        });

        terminal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (terminal.isSelected()){Terminal.showTerminal();}
                else {Terminal.hideTerminal();}
            }
        });

        buttonSettings.setMaximumSize(new Dimension(120,22));
        buttonSettings.setMinimumSize(new Dimension(120,22));
        buttonSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Settings.showSetting();
            }
        });

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
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSend.doClick();
                textField.setText("");
            }
        });

        //******************************* Размещение на панели ********************************************
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setBorder(new TitledBorder("Список доступных COM портов"));
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
        frame.getContentPane().add(panel);                            // добавление панели "panel" на фрейм
        frame.setPreferredSize(new Dimension(320, 150)); // размер окна при запуске программы
        frame.setResizable(false);
        frame.pack();                                                 // автоматическая подстройка всех компонентов окна
        frame.setLocationRelativeTo(null);                            // размещение окна по центру при запуске программы
        frame.setVisible(true);                                       // сделать окно видимым
    }

    static void clickTerminal() {
        if (terminal.isSelected()) {
            terminal.doClick();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICom();
            }
        });
    }
}