/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import GameView.MapModel;
import UDPCommunication.Communication;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

import sun.tools.jar.Main;

/**
 *
 * @author abdullahadeeb
 */
public class Player implements KeyListener {

    MapModel mapModel;
    private final Communication com;
    int id;

    Player() {

        mapModel = new MapModel(this);
        com = new Communication(new IncomingActionListener(), -1); //sending id as -1 to request new ID from server.

    }

    public static void main(String[] args) {
        Player p = new Player();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            com.send(id, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("left");
            com.send(id, 4);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("up");
            com.send(id, 2);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("down");
            com.send(id, 3);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("bomb");
            com.send(id, 5);
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
                    System.out.println(actionCmd);
                    System.out.println("loading map...");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException ex) {
//                        System.err.println("we got an error in the sleep ");
//                    }
//                    System.out.println(">>"+id);
                    mapModel.loadMap(p, new String(actionCmd));
                } else if (c == 1) {
                    System.out.println("--RECEIVED CMD--\nACK\n");
                } else if (c == 7) {
                    System.out.println("--RECEIVED CMD--\nACK\nCannot Drop\n");
                }
            }
        }
    }
}
