package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeNotRegisteredException;
import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.common.WalletEmptyException;
import uk.ac.standrews.cs.cs2001.w03.interfaces.ICustomer;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachine;
import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Customer implements ICustomer{
    private double wallet;

    @Override
    public void addMoney(double quantity) throws WalletEmptyException {
        if (quantity >= 0) {
            wallet += quantity;
        } else {
            throw new WalletEmptyException();
        }
    }

    @Override
    public void takeMoney(double quantity) throws WalletEmptyException{
        if (quantity >= 0 && quantity <= this.getWallet()) {
            wallet -= quantity;
            wallet = roundTwoDecimals(wallet);
        } else {
            throw new WalletEmptyException();
        }
    }

    public double getWallet() {
        return wallet;
    }

    @Override
    public void buyItem(IVendingMachine vendingMachine, String laneCode, int quantity)
    throws WalletEmptyException, LaneCodeNotRegisteredException, ProductUnavailableException {
            vendingMachine.buyItem(laneCode, quantity, this);
    }

    //Method found on internet, used for rounding doubles before displaying in GUI
    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
