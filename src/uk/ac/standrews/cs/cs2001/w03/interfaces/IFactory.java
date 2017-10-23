package uk.ac.standrews.cs.cs2001.w03.interfaces;


/**
 * Interface for a factory allowing the other interfaces to be instantiated without knowing the implementation classes.
 *
 */
public interface IFactory {

    /**
     * Creates an instance of {@link IVendingMachineProduct}.
     * @param laneCode the item's lane code, i.e. which lane the item is in, e.g. A1, A2, A3, B1, ... in the vending machine
     * @param description the description of the item
     * @return the item
     */
    IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description, double price);


    /**
     * This method creates an instance of {@link IProductRecord} for a new product.
     * @param vendingMachineProduct the product to use for this record
     * @return the product record
     */
    IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct);


    /**
     * Creates an instance of {@link IVendingMachine}.
     *
     * @return the vending machine
     */
    IVendingMachine makeVendingMachine();

    //Creates instance of ICustomer class
    ICustomer makeCustomer();


}
