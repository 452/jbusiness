package invoices;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import small.business.businesslayer.ComingsInvoicesService;
import small.business.dao.ComingInvoicesDAO;
import small.business.dao.NomenclatureDAO;
import small.business.dao.entity.ComingsGoods;
import small.business.dao.entity.ComingsInvoice;
import small.business.dao.entity.CounterParties;
import small.business.dao.entity.Nomenclature;
import small.business.dao.entity.StoreHouse;
import small.business.domainmodel.interfaces.IGoods;
import config.ApplicationConfig;

/**
 * Test check creates new invoice, changes quantity of goods and nomenclature
 * and nomenclature
 *
 * @author 452
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class ComingInvoiceNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public ComingsInvoicesService comingInvoicesService;
    @Autowired
    public ComingInvoicesDAO comingInvoicesDAO;
    @Autowired
    public NomenclatureDAO nomenclatureDAO;
    @Resource
    EntityManagerFactory entityManagerFactory;
    final static Integer CREATED_QUANTITY = 5;
    final static Integer CHANGE_QUANTITY = 3;
    final static Long NOMENCLATURE_ID = 3100L;
    final static Long NOMENCLATURE_PARENT_ID = 3100L;
    final static String NOMENCLATURE_TITLE = "СЗУ Силибрити Nokia 6101";
    public static int memorizedNomenclatureInitialQuantity = 0;
    public static Long memorizedCreatedInvoiceID = 0L;

    /**
     * billet of invoice
     *
     * @return OutputInvoice
     */
    private ComingsInvoice getTestInvoice() {
        ComingsInvoice comingsInvoice = new ComingsInvoice();
        ComingsGoods comingsGoods = new ComingsGoods(452L, new Nomenclature(NOMENCLATURE_ID, NOMENCLATURE_PARENT_ID, NOMENCLATURE_TITLE), CREATED_QUANTITY);
        comingsInvoice.setPaidAmount(452.678);
        comingsInvoice.setInfo("452_2");
        comingsInvoice.setStoreHouse(new StoreHouse(1L));
        comingsInvoice.setCounterParty(new CounterParties(4L));
        comingsInvoice.addGoods(comingsGoods);
        return comingsInvoice;
    }

    /**
     * Remembers Nomenclature quantity variable for future compare and test
     * Nomenclature assertNotNull
     */
    @Test(priority = 0)
    public void memNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        memorizedNomenclatureInitialQuantity = n.getQuantity();
        System.out.println("1. Memorized quantity: " + memorizedNomenclatureInitialQuantity + " Step UP: " + ((memorizedNomenclatureInitialQuantity + CREATED_QUANTITY) + CHANGE_QUANTITY) + " Step Down: " + ((memorizedNomenclatureInitialQuantity + CREATED_QUANTITY) - CHANGE_QUANTITY));
        assertNotNull(n);
    }

    /**
     * create new invoice and remembers invoice ID and test assertNotNull
     */
    @Test(priority = 1)
    public void createInvoiceTest() {
        ComingsInvoice comingsInvoice = getTestInvoice();
        comingInvoicesService.setCurrentElement(comingsInvoice);
        try {
            comingsInvoice = comingInvoicesService.saveOrUpdate();
            memorizedCreatedInvoiceID = comingsInvoice.getId();
            System.out.println("2. created invoice ID: " + memorizedCreatedInvoiceID);
        } catch (Exception ex) {
            Logger.getLogger(ComingInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(comingsInvoice);
    }

    /**
     * try to read created invoice from db
     */
    @Test(priority = 2, description = "check for created invoiced")
    public void readCreatedInvoiceTest() {
        ComingsInvoice ci = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(ci);
    }

    /**
     * test business logic, if invoice created must change quantity of
     * nomenclature. Checks changes of quantity.
     */
    @Test(priority = 3)
    public void readNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity + CREATED_QUANTITY));
    }

    /**
     * try change quantity of goods in invoice. BLogic - must be: change
     * quantity in goods and nomenclature and on storehouse
     */
    @Test(priority = 4, description = "change quantity to UP")
    public void changeInvoiceGoodsQuantityUPTest() {
        ComingsInvoice comingsInvoice = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(comingsInvoice);
        comingInvoicesService.setCurrentElement(comingsInvoice);
        for (IGoods g : comingsInvoice.getGoods()) {
            g.setQuantity(CREATED_QUANTITY + CHANGE_QUANTITY);
        }
        try {
            comingsInvoice = comingInvoicesService.saveOrUpdate();
            for (IGoods g : comingsInvoice.getGoods()) {
                assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY + CHANGE_QUANTITY));
            }
        } catch (Exception ex) {
            Logger.getLogger(ComingInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 5, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityUPTest() {
        ComingsInvoice comingsInvoice = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (IGoods g : comingsInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY + CHANGE_QUANTITY));
        }
    }

    @Test(priority = 6, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityWitchChangesTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity + CREATED_QUANTITY + CHANGE_QUANTITY));
    }

    @Test(priority = 7, description = "change quantity to down")
    public void changeInvoiceGoodsQuantityDownTest() {
        ComingsInvoice comingsInvoice = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        comingInvoicesService.setCurrentElement(comingsInvoice);
        for (IGoods g : comingsInvoice.getGoods()) {
            g.setQuantity(CREATED_QUANTITY - CHANGE_QUANTITY);
        }
        try {
            comingInvoicesService.saveOrUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ComingInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 8, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityDownTest() {
        ComingsInvoice comingsInvoice = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (IGoods g : comingsInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY - CHANGE_QUANTITY));
        }
    }

    @Test(priority = 9, enabled = true, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityChangesBackTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf((memorizedNomenclatureInitialQuantity + CREATED_QUANTITY) - CHANGE_QUANTITY));
    }

    @Test(priority = 10)
    public void readNomenclatureQuantityNotLesThanZeroTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertTrue(n.getQuantity() >= 0 ? true : false);
    }

    @Test(priority = 11, description = "remove invoice")
    public void removeInvoiceTest() {
        comingInvoicesService.removeCurrentElement(comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID), true);
    }

    @Test(priority = 12, description = "check invoice on remove")
    public void readRemovedInvoiceTest() {
        ComingsInvoice ci = comingInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNull(ci);
    }

    @Test(priority = 13, enabled = true, description = "check quantity of nomenclature")
    public void finalCheckNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity));
    }

    public ComingInvoiceNGTest() {
    }
}