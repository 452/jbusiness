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

import small.business.businesslayer.OutputInvoicesService;
import small.business.dao.NomenclatureDAO;
import small.business.dao.OutputInvoicesDAO;
import small.business.dao.entity.CounterParties;
import small.business.dao.entity.Nomenclature;
import small.business.dao.entity.OutputGoods;
import small.business.dao.entity.OutputInvoice;
import small.business.dao.entity.StoreHouse;
import config.ApplicationConfig;

/**
 * Test check creates new invoice, changes quantity of goods and nomenclature
 * and nomenclature
 *
 * @author 452
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class OutputInvoiceNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public OutputInvoicesService outputInvoicesService;
    @Autowired
    public OutputInvoicesDAO outputInvoicesDAO;
    @Autowired
    public NomenclatureDAO nomenclatureDAO;
    @Resource
    EntityManagerFactory entityManagerFactory;
    final static Integer CREATED_QUANTITY = 1;
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
    private OutputInvoice getTestInvoice() {
        OutputInvoice outInvoice = new OutputInvoice();
        OutputGoods OutputGoods = new OutputGoods(452L, new Nomenclature(NOMENCLATURE_ID, NOMENCLATURE_PARENT_ID, NOMENCLATURE_TITLE), CREATED_QUANTITY);
        outInvoice.setPaidAmount(452.678);
        outInvoice.setInfo("452_2");
        outInvoice.setStoreHouse(new StoreHouse(1L));
        outInvoice.setCounterParty(new CounterParties(4L));
        outInvoice.addGoods(OutputGoods);
        return outInvoice;
    }

    /**
     * Remembers Nomenclature quantity variable for future compare and test
     * Nomenclature assertNotNull
     */
    @Test(priority = 0)
    public void memNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        memorizedNomenclatureInitialQuantity = n.getQuantity();
        System.out.println("1. Memorized quantity: " + memorizedNomenclatureInitialQuantity + " Step UP: " + ((memorizedNomenclatureInitialQuantity - CREATED_QUANTITY) + CHANGE_QUANTITY) + " Step Down: " + (memorizedNomenclatureInitialQuantity - CREATED_QUANTITY - CHANGE_QUANTITY));
        assertNotNull(n);
    }

    /**
     * create new invoice and remembers invoice ID and test assertNotNull
     */
    @Test(priority = 1)
    public void createInvoiceTest() {
        OutputInvoice outputInvoice = getTestInvoice();
        outputInvoicesService.setCurrentElement(outputInvoice);
        try {
            outputInvoice = outputInvoicesService.saveOrUpdate();
            memorizedCreatedInvoiceID = outputInvoice.getId();
            System.out.println("2. created invoice ID: " + memorizedCreatedInvoiceID);
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(outputInvoice);
    }

    /**
     * try to read created invoice from db
     */
    @Test(priority = 2, description = "check for created invoiced")
    public void readCreatedInvoiceTest() {
        OutputInvoice oi = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(oi);
    }

    /**
     * test business logic, if invoice created must change quantity of
     * nomenclature. Checks changes of quantity.
     */
    @Test(priority = 3)
    public void readNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity - CREATED_QUANTITY));
    }

    /**
     * try change quantity of goods in invoice. BLogic - must be: change
     * quantity in goods and nomenclature and on storehouse
     */
    @Test(priority = 4, description = "change quantity to UP")
    public void changeInvoiceGoodsQuantityUPTest() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(outputInvoice);
        outputInvoicesService.setCurrentElement(outputInvoice);
        for (OutputGoods g : outputInvoice.getGoods()) {
            g.setQuantity(CREATED_QUANTITY + CHANGE_QUANTITY);
        }
        try {
            outputInvoice = outputInvoicesService.saveOrUpdate();
            for (OutputGoods g : outputInvoice.getGoods()) {
                assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY + CHANGE_QUANTITY));
            }
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 5, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityUPTest() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (OutputGoods g : outputInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY + CHANGE_QUANTITY));
        }
    }

    @Test(priority = 6, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityWitchChangesTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity - CREATED_QUANTITY - CHANGE_QUANTITY));
    }

    @Test(priority = 7, description = "change quantity to down")
    public void changeInvoiceGoodsQuantityDownTest() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        outputInvoicesService.setCurrentElement(outputInvoice);
        for (OutputGoods g : outputInvoice.getGoods()) {
            g.setQuantity(CREATED_QUANTITY - CHANGE_QUANTITY);
        }
        try {
            outputInvoicesService.saveOrUpdate();
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 8, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityDownTest() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (OutputGoods g : outputInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY - CHANGE_QUANTITY));
        }
    }

    @Test(priority = 9, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityChangesBackTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf((memorizedNomenclatureInitialQuantity - CREATED_QUANTITY) + CHANGE_QUANTITY));
    }

    @Test(priority = 10)
    public void readNomenclatureQuantityNotLesThanZeroTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertTrue(n.getQuantity() >= 0 ? true : false);
    }

    @Test(priority = 11, description = "remove invoice")
    public void removeInvoiceTest() {
        outputInvoicesService.removeCurrentElement(outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID), true);
    }

    @Test(priority = 12, description = "check invoice on remove")
    public void readRemovedInvoiceTest() {
        OutputInvoice oi = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNull(oi);
    }

    @Test(priority = 13, description = "check quantity of nomenclature")
    public void finalCheckNomenclatureQuantityTest() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity));
    }

    // ////////////////////
    @Test(priority = 14)
    public void createInvoiceTest2() {
        OutputInvoice outputInvoice = getTestInvoice();
        for (OutputGoods g : outputInvoice.getGoods()) {
            g.setQuantity(memorizedNomenclatureInitialQuantity);
        }
        outputInvoicesService.setCurrentElement(outputInvoice);
        try {
            outputInvoice = outputInvoicesService.saveOrUpdate();
            memorizedCreatedInvoiceID = outputInvoice.getId();
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(outputInvoice);
    }

    /**
     * try to read created invoice from db
     */
    @Test(priority = 15, description = "check for created invoiced")
    public void readCreatedInvoiceTest2() {
        OutputInvoice oi = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(oi);
    }

    /**
     * test business logic, if invoice created must change quantity of
     * nomenclature. Checks changes of quantity.
     */
    @Test(priority = 16)
    public void readNomenclatureQuantityTest2() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(0));
    }

    /**
     * try change quantity of goods in invoice. BLogic - must be: change
     * quantity in goods and nomenclature and on storehouse
     */
    @Test(priority = 17, description = "change quantity to Down")
    public void changeInvoiceGoodsQuantityUPTest2() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNotNull(outputInvoice);
        outputInvoicesService.setCurrentElement(outputInvoice);
        for (OutputGoods g : outputInvoice.getGoods()) {
            g.setQuantity(CHANGE_QUANTITY);
        }
        try {
            outputInvoice = outputInvoicesService.saveOrUpdate();
            for (OutputGoods g : outputInvoice.getGoods()) {
                assertEquals(g.getQuantity(), Integer.valueOf(CHANGE_QUANTITY));
            }
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 18, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityUPTest2() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (OutputGoods g : outputInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CHANGE_QUANTITY));
        }
    }

    @Test(priority = 19, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityWitchChangesTest2() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity - CHANGE_QUANTITY));
    }

    @Test(priority = 20, description = "change quantity to down")
    public void changeInvoiceGoodsQuantityDownTest2() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        outputInvoicesService.setCurrentElement(outputInvoice);
        for (OutputGoods g : outputInvoice.getGoods()) {
            g.setQuantity(CREATED_QUANTITY + CHANGE_QUANTITY);
        }
        try {
            outputInvoicesService.saveOrUpdate();
        } catch (Exception ex) {
            Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(priority = 21, description = "check changes quantity of goods")
    public void checkChangesInvoiceGoodsQuantityDownTest2() {
        OutputInvoice outputInvoice = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        for (OutputGoods g : outputInvoice.getGoods()) {
            assertEquals(g.getQuantity(), Integer.valueOf(CREATED_QUANTITY + CHANGE_QUANTITY));
        }
    }

    @Test(priority = 22, description = "check changes quantity of nomenclature")
    public void readNomenclatureQuantityChangesBackTest2() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf((memorizedNomenclatureInitialQuantity - CHANGE_QUANTITY) - CREATED_QUANTITY));
    }

    @Test(priority = 23)
    public void readNomenclatureQuantityNotLesThanZeroTest2() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertTrue(n.getQuantity() >= 0 ? true : false);
    }

    @Test(priority = 24, description = "remove invoice")
    public void removeInvoiceTest2() {
        outputInvoicesService.removeCurrentElement(outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID), true);
    }

    @Test(priority = 25, description = "check invoice on remove")
    public void readRemovedInvoiceTest2() {
        OutputInvoice oi = outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID);
        assertNull(oi);
    }

    @Test(priority = 26, description = "check quantity of nomenclature")
    public void finalCheckNomenclatureQuantityTest2() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        assertEquals(n.getQuantity(), Integer.valueOf(memorizedNomenclatureInitialQuantity));
    }

    // ///////////////////
    @Test(priority = 27, description = "check try create 0 quantity of goods in invoice, test 1")
    public void checkNomenclatureQuantityTest1Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(0);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(1);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 28, description = "check try create 0 quantity of goods in invoice, test 2")
    public void checkNomenclatureQuantityTest2Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(0);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(5);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 29, enabled = false, description = "check try create goods with max quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest3Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(0);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputGoods.setId(452L);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(1);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 30, enabled = false, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest4Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(0);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputGoods.setId(452L);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(5);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    //
    @Test(priority = 31, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest5Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(1);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputGoods.setId(452L);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(1);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 32, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest6Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(1);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(1);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 33, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest7Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(20);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(20);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 34, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest8Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(20);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputGoods.setId(452L);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(20);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 35, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest9Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(2);
        OutputGoods outputGoods = new OutputGoods(452L, 452L, n, 2, 2);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(4);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 36, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest10Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(2);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(4);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 37, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest11Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(20);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(20);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 38, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest12Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(20);
        OutputGoods outputGoods = new OutputGoods(452L, n, 1);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(21);
        outputInvoicesService.validate();
        assertTrue(!outputInvoicesService.isCanSaveGoods());
    }

    @Test(priority = 39, description = "check try create goods with max+1 quantity of nomenclature in goods, test 3")
    public void checkNomenclatureQuantityTest13Test() {
        Nomenclature n = nomenclatureDAO.getCurrentElement(NOMENCLATURE_ID);
        n.setQuantity(2);
        OutputGoods outputGoods = new OutputGoods(452L, 452L, n, 2, 2);
        outputInvoicesService.setCurrentGoodsElement(outputGoods);
        outputInvoicesService.setValidateQuantityOfGoods(4);
        outputInvoicesService.validate();
        assertTrue(outputInvoicesService.isCanSaveGoods());
    }

    public OutputInvoiceNGTest() {
    }
}