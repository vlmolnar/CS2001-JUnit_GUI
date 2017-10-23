package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.interfaces.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description, double price) {
        // TODO Auto-generated method stub
        //Product names can have various special characters, but lane codes are more strict
        boolean laneCodeGood = laneCodeGood(laneCode);
        if (laneCode != null && description != null && laneCodeGood && price >= 0) {
            IVendingMachineProduct vendingMachineProduct =
                    new VendingMachineProduct(laneCode, description, price);
            return vendingMachineProduct;
        } else {
            //Bad Exception handling because we're not allowed to make changes to the interface
            throw new NullPointerException();
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
    public IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct) {
        // TODO Auto-generated method stub
        IProductRecord productRecord = new ProductRecord(vendingMachineProduct);
        return productRecord;
    }

    @Override
    public IVendingMachine makeVendingMachine() {
        // TODO Auto-generated method stub
        IVendingMachine vendingMachine = new VendingMachine();
        return vendingMachine;
    }

    @Override
    public ICustomer makeCustomer() {
        ICustomer customer = new Customer();
        return customer;
    }

}
