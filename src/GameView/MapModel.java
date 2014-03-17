/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView;

import java.awt.event.KeyListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abdullahadeeb02
 */
public class MapModel extends DefaultTableModel {

    private URL GRASS_ICON_DIR = this.getClass().getClassLoader().getResource("res/PATH1.JPG");
    private URL WALL_ICON_DIR = this.getClass().getClassLoader().getResource("res/WALL1.JPG");
    private URL BOX_ICON_DIR = this.getClass().getClassLoader().getResource("res/BOX1.JPG");
    private URL EXIT_ICON_DIR = this.getClass().getClassLoader().getResource("res/EXIT1.JPG");
    private URL BOMB_ICON_DIR = this.getClass().getClassLoader().getResource("res/BOMB1.JPG");
    private URL ENEMY_ICON_DIR = this.getClass().getClassLoader().getResource("res/ENEMY1.JPG");
    private URL PLAYER_ICON_DIR = this.getClass().getClassLoader().getResource("res/PLAYER1.PNG");
    private ImageIcon grassIcon;
    private ImageIcon wallIcon;
    private ImageIcon boxIcon;
    private ImageIcon exitIcon;
    private ImageIcon playerIcon;
    MapView viewer;
    int width = 10;
    int height = 10;
    boolean isExitSet = false;
    private ImageIcon enemyIcon;
    private ImageIcon bombIcon;
    private int id = -1;

    public MapModel(KeyListener kl) {
        initImages();
        startMapView();
        this.viewer.addKeyListener(kl);
    }

    public synchronized void loadMap(int id, String ser) {
        if (this.id == -1) {
            this.id = id;
            this.viewer.setTitle("Player: Conencted ID <" + getID() + ">");
        }

        this.viewer.hideWaitingForConnection();
        setColumnCount(width);
        setRowCount(height);
        parseString(ser);
    }

    private void startMapView() {
        try {
            viewer = new MapView(this);
            viewer.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MapModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    public void setMapView(MapView v) {
        this.viewer = v;
    }

    private void putWallIn(int x, int y) {
        setValueAt(this.wallIcon, y, x);
    }

    private void putBoxIn(int x, int y) {
        setValueAt(this.boxIcon, y, x);
    }

    private void putPathIn(int x, int y) {
        setValueAt(this.grassIcon, y, x);
    }

    private void putExitIn(int x, int y) {
        setValueAt(this.exitIcon, y, x);
    }

    public synchronized void putBombOn(int x, int y) {
        setValueAt(bombIcon, y, x);
    }

    public synchronized void setPlayerOnEntity(int id, int x, int y) {
        System.out.println("player on entity " + x + "><" + y + "id =" + id);
        setValueAt(playerIcon, y, x);
    }

    public synchronized void setEnemyOnEntity(int id, int x, int y) {
        System.out.println("player on entity " + x + "><" + y);
        setValueAt(enemyIcon, y, x);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initImages() {
        grassIcon = new ImageIcon(GRASS_ICON_DIR);
        wallIcon = new ImageIcon(WALL_ICON_DIR);
        boxIcon = new ImageIcon(BOX_ICON_DIR);
        exitIcon = new ImageIcon(EXIT_ICON_DIR);
        playerIcon = new ImageIcon(PLAYER_ICON_DIR);
        enemyIcon = new ImageIcon(ENEMY_ICON_DIR);
        bombIcon = new ImageIcon(BOMB_ICON_DIR);
    }

    private void parseString(String ser) {

        String[] split = ser.split("-");

        int block = 0;

        int row = width;
        int col = this.height;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int code = Integer.parseInt(split[block]);
                switch (code) {
                    case Entity.BOX:
                        putBoxIn(i, j);
                        break;
                    case Entity.EXIT:
                        putExitIn(i, j);
                        break;
                    case Entity.PATH:
                        putPathIn(i, j);
                        break;
                    case Entity.WALL:
                        putWallIn(i, j);
                        break;
                    case Entity.BOMB:
                        putBombOn(i, j);
                        break;
                    default:
                        if (code == this.id) {
                            setPlayerOnEntity(code, i, j);
                        } else {
                            setEnemyOnEntity(code, i, j);
                        }
                }
                block++;
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return Icon.class;
    }

    protected int getID() {
        return this.id;
    }
}
