package small.business.swing.gui.counterparties;

import config.AppContext;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.context.ApplicationContext;
import small.business.businesslayer.CounterPartiesService;
import small.business.businesslayer.HistoryService;
import small.business.dao.entity.CounterParties;
import small.business.swing.gui.utils.ModalFrameUtil;

/**
 *
 * @author ihor
 */
public class CounterPartiesListJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private CounterPartiesService counterPartiesService = (CounterPartiesService) ctx.getBean("counterPartiesService");
    private HistoryService historyService = (HistoryService) ctx.getBean("historyService");
    private final static int OBJECT_COLUMN = 1;

    public CounterPartiesListJFrame() {
        initComponents();
        getList();
        svalidate();
    }

    private void getList() {
        DefaultTableModel dataModel = (DefaultTableModel) jTable.getModel();
        dataModel.setRowCount(0);
        for (CounterParties goods : counterPartiesService.getDataList()) {
            Object[] g = new Object[]{(goods.getIsgroup().equals(CounterParties.GROUP)) ? "Група" : "-", goods};
            dataModel.addRow(g);
        }
        jTable.setModel(dataModel);
        svalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonAddGroup = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonMove = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Список");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        jScrollPane.setAutoscrolls(true);

        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"\u0413\u0440\u0443\u043F\u0430", "\u041D\u0430\u0439\u043C\u0435\u043D\u0443\u0432\u0430\u043D\u043D\u044F"
        	}
        ) {
        	/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;
			boolean[] columnEditables = new boolean[] {
        		false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(0).setMaxWidth(50);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMouseReleased(evt);
            }
        });
        jScrollPane.setViewportView(jTable);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/add.png"))); // NOI18N
        jButtonAdd.setText("Створити");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonAddGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/addgroup.png"))); // NOI18N
        jButtonAddGroup.setText("Створити групу");
        jButtonAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddGroupActionPerformed(evt);
            }
        });

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
        jButtonExit.setText("Вихід");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/edit.png"))); // NOI18N
        jButtonEdit.setText("Редагувати");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonMove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/move.png"))); // NOI18N
        jButtonMove.setText("Перемістити");
        jButtonMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveActionPerformed(evt);
            }
        });

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/delete.png"))); // NOI18N
        jButtonDelete.setText("Видалити");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAddGroup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonMove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jButtonExit)
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAddGroup, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonMove)
                        .addComponent(jButtonDelete))
                    .addComponent(jButtonEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        CounterParties c = new CounterParties();
        c.setIsgroup(CounterParties.COUNTERPARTY);
        counterPartiesService.setCurrentElement(c);
        CounterPartyElementJFrame counterPartyElement = new CounterPartyElementJFrame();
        counterPartyElement.pack();
        ModalFrameUtil.showAsModal(counterPartyElement, this);
        getList();
        historyService.saveActionOfAdd("Контрагенти", counterPartiesService.getCurrentElement().getTitle());
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddGroupActionPerformed
        CounterParties c = new CounterParties();
        c.setIsgroup(CounterParties.GROUP);
        counterPartiesService.setCurrentElement(c);
        CounterPartyElementJFrame counterPartyElement = new CounterPartyElementJFrame();
        counterPartyElement.pack();
        ModalFrameUtil.showAsModal(counterPartyElement, this);
        getList();
        historyService.saveActionOfAdd("Контрагенти", counterPartiesService.getCurrentElement().getTitle());
    }//GEN-LAST:event_jButtonAddGroupActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        if (jTable.getSelectedRow() >= 0) {
            CounterParties selectedObject = (CounterParties) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            counterPartiesService.setCurrentElement(selectedObject);
            CounterPartyElementJFrame counterPartyElement = new CounterPartyElementJFrame();
            counterPartyElement.pack();
            ModalFrameUtil.showAsModal(counterPartyElement, this);
            getList();
        }
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() == 2) {
            CounterParties selectedObject = (CounterParties) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            counterPartiesService.setCurrentElement(selectedObject);
            if (selectedObject.getIsgroup().contentEquals(CounterParties.GROUP)) {
                counterPartiesService.setCurrentCategory(selectedObject);
                getList();
            } else {
                CounterPartyElementJFrame counterPartyElement = new CounterPartyElementJFrame();
                counterPartyElement.pack();
                ModalFrameUtil.showAsModal(counterPartyElement, this);
                getList();
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jButtonMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveActionPerformed
        if (jTable.getSelectedRow() >= 0) {
            counterPartiesService.setSelectType(CounterParties.GROUP);
            CounterParties selectedObject = (CounterParties) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            counterPartiesService.setCurrentElement(selectedObject);
            CounterPartiesSelectJFrame counterPartiesSelect = new CounterPartiesSelectJFrame();
            counterPartiesSelect.pack();
            ModalFrameUtil.showAsModal(counterPartiesSelect, this);
            counterPartiesService.move();
            getList();
        }
    }//GEN-LAST:event_jButtonMoveActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Дійсно видалити запис?", "", JOptionPane.YES_NO_OPTION);
        if ((jTable.getSelectedRow() >= 0) && (confirm == JOptionPane.YES_OPTION)) {
            CounterParties selectedObject = (CounterParties) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            counterPartiesService.removeCurrentElement(selectedObject);
            historyService.saveActionOfRemoval("Контрагенти", selectedObject.getTitle());
            getList();
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseReleased
        svalidate();
    }//GEN-LAST:event_jTableMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CounterPartiesListJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CounterPartiesListJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CounterPartiesListJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CounterPartiesListJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CounterPartiesListJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddGroup;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonMove;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

    private void svalidate() {
        if (jTable.getSelectedRow() >= 0) {
            CounterParties selectedObject = (CounterParties) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            counterPartiesService.setValidateElement(selectedObject);
        }
        counterPartiesService.validate();
        jButtonEdit.setEnabled(counterPartiesService.getCanEdit());
        jButtonMove.setEnabled(counterPartiesService.getCanMove());
        jButtonDelete.setEnabled(counterPartiesService.getCanRemove());
    }
}