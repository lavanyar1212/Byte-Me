import java.util.*;

public class Admin extends User{

    Admin (String emailid, String password){
        super(emailid, password);
    }
    Scanner object = new Scanner(System.in);

    void View_All_Food_Items(){
        for (int i = 0; i < Main.foodlist.size(); i++){
            Food food = Main.foodlist.get(i);
            System.out.println((i + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty + "\n Category: " + food.category);
        }
    }

    void Add_Food_Item(Food food){
        if (!Main.foodlist.contains(food)){
            Main.foodlist.add(food);
            System.out.println("Food Item Added.");
        }else{
            System.out.println("Food Item Already Present.");
        }
    }

    void Update_Item_Details(Food food, int newprice, String newavailabilty){
        if (Main.foodlist.contains(food)){
            food.setPrice(newprice);
            food.setAvailabilty(newavailabilty);
            System.out.println("Item " + food.itemname + ": Details Updated.");
        }
    }

    void Remove_Items(Food food){
        if ("Not Available".equals(food.availabilty)){
            if (Main.foodlist.remove(food)){
                System.out.println("Item Not Available Removed.");
            }else{
                System.out.println("All Items are Available.");
            }
        }
        else if(Main.foodlist.remove(food)){
            System.out.println("Discontinued Item Removed.");
        }
        else{
            System.out.println("Item Not Present.");
        }

        if (Main.VIPOrderedCartList!=null){
            for (OrderedCart order : Main.VIPOrderedCartList){
                if (order.orderedItems.containsKey(food)){
                    order.status = "Denied";
                }
            }
        }
        if (Main.orderedCartList!=null){
            for (OrderedCart order : Main.orderedCartList){
                if (order.orderedItems.containsKey(food)){
                    order.status = "Denied";
                    System.out.println("Order status changed");
                }
            }
        }
    }

    public void View_Pending_Orders(){
        boolean c = false;
        for(OrderedCart order : Main.orderedCartList){
            if (!order.status.equals("Delivered") && !order.status.equals("Cancelled") && !order.status.equals("Denied")){

                System.out.println("Order Id: " + order.orderId);;
                System.out.println("Customer Name: " + order.customer.emailid);
                System.out.println("Items: " + order.orderedItems);
                System.out.println("\n");
                c = true;
            }
        }
        if (!c){
            System.out.println("No Pending Orders Left. ");
        }
    }

    void Update_Order_Status(){
        System.out.println("Enter Order Id Of The Order Status To Be Changed: ");
        String orderId = object.nextLine();
        if (Main.VIPOrderedCartList!=null){
            for (OrderedCart order : Main.VIPOrderedCartList){
                if (order.orderId.equals(orderId)){
                    System.out.println("Enter New Status: ");
                    String status = object.nextLine();
                    order.status = status;
                    System.out.println("Order Status Updated to " + order.status);
                }
            }
        }
        for (OrderedCart order : Main.orderedCartList){
            if (order.orderId.equals(orderId)){
                System.out.println("Enter New Status: ");
                String status = object.nextLine();
                order.status = status;
                System.out.println("Order Status Updated to " + order.status);
            }

        }
    }

    void View_Special_Requests(){
        if (Main.VIPOrderedCartList!=null){
            for (OrderedCart order : Main.VIPOrderedCartList){
                if (!order.specialRequest.equals("")){
                    System.out.println("Order Id: "+ order.orderId);
                    System.out.println("Customer Name: " + order.customer.emailid);
                    System.out.println("Order: " + order.orderedItems.toString());
                    System.out.println("Special Request: "+ order.specialRequest);
                }
            }
        }
        for (OrderedCart order : Main.orderedCartList){
            if (!order.specialRequest.equals("")){
                System.out.println("Order Id: "+ order.orderId);
                System.out.println("Customer Name: " + order.customer.emailid);
                System.out.println("Order: " + order.orderedItems.toString());
                System.out.println("Special Request: "+ order.specialRequest);
            }
        }
    }

    void Process_Refunds(){
        for (OrderedCart cancelledOrders : Main.cancelledOrdersList){
            System.out.println("Order Id of cancelled order: " + cancelledOrders.orderId);
            System.out.println("Customer Name: " + cancelledOrders.customer);
            System.out.println("Order: " + cancelledOrders.orderedItems.toString());
            System.out.println("Do You Wish To Process Refund of the Cancelled Order: Y/N");
            String yesorno = object.nextLine();
            if (yesorno.equals("Y")){
                System.out.println("Refund Initiated. ");
            }else{
                System.out.println("Refund Not Initiated. ");
            }
        }
    }
    void Total_Orders(){
        int n = Main.orderedCartList.size();
        int m = Main.VIPOrderedCartList.size();
        int l = m+n;
        System.out.println("Total Orders: " + l);
    }

    void Total_Sales(){
        int total = 0;
        for (OrderedCart order : Main.orderedCartList){
            total += order.totalPrice;
        }
        for (OrderedCart order : Main.VIPOrderedCartList){
            total += order.totalPrice;
        }
        System.out.println("Total Sales: " + total);
    }

    void Most_Popular_Item(){
        HashMap<Food, Integer> popularitem = new HashMap<>();
        for (OrderedCart order: Main.orderedCartList){
            for (Map.Entry<Food, Integer> entry: order.orderedItems.entrySet()){
                Food food = entry.getKey();
                int quantity = entry.getValue();
                if (popularitem.containsKey(food)){
                    int newQuantity = popularitem.get(food) + quantity;
                    popularitem.put(food, newQuantity);
                }else{
                    popularitem.put(food, quantity);
                }
            }
        }
        for (OrderedCart order: Main.VIPOrderedCartList){
            for (Map.Entry<Food, Integer> entry: order.orderedItems.entrySet()){
                Food food = entry.getKey();
                int quantity = entry.getValue();
                if (popularitem.containsKey(food)){
                    int newQuantity = popularitem.get(food) + quantity;
                    popularitem.put(food, newQuantity);
                }else{
                    popularitem.put(food, quantity);
                }
            }
        }
        Food popular = null;
        int max = 0;
        for (Map.Entry<Food, Integer> entry: popularitem.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
                popular = entry.getKey();
            }
        }
        if (popular != null){
            System.out.println("Most Popular Item: " + popular.itemname);
        }else{
            System.out.println("Nothing Has Been Ordered Yet.");
        }

    }
}

