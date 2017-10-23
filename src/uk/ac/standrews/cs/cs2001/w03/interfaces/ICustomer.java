package uk.ac.standrews.cs.cs2001.w03.interfaces;

import uk.ac.standrews.cs.cs2001.w03.common.LaneCodeNotRegisteredException;
import uk.ac.standrews.cs.cs2001.w03.common.ProductUnavailableException;
import uk.ac.standrews.cs.cs2001.w03.common.WalletEmptyException;

public interface ICustomer {

    //Adds money to the wallet of the customer
    void addMoney(double quantity) throws WalletEmptyException;

    //Takes money from the wallet of the customer
    void takeMoney(double quantity) throws WalletEmptyException;

    //Returns current amount of money in wallet
    double getWallet();

    //Buys given quantity of items from given vending machine using money from customer
    void buyItem(IVendingMachine vendingMachine, String laneCode, int quantity)
            throws WalletEmptyException, LaneCodeNotRegisteredException, ProductUnavailableException;
/*
    int itemCount(IVendingMachineProduct vendingMachineProduct);

    int totalItemCount();
*/



}
