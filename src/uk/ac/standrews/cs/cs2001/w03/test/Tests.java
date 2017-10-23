package uk.ac.standrews.cs.cs2001.w03.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import uk.ac.standrews.cs.cs2001.w03.common.*;
import uk.ac.standrews.cs.cs2001.w03.interfaces.*;

import java.util.EmptyStackException;

/**
 * This is a JUnit test class for the Vending Machine ADT.
 *
 */
public class Tests extends AbstractFactoryClient {
    private IFactory factory;
    private IVendingMachine vendingMachine;
    private IVendingMachineProduct vendingMachineProduct1;
    private IVendingMachineProduct vendingMachineProduct2;
    private ICustomer customer1;
    private final String LANE_CODE1 = "A1";
    private final String DESCRIPTION1 = "Haggis Crisps";
    private final String LANE_CODE2 = "B2";
    private final String DESCRIPTION2 = "Canned coffee";
    private final int QUANTITY1 = 1;
    private final int QUANTITY2 = 2;
    private final double PRICE1 = 2.48;
    private final double PRICE2 = 1.99;
    private final double WALLET1 = 34.9;

    @Before
    public void setup() {
        factory = getFactory();
        vendingMachine = factory.makeVendingMachine();
        vendingMachineProduct1 = factory
                .makeVendingMachineProduct(LANE_CODE1, DESCRIPTION1, PRICE1);
        vendingMachineProduct2 = factory
                .makeVendingMachineProduct(LANE_CODE2, DESCRIPTION2, PRICE2);
        customer1 = factory.makeCustomer();
    }

    @After
    public void teardown() {
        factory = null;
        vendingMachine = null;
        vendingMachineProduct1 = null;
        vendingMachineProduct2 = null;
        customer1 = null;
    }

    //VendingMachineProduct Tests
    @Test
    public void laneCodeRetrievalTest() {
        assertEquals(LANE_CODE1 , vendingMachineProduct1.getLaneCode());
    }

    @Test
    public void descriptionRetrievalTest() {
        assertEquals(DESCRIPTION1, vendingMachineProduct1.getDescription());
    }

