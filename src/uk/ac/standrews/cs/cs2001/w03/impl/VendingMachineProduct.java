package uk.ac.standrews.cs.cs2001.w03.impl;

import uk.ac.standrews.cs.cs2001.w03.interfaces.IVendingMachineProduct;

/**
 * This class represents products that can be stocked and sold in a vending machine in a specific lane.
 *
 */
public class VendingMachineProduct implements IVendingMachineProduct {
    private String laneCode;
    private String description;
    private double price;

    public VendingMachineProduct(String laneCode, String description, double price) {
        this.laneCode = laneCode;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getLaneCode() {
        // TODO Auto-generated method stub
        return laneCode;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

}
