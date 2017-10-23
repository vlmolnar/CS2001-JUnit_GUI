package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;

/**
 * This class represents a ProductRecord, recording information relating to a product sold in a vending machine.
 *
 */
public class ProductRecord implements IProductRecord {
    private IVendingMachineProduct product;
    private int numberOfSales;
    private int numberAvailable;

    public ProductRecord(IVendingMachineProduct vendingMachineProduct) {
        this.product = vendingMachineProduct;
        this.numberOfSales = 0;
        this.numberAvailable = 0;
    }

    @Override
    public IVendingMachineProduct getProduct() {
        // TODO Auto-generated method stub
        return product;
    }

    @Override
    public int getNumberOfSales() {
        // TODO Auto-generated method stub
        return numberOfSales;
    }

    @Override
    public int getNumberAvailable() {
        // TODO Auto-generated method stub
        return numberAvailable;
    }

    @Override
    public void addItem(int quantity) {
        // TODO Auto-generated method stub
        numberAvailable += quantity;

    }

    @Override
    public void buyItem(int quantity) throws ProductUnavailableException {
        // TODO Auto-generated method stub
        if (numberAvailable >= quantity) {
            numberAvailable -= quantity;
            numberOfSales += quantity;
        } else {
            throw new ProductUnavailableException();
        }

    }

}
