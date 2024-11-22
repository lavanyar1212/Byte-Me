import java.util.*;

public class OrderedCart {
    String orderId;
    Customer customer;
    String status;
    HashMap<Food, Integer> orderedItems;
    String specialRequest;
    String paymentMethod;
    int totalPrice;

    OrderedCart(String orderId, Customer customer, HashMap<Food, Integer> orderedItems, String specialRequest, String paymentMethod, int totalprice) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderedItems = orderedItems;
        this.status = "Order Received.";
        this.specialRequest = specialRequest;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalprice;
    }

    void update_Status(String status) {
        this.status = status;
        System.out.println("Order Status updated to : " + status);
    }

    void display_Cart_Details() {
        System.out.println("Order ID : " + orderId);
        System.out.println("Customer: " + customer.emailid);
        System.out.println("Status: " + status);
        System.out.println("OrderedItems: ");
        for (Map.Entry<Food, Integer> entry : orderedItems.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Item name: " + food.itemname + " Quantity: " + quantity);
        }
    }


}
