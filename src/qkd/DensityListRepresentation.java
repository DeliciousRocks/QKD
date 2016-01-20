/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkd;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author waltersquires
 */
public class DensityListRepresentation extends javax.swing.JPanel {

    private String[] modifiers = {"conditional op H", "op H", "trace out", "measure", "project"};
    private String name;
    private DensityList data;

    /**
     * Creates new form densityListRepresentation
     */
    //From scratch
    public DensityListRepresentation() {
        initComponents();
        data = new DensityList();
        name = data.getName();
        updateWires(data);
        updateCombo(targets);

        
    }

    //From existing

    public DensityListRepresentation(DensityList temp) {
        initComponents();
        data = temp;
        name = data.getName();
        updateWires(data);
        updateCombo(targets);
    }

    public void updateCombo(JComboBox temp)
    {
        DefaultComboBoxModel tempModel = (DefaultComboBoxModel)temp.getModel();
        for (Wire wire : data.getWires()) {
                tempModel.addElement(wire.getName());
        }
        temp.setModel(tempModel);
    }
    
     public String toString()
    {
        String outName = name+".txt";
        String def = "define H as QFT 2"; //Not sure what this does, need to double check
        String createSpace = "create space (";
        String probs = getProbs();
        String preMods = getMods(1);
        String attackTarget = "attack ("+(String)targets.getSelectedItem()+")";
        String postMods = getMods(2);
        String isPrimary = "";
        if (primary.isSelected())
            isPrimary=  "save as primary";
        
        String output = outName + "\n \n" +
                        def + "\n" +
                        createSpace + "\n" +
                        probs + "\n" +
                        preMods + "\n" +
                        attackTarget + "\n" +
                        postMods+ "\n" +
                        isPrimary+ "\n" +
                        "end";
        
        return null;
    }
    
    public void updateWires(DensityList model) {
        DefaultTableModel newModel = (new DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));

        ArrayList<Integer> dimCount = new ArrayList();
        for (Wire tempWire : model.getWires()) {
            newModel.addColumn(tempWire.getName());
            dimCount.add(tempWire.getDimensions());
        }
        newModel.addColumn("aKey");
        newModel.addColumn("Probability");

        //*
        int count = 0;
        int neededRows = 1;
        for (Wire tempWire : model.getWires()) {
            neededRows = neededRows* tempWire.getDimensions();
        }
            for (int i = 0; i < neededRows; i++) {
                ArrayList<Object> tempRow = new ArrayList();
                for (int j = 0; j < model.getWires().size(); j++) {
                    tempRow.add(0);
                }
                tempRow.add(0);
                tempRow.add(-1);

                Object[] tempData = tempRow.toArray();
                newModel.addRow(tempData);
            }
            count++;

