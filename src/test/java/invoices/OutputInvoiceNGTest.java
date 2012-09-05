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
	public OutputInvoicesService	outputInvoicesService;
	@Autowired
	public OutputInvoicesDAO		outputInvoicesDAO;
	@Autowired
	public NomenclatureDAO			nomenclatureDAO;
	@Resource
	EntityManagerFactory			entityManagerFactory;
	final static Integer			CREATED_QUANTITY						= 1;
	final static Integer			CHANGE_QUANTITY							= 3;
	final static Long				NOMENCLATURE_ID							= 3100L;
	final static Long				NOMENCLATURE_PARENT_ID					= 3100L;
	final static String				NOMENCLATURE_TITLE						= "СЗУ Силибрити Nokia 6101";
	public static int				memorizedNomenclatureInitialQuantity	= 0;
	public static Long				memorizedCreatedInvoiceID				= 0L;

	/**
	 * billet of invoice
	 * 
	 * @return OutputInvoice
	 */
	private OutputInvoice getTestInvoice() {
		OutputInvoice outInvoice = new OutputInvoice();
		OutputGoods outputGoods = new OutputGoods(452L, new Nomenclature(NOMENCLATURE_ID, NOMENCLATURE_PARENT_ID, NOMENCLATURE_TITLE), CREATED_QUANTITY);
		outInvoice.setPaidAmount(452.678);
		outInvoice.setInfo("452_2");
		outInvoice.setStoreHouse(new StoreHouse(1L));
		outInvoice.setCounterParty(new CounterParties(4L));
		outInvoice.addGoods(outputGoods);
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
		OutputInvoice oi = getTestInvoice();
		outputInvoicesService.setCurrentElement(oi);
		try {
			oi = outputInvoicesService.saveOrUpdate();
			memorizedCreatedInvoiceID = oi.getId();
			System.out.println("2. created invoice ID: " + memorizedCreatedInvoiceID);
		}
		catch (Exception ex) {
			Logger.getLogger(OutputInvoiceNGTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		assertNotNull(oi);
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
		}
		catch (Exception ex) {
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
		}
		catch (Exception ex) {
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
		outputInvoicesService.removeCurrentElement(outputInvoicesDAO.getCurrentElement(memorizedCreatedInvoiceID));
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

	public OutputInvoiceNGTest() {
	}
}