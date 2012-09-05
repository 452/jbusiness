package small.business.swing.gui;

import config.AppContext;
import org.springframework.context.ApplicationContext;
import small.business.businesslayer.SettingsService;

/**
 * 
 * @author root
 */
public class SettingsJDialog extends javax.swing.JDialog {

	private static final long	serialVersionUID	= 1L;
	ApplicationContext			ctx					= AppContext.getApplicationContext();
	SettingsService				settingsService		= (SettingsService) ctx.getBean("settingsService");

	/**
	 * Creates new form SettingsJDialog
	 */
	public SettingsJDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		jSpinnerExchangeRate.setValue(settingsService.getExchangeRate());
		jTextAreaInfo.setText(settingsService.getNameData());
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
		jTextAreaInfo = new javax.swing.JTextArea();
		jSpinnerExchangeRate = new javax.swing.JSpinner();
		jButtonExit = new javax.swing.JButton();
		jButtonSave = new javax.swing.JButton();
		jLabelInfo = new javax.swing.JLabel();
		jLabelExchangeRate = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Налаштування");
		setModal(true);
		setResizable(false);

		jTextAreaInfo.setColumns(20);
		jTextAreaInfo.setRows(5);
		jScrollPane.setViewportView(jTextAreaInfo);

		jSpinnerExchangeRate.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.01d, 16789.0d, 0.1d));

		jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
		jButtonExit.setText("Вихід");
		jButtonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExitActionPerformed(evt);
			}
		});

		jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/save.png"))); // NOI18N
		jButtonSave.setText("Зберегти");
		jButtonSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSaveActionPerformed(evt);
			}
		});

		jLabelInfo.setText("Інформація про фірму");

		jLabelExchangeRate.setText("Курс валюти");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelExchangeRate).addComponent(jSpinnerExchangeRate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabelInfo)).addGap(0, 0, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jButtonSave).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonExit))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabelExchangeRate).addGap(1, 1, 1).addComponent(jSpinnerExchangeRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(7, 7, 7).addComponent(jLabelInfo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)).addGap(6, 6, 6)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExitActionPerformed
		dispose();
	}// GEN-LAST:event_jButtonExitActionPerformed

	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSaveActionPerformed
		settingsService.setExchangeRate(jSpinnerExchangeRate.getValue().toString());
		settingsService.setNameData(jTextAreaInfo.getText());
		dispose();
	}// GEN-LAST:event_jButtonSaveActionPerformed

	/**
	 * @param args
	 *            the command line arguments
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
		}
		catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SettingsJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SettingsJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SettingsJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SettingsJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				SettingsJDialog dialog = new SettingsJDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton		jButtonExit;
	private javax.swing.JButton		jButtonSave;
	private javax.swing.JLabel		jLabelExchangeRate;
	private javax.swing.JLabel		jLabelInfo;
	private javax.swing.JScrollPane	jScrollPane;
	private javax.swing.JSpinner	jSpinnerExchangeRate;
	private javax.swing.JTextArea	jTextAreaInfo;
	// End of variables declaration//GEN-END:variables
}
