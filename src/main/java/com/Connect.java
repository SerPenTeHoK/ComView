package com;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

class Connect {
    private static SerialPort serialPort;
    private static int speed = 9600;
    private static int dataBits = 8;
    private static int stopBits = 1;
    private static int parity = 0;
    private static String errorFlag ="";

    private static void setErrorFlag(String errorFlag) {
        Connect.errorFlag = errorFlag;
    }

    static String getErrorFlag() {
        return errorFlag;
    }

    static void setSpeed(String speed) {
        Connect.speed = Integer.parseInt(speed);
    }

    static void setDataBits(String dataBits) {
        Connect.dataBits = Integer.parseInt(dataBits);
    }

    static void setStopBits(String stopBits) {
        Connect.stopBits = Integer.parseInt(stopBits);
    }

    static void setParity(String parity) {
        Connect.parity = Integer.parseInt(parity);
    }

    Connect(String selectedCom) {
        serialPort = new SerialPort(selectedCom);
    }

    void openConnect() {
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

    void closeConnect(){
        try{
            setErrorFlag("");
            serialPort.closePort();
        }
        catch (SerialPortException ex){
            System.err.println(ex);
        }
    }

    void sendCom(String data){
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
