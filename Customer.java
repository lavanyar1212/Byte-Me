import java.util.*;

public class Customer extends User{
    private Cart cart;
    boolean VIP = false;

    Scanner object = new Scanner(System.in);
    Customer (String emailid, String password, boolean VIP) {
        super(emailid, password);
        cart = new Cart();
        this.VIP = VIP;

    }

    void VIP_Becomer(){
        if (!VIP){
            this.VIP = true;
            System.out.println("You Are Now A VIP Customer!!");
        }
    }


    void View_All_Food_Items(){
        for (int i = 0; i < Main.foodlist.size(); i++){
            Food food = Main.foodlist.get(i);
            System.out.println((i + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty);
        }
    }

    void Search_Food_Item_By_Name(String fooditemname){
        int c = 0;
        for (int i = 0; i < Main.foodlist.size(); i++){
            Food food = Main.foodlist.get(i);
            if (food.itemname.equals(fooditemname)){
                System.out.println((i + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty + "\n Category: " + food.category);
                c = 1;
            }
        }
        if (c==0){
            System.out.println("Item Not Present.");
        }
    }

    void Search_Food_Item_By_Category(String category){
        int c = 0;
        for (int i = 0; i < Main.foodlist.size(); i++){
            Food food = Main.foodlist.get(i);
            if (food.category.equals(category)){
                System.out.println((i + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty);
                c = 1;
            }
        }
        if (c == 0){
            System.out.println("Incorrect Category. ");
        }
    }


    void Sort_Food_Item_By_Price_Ascending(){
        int i = Main.foodlist.size();
        ArrayList<Food> ascendingfoodlist = new ArrayList<>(Main.foodlist);
        int swap;
        for (int j = 0; j < i -1 ; j++){
            swap = 0;
            for (int k = 0; k < i -1 - j; k++){
                if (ascendingfoodlist.get(k).price > ascendingfoodlist.get(k+1).price){
                    Food temp = ascendingfoodlist.get(k);
                    ascendingfoodlist.set(k,ascendingfoodlist.get(k+1));
                    ascendingfoodlist.set(k+1, temp);
                    swap = 1;
                }
            }
            if (swap == 0){
                break;
            }
        }
        for (int l = 0; l < ascendingfoodlist.size(); l++){
            Food food = ascendingfoodlist.get(l);
            System.out.println((l + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty + "\n Category: " + food.category);

        }

    }

    void Sort_Food_Item_By_Price_Descending(){
        int i = Main.foodlist.size();
        ArrayList<Food> ascendingfoodlist = new ArrayList<>(Main.foodlist);
        int swap;
        for (int j = 0; j < i -1 ; j++){
            swap = 0;
            for (int k = 0; k < i -1 - j; k++){
                if (ascendingfoodlist.get(k).price < ascendingfoodlist.get(k+1).price){
                    Food temp = ascendingfoodlist.get(k);
                    ascendingfoodlist.set(k,ascendingfoodlist.get(k+1));
                    ascendingfoodlist.set(k+1, temp);
                    swap = 1;
                }
            }
            if (swap == 0){
                break;
            }
        }
        for (int l = 0; l < ascendingfoodlist.size(); l++){
            Food food = ascendingfoodlist.get(l);
            System.out.println((l + 1) + ". \n Item Name: " + food.itemname + "\n Price: " + food.price + "\n Availability: "+food.availabilty + "\n Category: " + food.category);

        }

    }

    void View_Cart(){
        cart.View_Cart();
    }

    void Add_To_Cart(Food food, int quantity){
        cart.Add_Item_To_Cart(food, quantity);
    }

    void Remove_From_Cart(Food food){
        cart.Remove_Item_From_Cart(food);
    }

    void Modify_Quantity_Cart(Food food, int quantity){
        cart.Modify_Quantity_Of_Cart_Item(food, quantity);
    }

    int View_Total(){
        int total_amount = cart.Cart_Total();
        return total_amount;
    }

    void Checkout(){
        cart.Checkout_And_Payment(this);
    }

    void View_Order_Status(){
        if (this.VIP){
            for (OrderedCart specificOrder : Main.VIPOrderedCartList){
                if (specificOrder.customer.equals(this)){
                    System.out.println("Your Order Status: " + specificOrder.status);
                    System.out.println("Your Order Details: ");
                    System.out.println(specificOrder.orderedItems);
                }
            }
        }
        else{
            for (OrderedCart specificOrder : Main.orderedCartList){
                if (specificOrder.customer.equals(this)){
                    System.out.println("Your Order Status: " + specificOrder.status);
                    System.out.println("Your Order Details: ");
                    System.out.println(specificOrder.orderedItems);
                }
            }
        }
    }

    void Cancel_Order(){
        System.out.println("Enter Order ID of the Order to be cancelled: ");
        String orderidcancel = object.nextLine();
        if (this.VIP){
            for (OrderedCart specificOrder : Main.VIPOrderedCartList){
                if (specificOrder.customer.equals(this) && specificOrder.status.equals("Order Received.")){
                    if (specificOrder.orderId.equals(orderidcancel)) {
                        specificOrder.status = "Cancelled";
                        System.out.println("Order Cancelled. ");
                        Main.cancelledOrdersList.add(specificOrder);
                    }
                }
            }
            Main.VIPOrderedCartList.remove(Main.cancelledOrdersList);
        }else{
            for (OrderedCart specificOrder : Main.orderedCartList){
                if (specificOrder.customer.equals(this) && specificOrder.status.equals("Order Received.")){
                    if (specificOrder.orderId.equals(orderidcancel)) {
                        specificOrder.status = "Cancelled";
                        System.out.println("Order Cancelled. ");
                        Main.cancelledOrdersList.add(specificOrder);
                    }
                }
            }
            Main.orderedCartList.remove(Main.cancelledOrdersList);
        }

    }

    void View_Order_History(){
        if (this.VIP){
            for (OrderedCart specificOrder: Main.VIPOrderedCartList){
                System.out.println("Your Order History: ");
                if (specificOrder.customer.equals(this) && specificOrder.status.equals("Delivered")){
                    System.out.println("Order Id: " + specificOrder.orderId);
                    System.out.println("Order Items: " + specificOrder.orderedItems.toString());
                    System.out.println("Would You Like To Reorder: Y/N");
                    String yesorno = object.nextLine();
                    if (yesorno.equals("Y")){
                        System.out.println("Enter Order Id of the Item You Would Like To Reorder: ");
                        String orderid = object.nextLine();
                        Main.VIPOrderedCartList.add(specificOrder);
                    }
                }
            }
        }else{
            for (OrderedCart specificOrder: Main.orderedCartList){
                System.out.println("Your Order History: ");
                if (specificOrder.customer.equals(this) && specificOrder.status.equals("Delivered")){
                    System.out.println("Order Id: " + specificOrder.orderId);
                    System.out.println("Order Items: " + specificOrder.orderedItems.toString());
                    System.out.println("Would You Like To Reorder: Y/N");
                    String yesorno = object.nextLine();
                    if (yesorno.equals("Y")){
                        System.out.println("Enter Order Id of the Item You Would Like To Reorder: ");
                        String orderid = object.nextLine();
                        Main.orderedCartList.add(specificOrder);
                    }
                }
            }
        }

    }

    void Add_Review(Food food, Customer customer, String given_review){
        if (food!=null){
            Review review = new Review(this.emailid, given_review);
            food.addReview(review);
            System.out.println("Review Given for "+ food.itemname + " by " + this.emailid);
        }

    }

    void View_All_Reviews(Food food){
        if (food!=null){
            List<Review> reviewList = food.getReviewsList();
            if (reviewList!=null){
                System.out.println("Reviews for "+ food.itemname);
                for (Review review : reviewList){
                    System.out.println(review);
                }
            }
            else{
                System.out.println("No reviews for "+ food);
            }
        }
    }




}
