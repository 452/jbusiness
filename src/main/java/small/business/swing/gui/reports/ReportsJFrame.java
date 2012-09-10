package small.business.swing.gui.reports;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import small.business.dao.entity.Nomenclature;
import small.business.dao.entity.OutputGoods;
import small.business.dao.entity.OutputInvoice;
import config.AppContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.eclipse.persistence.jpa.JpaQuery;

/**
 *
 * @author root
 */
public class ReportsJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(ReportsJFrame.class.getName());
    private ApplicationContext ctx = AppContext.getApplicationContext();
    private EntityManagerFactory entityManagerFactory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");

    /**
     * Creates new form ReportsJFrame
     */
    public ReportsJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Звіти");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("reports"); // NOI18N
        setType(java.awt.Window.Type.UTILITY);

        jButton1.setText("Графік по торгівлі загальний");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Звіт по найбільш продаваємим товарам");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Звіт по товарам на складах");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Звіт по потрібним закупкам товарів на склади");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //log.debug("try print");
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("db.dbPU");
            //EntityManager em = emf.createEntityManager();
            //parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
            //parameters.put("ID", outputInvoicesService.getCurrentElement().getId());
            //parameters.put("M", settingsService.getNameData());
            //parameters.put("COUNTERPARTY", outputInvoicesService.getCurrentElement().getCounterParty().getGeneralInformation());
            /*JasperReport jre = null;
             try {
             jre = JasperCompileManager.compileReport("src//small//business//reports//OutputInvoice.jrxml");
             } catch (JRException ex) {
             System.out.println("eerrr");
             Logger.getLogger(OutputInvoiceElementJFrame.class.getName()).log(Level.SEVERE, null, ex);
             }*/
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = cb.createQuery();

            Root<OutputInvoice> fromOutputInvoice = criteriaQuery.from(OutputInvoice.class);
            Root<OutputGoods> fromOutputGoods = criteriaQuery.from(OutputGoods.class);
            //Metamodel m = entityManager.getMetamodel();
            //EntityType<OutputInvoice> outputInvoice = m.entity(OutputInvoice.class);
            //criteriaQuery.multiselect(cb.construct(OutputInvoice.class,fromOutputInvoice.<String>get("id"),fromOutputInvoice.<String>get("createdDate")));
            //fromOutputGoods.get("quantity").as(Number.class));
            ///criteriaQuery.multiselect(fromOutputInvoice.<Long>get("id"),fromOutputInvoice.<String>get("createdDate"),cb.sum(fromOutputGoods.<Integer>get("quantity")).alias("quantity"),fromOutputGoods.<Double>get("price"));
            criteriaQuery.multiselect(fromOutputInvoice.get("id"), cb.function("DATE", String.class, fromOutputInvoice.get("createdDate")), cb.sum(fromOutputGoods.<Integer>get("quantity")), cb.sum(fromOutputGoods.<Double>get("price")));
            criteriaQuery.where(cb.equal(fromOutputInvoice.get("id"), fromOutputGoods.get("invoiceid")));
            criteriaQuery.groupBy(cb.function("DATE", String.class, fromOutputInvoice.get("createdDate"))).orderBy(cb.asc(fromOutputInvoice.get("createdDate")));

            //Query q = entityManager.createQuery("SELECT t0.id, t0.createdDate, SUM(t1.quantity) AS quantity, t1.price AS price FROM OutputInvoice t0, OutputGoods t1 WHERE (t0.id = t1.invoiceid) GROUP BY t0.createdDate ORDER BY t0.createdDate ASC");//criteriaQuery);
            Query q = entityManager.createQuery(criteriaQuery);

            @SuppressWarnings("unchecked")
            List<Object[]> result = q.getResultList();
            /*System.out.println(fromOutputInvoice.getAlias());
             System.out.println(q.unwrap(JpaQuery.class).getDatabaseQuery().getSQLString());
             System.out.println(q.unwrap(JpaQuery.class).getDatabaseQuery().getEJBQLString());
             for (Object[] o: result) {
             System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]);
             }*/
            //System.out.println(result.toString());
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(result);
            //JasperReport jre = JasperCompileManager.compileReport("src" + File.separator + "small" + File.separator + "reports" + File.separator + "TradeInvoicesCounts.jrxml");
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jre, parameters, beanCollectionDataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport("reports" + File.separator + "TradeInvoicesCounts.jasper", parameters, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            log.error(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = cb.createQuery();
            Root<OutputGoods> fromOutputGoods = criteriaQuery.from(OutputGoods.class);
            criteriaQuery.multiselect(fromOutputGoods.get("nomenclature").get("id"), fromOutputGoods.get("nomenclature").get("title"), cb.sum(fromOutputGoods.<Integer>get("quantity")), cb.sum(fromOutputGoods.<Double>get("price")));
            criteriaQuery.groupBy(fromOutputGoods.get("nomenclature").get("id"));
            criteriaQuery.orderBy(cb.desc(cb.sum(fromOutputGoods.<Integer>get("quantity"))), cb.asc(cb.sum(fromOutputGoods.<Double>get("price"))), cb.asc(fromOutputGoods.get("nomenclature").get("title")));
            Query q = entityManager.createQuery(criteriaQuery);
            @SuppressWarnings("unchecked")
            List<Object[]> result = q.getResultList();
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(result);
            //JasperReport jre = JasperCompileManager.compileReport("src" + File.separator + "small" + File.separator + "reports" + File.separator + "TradeTopNomenclature.jrxml");
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jre, parameters, beanCollectionDataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports" + File.separator + "TradeTopNomenclature.jasper", parameters, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            log.error(ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = cb.createQuery();
            Root<Nomenclature> fromNomenclature = criteriaQuery.from(Nomenclature.class);
            criteriaQuery.multiselect(fromNomenclature.get("id"), fromNomenclature.get("title"), fromNomenclature.get("articleofgoods"), fromNomenclature.get("articleinside"), fromNomenclature.<Integer>get("quantity"), fromNomenclature.<Double>get("price"));
            criteriaQuery.where(cb.equal(fromNomenclature.get("isgroup"), Nomenclature.NOMENCLATURE));
            criteriaQuery.orderBy(cb.asc(fromNomenclature.get("title")), cb.desc(fromNomenclature.<Integer>get("quantity")), cb.asc(fromNomenclature.<Double>get("price")));

            //System.out.println(entityManager.createQuery(criteriaQuery).unwrap(JpaQuery.class).getDatabaseQuery().getSQLString());
            //System.out.println(entityManager.createQuery(criteriaQuery).unwrap(JpaQuery.class).getDatabaseQuery().getEJBQLString());

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(entityManager.createQuery(criteriaQuery).getResultList());
            //JasperReport jre = JasperCompileManager.compileReport("src" + File.separator + "small" + File.separator + "reports" + File.separator + "NomenclatureTotalAudit.jrxml");
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jre, parameters, beanCollectionDataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports" + File.separator + "NomenclatureTotalAudit.jasper", parameters, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            log.error(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<?> criteriaQuery = cb.createQuery();
            Root<Nomenclature> fromNomenclature = criteriaQuery.from(Nomenclature.class);
            criteriaQuery.multiselect(fromNomenclature.get("id"), fromNomenclature.get("title"), fromNomenclature.get("articleofgoods"), fromNomenclature.get("articleinside"), fromNomenclature.<Integer>get("quantity"), fromNomenclature.<Integer>get("quantitymin"), fromNomenclature.<Double>get("price"));
            criteriaQuery.where(cb.equal(fromNomenclature.get("isgroup"), Nomenclature.NOMENCLATURE), cb.lessThanOrEqualTo(fromNomenclature.<Integer>get("quantity"), fromNomenclature.<Integer>get("quantitymin")));
            criteriaQuery.orderBy(cb.asc(fromNomenclature.get("title")), cb.desc(fromNomenclature.<Integer>get("quantity")), cb.asc(fromNomenclature.<Double>get("price")));
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(entityManager.createQuery(criteriaQuery).getResultList());
            //JasperReport jre = JasperCompileManager.compileReport("src" + File.separator + "main" + File.separator + "java" + File.separator + "small" + File.separator + "reports" + File.separator + "NomenclatureForBuy.jrxml");
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jre, parameters, beanCollectionDataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports" + File.separator + "NomenclatureForBuy.jasper", parameters, beanCollectionDataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            log.error(ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReportsJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}