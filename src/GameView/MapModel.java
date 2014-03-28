/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView;

import java.awt.event.ActionListener;
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
    int colCount = 10;
    int rowCount = 10;
    boolean isExitSet = false;
    private ImageIcon enemyIcon;
    private ImageIcon bombIcon;
//    private MapDefaultTableModel mapTableModel;
    private int id = -1;

    public MapModel(KeyListener kl, ActionListener connectButtonListener) {
        super(10, 10);

        initImages();
        startMapView();

        this.viewer.setConnectButtonListener(kl,connectButtonListener);
        
        
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

    private void startMapView() {
        try {
            viewer = new MapView(this);
            viewer.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MapModel.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    public synchronized void loadMap(int id, String ser) {
        if (this.id == -1) {
            this.id = id;
            this.viewer.setTitle("Player: Conencted ID <" + getID() + ">");
            this.viewer.hideWaitingForConnection();

        }
        parseString(ser);
    }

    private void putWallIn(int x, int y) {
        this.setValueAt(this.wallIcon, y, x);
    }

    private void putBoxIn(int x, int y) {
        this.setValueAt(this.boxIcon, y, x);
    }

    private void putPathIn(int x, int y) {
        this.setValueAt(this.grassIcon, y, x);

    }

    private void putExitIn(int x, int y) {
        this.setValueAt(this.exitIcon, y, x);
    }

    public synchronized void putBombOn(int x, int y) {
        this.setValueAt(bombIcon, y, x);
    }

    public synchronized void setPlayerOnEntity(int x, int y) {
        System.out.println("player on entity " + x + "><" + y + "id =" + id);
        this.setValueAt(playerIcon, y, x);
    }

    public synchronized void setEnemyOnEntity(int x, int y) {
        System.out.println("Enemy on entity " + x + "><" + y);
        this.setValueAt(enemyIcon, y, x);
    }

    private void parseString(String ser) {

        String[] split = ser.split("-");

        int cellCode = 0;
        int row = this.colCount;
        int col = this.rowCount;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int code = Integer.parseInt(split[cellCode]);
                switch (code) {
                    case Entity.BOX:
                        putBoxIn(r, c);
                        break;
                    case Entity.EXIT:
                        putExitIn(r, c);
                        break;
                    case Entity.PATH:
                        putPathIn(r, c);
                        break;
                    case Entity.WALL:
                        putWallIn(r, c);
                        break;
                    case Entity.BOMB:
                        putBombOn(r, c);
                        break;
                    default:
                        if (code == this.id) {
                            setPlayerOnEntity(r, c);
                        } else {
                            setEnemyOnEntity(r, c);
                        }
                }
                cellCode++;
            }
        }

    }

    // GETTERS AND SETTERS
    public MapView getViewer() {
        return viewer;
    }

    public int getWidth() {
        return colCount;
    }

    public int getHeight() {
        return rowCount;
    }

    protected int getID() {
        return this.id;
    }

    /**
     * *****************
     * INNER CLASSES
     */////////////////
    @Override
    public Class<?> getColumnClass(int i) {
        return Icon.class;
    }
}