        for (int i = 0; i < newModel.getRowCount(); i++) {
            boolean inc = true;
            int j = newModel.getColumnCount() - 3;
            do {
                if(i!=0)
                {
                    if(inc)
                    {
                        newModel.setValueAt(((int)newModel.getValueAt(i-1, j)+1), i, j);
                    }   
                    else
                    {
                        newModel.setValueAt(((int)newModel.getValueAt(i-1, j)), i, j);
                        //newModel.setValueAt(1, i, j);

                    }
                }
               
                if (newModel.getValueAt(i, j) == dimCount.get(j)) 
                {
                    newModel.setValueAt(0, i, j);
                    if (j > 0) 
                    {
                        inc = true;
                    } 
                    else 
                    {
                        inc = false;
                    }
                } 
                else 
                {
                    inc = false;
                }
                 j--;
            }while (inc);
            while (j >= 0) {
                if(i==0)
                {
                    newModel.setValueAt(0, i, j);
                }
                else
                {
                    newModel.setValueAt(newModel.getValueAt(i-1, j), i, j);
                }
                j--;
            }
        }
        //*/
        wires.setModel(newModel);
    }

    public String getProbs()
    {
        DefaultTableModel data = (DefaultTableModel)wires.getModel();   
        String probability = "with ";
        for(int i = 0; i< wires.getRowCount();i++)
        {
            String content = "prob "+ data.getValueAt(i, data.getColumnCount()-1)+ " prepare (";
            for (int j = 0; j < data.getColumnCount()-2;j++)
            {
                content+= data.getColumnName(j)+" = "+ data.getValueAt(i, j);
                if(j!=data.getColumnCount()-2)
                    content+=",";
                else
                    content+=")";
            }
            probability += content;
            if(i!= wires.getRowCount())
                probability+="\n elsewith ";
            else
                probability+="\n endwith ";  
        }
        return probability;
    }
    
    public boolean updateProbs() {
        float sum = 0;
        int rows = wires.getRowCount();
        int columns = wires.getColumnCount() - 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += (float) wires.getModel().getValueAt(i, j);
                data.setProb((String) wires.getModel().getColumnName(j), i, (float) wires.getModel().getValueAt(i, j));
            }
        }
        if (sum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        repAlice = new javax.swing.JPanel();
        aliceLabel = new javax.swing.JLabel();
        aliceContent = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        wires = new javax.swing.JTable();
        repEve = new javax.swing.JPanel();
        targets = new javax.swing.JComboBox();
        eveLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        repBob = new javax.swing.JPanel();
        bobLabel = new javax.swing.JLabel();
        modPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addModPre = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        modifierList = new javax.swing.JTable();
        removeMod = new javax.swing.JToggleButton();
        primary = new javax.swing.JCheckBox();
        modPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addModPre1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        modifierList1 = new javax.swing.JTable();
        removeMod1 = new javax.swing.JToggleButton();

        repAlice.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        repAlice.setLayout(new javax.swing.BoxLayout(repAlice, javax.swing.BoxLayout.Y_AXIS));

        aliceLabel.setText("Alice");
        repAlice.add(aliceLabel);

        aliceContent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        aliceContent.setLayout(new javax.swing.BoxLayout(aliceContent, javax.swing.BoxLayout.LINE_AXIS));

        wires.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Wire", "Probability", "AKey"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(wires);
        if (wires.getColumnModel().getColumnCount() > 0) {
            wires.getColumnModel().getColumn(0).setResizable(false);
            wires.getColumnModel().getColumn(1).setResizable(false);
        }

        aliceContent.add(jScrollPane2);

        repAlice.add(aliceContent);

        repEve.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        targets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetsActionPerformed(evt);
            }
        });

        eveLabel.setText("Eve");

        jLabel1.setText("Attack Target");

        javax.swing.GroupLayout repEveLayout = new javax.swing.GroupLayout(repEve);
        repEve.setLayout(repEveLayout);
        repEveLayout.setHorizontalGroup(
            repEveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repEveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(repEveLayout.createSequentialGroup()
                .addComponent(eveLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(repEveLayout.createSequentialGroup()
                .addComponent(targets, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        repEveLayout.setVerticalGroup(
            repEveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repEveLayout.createSequentialGroup()
                .addComponent(eveLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(targets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        repBob.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bobLabel.setText("Bob");

        javax.swing.GroupLayout repBobLayout = new javax.swing.GroupLayout(repBob);
        repBob.setLayout(repBobLayout);
        repBobLayout.setHorizontalGroup(
            repBobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repBobLayout.createSequentialGroup()
                .addComponent(bobLabel)
                .addGap(0, 55, Short.MAX_VALUE))
        );
        repBobLayout.setVerticalGroup(
            repBobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(repBobLayout.createSequentialGroup()
                .addComponent(bobLabel)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        modPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Modifiers");

        addModPre.setText("Add");
        addModPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModPreActionPerformed(evt);
            }
        });

        modifierList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modifiers"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(modifierList);

        removeMod.setText("Remove");
        removeMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modPanel1Layout = new javax.swing.GroupLayout(modPanel1);
        modPanel1.setLayout(modPanel1Layout);
        modPanel1Layout.setHorizontalGroup(
            modPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeMod))
                    .addGroup(modPanel1Layout.createSequentialGroup()
                        .addGroup(modPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(modPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(addModPre)))
                        .addContainerGap())))
        );
        modPanel1Layout.setVerticalGroup(
            modPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPanel1Layout.createSequentialGroup()
                .addGroup(modPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(addModPre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeMod))
        );

        primary.setText("Primary Density List?");
        primary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primaryActionPerformed(evt);
            }
        });

        modPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Modifiers");

        addModPre1.setText("Add");
        addModPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModPre1ActionPerformed(evt);
            }
        });

        modifierList1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Modifiers"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(modifierList1);

        removeMod1.setText("Remove");
        removeMod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMod1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modPanel2Layout = new javax.swing.GroupLayout(modPanel2);
        modPanel2.setLayout(modPanel2Layout);
        modPanel2Layout.setHorizontalGroup(
            modPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeMod1))
                    .addGroup(modPanel2Layout.createSequentialGroup()
                        .addGroup(modPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(modPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(addModPre1)))
                        .addContainerGap())))
        );
        modPanel2Layout.setVerticalGroup(
            modPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPanel2Layout.createSequentialGroup()
                .addGroup(modPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addModPre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeMod1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(repAlice, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(modPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(primary))
                .addGap(44, 44, 44)
                .addComponent(repEve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(repBob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(modPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(repAlice, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addComponent(primary))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(repEve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(repBob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getMods(int x)
    {
        String modList = "";
        javax.swing.JTable extract = null;
        if (x==1)
            extract = modifierList;
        else
            extract = modifierList1;
            
        for(int i = 0; i< extract.getRowCount();i++)
        {
            modList+= extract.getModel().getValueAt(i, 0);
            if(i!=extract.getRowCount())
                modList+= "\n";
        }
        return modList;
    }
    
    public String addModifier() {
        String choice = (String) JOptionPane.showInputDialog(
                GUI.popOut,
                "Which modifier will you add?",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                modifiers,
                modifiers[0]
        );
        if (choice.equals(modifiers[0])) {
            ArrayList<String> names = new ArrayList();
            for (Wire temp : data.getWires()) {
                names.add(temp.getName());
            }
            Object[] n = names.toArray();
            String target = (String) JOptionPane.showInputDialog(
                    GUI.popOut,
                    "Which wire is the target?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    n,
                    n[0]
            );
            String conditionA = (String) JOptionPane.showInputDialog(
                    GUI.popOut,
                    "Which wire should be checked?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    n,
                    n[0]
            );
            Object[] logic = {"=", ">", ">=", "<", "<=", "!="};
            String conditionB = (String) JOptionPane.showInputDialog(
                    GUI.popOut,
                    "What logical operator should be used?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    logic,
                    logic[0]
            );

            int value = -1;
            do {
                String conditionC = (String) JOptionPane.showInputDialog(
                        GUI.popOut,
                        "What is the value to be checked for?",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "0");

                try {
                    int i = Integer.parseInt(conditionC);
                    if (i >= 0) {
                        value = Integer.parseInt(conditionC);
                    }
                } catch (NumberFormatException e) {

                }
            } while (value == -1);

            return "apply " + choice + " to " + target + " if (" + conditionA + conditionB + value + ")";

        } else if (choice.equals(modifiers[1])) {

        } else if (choice.equals(modifiers[2])) {

        } else if (choice.equals(modifiers[3])) {

        } else if (choice.equals(modifiers[4])) {

        }

        return null;
    }

    private void addModPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModPreActionPerformed

        //newModel.addColumn("Modifiers");
        ArrayList<Object> tempRow = new ArrayList();
        tempRow.add(addModifier());
        Object[] tempData = tempRow.toArray();
        //newModel.addRow(tempData);

        DefaultTableModel modModel = (DefaultTableModel) modifierList.getModel();
        modModel.addRow(tempData);
        modifierList.setModel(modModel);
    }//GEN-LAST:event_addModPreActionPerformed

    private void removeModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeModActionPerformed
        DefaultTableModel modModel = (DefaultTableModel) modifierList.getModel();
        modModel.removeRow(modifierList.getSelectedRow());
        modifierList.setModel(modModel);

    }//GEN-LAST:event_removeModActionPerformed

    private void targetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetsActionPerformed

    private void primaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_primaryActionPerformed

    private void addModPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModPre1ActionPerformed
  ArrayList<Object> tempRow = new ArrayList();
        tempRow.add(addModifier());
        Object[] tempData = tempRow.toArray();
        //newModel.addRow(tempData);

        DefaultTableModel modModel = (DefaultTableModel) modifierList1.getModel();
        modModel.addRow(tempData);
        modifierList1.setModel(modModel);    }//GEN-LAST:event_addModPre1ActionPerformed

    private void removeMod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMod1ActionPerformed
 DefaultTableModel modModel = (DefaultTableModel) modifierList1.getModel();
        modModel.removeRow(modifierList1.getSelectedRow());
        modifierList1.setModel(modModel);    }//GEN-LAST:event_removeMod1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addModPre;
    private javax.swing.JButton addModPre1;
    private javax.swing.JPanel aliceContent;
    private javax.swing.JLabel aliceLabel;
    private javax.swing.JLabel bobLabel;
    private javax.swing.JLabel eveLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel modPanel1;
    private javax.swing.JPanel modPanel2;
    private javax.swing.JTable modifierList;
    private javax.swing.JTable modifierList1;
    private javax.swing.JCheckBox primary;
    private javax.swing.JToggleButton removeMod;
    private javax.swing.JToggleButton removeMod1;
    private javax.swing.JPanel repAlice;
    private javax.swing.JPanel repBob;
    private javax.swing.JPanel repEve;
    private javax.swing.JComboBox targets;
    private javax.swing.JTable wires;
    // End of variables declaration//GEN-END:variables
}
