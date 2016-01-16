/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkd;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author waltersquires
 */
public class Main extends javax.swing.JPanel {
    GUI holder;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }
    public void setMaker(GUI maker)
    {
        holder = maker;  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        newListButton = new javax.swing.JButton();
        EditListButton = new javax.swing.JButton();
        testListButton = new javax.swing.JButton();

        buttonPanel.setLayout(new java.awt.GridLayout(3, 1));

        newListButton.setText("New Density List");
        newListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(newListButton);

        EditListButton.setText("Edit Density List");
        EditListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditListButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(EditListButton);

        testListButton.setText("Test Density List");
        buttonPanel.add(testListButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newListButtonActionPerformed
        ArrayList<Wire> tempWires = new ArrayList();
        int subspaces = -1;
        do
        {
            String s = (String)JOptionPane.showInputDialog(
                    GUI.popOut,
                    "How many subspaces will this density list have?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "2");
                    
            try
            {
                int i = Integer.parseInt(s);
                if(i>0)
                    subspaces =Integer.parseInt(s);
            }
            catch (NumberFormatException e)
            {
                    
            }
        }while(subspaces==-1);
for(int i = 0; i<subspaces; i++)
{
    int subSize = -1;
    int number = i+1;
    String subspace = (String)JOptionPane.showInputDialog(
                    GUI.popOut,
                    "What is the name of subspace "+ number +" ?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
    
    do
        {
            String s = (String)JOptionPane.showInputDialog(
                    GUI.popOut,
                    "What is the dimension of "+subspace+"?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "2");
                    
            try
            {
                int j = Integer.parseInt(s);
                if(j>0)
                    subSize =Integer.parseInt(s);
            }
            catch (NumberFormatException e)
            {
                    
            }
        }while(subSize==-1);
    tempWires.add(new Wire(subspace,subSize));
   
}
DensityList t = new DensityList(tempWires);
DensityListRepresentation temp = new DensityListRepresentation(t);
holder.addNewDensityList(temp);
holder.displayListBuilder();

    }//GEN-LAST:event_newListButtonActionPerformed

    private void EditListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditListButtonActionPerformed
DensityListRepresentation temp = new DensityListRepresentation();
holder.addNewDensityList(temp);
holder.displayListBuilder();
    }//GEN-LAST:event_EditListButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditListButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton newListButton;
    private javax.swing.JButton testListButton;
    // End of variables declaration//GEN-END:variables
}
