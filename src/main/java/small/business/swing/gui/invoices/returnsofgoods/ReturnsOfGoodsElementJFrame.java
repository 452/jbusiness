package small.business.swing.gui.invoices.returnsofgoods;

import java.awt.Color;

import org.springframework.context.ApplicationContext;

import small.business.businesslayer.NomenclatureService;
import small.business.businesslayer.ReturnsOfGoodsService;
import small.business.businesslayer.SettingsService;
import small.business.dao.entity.Nomenclature;
import small.business.swing.gui.nomenclature.NomenclatureSelectJFrame;
import small.business.swing.gui.utils.ModalFrameUtil;
import config.AppContext;

/**
 *
 * @author root
 */
public class ReturnsOfGoodsElementJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private NomenclatureService nomenclatureService = (NomenclatureService) ctx.getBean("nomenclatureService");
    private SettingsService settingsService = (SettingsService) ctx.getBean("settingsService");
    private ReturnsOfGoodsService returnsOfGoodsService = (ReturnsOfGoodsService) ctx.getBean("returnsOfGoodsService");

    /**
     * Creates new form ReturnsOfGoodsElementJFrame
     */
    public ReturnsOfGoodsElementJFrame() {
        initComponents();
        updateData();
        svalidate();
    }

    private void svalidate() {
    	returnsOfGoodsService.setValidateQuantityOfGoods((Integer) jSpinnerQuantity.getValue());
        returnsOfGoodsService.validate();
        jButtonSave.setEnabled(returnsOfGoodsService.isCanSaveGoods());
        jButtonNomenclatureSelect.setEnabled(returnsOfGoodsService.isCanChangeNomenclature());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jButtonNomenclatureSelect = new javax.swing.JButton();
		jSpinnerQuantity = new javax.swing.JSpinner();
		jLabelSum = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jSpinnerPrice = new javax.swing.JSpinner();
		jLabel5 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabelSumValue = new javax.swing.JLabel();
		jLabelNomenclatureTitle = new javax.swing.JLabel();
		jLabelNomenclaturePrice = new javax.swing.JLabel();
		jLabelRetailPrice = new javax.swing.JLabel();
		jLabelSmallWholeSalePrice = new javax.swing.JLabel();
		jLabelBigWholeSalePrice = new javax.swing.JLabel();
		jPanelButtons = new javax.swing.JPanel();
		jButtonSave = new javax.swing.JButton();
		jButtonExit = new javax.swing.JButton();
		jButtonCalc = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Повернення товару");

		jLabel2.setText("Кількість:");

		jLabel7.setText("Дрібний опт грн:");

		jLabel1.setText("Номенклатура:");

		jButtonNomenclatureSelect.setText("...");
		jButtonNomenclatureSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonNomenclatureSelectActionPerformed(evt);
			}
		});

		jSpinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));
		jSpinnerQuantity.setToolTipText("Відомості про товар");
		jSpinnerQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSpinnerQuantityStateChanged(evt);
			}
		});

		jLabelSum.setText("Сума:");

		jLabel8.setText("Крупний опт грн:");

		jLabel6.setText("Роздріб грн:");

		jSpinnerPrice.setModel(new javax.swing.SpinnerNumberModel(0.01d, 0.01d, 999999.0d, 0.01d));
		jSpinnerPrice.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSpinnerPriceStateChanged(evt);
			}
		});

		jLabel5.setText("Ціна номенклатури:");

		jLabel3.setText("Ціна:");

		jLabelSumValue.setText("0");

		jLabelNomenclaturePrice.setText("0");

		jLabelRetailPrice.setText("0");

		jLabelSmallWholeSalePrice.setText("0");

		jLabelBigWholeSalePrice.setText("0");

		javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
		jPanel.setLayout(jPanelLayout);
		jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel6).addComponent(jLabel5).addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabelSum)).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addComponent(jLabelNomenclatureTitle).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE).addComponent(jButtonNomenclatureSelect)).addComponent(jSpinnerQuantity).addComponent(jSpinnerPrice).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelSumValue).addComponent(jLabelNomenclaturePrice).addComponent(jLabelRetailPrice).addComponent(jLabelSmallWholeSalePrice).addComponent(jLabelBigWholeSalePrice)).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));
		jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jButtonNomenclatureSelect)).addGap(10, 10, 10)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup().addComponent(jLabelNomenclatureTitle).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jSpinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jSpinnerPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelSum).addComponent(jLabelSumValue)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(jLabelNomenclaturePrice)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(jLabelRetailPrice)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(jLabelSmallWholeSalePrice)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jLabelBigWholeSalePrice)).addContainerGap()));

		jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/save.png"))); // NOI18N
		jButtonSave.setText("Зберегти");
		jButtonSave.setEnabled(false);
		jButtonSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSaveActionPerformed(evt);
			}
		});

		jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
		jButtonExit.setText("Закрити");
		jButtonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExitActionPerformed(evt);
			}
		});

		jButtonCalc.setText("Порахувати");
		jButtonCalc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCalcActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
		jPanelButtons.setLayout(jPanelButtonsLayout);
		jPanelButtonsLayout.setHorizontalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelButtonsLayout.createSequentialGroup().addContainerGap().addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonCalc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
		jPanelButtonsLayout.setVerticalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelButtonsLayout.createSequentialGroup().addContainerGap().addComponent(jButtonSave).addGap(6, 6, 6).addComponent(jButtonExit).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButtonCalc).addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void jButtonNomenclatureSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNomenclatureSelectActionPerformed
        nomenclatureService.setSelectType(Nomenclature.NOMENCLATURE);
        NomenclatureSelectJFrame ns = new NomenclatureSelectJFrame();
        ns.pack();
        ModalFrameUtil.showAsModal(ns, this);
        returnsOfGoodsService.getCurrentGoodsElement().setNomenclature(nomenclatureService.getSelectedElement());
        if (returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() == null) {
            jButtonSave.setEnabled(false);
        } else {
            jButtonSave.setEnabled(true);
            jSpinnerPrice.setValue(returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getPrice());
        }
        updateData();
        // jSpinnerPrice.setValue(getPrice());
        calcData();
    }//GEN-LAST:event_jButtonNomenclatureSelectActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        setData();
        returnsOfGoodsService.getCurrentElement().getGoods().add(returnsOfGoodsService.getCurrentGoodsElement());
        dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcActionPerformed
        calcData();
        svalidate();
    }//GEN-LAST:event_jButtonCalcActionPerformed

    private void jSpinnerQuantityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerQuantityStateChanged
        calcData();
        svalidate();
    }//GEN-LAST:event_jSpinnerQuantityStateChanged

    private void jSpinnerPriceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPriceStateChanged
        calcData();
    }//GEN-LAST:event_jSpinnerPriceStateChanged

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
            java.util.logging.Logger.getLogger(ReturnsOfGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnsOfGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnsOfGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnsOfGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnsOfGoodsElementJFrame().setVisible(true);
            }
        });
    }
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton		jButtonCalc;
	private javax.swing.JButton		jButtonExit;
	private javax.swing.JButton		jButtonNomenclatureSelect;
	private javax.swing.JButton		jButtonSave;
	private javax.swing.JLabel		jLabel1;
	private javax.swing.JLabel		jLabel2;
	private javax.swing.JLabel		jLabel3;
	private javax.swing.JLabel		jLabel5;
	private javax.swing.JLabel		jLabel6;
	private javax.swing.JLabel		jLabel7;
	private javax.swing.JLabel		jLabel8;
	private javax.swing.JLabel		jLabelBigWholeSalePrice;
	private javax.swing.JLabel		jLabelNomenclaturePrice;
	private javax.swing.JLabel		jLabelNomenclatureTitle;
	private javax.swing.JLabel		jLabelRetailPrice;
	private javax.swing.JLabel		jLabelSmallWholeSalePrice;
	private javax.swing.JLabel		jLabelSum;
	private javax.swing.JLabel		jLabelSumValue;
	private javax.swing.JPanel		jPanel;
	private javax.swing.JPanel		jPanelButtons;
	private javax.swing.JSpinner	jSpinnerPrice;
	private javax.swing.JSpinner	jSpinnerQuantity;

	// End of variables declaration//GEN-END:variables
    private void calcData() {
        jLabelSumValue.setText(Double.valueOf((Double) jSpinnerPrice.getValue() * (Integer) jSpinnerQuantity.getValue()).toString());
    }

    private void setData() {
        returnsOfGoodsService.getCurrentGoodsElement().setQuantity((Integer) jSpinnerQuantity.getValue());
        returnsOfGoodsService.getCurrentGoodsElement().setPrice((Double) jSpinnerPrice.getValue());
        updateData();
    }

    private void updateData() {
        jLabelNomenclatureTitle.setText(returnsOfGoodsService.getCurrentGoodsElement().toString());
        jSpinnerQuantity.setValue(returnsOfGoodsService.getCurrentGoodsElement().getQuantity());
        jSpinnerPrice.setValue(returnsOfGoodsService.getCurrentGoodsElement().getPrice());

        jLabelSumValue.setText(returnsOfGoodsService.getCurrentGoodsElement().getSum().toString());
        if (returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() != null && returnsOfGoodsService.getCurrentGoodsElement().getPrice() > returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getPrice()) {
            jLabelNomenclaturePrice.setForeground(Color.red);
        } else {
            jLabelNomenclaturePrice.setForeground(null);
        }
        jLabelNomenclaturePrice.setText((returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() == null) ? null : returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getPrice().toString());
        jLabelRetailPrice.setText(Double.valueOf((returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getRetailPrice() * settingsService.getExchangeRate()).toString());
        jLabelSmallWholeSalePrice.setText(Double.valueOf((returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getSmallWholeSalePrice() * settingsService.getExchangeRate()).toString());
        jLabelBigWholeSalePrice.setText(Double.valueOf((returnsOfGoodsService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : returnsOfGoodsService.getCurrentGoodsElement().getNomenclature().getBigWholeSalePrice() * settingsService.getExchangeRate()).toString());
    }
}