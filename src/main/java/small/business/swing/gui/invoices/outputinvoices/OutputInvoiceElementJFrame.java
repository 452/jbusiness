package small.business.swing.gui.invoices.outputinvoices;

import config.AppContext;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import small.business.businesslayer.CounterPartiesService;
import small.business.businesslayer.OutputInvoicesService;
import small.business.businesslayer.SettingsService;
import small.business.businesslayer.StoreHousesService;
import small.business.dao.entity.CounterParties;
import small.business.dao.entity.OutputGoods;
import small.business.swing.gui.counterparties.CounterPartiesSelectJFrame;
import small.business.swing.gui.storehouses.StoreHouseSelectJFrame;
import small.business.swing.gui.utils.ModalFrameUtil;

/**
 *
 * @author root
 */
public class OutputInvoiceElementJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(OutputInvoiceElementJFrame.class.getName());
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private OutputInvoicesService outputInvoicesService = (OutputInvoicesService) ctx.getBean("outputInvoicesService");
    private SettingsService settingsService = (SettingsService) ctx.getBean("settingsService");
    private CounterPartiesService counterPartiesService = (CounterPartiesService) ctx.getBean("counterPartiesService");
    private StoreHousesService storeHousesService = (StoreHousesService) ctx.getBean("storeHousesService");
    private final static int OBJECT_COLUMN = 1;

    /**
     * Creates new form ComingInvoiceElementJFrame
     */
    public OutputInvoiceElementJFrame() {
        initComponents();
        if (outputInvoicesService.getCurrentElement().getCounterParty() != null) {
            jLabelCounterParty.setText(outputInvoicesService.getCurrentElement().getCounterParty().getTitle());
        }
        if (outputInvoicesService.getCurrentElement().getStoreHouse() != null) {
            jLabelStoreHouse.setText(outputInvoicesService.getCurrentElement().getStoreHouse().getTitle());
        }

        jSpinnerPaidAmount.setValue(outputInvoicesService.getCurrentElement().getPaidAmount());
        jComboBoxStatusOfPayment.setSelectedIndex(outputInvoicesService.getCurrentElement().getStatusOfPayment());
        jTextAreaInfo.setText(outputInvoicesService.getCurrentElement().getInfo());
        getGoodsList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "rawtypes", "serial"})
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInvoiceInfo = new javax.swing.JPanel();
        jButtonCounterPartySelect = new javax.swing.JButton();
        jButtonStoreHouseSelect = new javax.swing.JButton();
        jPanelInfo = new javax.swing.JPanel();
        jLabelStoreHouse = new javax.swing.JLabel();
        jLabelCounterParty = new javax.swing.JLabel();
        jSpinnerPaidAmount = new javax.swing.JSpinner();
        jComboBoxStatusOfPayment = new javax.swing.JComboBox();
        jPanelLabels = new javax.swing.JPanel();
        jLabelStoreHouseTitle = new javax.swing.JLabel();
        jLabelCounterPartyTitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfo = new javax.swing.JTextArea();
        jPanelButtons = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jPanelGoods = new javax.swing.JPanel();
        jButtonAddGoods = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTableGoods = new javax.swing.JTable();
        jButtonRemoveGoods = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelAllGoodsQuantity = new javax.swing.JLabel();
        jLabelAllGoodsPrice = new javax.swing.JLabel();
        jButtonEditGoods = new javax.swing.JButton();
        jButtonsShowPictures = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Видаткова накладна");
        setExtendedState(6);

        jPanelInvoiceInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Інформація накладної"));

        jButtonCounterPartySelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/counterparty.png"))); // NOI18N
        jButtonCounterPartySelect.setText("1 Вибрати клієнта");
        jButtonCounterPartySelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCounterPartySelectActionPerformed(evt);
            }
        });

        jButtonStoreHouseSelect.setText("2 Вибрати склад");
        jButtonStoreHouseSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStoreHouseSelectActionPerformed(evt);
            }
        });

        jLabelStoreHouse.setText("не вибрано");

        jLabelCounterParty.setText("не вибрано");

        jSpinnerPaidAmount.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.01d)));

        jComboBoxStatusOfPayment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Не оплачено", "Частково оплачено", "Оплачено" }));

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCounterParty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelStoreHouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSpinnerPaidAmount, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxStatusOfPayment, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 224, Short.MAX_VALUE))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelCounterParty)
                .addGap(11, 11, 11)
                .addComponent(jLabelStoreHouse)
                .addGap(11, 11, 11)
                .addComponent(jSpinnerPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jComboBoxStatusOfPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelStoreHouseTitle.setText("Склад:");

        jLabelCounterPartyTitle.setText("Контрагент:");

        jLabel3.setText("Сплачена сума:");

        jLabel4.setText("Статус оплати:");

        javax.swing.GroupLayout jPanelLabelsLayout = new javax.swing.GroupLayout(jPanelLabels);
        jPanelLabels.setLayout(jPanelLabelsLayout);
        jPanelLabelsLayout.setHorizontalGroup(
            jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCounterPartyTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelStoreHouseTitle, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2))
        );
        jPanelLabelsLayout.setVerticalGroup(
            jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLabelsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLabelsLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabelStoreHouseTitle))
                    .addComponent(jLabelCounterPartyTitle))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jLabelInfo.setText("Додаткова інформація:");

        jTextAreaInfo.setColumns(20);
        jTextAreaInfo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfo);

        javax.swing.GroupLayout jPanelInvoiceInfoLayout = new javax.swing.GroupLayout(jPanelInvoiceInfo);
        jPanelInvoiceInfo.setLayout(jPanelInvoiceInfoLayout);
        jPanelInvoiceInfoLayout.setHorizontalGroup(
            jPanelInvoiceInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInvoiceInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInvoiceInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelInvoiceInfoLayout.createSequentialGroup()
                        .addComponent(jLabelInfo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelInvoiceInfoLayout.createSequentialGroup()
                        .addComponent(jPanelLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanelInvoiceInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonCounterPartySelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonStoreHouseSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelInvoiceInfoLayout.setVerticalGroup(
            jPanelInvoiceInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInvoiceInfoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelInvoiceInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelInvoiceInfoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButtonStoreHouseSelect))
                    .addComponent(jButtonCounterPartySelect)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelLabels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/save.png"))); // NOI18N
        jButtonSave.setText("5 Зберегти");
        jButtonSave.setEnabled(false);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
        jButtonExit.setText("6 Закрити");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jButtonPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/print.png"))); // NOI18N
        jButtonPrint.setText("4 Друкувати");
        jButtonPrint.setEnabled(false);
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit)
                .addGap(6, 6, 6))
        );

        jPanelGoods.setBorder(javax.swing.BorderFactory.createTitledBorder("Товари"));

        jButtonAddGoods.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/add.png"))); // NOI18N
        jButtonAddGoods.setText("3 Додати");
        jButtonAddGoods.setEnabled(false);
        jButtonAddGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddGoodsActionPerformed(evt);
            }
        });

        jTableGoods.setAutoCreateRowSorter(true);
        jTableGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Номенклатура", "Заводський артикул", "Внутрішній артикул", "Кількість", "Вид ціни", "Ціна", "Сума"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGoods.getTableHeader().setReorderingAllowed(false);
        jTableGoods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableGoodsMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(jTableGoods);
        jTableGoods.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTableGoods.getColumnModel().getColumn(0).setMaxWidth(60);
        jTableGoods.getColumnModel().getColumn(1).setMinWidth(200);
        jTableGoods.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTableGoods.getColumnModel().getColumn(4).setMaxWidth(70);
        jTableGoods.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTableGoods.getColumnModel().getColumn(5).setMaxWidth(120);
        jTableGoods.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTableGoods.getColumnModel().getColumn(6).setMaxWidth(60);
        jTableGoods.getColumnModel().getColumn(7).setPreferredWidth(60);
        jTableGoods.getColumnModel().getColumn(7).setMaxWidth(60);

        jButtonRemoveGoods.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/delete.png"))); // NOI18N
        jButtonRemoveGoods.setText("Видалити");
        jButtonRemoveGoods.setEnabled(false);
        jButtonRemoveGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveGoodsActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Всього вартість:");

        jLabel1.setText("Всього кількість:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAllGoodsQuantity)
                    .addComponent(jLabelAllGoodsPrice))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelAllGoodsQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelAllGoodsPrice))
                .addGap(0, 0, 0))
        );

        jButtonEditGoods.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/edit.png"))); // NOI18N
        jButtonEditGoods.setText("Редагувати");
        jButtonEditGoods.setEnabled(false);
        jButtonEditGoods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditGoodsActionPerformed(evt);
            }
        });

        jButtonsShowPictures.setText("Показати фото");
        jButtonsShowPictures.setEnabled(false);
        jButtonsShowPictures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsShowPicturesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGoodsLayout = new javax.swing.GroupLayout(jPanelGoods);
        jPanelGoods.setLayout(jPanelGoodsLayout);
        jPanelGoodsLayout.setHorizontalGroup(
            jPanelGoodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGoodsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAddGoods)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditGoods)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemoveGoods)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonsShowPictures)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane)
        );
        jPanelGoodsLayout.setVerticalGroup(
            jPanelGoodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGoodsLayout.createSequentialGroup()
                .addGroup(jPanelGoodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelGoodsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonsShowPictures, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddGoods, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEditGoods, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemoveGoods, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInvoiceInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanelGoods, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelInvoiceInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGoods, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        outputInvoicesService.getCurrentElement().setPaidAmount((Double) jSpinnerPaidAmount.getValue());
        outputInvoicesService.getCurrentElement().setStatusOfPayment(jComboBoxStatusOfPayment.getSelectedIndex());
        outputInvoicesService.getCurrentElement().setInfo(jTextAreaInfo.getText());
        try {
            outputInvoicesService.saveOrUpdate();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Помилка збереження накладної:\nінформація про помилку знаходиться в logJ.txt", null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonAddGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddGoodsActionPerformed
        outputInvoicesService.setCurrentGoodsElement(new OutputGoods());
        OutputInvoiceGoodsElementJFrame outputInvoiceGoods = new OutputInvoiceGoodsElementJFrame();
        outputInvoiceGoods.pack();
        ModalFrameUtil.showAsModal(outputInvoiceGoods, this);
        getGoodsList();
    }//GEN-LAST:event_jButtonAddGoodsActionPerformed

    private void jButtonEditGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditGoodsActionPerformed
        edit();
    }//GEN-LAST:event_jButtonEditGoodsActionPerformed

    private void jButtonCounterPartySelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCounterPartySelectActionPerformed
        counterPartiesService.setSelectType(CounterParties.COUNTERPARTY);
        CounterPartiesSelectJFrame counterPartiesSelect = new CounterPartiesSelectJFrame();
        counterPartiesSelect.pack();
        ModalFrameUtil.showAsModal(counterPartiesSelect, this);
        outputInvoicesService.setCounterParty(counterPartiesService.getSelectedElement());
        jLabelCounterParty.setText(outputInvoicesService.getCounterPartyTitle());
        svalidate();
    }//GEN-LAST:event_jButtonCounterPartySelectActionPerformed

    private void jButtonStoreHouseSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStoreHouseSelectActionPerformed
        StoreHouseSelectJFrame storeHouseSelect = new StoreHouseSelectJFrame();
        storeHouseSelect.pack();
        ModalFrameUtil.showAsModal(storeHouseSelect, this);
        outputInvoicesService.setStoreHouse(storeHousesService.getSelectedElement());
        jLabelStoreHouse.setText(outputInvoicesService.getStoreHouseTitle());
        svalidate();
    }//GEN-LAST:event_jButtonStoreHouseSelectActionPerformed

    private void jButtonRemoveGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveGoodsActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Дійсно видалити запис?", "", JOptionPane.YES_NO_OPTION);
        if ((jTableGoods.getSelectedRow() >= 0) && (confirm == JOptionPane.YES_OPTION)) {
            OutputGoods selectedObject = (OutputGoods) jTableGoods.getValueAt(jTableGoods.getSelectedRow(), OBJECT_COLUMN);
            outputInvoicesService.removeGoods(selectedObject);
            getGoodsList();
        }
    }//GEN-LAST:event_jButtonRemoveGoodsActionPerformed

    private void jTableGoodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGoodsMouseClicked
        svalidate();
        if (evt.getClickCount() == 2) {
            edit();
        }
    }//GEN-LAST:event_jTableGoodsMouseClicked

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        log.debug("try print");
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            // EntityManagerFactory emf =
            // Persistence.createEntityManagerFactory("db.dbPU");
            // EntityManager em = emf.createEntityManager();
            // parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
            // em);
            parameters.put("ID", outputInvoicesService.getCurrentElement().getId());
            parameters.put("M", settingsService.getNameData());
            parameters.put("COUNTERPARTY", outputInvoicesService.getCurrentElement().getCounterParty().getGeneralInformation());
            /*
             * JasperReport jre = null;
             * try {
             * jre = JasperCompileManager.compileReport(
             * "src//small//business//reports//OutputInvoice.jrxml");
             * } catch (JRException ex) {
             * System.out.println("eerrr");
             * Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(
             * Level.SEVERE, null, ex);
             * }
             */
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(outputInvoicesService.getCurrentElement().getGoods());
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports" + File.separator + "OutputInvoice.jasper", parameters, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            log.error("Try print", ex);
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jButtonsShowPicturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsShowPicturesActionPerformed
    }//GEN-LAST:event_jButtonsShowPicturesActionPerformed

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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OutputInvoiceElementJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddGoods;
    private javax.swing.JButton jButtonCounterPartySelect;
    private javax.swing.JButton jButtonEditGoods;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonRemoveGoods;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonStoreHouseSelect;
    private javax.swing.JButton jButtonsShowPictures;
    private javax.swing.JComboBox jComboBoxStatusOfPayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAllGoodsPrice;
    private javax.swing.JLabel jLabelAllGoodsQuantity;
    private javax.swing.JLabel jLabelCounterParty;
    private javax.swing.JLabel jLabelCounterPartyTitle;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JLabel jLabelStoreHouse;
    private javax.swing.JLabel jLabelStoreHouseTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelGoods;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelInvoiceInfo;
    private javax.swing.JPanel jPanelLabels;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPaidAmount;
    private javax.swing.JTable jTableGoods;
    private javax.swing.JTextArea jTextAreaInfo;
    // End of variables declaration//GEN-END:variables

    private void edit() {
        if (outputInvoicesService.isCanEditGoods() && jTableGoods.getSelectedRow() >= 0) {
            OutputGoods selectedObject = (OutputGoods) jTableGoods.getValueAt(jTableGoods.getSelectedRow(), OBJECT_COLUMN);
            outputInvoicesService.setCurrentGoodsElement(selectedObject);
            OutputInvoiceGoodsElementJFrame outputInvoiceGoods = new OutputInvoiceGoodsElementJFrame();
            outputInvoiceGoods.pack();
            ModalFrameUtil.showAsModal(outputInvoiceGoods, this);
            getGoodsList();
        }
    }

    private void svalidate() {
        outputInvoicesService.validate();
        jButtonAddGoods.setEnabled(outputInvoicesService.isCanAddGoods());
        if (jTableGoods.getSelectedRow() >= 0) {
            jButtonEditGoods.setEnabled(outputInvoicesService.isCanEditGoods());
            jButtonRemoveGoods.setEnabled(outputInvoicesService.isCanRemoveGoods());
        } else {
            jButtonEditGoods.setEnabled(false);
            jButtonRemoveGoods.setEnabled(false);
        }
        jButtonSave.setEnabled(outputInvoicesService.isCanSave());
        jButtonPrint.setEnabled(outputInvoicesService.isCanPrint());
        jButtonCounterPartySelect.setEnabled(outputInvoicesService.isCanSelectCounterParty());
        jButtonStoreHouseSelect.setEnabled(outputInvoicesService.isCanSelectStoreHouse());
    }

    private void getGoodsList() {
        DefaultTableModel dataModel = (DefaultTableModel) jTableGoods.getModel();
        dataModel.setRowCount(0);
        double sum = 0;
        long quantity = 0;
        if (outputInvoicesService.getCurrentElement().getGoods() != null) {
            for (OutputGoods goods : outputInvoicesService.getCurrentElement().getGoods()) {
                Object[] g = new Object[]{goods.getId(), goods, goods.getNomenclature().getArticleofgoods(), goods.getNomenclature().getArticleinside(), goods.getQuantity(), goods.getTypeOfPrice().equals(1) ? "Роздріб" : goods.getTypeOfPrice().equals(2) ? "Дрібний опт" : "Крупний опт", goods.getSum(), goods.getPrice()};
                dataModel.addRow(g);
                quantity += goods.getQuantity();
                sum += goods.getPrice();
            }
        }
        jLabelAllGoodsQuantity.setText(String.valueOf(quantity));
        jLabelAllGoodsPrice.setText(String.valueOf(sum));
        jTableGoods.setModel(dataModel);
        svalidate();
    }
}