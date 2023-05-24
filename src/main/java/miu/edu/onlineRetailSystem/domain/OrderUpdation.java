package miu.edu.onlineRetailSystem.domain;


public class OrderUpdation {

    private int orderId;
    private int customerId;
    private OrderStatus orderStatus;



    public OrderUpdation(){}

    public OrderUpdation(int orderId, int customerId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
