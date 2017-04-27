package com;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Connect {
    private static SerialPort serialPort;
    private static int speed = 9600;
    private static int dataBits = 8;
    private static int stopBits = 1;
    private static int parity = 0;
    private static String errorFlag ="";

    public static void setErrorFlag(String errorFlag) {
        Connect.errorFlag = errorFlag;
    }

    public static String getErrorFlag() {
        return errorFlag;
    }

    public static void setSpeed(String speed) {
        Connect.speed = Integer.parseInt(speed);
    }

    public static void setDataBits(String dataBits) {
        Connect.dataBits = Integer.parseInt(dataBits);
    }

    public static void setStopBits(String stopBits) {
        Connect.stopBits = Integer.parseInt(stopBits);
    }

    public static void setParity(String parity) {
        Connect.parity = Integer.parseInt(parity);
    }

    public Connect(String selectedCom) {
        serialPort = new SerialPort(selectedCom);
    }

    public void openConnect() {
        try {
            serialPort.openPort();
            serialPort.setParams(speed, dataBits, stopBits, parity);
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //System.out.println(speed + " / " + dataBits + " / " + stopBits + " / " + parity);
        }
        catch (SerialPortException ex) {
            System.err.println(ex);
            if (ex.getExceptionType().equals("Port busy")){
                ErrorMessage.setErrorMessage("Порт занят. Выберите другой порт.");
                ErrorMessage.setErrorDialogShow();
                errorFlag = "error";
            }
        }
    }

    public void closeConnect(){
        try{
            setErrorFlag("");
            serialPort.closePort();
        }
        catch (SerialPortException ex){
            System.err.println(ex);
        }
    }

    public void sendCom(String data){
        try{
            serialPort.writeBytes(data.getBytes());
        }
        catch (SerialPortException ex){
            System.err.println(ex);
        }
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    Terminal.writeTerminal(data);
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
