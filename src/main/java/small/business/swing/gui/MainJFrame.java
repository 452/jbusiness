package small.business.swing.gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import small.business.businesslayer.SettingsService;
import small.business.swing.gui.counterparties.CounterPartiesListJFrame;
import small.business.swing.gui.history.HistoryJFrame;
import small.business.swing.gui.invoices.cominginvoices.SuplyJFrame;
import small.business.swing.gui.invoices.outputinvoices.TradeJFrame;
import small.business.swing.gui.invoices.returnsofgoods.ReturnsOfGoodsJFrame;
import small.business.swing.gui.nomenclature.NomenclatureListJFrame;
import small.business.swing.gui.reports.ReportsJFrame;
import small.business.swing.gui.storehouses.StoreHousesJFrame;
import small.business.swing.gui.utils.ModalFrameUtil;
import small.business.swing.gui.utils.TrayMessage;
import small.business.u18n.Languages;
import config.ApplicationConfig;
import small.business.swing.gui.priceslist.PricesListJFrame;
import small.business.swing.gui.repair.RepairListJFrame;

/**
 *
 * @author root
 */
public class MainJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(MainJFrame.class.getName());
    
	static {
		System.setProperty("mail.smtp.starttls.enable", "true");
		System.setProperty("mail.smtp.quitwait", "false");
	}

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        //System.out.println(Locale.getDefault().getDisplayName());
        //System.out.println(Locale.getDefault().getLanguage());
        //System.out.println(Locale.getDefault().getCountry());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"deprecation"})
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jButtonHistory = new javax.swing.JButton();
        jButtonTrade = new javax.swing.JButton();
        jButtonCounterParties = new javax.swing.JButton();
        jButtonAbout = new javax.swing.JButton();
        jButtonNomenclature = new javax.swing.JButton();
        jButtonStoreHouses = new javax.swing.JButton();
        jButtonReports = new javax.swing.JButton();
        jButtonPricesList = new javax.swing.JButton();
        jButtonRepair = new javax.swing.JButton();
        jButtonSuply = new javax.swing.JButton();
        jButtonSettings = new javax.swing.JButton();
        jButtonCashRegister = new javax.swing.JButton();
        jButtonSite = new javax.swing.JButton();
        jButtonReturnsOfGoods = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Small Business");
        setExtendedState(6);
        setIconImages(null);
        setName("frameMain"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButtonHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/history.png"))); // NOI18N
        jButtonHistory.setText(Languages.getTranslatedText("history"));
        jButtonHistory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonHistory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHistory.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonHistory.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonHistory.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonHistory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoryActionPerformed(evt);
            }
        });

        jButtonTrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/trade.png"))); // NOI18N
        jButtonTrade.setText("Торгівля");
        jButtonTrade.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonTrade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTrade.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonTrade.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonTrade.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonTrade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTradeActionPerformed(evt);
            }
        });

        jButtonCounterParties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/counterparties.png"))); // NOI18N
        jButtonCounterParties.setText("Контрагенти");
        jButtonCounterParties.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonCounterParties.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCounterParties.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonCounterParties.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonCounterParties.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonCounterParties.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCounterParties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCounterPartiesActionPerformed(evt);
            }
        });

        jButtonAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/about.png"))); // NOI18N
        jButtonAbout.setText("Про програму");
        jButtonAbout.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbout.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonAbout.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonAbout.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });

        jButtonNomenclature.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/nomenclature.png"))); // NOI18N
        jButtonNomenclature.setText("Номенклатура товарів");
        jButtonNomenclature.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonNomenclature.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNomenclature.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonNomenclature.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonNomenclature.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonNomenclature.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNomenclature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNomenclatureActionPerformed(evt);
            }
        });

        jButtonStoreHouses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/storehouses.png"))); // NOI18N
        jButtonStoreHouses.setText("Склади та запаси в них");
        jButtonStoreHouses.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonStoreHouses.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonStoreHouses.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonStoreHouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStoreHousesActionPerformed(evt);
            }
        });

        jButtonReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/reports.png"))); // NOI18N
        jButtonReports.setText("Звіти");
        jButtonReports.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonReports.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonReports.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportsActionPerformed(evt);
            }
        });

        jButtonPricesList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/xls.png"))); // NOI18N
        jButtonPricesList.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonPricesList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPricesList.setLabel("Прайс лист");
        jButtonPricesList.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonPricesList.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonPricesList.setName(""); // NOI18N
        jButtonPricesList.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonPricesList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPricesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPricesListActionPerformed(evt);
            }
        });

        jButtonRepair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/repair.png"))); // NOI18N
        jButtonRepair.setText("Ремонт");
        jButtonRepair.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonRepair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRepair.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonRepair.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonRepair.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonRepair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRepairActionPerformed(evt);
            }
        });

        jButtonSuply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/suply.png"))); // NOI18N
        jButtonSuply.setText("Закупки");
        jButtonSuply.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonSuply.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSuply.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonSuply.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonSuply.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonSuply.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSuply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuplyActionPerformed(evt);
            }
        });

        jButtonSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/settings.png"))); // NOI18N
        jButtonSettings.setText("Налаштування");
        jButtonSettings.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSettings.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonSettings.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonSettings.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });

        jButtonCashRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/cashregister.png"))); // NOI18N
        jButtonCashRegister.setText("Каса");
        jButtonCashRegister.setEnabled(false);
        jButtonCashRegister.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonCashRegister.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCashRegister.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonCashRegister.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonCashRegister.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonCashRegister.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCashRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCashRegisterActionPerformed(evt);
            }
        });

        jButtonSite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/site.png"))); // NOI18N
        jButtonSite.setText("Сайт");
        jButtonSite.setEnabled(false);
        jButtonSite.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonSite.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSite.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonSite.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonSite.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonSite.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiteActionPerformed(evt);
            }
        });

        jButtonReturnsOfGoods.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/returnsofgoods.png"))); // NOI18N
        jButtonReturnsOfGoods.setText("Повернення товарів");
        jButtonReturnsOfGoods.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButtonReturnsOfGoods.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonReturnsOfGoods.setMaximumSize(new java.awt.Dimension(175, 161));
        jButtonReturnsOfGoods.setMinimumSize(new java.awt.Dimension(175, 161));
        jButtonReturnsOfGoods.setPreferredSize(new java.awt.Dimension(175, 161));
        jButtonReturnsOfGoods.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonReturnsOfGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnsOfGoodsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSuply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNomenclature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReturnsOfGoods, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonStoreHouses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTrade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonCounterParties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPricesList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCashRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRepair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTrade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSuply, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPricesList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNomenclature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCounterParties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCashRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonStoreHouses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReturnsOfGoods, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
        SettingsJDialog sf = new SettingsJDialog(this, true);
        sf.setLocationRelativeTo(null);
        sf.pack();
        sf.setVisible(true);
    }//GEN-LAST:event_jButtonSettingsActionPerformed

    private void jButtonPricesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPricesListActionPerformed
        PricesListJFrame pricesList = new PricesListJFrame();
        pricesList.pack();
        ModalFrameUtil.showAsModal(pricesList, this);
    }//GEN-LAST:event_jButtonPricesListActionPerformed

    private void jButtonSuplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuplyActionPerformed
        SuplyJFrame suply = new SuplyJFrame();
        suply.pack();
        ModalFrameUtil.showAsModal(suply, this);
    }//GEN-LAST:event_jButtonSuplyActionPerformed

    private void jButtonTradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTradeActionPerformed
        TradeJFrame trade = new TradeJFrame();
        trade.pack();
        ModalFrameUtil.showAsModal(trade, this);
    }//GEN-LAST:event_jButtonTradeActionPerformed

    private void jButtonRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRepairActionPerformed
        RepairListJFrame repairList = new RepairListJFrame();
        repairList.pack();
        ModalFrameUtil.showAsModal(repairList, this);
    }//GEN-LAST:event_jButtonRepairActionPerformed

    private void jButtonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportsActionPerformed
        ReportsJFrame reports = new ReportsJFrame();
        reports.pack();
        ModalFrameUtil.showAsModal(reports, this);
    }//GEN-LAST:event_jButtonReportsActionPerformed

    private void jButtonSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiteActionPerformed
    }//GEN-LAST:event_jButtonSiteActionPerformed

    private void jButtonNomenclatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNomenclatureActionPerformed
        NomenclatureListJFrame nomenclatureList = new NomenclatureListJFrame();
        nomenclatureList.pack();
        ModalFrameUtil.showAsModal(nomenclatureList, this);
    }//GEN-LAST:event_jButtonNomenclatureActionPerformed

    private void jButtonStoreHousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStoreHousesActionPerformed
        StoreHousesJFrame storeHouses = new StoreHousesJFrame();
        storeHouses.pack();
        ModalFrameUtil.showAsModal(storeHouses, this);
    }//GEN-LAST:event_jButtonStoreHousesActionPerformed

    private void jButtonCounterPartiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCounterPartiesActionPerformed
        CounterPartiesListJFrame сounterPartiesList = new CounterPartiesListJFrame();
        сounterPartiesList.pack();
        ModalFrameUtil.showAsModal(сounterPartiesList, this);
    }//GEN-LAST:event_jButtonCounterPartiesActionPerformed

    private void jButtonCashRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCashRegisterActionPerformed
    }//GEN-LAST:event_jButtonCashRegisterActionPerformed

    private void jButtonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAboutActionPerformed
        AboutJFrame about = new AboutJFrame();
        about.pack();
        ModalFrameUtil.showAsModal(about, this);
    }//GEN-LAST:event_jButtonAboutActionPerformed

    private void jButtonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoryActionPerformed
        HistoryJFrame history = new HistoryJFrame();
        history.pack();
        ModalFrameUtil.showAsModal(history, this);
    }//GEN-LAST:event_jButtonHistoryActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        log.info("Close program");
    }//GEN-LAST:event_formWindowClosed

    private void jButtonReturnsOfGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnsOfGoodsActionPerformed
        ReturnsOfGoodsJFrame returnsOfGoods = new ReturnsOfGoodsJFrame();
        returnsOfGoods.pack();
        ModalFrameUtil.showAsModal(returnsOfGoods, this);
    }//GEN-LAST:event_jButtonReturnsOfGoodsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
		/*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {// Nimbus GTK+ Windows
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("Start program");
                    long start = System.currentTimeMillis();
                    if (SystemTray.isSupported()) {
                        final SystemTray tray = SystemTray.getSystemTray();
                        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/small/business/swing/gui/images/settings.png"));
                        PopupMenu popup = new PopupMenu();
                        final TrayIcon trayIcon = new TrayIcon(image, "Small business", popup);
                        trayIcon.setImageAutoSize(true);
                        MenuItem item = new MenuItem("Вихід");
                        item.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tray.remove(trayIcon);
                                System.exit(0);
                            }
                        });
                        popup.addSeparator();
                        popup.add(item);
                        try {
                            tray.add(trayIcon);
                            // trayIcon.displayMessage("Small business",
                            // "Ініціалізація програми",
                            // TrayIcon.MessageType.INFO);
                            TrayMessage trayMessage = new TrayMessage();
                            trayMessage.showTrayMessageInfo("Ініціалізація програми");
                        } catch (AWTException ex) {
                        	log.debug("Can't add to tray>> " + ex);
                        }
                    } else {
                    	log.debug("Tray unavailable");
                    }
                    long end = System.currentTimeMillis();
                    log.debug("Time initialize tray: " + (end - start) + " ms");
                    start = System.currentTimeMillis();
                    try {
                        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
                        SettingsService settingsService = (SettingsService) ctx.getBean("settingsService");
                        settingsService.getExchangeRate();
                        // throw new SettingsService();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Помилка старту програми:\nінформація про помилку знаходиться в logJ.txt", null, JOptionPane.ERROR_MESSAGE);
                        log.error(e.getMessage(), e);
                        System.exit(0);
                    }
                    end = System.currentTimeMillis();
                    log.debug("Time initialize spring: " + (end - start) + " ms");
                    new MainJFrame().setVisible(true);
                } catch (Exception e) {
                	log.error(e.getMessage(), e);
                    JOptionPane.showMessageDialog(null, "Помилка програми:\n" + e, null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbout;
    private javax.swing.JButton jButtonCashRegister;
    private javax.swing.JButton jButtonCounterParties;
    private javax.swing.JButton jButtonHistory;
    private javax.swing.JButton jButtonNomenclature;
    private javax.swing.JButton jButtonPricesList;
    private javax.swing.JButton jButtonRepair;
    private javax.swing.JButton jButtonReports;
    private javax.swing.JButton jButtonReturnsOfGoods;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JButton jButtonSite;
    private javax.swing.JButton jButtonStoreHouses;
    private javax.swing.JButton jButtonSuply;
    private javax.swing.JButton jButtonTrade;
    private javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables
}
