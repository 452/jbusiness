package small.business.swing.gui.invoices.cominginvoices;

import config.AppContext;
import java.text.DateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.context.ApplicationContext;
import small.business.businesslayer.ComingsInvoicesService;
import small.business.dao.entity.ComingsInvoice;
import small.business.swing.gui.utils.ModalFrameUtil;

/**
 *
 * @author ihor
 */
public class ComingInvoicesListJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private ComingsInvoicesService comingsInvoicesService = (ComingsInvoicesService) ctx.getBean("comingsInvoicesService");
    private final static int OBJECT_COLUMN = 0;

    /**
     * Creates new form ComingInvoicesListJFrame
     */
    public ComingInvoicesListJFrame() {
        initComponents();
        getList();
    }

    private void getList() {
        DefaultTableModel dataModel = (DefaultTableModel) jTable.getModel();
        dataModel.setRowCount(0);
        try {
            for (ComingsInvoice comingsInvoice : comingsInvoicesService.getDataList()) {
                Object[] g = new Object[]{comingsInvoice, (comingsInvoice.getStatusOfPayment().equals(2) ? "Оплачено" : comingsInvoice.getStatusOfPayment().equals(1) ? "Частково оплачено" : "Не оплачено"), comingsInvoice.getInvoiceid(), DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(comingsInvoice.getCreatedDate()), (comingsInvoice.getCounterParty() != null) ? comingsInvoice.getCounterParty().getTitle() : null, (comingsInvoice.getStoreHouse() != null) ? comingsInvoice.getStoreHouse() : null};
                dataModel.addRow(g);
            }
            jTable.setModel(dataModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Помилка при отриманні списку накладних:\nінформація про помилку знаходиться в logJ.txt", null, JOptionPane.ERROR_MESSAGE);
        }
        svalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane = new javax.swing.JScrollPane();
		jTable = new javax.swing.JTable();
		jPanelButtons = new javax.swing.JPanel();
		jButtonAdd = new javax.swing.JButton();
		jButtonEdit = new javax.swing.JButton();
		jButtonRemove = new javax.swing.JButton();
		jButtonExit = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Список прихідних накладних");
		setExtendedState(6);

		jTable.setAutoCreateRowSorter(true);
		jTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "id", "Статус оплати", "№ вхідної накладної", "Дата створення", "Контрагент/Постачальник", "Склад" }) {
			private static final long	serialVersionUID	= 1L;
			@SuppressWarnings("rawtypes")
			Class[]						types				= new Class[] { java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class };
			boolean[]					canEdit				= new boolean[] { false, false, false, false, false, false };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
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
		jTable.getColumnModel().getColumn(0).setPreferredWidth(86);
		jTable.getColumnModel().getColumn(0).setMaxWidth(86);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(126);
		jTable.getColumnModel().getColumn(1).setMaxWidth(126);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(226);
		jTable.getColumnModel().getColumn(3).setMaxWidth(226);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(210);
		jTable.getColumnModel().getColumn(4).setMaxWidth(210);

		jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/add.png"))); // NOI18N
		jButtonAdd.setText("Створити");
		jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAddActionPerformed(evt);
			}
		});

		jButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/edit.png"))); // NOI18N
		jButtonEdit.setText("Редагувати");
		jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonEditActionPerformed(evt);
			}
		});

		jButtonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/delete.png"))); // NOI18N
		jButtonRemove.setText("Видалити");
		jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRemoveActionPerformed(evt);
			}
		});

		jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
		jButtonExit.setText("Закрити");
		jButtonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExitActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
		jPanelButtons.setLayout(jPanelButtonsLayout);
		jPanelButtonsLayout.setHorizontalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelButtonsLayout.createSequentialGroup().addContainerGap().addComponent(jButtonAdd).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonEdit).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonRemove).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonExit).addContainerGap(297, Short.MAX_VALUE)));
		jPanelButtonsLayout.setVerticalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelButtonsLayout.createSequentialGroup().addContainerGap().addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonExit)).addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jScrollPane));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        comingsInvoicesService.setCurrentElement(new ComingsInvoice());
        ComingInvoiceElementJFrame ce = new ComingInvoiceElementJFrame();
        ce.pack();
        ModalFrameUtil.showAsModal(ce, this);
        getList();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        edit();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseReleased
        svalidate();
    }//GEN-LAST:event_jTableMouseReleased

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() == 2) {
            edit();
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Так - Повернути товари на склад\nНі - Видалити без повернення товарів на склад", "Видалення запису", JOptionPane.YES_NO_CANCEL_OPTION);
        if (comingsInvoicesService.isCanRemoveInvoice()) {
            if (confirm == JOptionPane.YES_OPTION) {
                ComingsInvoice selectedObject = (ComingsInvoice) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
                try {
                    comingsInvoicesService.removeCurrentElement(selectedObject, true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Помилка при видаленні накладної:\nінформація про помилку знаходиться в logJ.txt", null, JOptionPane.ERROR_MESSAGE);
                }
                getList();
            }
            if (confirm == JOptionPane.NO_OPTION) {
                ComingsInvoice selectedObject = (ComingsInvoice) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
                try {
                    comingsInvoicesService.removeCurrentElement(selectedObject, false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Помилка при видаленні накладної:\nінформація про помилку знаходиться в logJ.txt", null, JOptionPane.ERROR_MESSAGE);
                }
                getList();
            }
        }

    }//GEN-LAST:event_jButtonRemoveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
		/*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel
         * /plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;


                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComingInvoicesListJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComingInvoicesListJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComingInvoicesListJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComingInvoicesListJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ComingInvoicesListJFrame().setVisible(true);
            }
        });
    }
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton		jButtonAdd;
	private javax.swing.JButton		jButtonEdit;
	private javax.swing.JButton		jButtonExit;
	private javax.swing.JButton		jButtonRemove;
	private javax.swing.JPanel		jPanelButtons;
	private javax.swing.JScrollPane	jScrollPane;
	private javax.swing.JTable		jTable;

	// End of variables declaration//GEN-END:variables
    private void svalidate() {
        // throw new UnsupportedOperationException("Not yet implemented");
        ComingsInvoice validateElement = null;
        if (jTable.getSelectedRow() >= 0) {
            validateElement = (ComingsInvoice) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
        }
        comingsInvoicesService.setCurrentElement(validateElement);
        comingsInvoicesService.validate();
        jButtonEdit.setEnabled(comingsInvoicesService.isCanEditInvoice());
        jButtonRemove.setEnabled(comingsInvoicesService.isCanRemoveInvoice());
    }

    private void edit() {
        if (comingsInvoicesService.isCanEditInvoice()) {
            ComingsInvoice selectedObject = (ComingsInvoice) jTable.getValueAt(jTable.getSelectedRow(), OBJECT_COLUMN);
            comingsInvoicesService.setCurrentElement(selectedObject);
            ComingInvoiceElementJFrame ce = new ComingInvoiceElementJFrame();
            ce.pack();
            ModalFrameUtil.showAsModal(ce, this);
            getList();
        }
    }
}