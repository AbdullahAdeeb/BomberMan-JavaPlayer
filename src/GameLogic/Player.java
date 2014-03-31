package GameLogic;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import GameView.MapModel;
import UDPCommunication.Communication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author abdullahadeeb
 */
public class Player {

    MapModel mapModel;
    private Communication com = null;
    int id;
    JFrame frame;
    
    public int getID(){return id;}

    public Communication getCommunications(){return com;}
    
    Player() {
        mapModel = new MapModel(new playerKeysListener(), new ConnectButtonListener(),this);
    }

    public static void main(String[] args) {
        Player p = new Player();
    }

    class playerKeysListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                com.send(id, 1);
            } else if (ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("left");
                com.send(id, 4);
            } else if (ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("up");
                com.send(id, 2);
            } else if (ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("down");
                com.send(id, 3);
            } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                System.out.println("bomb");
                com.send(id, 5);
            } else if (ke.getKeyCode() == KeyEvent.VK_K){
            	 System.out.println("disconnect");
            	 com.send(id, 6);
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
    }

    class ConnectButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String c = ae.getActionCommand();
            try {
                InetAddress ip = InetAddress.getByName(c);
                com = new Communication(new IncomingActionListener(), -1, ip); //sending id as -1 to request new ID from server.
            } catch (UnknownHostException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    class IncomingActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            synchronized (this) {
                String actionCmd = e.getActionCommand();
                //System.out.println("****"+actionCmd+"****");
                int p = Character.getNumericValue(actionCmd.charAt(0));
                int c = Character.getNumericValue(actionCmd.charAt(1));

                if (c == 0) {
                    id = p;
                    //System.out.println("ID: " + id);
                } else if (c == 5 && id == p) {
                    System.out.println("--RECEIVED CMD--\nUpdate Map\n");
                    actionCmd = actionCmd.substring(3);
                    actionCmd = actionCmd.trim();
//                    System.out.println(actionCmd);
                    System.out.println("loading map...");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException ex) {
//                        System.err.println("we got an error in the sleep ");
//                    }
//                    System.out.println(">>"+id);
                    mapModel.loadMap(p, actionCmd);
                } else if (c == 1) {
                    System.out.println("--RECEIVED CMD--\nACK\n");
                } else if (c == 7) {
                    System.out.println("--RECEIVED CMD--\nACK\nCannot Drop\n");
                }else if (c == 9){
                	//This is where the game closing code is
                	JOptionPane.showMessageDialog(frame, "Game done, closing");
                	System.exit(0);
                	
                }
            }
        }
    }
}
