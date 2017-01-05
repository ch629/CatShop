package uk.ac.brighton.uni.ch629.catshop.update;

public class UpdateOrderID implements Update { //Server -> (Cashier, ShopDisplay)
    private final int orderID;

    public UpdateOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getType() {
        return "UpdateOrderID";
    }

    public int getOrderID() {
        return orderID;
    }
}
