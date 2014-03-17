package UDPCommunication;

import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zachcousins
 */
public class Receiver implements Runnable {

    DatagramPacket recvPack;
    DatagramSocket recvSock;
    boolean listen = true;
    boolean newPack = false;
    ActionListener actionList;
    int port = 8000;

    public Receiver(ActionListener al) {

        this.actionList = al;

        try {
            recvSock = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        System.out.print("Receiver Start\n");
        try {
            receiveMessages();
        } catch (UnsupportedEncodingException ex) {
            System.err.println("receiveMessages() error");
        }
    }

    public void receiveMessages() throws UnsupportedEncodingException {

        while (listen == true) {

            byte[] recvData = new byte[512];
            recvPack = new DatagramPacket(recvData, recvData.length);
            try {
                recvSock.receive(recvPack);
            } catch (IOException ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            int getCmd = Integer.parseInt(String.valueOf(recvPack.getData()[1]));
            if (getCmd == 0) {
                String init = String.valueOf(recvPack.getData()[0])+String.valueOf(recvPack.getData()[1]);
                actionList.actionPerformed(new ActionEvent(this, 0, init));
                System.out.println("Player ID = " + Integer.parseInt(String.valueOf(recvPack.getData()[0])));
                try {
                    recvSock.close();
                    port = 6000 + Integer.parseInt(String.valueOf(recvPack.getData()[0]));
                    System.out.println("Port: " + port);
                    recvSock = new DatagramSocket(port);
                    System.out.println("--RECEIVED CMD--\nConnection Made\n");
                } catch (SocketException ex) {
                    // TODO implemetn this and log it
                    Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            String cmd = new String(recvPack.getData(), "ISO-8859-1");
            //System.out.println("++++" + cmd + "++++");
            actionList.actionPerformed(new ActionEvent(this, 0, cmd));

        }
    }

    public void close() {
        recvSock.disconnect();
        recvSock.close();
    }
}
