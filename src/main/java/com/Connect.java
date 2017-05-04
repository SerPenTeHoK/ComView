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
        }
        catch (SerialPortException ex) {
            if (ex.getExceptionType().equals("Port busy")){
                ErrorMessage.setErrorMessage("Порт занят. Выберите другой порт.");
                ErrorMessage.setErrorDialogShow();
                setErrorFlag("error");
            }
        }
    }

    void closeConnect(){
        try{
            setErrorFlag("");
            serialPort.closePort();
        }
        catch (SerialPortException ex){
            ex.printStackTrace();
        }
    }

    void sendCom(String data){
        try{
            serialPort.writeBytes(data.getBytes());
        }
        catch (SerialPortException ex){
            ex.printStackTrace();
        }
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    String data = serialPort.readString(event.getEventValue());
                    Terminal.writeTerminal(data);
                }
                catch (SerialPortException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
