/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author abdullahadeeb
 */
public class MapView extends javax.swing.JFrame {

    private ActionListener connectButtonListener;
    private KeyListener keyListener;

    /**
     * Creates new form mapView
     */
//    DefaultTableModel model;
    public MapView(DefaultTableModel model) {
        initComponents();
        this.mapTable.setModel(model);

        this.setTitle("Player: Not Connected");


        this.mapTable.setCellSelectionEnabled(false);
        this.mapTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        TableColumnModel columnModel = this.mapTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).sizeWidthToFit();
        }


        mapTable.setShowGrid(false);
        this.mapPanel.setBackground(Color.BLUE);
        this.mapTable.setPreferredScrollableViewportSize(this.mapTable.getSize());
        this.mapPanel.setSize(this.mapTable.getSize());

    }

    public void hideWaitingForConnection() {
        this.bigLabel.setVisible(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mapTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bigLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mapPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        mapTable.setBackground(new java.awt.Color(0, 0, 0));
        mapTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        mapTable.setEnabled(false);
        mapTable.setMaximumSize(new java.awt.Dimension(600, 600));
        mapTable.setMinimumSize(new java.awt.Dimension(0, 0));
        mapTable.setPreferredSize(new java.awt.Dimension(600, 600));
        mapTable.setRowHeight(60);
        mapTable.setRowMargin(0);
        mapTable.setTableHeader(null);
        jScrollPane1.setViewportView(mapTable);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                .addContainerGap())
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bomber MAn");

        bigLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        bigLabel.setForeground(new java.awt.Color(255, 51, 51));
        bigLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bigLabel.setText("(: Welcome :)");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel3.setText("Server IP:");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mapPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectButton)
                                .addGap(204, 204, 204)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        connectButton.getAccessibleContext().setAccessibleName("connect");
        connectButton.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        if (ipField.getText().matches("([0-9]{1,3}.){3}[0-9]{1,3}")) {
            this.connectButtonListener.actionPerformed(new ActionEvent(this.connectButton, 0, this.ipField.getText()));
            this.bigLabel.setText("Waiting for Connection");
            this.ipField.addKeyListener(keyListener);
            this.connectButton.setEnabled(false);
            this.ipField.setEditable(false);

        } else {
            this.bigLabel.setText("Bad IP :(");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bigLabel;
    private javax.swing.JButton connectButton;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JTable mapTable;
    // End of variables declaration//GEN-END:variables

    void setConnectButtonListener(KeyListener kl, ActionListener al) {
        this.keyListener = kl;
        this.connectButtonListener = al;
    }
}
