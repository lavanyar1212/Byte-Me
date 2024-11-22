import java.util.*;

public class Cart {
    HashMap<Food, Integer> cart;
    private static int orderCounter = 0;

    Cart() {
        cart = new HashMap<>();
    }
    Scanner object = new Scanner(System.in);

    void View_Cart(){
        if (cart.isEmpty()){
            System.out.println("Your Cart is Empty.");
        }
        else{
            System.out.println("Your Cart: ");
            for (Map.Entry<Food, Integer> entry : cart.entrySet()){
                Food food = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("Item Name: " + food.itemname);
                System.out.println( "Price: " + food.price);
                System.out.println("Quantity: " + quantity);
            }
        }
    }

    void Add_Item_To_Cart(Food food, int quantity) {
        if (cart.containsKey(food)) {
            System.out.println("Item Already Present In Cart.");
        } else {
            if (food.availabilty.equals("Available")) {
                cart.put(food, quantity);
                System.out.println("Item: " + food.itemname + "\n" + "Quantity: " + quantity);
                System.out.println("Item Added To Cart.");
            } else {
                System.out.println("Item Not Available.");
            }
        }
    }
    void Modify_Quantity_Of_Cart_Item(Food food, int quantity) {
        if (cart.containsKey(food)) {
            cart.replace(food, quantity);
        }
        System.out.println("Item: " + food.itemname +"\n"+ "New Quantity: " + quantity);
        System.out.println("Item Modified In Cart.");
    }

    void Remove_Item_From_Cart(Food food){
        if (cart.containsKey(food)) {
            cart.remove(food);
        }
        System.out.println("Item Removed From Cart.");
    }

    int Cart_Total(){
        int total_amount = 0;
        int amount = 0;
        for (Map.Entry<Food, Integer> entry : cart.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            amount = quantity*food.price;
            total_amount += amount;
        }
        return total_amount;

    }

    OrderedCart Checkout_And_Payment(Customer customer){
        int total_amount = Cart_Total();
        System.out.println("Any Special Requests: Y/N");
        String choice = object.nextLine();
        String specialRequest = "";
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("Enter Special Request: ");
            specialRequest = object.nextLine();
        }
        System.out.println("Enter Delivery Address: ");
        String delivery = object.nextLine();
        System.out.println("Enter Card Details: ");
        String details = object.nextLine();

        String orderId = String.valueOf((++orderCounter % 50) + 1);
        OrderedCart orderedCart = new OrderedCart(orderId, customer, new HashMap<>(cart), specialRequest,details, total_amount);
        System.out.println("Order Placed Successfully.");
        System.out.println("Your Order ID: " + orderId);
        if (customer.VIP == true){
            Main.VIPOrderedCartList.add(orderedCart);
        }else{
            Main.orderedCartList.add(orderedCart);
        }
        cart.clear();
        return orderedCart;
    }
}
