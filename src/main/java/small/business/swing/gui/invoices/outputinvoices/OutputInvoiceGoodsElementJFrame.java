package small.business.swing.gui.invoices.outputinvoices;

import config.AppContext;
import org.springframework.context.ApplicationContext;
import small.business.businesslayer.NomenclatureService;
import small.business.businesslayer.OutputInvoicesService;
import small.business.businesslayer.SettingsService;
import small.business.dao.entity.Nomenclature;
import small.business.swing.gui.nomenclature.NomenclatureSelectJFrame;
import small.business.swing.gui.utils.ModalFrameUtil;

/**
 *
 * @author root
 */
public class OutputInvoiceGoodsElementJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private OutputInvoicesService outputInvoicesService = (OutputInvoicesService) ctx.getBean("outputInvoicesService");
    private SettingsService settingsService = (SettingsService) ctx.getBean("settingsService");
    private NomenclatureService nomenclatureService = (NomenclatureService) ctx.getBean("nomenclatureService");
    private Integer initialValue;

    /**
     * Creates new form ComingInvoiceGoodsElementJFrame
     */
    public OutputInvoiceGoodsElementJFrame() {
        initComponents();
        initialValue = outputInvoicesService.getCurrentGoodsElement().getQuantity();
        if (initialValue == null) {
            initialValue = 0;
        }
        if (outputInvoicesService.getCurrentGoodsElement().getInitialQuantity() != null) {
            initialValue = outputInvoicesService.getCurrentGoodsElement().getInitialQuantity();
        }
        if (outputInvoicesService.getCurrentGoodsElement().getNomenclature() != null) {
            if (outputInvoicesService.getCurrentGoodsElement().getNomenclature().getQuantity() > 0) {
                jSpinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(initialValue + outputInvoicesService.getCurrentGoodsElement().getNomenclature().getQuantity()), Integer.valueOf(1)));
            }
        }
        updateData();
        calcData();
        svalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelButtons = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jButtonCalc = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();
        jLabelQuantity = new javax.swing.JLabel();
        jLabelSmallWholeSale = new javax.swing.JLabel();
        jLabelNomenclature = new javax.swing.JLabel();
        jButtonNomenclatureSelect = new javax.swing.JButton();
        jSpinnerQuantity = new javax.swing.JSpinner();
        jLabelSum = new javax.swing.JLabel();
        jLabelBigWholeSale = new javax.swing.JLabel();
        jLabelRetail = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabelSumValue = new javax.swing.JLabel();
        jLabelNomenclatureTitle = new javax.swing.JLabel();
        jLabelRetailPrice = new javax.swing.JLabel();
        jLabelSmallWholeSalePrice = new javax.swing.JLabel();
        jLabelBigWholeSalePrice = new javax.swing.JLabel();
        jComboBoxTypeOfPrice = new javax.swing.JComboBox();
        jLabelTypeOfPrice = new javax.swing.JLabel();
        jSpinnerPrice = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Вибір товару");

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
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCalc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addGap(6, 6, 6)
                .addComponent(jButtonExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCalc)
                .addContainerGap())
        );

        jLabelQuantity.setText("Кількість:");

        jLabelSmallWholeSale.setText("Дрібний опт грн:");

        jLabelNomenclature.setText("Номенклатура:");

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

        jLabelBigWholeSale.setText("Крупний опт грн:");

        jLabelRetail.setText("Роздріб грн:");

        jLabelPrice.setText("Ціна:");

        jLabelSumValue.setText("0");

        jLabelRetailPrice.setText("0");

        jLabelSmallWholeSalePrice.setText("0");

        jLabelBigWholeSalePrice.setText("0");

        jComboBoxTypeOfPrice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Роздріб", "Дрібний опт", "Крупний опт" }));
        jComboBoxTypeOfPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeOfPriceActionPerformed(evt);
            }
        });

        jLabelTypeOfPrice.setText("Вид ціни:");

        jSpinnerPrice.setModel(new javax.swing.SpinnerNumberModel(0.01d, 0.01d, 999999.0d, 0.01d));
        jSpinnerPrice.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerPrice, "##.##"));
        jSpinnerPrice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPriceStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNomenclature, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTypeOfPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelQuantity, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelSum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRetail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelSmallWholeSale, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBigWholeSale, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelNomenclatureTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNomenclatureSelect))
                    .addComponent(jSpinnerQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTypeOfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSumValue)
                            .addComponent(jLabelRetailPrice)
                            .addComponent(jLabelSmallWholeSalePrice)
                            .addComponent(jLabelBigWholeSalePrice))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSpinnerPrice))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelNomenclatureTitle)
                    .addComponent(jLabelNomenclature)
                    .addComponent(jButtonNomenclatureSelect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTypeOfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTypeOfPrice))
                .addGap(6, 6, 6)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuantity)
                    .addComponent(jSpinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrice)
                    .addComponent(jSpinnerPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSum)
                    .addComponent(jLabelSumValue))
                .addGap(6, 6, 6)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRetail)
                    .addComponent(jLabelRetailPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSmallWholeSale)
                    .addComponent(jLabelSmallWholeSalePrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBigWholeSale)
                    .addComponent(jLabelBigWholeSalePrice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNomenclatureSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNomenclatureSelectActionPerformed
        nomenclatureService.setSelectType(Nomenclature.NOMENCLATURE);
        NomenclatureSelectJFrame ns = new NomenclatureSelectJFrame();
        ns.pack();
        ModalFrameUtil.showAsModal(ns, this);
        if (nomenclatureService.getSelectedElement() != null) {
            outputInvoicesService.getCurrentGoodsElement().setNomenclature(nomenclatureService.getSelectedElement());
            if (outputInvoicesService.getCurrentGoodsElement().getNomenclature() != null) {
                if (outputInvoicesService.getCurrentGoodsElement().getNomenclature().getQuantity() > 0) {
                    jSpinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(outputInvoicesService.getCurrentGoodsElement().getNomenclature().getQuantity()), Integer.valueOf(1)));
                } else {
                    jSpinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1)));
                }
            }
            updateData();
            jSpinnerPrice.setValue(getPrice());
            calcData();
        }
        svalidate();
    }//GEN-LAST:event_jButtonNomenclatureSelectActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        setData();
        outputInvoicesService.getCurrentElement().getGoods().add(outputInvoicesService.getCurrentGoodsElement());
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

    private void jComboBoxTypeOfPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeOfPriceActionPerformed
        jSpinnerPrice.setValue(getPrice());
        calcData();
        svalidate();
    }//GEN-LAST:event_jComboBoxTypeOfPriceActionPerformed

    private void jSpinnerPriceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPriceStateChanged
        calcData();
        svalidate();
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
            java.util.logging.Logger.getLogger(OutputInvoiceGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceGoodsElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OutputInvoiceGoodsElementJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalc;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonNomenclatureSelect;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxTypeOfPrice;
    private javax.swing.JLabel jLabelBigWholeSale;
    private javax.swing.JLabel jLabelBigWholeSalePrice;
    private javax.swing.JLabel jLabelNomenclature;
    private javax.swing.JLabel jLabelNomenclatureTitle;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelRetail;
    private javax.swing.JLabel jLabelRetailPrice;
    private javax.swing.JLabel jLabelSmallWholeSale;
    private javax.swing.JLabel jLabelSmallWholeSalePrice;
    private javax.swing.JLabel jLabelSum;
    private javax.swing.JLabel jLabelSumValue;
    private javax.swing.JLabel jLabelTypeOfPrice;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JSpinner jSpinnerPrice;
    private javax.swing.JSpinner jSpinnerQuantity;
    // End of variables declaration//GEN-END:variables
    private Double getPrice() {
        Double result = 0.0;
        if (outputInvoicesService.getCurrentGoodsElement().getNomenclature() != null) {
            if (jComboBoxTypeOfPrice.getSelectedIndex() == 0) {
                result = outputInvoicesService.getCurrentGoodsElement().getNomenclature().getRetailPrice() * settingsService.getExchangeRate();
            }
            if (jComboBoxTypeOfPrice.getSelectedIndex() == 1) {
                result = outputInvoicesService.getCurrentGoodsElement().getNomenclature().getSmallWholeSalePrice() * settingsService.getExchangeRate();
            }
            if (jComboBoxTypeOfPrice.getSelectedIndex() == 2) {
                result = outputInvoicesService.getCurrentGoodsElement().getNomenclature().getBigWholeSalePrice() * settingsService.getExchangeRate();
            }
        }
        return result;
    }

    private void setData() {
        outputInvoicesService.getCurrentGoodsElement().setQuantity((Integer) jSpinnerQuantity.getValue());
        outputInvoicesService.getCurrentGoodsElement().setTypeOfPrice(jComboBoxTypeOfPrice.getSelectedIndex() + 1);
        outputInvoicesService.getCurrentGoodsElement().setPrice((Double) jSpinnerPrice.getValue() * outputInvoicesService.getCurrentGoodsElement().getQuantity());
        updateData();
    }

    private void calcData() {
        jLabelSumValue.setText(Double.valueOf((Double) jSpinnerPrice.getValue() * (Integer) jSpinnerQuantity.getValue()).toString());
    }

    private void updateData() {
        jComboBoxTypeOfPrice.setSelectedIndex(outputInvoicesService.getCurrentGoodsElement().getTypeOfPrice() > 0 ? outputInvoicesService.getCurrentGoodsElement().getTypeOfPrice() - 1 : 0);
        jLabelNomenclatureTitle.setText(outputInvoicesService.getCurrentGoodsElement().toString());
        jSpinnerQuantity.setValue(outputInvoicesService.getCurrentGoodsElement().getQuantity());
        jSpinnerPrice.setValue(getPrice());
        jLabelSumValue.setText(outputInvoicesService.getCurrentGoodsElement().getSum().toString());
        jLabelRetailPrice.setText(Double.valueOf((outputInvoicesService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : outputInvoicesService.getCurrentGoodsElement().getNomenclature().getRetailPrice() * settingsService.getExchangeRate()).toString());
        jLabelSmallWholeSalePrice.setText(Double.valueOf((outputInvoicesService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : outputInvoicesService.getCurrentGoodsElement().getNomenclature().getSmallWholeSalePrice() * settingsService.getExchangeRate()).toString());
        jLabelBigWholeSalePrice.setText(Double.valueOf((outputInvoicesService.getCurrentGoodsElement().getNomenclature() == null) ? 0.0 : outputInvoicesService.getCurrentGoodsElement().getNomenclature().getBigWholeSalePrice() * settingsService.getExchangeRate()).toString());
    }

    private void svalidate() {
		outputInvoicesService.validate();
		jButtonNomenclatureSelect.setEnabled(outputInvoicesService.isCanChangeNomenclature());
		if (outputInvoicesService.getCurrentGoodsElement().getNomenclature() != null) {
			Integer quantity = (Integer) jSpinnerQuantity.getValue();
            Integer q = initialValue + (outputInvoicesService.getCurrentGoodsElement().getNomenclature().getQuantity() - quantity);
            if (q < 0) {
                jButtonSave.setEnabled(false);
            } else {
                jButtonSave.setEnabled(true);
            }
            if (initialValue - quantity > 0) {
                jButtonSave.setEnabled(true);
            }
        }
    }
}