    //register/unregister
    @Test
    public void vendingMachineProductRegTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            assertEquals(0, vendingMachine.getNumberOfItems(LANE_CODE1));
        } catch (LaneCodeNotRegisteredException | LaneCodeAlreadyInUseException e) {
            fail();
        }
    }

    //Can't throw custom Exceptions without changing the interface
    @Test (expected = NullPointerException.class)
    public void vendingMachineProductRegNullTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        IVendingMachineProduct vendingMachineProduct3 = factory.makeVendingMachineProduct(null, DESCRIPTION1, PRICE1);
    }

    //Can't throw custom Exceptions without changing the interface
    @Test (expected = NullPointerException.class)
    public void vendingMachineProductRegNull2Test() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        IVendingMachineProduct vendingMachineProduct3 = factory.makeVendingMachineProduct(LANE_CODE1, null, PRICE1);
    }

    //Can't throw custom Exceptions without changing the interface
    @Test (expected = NullPointerException.class)
    public void vendingMachineProductRegEmptyTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        IVendingMachineProduct vendingMachineProduct3 = factory.makeVendingMachineProduct("", DESCRIPTION1, PRICE1);
    }

    @Test
    public void vendingMachineProductRegEmpty2Test() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        IVendingMachineProduct vendingMachineProduct3 = factory.makeVendingMachineProduct(LANE_CODE1, "", PRICE1);
        assertEquals("", vendingMachineProduct3.getDescription());
    }

    @Test (expected = NullPointerException.class)
    public void vendingMachineProductRegEmptyBothTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        IVendingMachineProduct vendingMachineProduct3 = factory.makeVendingMachineProduct("", "", PRICE1);
    }

    @Test (expected = LaneCodeNotRegisteredException.class)
    public void vendingMachineProductUnregisterTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.unregisterProduct(vendingMachineProduct1);
        int exceptionInt = vendingMachine.getNumberOfItems(LANE_CODE1);
    }

    @Test (expected = LaneCodeAlreadyInUseException.class)
    public void vendingMachineRegLaneCodeAlreadyInUseTest() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct1);
    }

    @Test (expected = LaneCodeAlreadyInUseException.class)
    public void vendingMachineRegLaneCodeAlreadyInUse2Test() throws LaneCodeAlreadyInUseException {
        IVendingMachineProduct vendingMachineProduct3 = getFactory()
                .makeVendingMachineProduct(LANE_CODE1, DESCRIPTION2, PRICE1);
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.registerProduct(vendingMachineProduct3);
    }

    @Test (expected = LaneCodeNotRegisteredException.class)
    public void unregisterLandCodeNotRegTest() throws LaneCodeNotRegisteredException{
        vendingMachine.unregisterProduct(vendingMachineProduct1);
    }

    //ICustomer

    @Test
    public void customerAddMoneyTest() {
        try {
            customer1.addMoney(WALLET1);
            assertEquals(WALLET1, customer1.getWallet(), 0.01);
        } catch (WalletEmptyException e) {
            System.err.print("Ran into exception: WalletEmptyException");
        }
    }

    @Test (expected = WalletEmptyException.class)
    public void customerAddMoneyNegativeTest() throws WalletEmptyException{
        customer1.addMoney(-2);
    }

    @Test
    public void customerTakeMoneyTest() {
        try {
            customer1.addMoney(WALLET1);
            customer1.takeMoney(9.05);
            assertEquals(25.85, customer1.getWallet(), 0.01);
        } catch (WalletEmptyException e) {
            System.err.print("Ran into Exception: WalletEmptyException");
        }
    }

    @Test (expected = WalletEmptyException.class)
    public void customerTakeMoneyNegativeTest() throws WalletEmptyException{
        customer1.takeMoney(WALLET1);
    }

    @Test (expected = WalletEmptyException.class)
    public void customerTakeMoneyNegative2Test() throws WalletEmptyException{
        customer1.takeMoney(-43);
    }


    //add/buy

    //getNumberOfProducts/getTotalNumberOfItems
    @Test
    public void vendingMachineNumOfProductsZeroTest(){
        assertEquals(0, vendingMachine.getNumberOfProducts());
    }

    @Test
    public void vendingMachineNumOfProductsOneTest(){
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            assertEquals(1, vendingMachine.getNumberOfProducts());
        } catch (LaneCodeAlreadyInUseException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineNumOfProductsTwoTest(){
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            assertEquals(2, vendingMachine.getNumberOfProducts());
        } catch (LaneCodeAlreadyInUseException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineTotalNumOfItemsZeroTest(){
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            assertEquals(0, vendingMachine.getTotalNumberOfItems());
        } catch (LaneCodeAlreadyInUseException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineProductNumberTwoTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.addItem(LANE_CODE1, QUANTITY2);
            assertEquals(2, vendingMachine.getNumberOfItems(LANE_CODE1));
        } catch (LaneCodeNotRegisteredException | LaneCodeAlreadyInUseException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineAddAndBuyTest() {
        try {
            customer1.addMoney(30);
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.addItem(LANE_CODE1, QUANTITY1);
            vendingMachine.buyItem(LANE_CODE1, QUANTITY1, customer1);
            assertEquals(0, vendingMachine.getNumberOfItems(LANE_CODE1));
        } catch (LaneCodeNotRegisteredException | LaneCodeAlreadyInUseException | ProductUnavailableException | WalletEmptyException e) {
            fail();
        }
    }

    @Test (expected = LaneCodeNotRegisteredException.class)
    public void addItemCodeNotRegException() throws LaneCodeNotRegisteredException{
        vendingMachine.addItem("XYZ", QUANTITY1);
    }

    @Test (expected = WalletEmptyException.class)
    public void vendingMachineBuyProductUnavailableTest() throws ProductUnavailableException, LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException, WalletEmptyException {
        vendingMachine.registerProduct(vendingMachineProduct1);
        vendingMachine.buyItem(LANE_CODE1, QUANTITY1, customer1);
    }

    @Test (expected = LaneCodeNotRegisteredException.class)
    public void vendingMachineBuyLaneCodeNotRegTest() throws LaneCodeNotRegisteredException, ProductUnavailableException, WalletEmptyException{
        vendingMachine.buyItem(LANE_CODE1, QUANTITY1, customer1);
    }

/*

    @Test
    public void vendingMachineTotalNumOfItemsSingleProductTest(){
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.addItem(LANE_CODE1);
            assertEquals(1, vendingMachine.getTotalNumberOfItems());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineTotalNumOfItemsMultipleProductTest(){
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE2);
            assertEquals(3, vendingMachine.getTotalNumberOfItems());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    //getNumberOfItems/getNumberOfSales
    @Test
    public void vendingMachineGetNumberOfItemsMultipleProductsTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            assertEquals(1, vendingMachine.getNumberOfItems(LANE_CODE1));
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    @Test(expected = LaneCodeNotRegisteredException.class)
    public void vendingMachineGetNumberOfItemsLaneCodeNotRegTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        vendingMachine.getNumberOfItems(LANE_CODE1);
    }

    @Test
    public void vendingMachineGetNumberOfSalesZeroTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            assertEquals(0, vendingMachine.getNumberOfSales(LANE_CODE1));
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineGetNumberOfSalesOneTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.buyItem(LANE_CODE1);
            assertEquals(1, vendingMachine.getNumberOfSales(LANE_CODE1));
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException | ProductUnavailableException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineGetNumberOfSalesMultipleProductsTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE2);
            vendingMachine.buyItem(LANE_CODE1);
            vendingMachine.buyItem(LANE_CODE1);
            assertEquals(2, vendingMachine.getNumberOfSales(LANE_CODE1));
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException | ProductUnavailableException e) {
            fail();
        }
    }
    @Test
    public void vendingMachineGetNumberOfSalesMultipleProductsBuyTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE2);
            vendingMachine.buyItem(LANE_CODE1);
            vendingMachine.buyItem(LANE_CODE2);
            assertEquals(1, vendingMachine.getNumberOfSales(LANE_CODE2));
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException | ProductUnavailableException e) {
            fail();
        }
    }

    @Test(expected = LaneCodeNotRegisteredException.class)
    public void vendingMachineGetNumberOfSalesLaneCodeNotRegTest() throws LaneCodeNotRegisteredException, LaneCodeAlreadyInUseException{
        vendingMachine.getNumberOfSales(LANE_CODE1);
    }

    //findProduct
    @Test
    public void vendingMachineGetMostPopularNeitherTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            assertEquals(null, vendingMachine.getMostPopular());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineGetMostPopularExistsTest() {
        try {
            IVendingMachineProduct p = vendingMachineProduct1;
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE2);
            vendingMachine.buyItem(LANE_CODE2);
            assertEquals(vendingMachineProduct2, vendingMachine.getMostPopular());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException | ProductUnavailableException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineGetMostPopularSingleTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            assertEquals(vendingMachineProduct1, vendingMachine.getMostPopular());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException e) {
            fail();
        }
    }

    @Test
    public void vendingMachineGetMostPopularEqualTest() {
        try {
            vendingMachine.registerProduct(vendingMachineProduct1);
            vendingMachine.registerProduct(vendingMachineProduct2);
            vendingMachine.addItem(LANE_CODE1);
            vendingMachine.addItem(LANE_CODE2);
            vendingMachine.buyItem(LANE_CODE1);
            vendingMachine.buyItem(LANE_CODE2);
            assertEquals(null, vendingMachine.getMostPopular());
        } catch (LaneCodeAlreadyInUseException | LaneCodeNotRegisteredException | ProductUnavailableException e) {
            fail();
        }
    }

    @Test(expected = LaneCodeNotRegisteredException.class)
    public void vendingMachineGetMostPopularLaneCodeNotRegTest() throws LaneCodeNotRegisteredException{
       IVendingMachineProduct exceptionProduct = vendingMachine.getMostPopular();

    }

    //ProductRecord tests

    @Test
    public void productRecordGetProductTest() {
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        assertEquals(vendingMachineProduct1, productRecord.getProduct());
    }

    @Test
    public void productRecordGetNumberOfSalesZeroTest() {
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        assertEquals(0, productRecord.getNumberOfSales());
    }

    @Test
    public void productRecordGetNumberOfSalesOneTest() throws ProductUnavailableException{
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        productRecord.addItem();
        productRecord.buyItem();
        assertEquals(1, productRecord.getNumberOfSales());
    }

    @Test (expected = ProductUnavailableException.class)
    public void productRecordGetNumberOfSalesException() throws ProductUnavailableException {
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        productRecord.buyItem();
    }

    @Test
    public void productRecordGetNumberAvailableZeroTest() {
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        assertEquals(0, productRecord.getNumberAvailable());
    }


    @Test
    public void productRecordGetNumberAvailableZeroAfterBuyTest() throws ProductUnavailableException{
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        productRecord.addItem();
        productRecord.buyItem();
        assertEquals(0, productRecord.getNumberAvailable());
    }

    @Test
    public void productRecordGetNumberAvailableOneAfterBuyTest() throws ProductUnavailableException{
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        productRecord.addItem();
        productRecord.addItem();
        productRecord.buyItem();
        assertEquals(1, productRecord.getNumberAvailable());
    }

    @Test
    public void productRecordGetNumberAvailableMany() throws ProductUnavailableException{
        IProductRecord productRecord = factory.makeProductRecord(vendingMachineProduct1);
        for (int i = 0; i < 20; i++) {
            productRecord.addItem();
        }
        assertEquals(20, productRecord.getNumberAvailable());
    }
    */
}