package uk.ac.standrews.cs.cs2001.w03.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.standrews.cs.cs2001.w03.common.*;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IProductRecord;

/**
 * This class represents a simple vending machine which can stock and sell products.
 *
 */
public class VendingMachine extends AbstractFactoryClient implements IVendingMachine {

    //HashMap that contains a product in a machine and its record
    private Map<IVendingMachineProduct, IProductRecord> products = new HashMap<>();

    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct)
            throws LaneCodeAlreadyInUseException {
        String laneCode = vendingMachineProduct.getLaneCode();
        IVendingMachineProduct myProduct = findProduct(laneCode);
        boolean allGood = laneCodeGood(laneCode);
        if (myProduct == null && allGood) {
            IProductRecord newRecord = Factory.getInstance().makeProductRecord(vendingMachineProduct);
            products.put(vendingMachineProduct, newRecord);
        } else {
            throw new LaneCodeAlreadyInUseException();
        }
    }


    //Makes sure the lane code only contains letters and numbers
    private boolean laneCodeGood(String laneCode) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(laneCode);
        if(laneCode == null || laneCode.equals("") || matcher.find()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        if (products.containsKey(vendingMachineProduct)) {
            products.remove(vendingMachineProduct);
        } else {
            throw new LaneCodeNotRegisteredException();
        }

    }

    @Override
    public void addItem(String laneCode, int quantity) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        IVendingMachineProduct myProduct = findProduct(laneCode);
        if (myProduct != null) {
            IProductRecord myRecord = products.get(myProduct);
            myRecord.addItem(quantity);
        } else {
            throw new LaneCodeNotRegisteredException();
        }
    }

    @Override
    public void buyItem(String laneCode, int quantity, ICustomer customer) throws ProductUnavailableException, LaneCodeNotRegisteredException, WalletEmptyException {
        // TODO Auto-generated method stub
        double totalPrice;
        IVendingMachineProduct myProduct = findProduct(laneCode);
        if (myProduct != null) {
            IProductRecord myRecord = products.get(myProduct);
            totalPrice = myRecord.getProduct().getPrice() * quantity;
            if (customer.getWallet() >= totalPrice) {
                myRecord.buyItem(quantity);
                customer.takeMoney(totalPrice);
            } else {
                throw new WalletEmptyException();
            }
        } else {
            throw new LaneCodeNotRegisteredException();
        }
    }

    @Override
    public int getNumberOfProducts() {
        // TODO Auto-generated method stub
        return products.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        // TODO Auto-generated method stub
        int total = 0;
        for (IProductRecord value : products.values()) {
            total += value.getNumberAvailable();
        }
        return total;
    }



    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        int numAvailable;
        IVendingMachineProduct myProduct = findProduct(laneCode);
        if (myProduct != null) {
            IProductRecord myRecord = products.get(myProduct);
           numAvailable = myRecord.getNumberAvailable();
        } else {
            throw new LaneCodeNotRegisteredException();
        }

        return numAvailable;
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        int numSale;
        IVendingMachineProduct myProduct = findProduct(laneCode);
        if (myProduct != null) {
            IProductRecord myRecord = products.get(myProduct);
            numSale = myRecord.getNumberOfSales();
        } else {
            throw new LaneCodeNotRegisteredException();
        }
        return numSale;
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {
        //returns null if there is more than 1 most popular item
        IVendingMachineProduct popularProduct = null;
        int popularSale = -1;
        if (products.values().size() == 0) {
            throw new LaneCodeNotRegisteredException();
        }
        for (IProductRecord value : products.values()) {
            int currentProductSale = value.getNumberOfSales();
            if (currentProductSale > popularSale) {
                popularSale = currentProductSale;
                popularProduct = value.getProduct();
            } else if (popularSale == currentProductSale) {
                //Returns null if no singular most popular item
                popularProduct = null;
            }
        }
        return popularProduct;
    }

    //Method to avoid repetition in code, returns null if product not registered
    private IVendingMachineProduct findProduct(String laneCode) {
        IVendingMachineProduct myProduct = null;
        for (IVendingMachineProduct key : products.keySet()) {
            if (key.getLaneCode().equals(laneCode)) {
                myProduct = key;
                break;
            }
        }
        return myProduct;
    }
}