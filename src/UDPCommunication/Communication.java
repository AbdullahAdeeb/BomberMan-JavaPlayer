package UDPCommunication;

import java.awt.event.*;
import java.net.*;

/**
 *
 * @author zachcousins
 */
public class Communication {

    DatagramPacket sendPack;
    Receiver recv;
    Sender sender;
    int id;
    ActionListener al;
    InetAddress server;

    public Communication(ActionListener al, int id, InetAddress server) {

        this.server = server;
        this.al = al;
        this.id = id;
        init();
    }

    private void init() {


        recv = new Receiver(al);
        new Thread(recv).start();
        sender = new Sender();
        this.send(-1, 0);

    }

    public void send(int id, int c) {

        byte[] cmd = new byte[2];
        cmd[0] = (byte) id;
        cmd[1] = (byte) c;

        sendPack = new DatagramPacket(cmd, cmd.length, this.server, 5000);
        sender.sendCmd(sendPack);
    }

    public void close() {
        recv.close();
        sender.close();
    }
}
