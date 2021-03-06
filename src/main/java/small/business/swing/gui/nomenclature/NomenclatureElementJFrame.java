package small.business.swing.gui.nomenclature;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import small.business.businesslayer.NomenclatureService;
import small.business.dao.entity.Pictures;
import small.business.swing.gui.GroupSelectJFrame;
import small.business.swing.gui.utils.ModalFrameUtil;
import config.AppContext;

/**
 *
 * @author ihor
 */
public class NomenclatureElementJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(NomenclatureElementJFrame.class.getName());
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private NomenclatureService nomenclatureService = (NomenclatureService) ctx.getBean("nomenclatureService");

    private void picturesValidate() {
        nomenclatureService.setCurrentPicture(null);
        if (jTable.getSelectedRow() >= 0) {
            Pictures selectedPicture = (Pictures) jTable.getValueAt(jTable.getSelectedRow(), 0);
            nomenclatureService.setCurrentPicture(selectedPicture);
        }
        svalidate();
    }

    private void svalidate() {
        setDataFields();
        nomenclatureService.validate();
        jButtonSave.setEnabled(nomenclatureService.isCanSave());
        jButtonEditPicture.setEnabled(nomenclatureService.isCanEditPicture());
        jButtonRemovePicture.setEnabled(nomenclatureService.isCanRemovePicture());
    }

    private void setDataFields() {
        nomenclatureService.getCurrentElement().setTitle(jTextFieldTitle.getText());
        nomenclatureService.getCurrentElement().setInfo(jTextAreaInfo.getText());
        nomenclatureService.getCurrentElement().setArticleofgoods(jTextFieldArticle.getText());
        nomenclatureService.getCurrentElement().setArticleinside(jTextFieldArticleInside.getText());
        nomenclatureService.getCurrentElement().setPrice((Double) jSpinnerPrice.getValue());
        nomenclatureService.getCurrentElement().setPriceretail((Integer) jSpinnerPriceRetail.getValue());
        nomenclatureService.getCurrentElement().setPricesmallwholesale((Integer) jSpinnerPriceSmallWholeSale.getValue());
        nomenclatureService.getCurrentElement().setPricebigwholesale((Integer) jSpinnerPriceBigWholeSale.getValue());
        nomenclatureService.getCurrentElement().setQuantitymin((Integer) jSpinnerMinQuantity.getValue());
    }

    private void showPictures() {
        DefaultTableModel dataModel = (DefaultTableModel) jTable.getModel();
        dataModel.setRowCount(0);
        jTable.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
        int i = 0;
        for (Pictures pic : nomenclatureService.getCurrentElement().getPictures()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(pic.getData()));
                Object[] g = new Object[]{pic, pic.getData(), pic.getInfo()};
                dataModel.addRow(g);
                jTable.setRowHeight(i, bufferedImage.getHeight());
                i++;
            } catch (Exception ex) {
                log.error(ex);
            }
        }
        jTable.setModel(dataModel);
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value != null) {
                label.setHorizontalAlignment(JLabel.CENTER);
                //value is parameter which filled by byteOfImage
                label.setIcon(new ImageIcon((byte[]) value));
            }
            return label;
        }
    }

    /**
     * Creates new form NomenclatureElementJFrame
     */
    public NomenclatureElementJFrame() {
        initComponents();
        jTextFieldTitle.setText(nomenclatureService.getCurrentElement().getTitle());
        jTextAreaInfo.setText(nomenclatureService.getCurrentElement().getInfo());
        jTextFieldArticle.setText(nomenclatureService.getCurrentElement().getArticleofgoods());
        jTextFieldArticleInside.setText(nomenclatureService.getCurrentElement().getArticleinside());
        jSpinnerPrice.setValue(nomenclatureService.getCurrentElement().getPrice());
        jSpinnerPriceRetail.setValue(nomenclatureService.getCurrentElement().getPriceretail());
        jSpinnerPriceSmallWholeSale.setValue(nomenclatureService.getCurrentElement().getPricesmallwholesale());
        jSpinnerPriceBigWholeSale.setValue(nomenclatureService.getCurrentElement().getPricebigwholesale());
        jSpinnerMinQuantity.setValue(nomenclatureService.getCurrentElement().getQuantitymin());
        jLabelAllQuantityInAllStoreHouses.setText(nomenclatureService.getCurrentElement().getQuantity().toString());
        nomenclatureService.setCurrentPicture(null);
        showPictures();
        svalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "rawtypes", "deprecation", "serial"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextFieldArticle = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabelPriceRetail = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelArticle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextFieldTitle = new javax.swing.JTextField();
        jLabelArticleInside = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabelMinQuantity = new javax.swing.JLabel();
        jTextFieldArticleInside = new javax.swing.JTextField();
        jSpinnerPrice = new javax.swing.JSpinner();
        jSpinnerPriceRetail = new javax.swing.JSpinner();
        jSpinnerPriceSmallWholeSale = new javax.swing.JSpinner();
        jSpinnerPriceBigWholeSale = new javax.swing.JSpinner();
        jSpinnerMinQuantity = new javax.swing.JSpinner();
        jLabelAllQuantityInAllStoreHouses = new javax.swing.JLabel();
        jPanelButtons = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonSwapPlaceArticul = new javax.swing.JButton();
        jPanelA = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTextAreaInfo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButtonAddPicture = new javax.swing.JButton();
        jButtonEditPicture = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonRemovePicture = new javax.swing.JButton();
        jButtonShowPictures = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Номенклатура");
        setExtendedState(6);

        jTextField1.setEnabled(false);

        jTextFieldArticle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldArticleMouseReleased(evt);
            }
        });
        jTextFieldArticle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldArticleKeyReleased(evt);
            }
        });

        jLabel6.setLabelFor(jSpinnerPriceSmallWholeSale);
        jLabel6.setText("Вартість товару / дрібний опт %");

        jLabelPriceRetail.setLabelFor(jSpinnerPriceRetail);
        jLabelPriceRetail.setText("Вартість товару / роздріб %");

        jLabelTitle.setLabelFor(jTextFieldTitle);
        jLabelTitle.setText("Найменування:");

        jLabelArticle.setLabelFor(jTextFieldArticle);
        jLabelArticle.setText("Заводський артикул товару:");

        jLabel1.setLabelFor(jTextField1);
        jLabel1.setText("Група:");
        jLabel1.setEnabled(false);

        jButton1.setText("...");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTitleKeyReleased(evt);
            }
        });

        jLabelArticleInside.setLabelFor(jTextFieldArticleInside);
        jLabelArticleInside.setText("Внутрішній артикул товару:");

        jLabel7.setLabelFor(jSpinnerPriceBigWholeSale);
        jLabel7.setText("Вартість товару / крупний опт %");

        jLabel10.setText("Загальна кількість на всіх складах:");

        jLabelPrice.setLabelFor(jSpinnerPrice);
        jLabelPrice.setText("Закупівельна ціна товару:");

        jLabelMinQuantity.setLabelFor(jSpinnerMinQuantity);
        jLabelMinQuantity.setText("Мінімальна кількість:");

        jTextFieldArticleInside.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldArticleInsideMouseReleased(evt);
            }
        });
        jTextFieldArticleInside.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldArticleInsideKeyReleased(evt);
            }
        });

        jSpinnerPrice.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.01d), Double.valueOf(0.01d), null, Double.valueOf(0.01d)));
        jSpinnerPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSpinnerPriceMouseReleased(evt);
            }
        });

        jSpinnerPriceRetail.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(50), Integer.valueOf(0), null, Integer.valueOf(1)));

        jSpinnerPriceSmallWholeSale.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(40), Integer.valueOf(0), null, Integer.valueOf(1)));

        jSpinnerPriceBigWholeSale.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(20), Integer.valueOf(0), null, Integer.valueOf(1)));

        jSpinnerMinQuantity.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabelAllQuantityInAllStoreHouses.setText("0");

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/exit.png"))); // NOI18N
        jButtonCancel.setText("Відмінити");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/save.png"))); // NOI18N
        jButtonSave.setText("Зберегти");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancel)
                .addContainerGap())
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel))
                .addContainerGap())
        );

        jButtonSwapPlaceArticul.setText("<>");
        jButtonSwapPlaceArticul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSwapPlaceArticulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldArticle)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPriceRetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jTextFieldTitle)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelArticleInside, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSwapPlaceArticul))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMinQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldArticleInside)
                    .addComponent(jSpinnerPrice)
                    .addComponent(jSpinnerPriceRetail)
                    .addComponent(jSpinnerPriceSmallWholeSale)
                    .addComponent(jSpinnerPriceBigWholeSale)
                    .addComponent(jSpinnerMinQuantity)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabelAllQuantityInAllStoreHouses)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelLayout.createSequentialGroup()
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 134, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelArticle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelArticleInside)
                    .addComponent(jButtonSwapPlaceArticul))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldArticleInside, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabelPriceRetail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerPriceRetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerPriceSmallWholeSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerPriceBigWholeSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMinQuantity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerMinQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAllQuantityInAllStoreHouses)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextAreaInfo.setColumns(20);
        jTextAreaInfo.setLineWrap(true);
        jTextAreaInfo.setRows(5);
        jTextAreaInfo.setWrapStyleWord(true);
        jTextAreaInfo.setNextFocusableComponent(jButtonSave);
        jTextAreaInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaInfoKeyReleased(evt);
            }
        });
        jScrollPane.setViewportView(jTextAreaInfo);

        jLabel2.setText("Додаткова інформація про товар");

        jButtonAddPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/add.png"))); // NOI18N
        jButtonAddPicture.setText("Додати");
        jButtonAddPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPictureActionPerformed(evt);
            }
        });

        jButtonEditPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/edit.png"))); // NOI18N
        jButtonEditPicture.setText("Редагувати");
        jButtonEditPicture.setEnabled(false);
        jButtonEditPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditPictureActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Зображення", "Опис"
            }
        ) {
			Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(40);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(96);
        jTable.getColumnModel().getColumn(0).setMaxWidth(96);

        jButtonRemovePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/delete.png"))); // NOI18N
        jButtonRemovePicture.setText("Видалити");
        jButtonRemovePicture.setEnabled(false);
        jButtonRemovePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemovePictureActionPerformed(evt);
            }
        });

        jButtonShowPictures.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small/business/swing/gui/images/print.png"))); // NOI18N
        jButtonShowPictures.setText("Показати фото");
        jButtonShowPictures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowPicturesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelALayout = new javax.swing.GroupLayout(jPanelA);
        jPanelA.setLayout(jPanelALayout);
        jPanelALayout.setHorizontalGroup(
            jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelALayout.createSequentialGroup()
                        .addGroup(jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelALayout.createSequentialGroup()
                                .addComponent(jButtonShowPictures)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAddPicture)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEditPicture)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemovePicture)))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelALayout.setVerticalGroup(
            jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRemovePicture)
                    .addGroup(jPanelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAddPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonShowPictures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonEditPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        setDataFields();
        nomenclatureService.saveUpdate();
        this.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GroupSelectJFrame groupSelect = new GroupSelectJFrame();
        groupSelect.pack();
        ModalFrameUtil.showAsModal(groupSelect, this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTitleKeyReleased
        svalidate();
    }//GEN-LAST:event_jTextFieldTitleKeyReleased

    private void jTextAreaInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaInfoKeyReleased
        svalidate();
    }//GEN-LAST:event_jTextAreaInfoKeyReleased

    private void jSpinnerPriceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinnerPriceMouseReleased
        svalidate();
    }//GEN-LAST:event_jSpinnerPriceMouseReleased

    private void jTextFieldArticleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldArticleMouseReleased
        svalidate();
    }//GEN-LAST:event_jTextFieldArticleMouseReleased

    private void jTextFieldArticleInsideMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldArticleInsideMouseReleased
        svalidate();
    }//GEN-LAST:event_jTextFieldArticleInsideMouseReleased

    private void jTextFieldArticleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldArticleKeyReleased
        svalidate();
    }//GEN-LAST:event_jTextFieldArticleKeyReleased

    private void jTextFieldArticleInsideKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldArticleInsideKeyReleased
        svalidate();
    }//GEN-LAST:event_jTextFieldArticleInsideKeyReleased

    private void jButtonAddPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPictureActionPerformed
        nomenclatureService.setCurrentPicture(new Pictures());
        NomenclaturePictureElementJFrame picture = new NomenclaturePictureElementJFrame();
        picture.pack();
        ModalFrameUtil.showAsModal(picture, this);
        showPictures();
    }//GEN-LAST:event_jButtonAddPictureActionPerformed

    private void jButtonShowPicturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowPicturesActionPerformed
        showPictures();
    }//GEN-LAST:event_jButtonShowPicturesActionPerformed

    private void jButtonEditPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditPictureActionPerformed
        NomenclaturePictureElementJFrame picture = new NomenclaturePictureElementJFrame();
        picture.pack();
        ModalFrameUtil.showAsModal(picture, this);
        showPictures();
    }//GEN-LAST:event_jButtonEditPictureActionPerformed

    private void jButtonRemovePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemovePictureActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Дійсно видалити запис?", "", JOptionPane.YES_NO_OPTION);
        if ((jTable.getSelectedRow() >= 0) && (confirm == JOptionPane.YES_OPTION)) {
            //Pictures selectedObject = (Pictures) jTable.getValueAt(jTable.getSelectedRow(), jTable.getSelectedColumn());
            nomenclatureService.getCurrentElement().getPictures().remove(nomenclatureService.getCurrentPicture());
            showPictures();
        }
    }//GEN-LAST:event_jButtonRemovePictureActionPerformed

    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseReleased
        picturesValidate();
    }//GEN-LAST:event_jTableMouseReleased

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        picturesValidate();
    }//GEN-LAST:event_jTableMouseClicked

    private void jButtonSwapPlaceArticulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSwapPlaceArticulActionPerformed
        String articul = jTextFieldArticle.getText();
        jTextFieldArticle.setText(jTextFieldArticleInside.getText());
        jTextFieldArticleInside.setText(articul);
    }//GEN-LAST:event_jButtonSwapPlaceArticulActionPerformed

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
            java.util.logging.Logger.getLogger(NomenclatureElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NomenclatureElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NomenclatureElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NomenclatureElementJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NomenclatureElementJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddPicture;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonEditPicture;
    private javax.swing.JButton jButtonRemovePicture;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonShowPictures;
    private javax.swing.JButton jButtonSwapPlaceArticul;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAllQuantityInAllStoreHouses;
    private javax.swing.JLabel jLabelArticle;
    private javax.swing.JLabel jLabelArticleInside;
    private javax.swing.JLabel jLabelMinQuantity;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelPriceRetail;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelA;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerMinQuantity;
    private javax.swing.JSpinner jSpinnerPrice;
    private javax.swing.JSpinner jSpinnerPriceBigWholeSale;
    private javax.swing.JSpinner jSpinnerPriceRetail;
    private javax.swing.JSpinner jSpinnerPriceSmallWholeSale;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jTextAreaInfo;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldArticle;
    private javax.swing.JTextField jTextFieldArticleInside;
    private javax.swing.JTextField jTextFieldTitle;
    // End of variables declaration//GEN-END:variables
}