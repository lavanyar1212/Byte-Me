import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Food> foodlist = new ArrayList<>();
    static List<OrderedCart> orderedCartList = new ArrayList<>();
    static List<OrderedCart> VIPOrderedCartList = new ArrayList<>();
    static List<Customer> customersList = new ArrayList<>();
    static List<OrderedCart> cancelledOrdersList = new ArrayList<>();

    public static void main(String[] args) {
        Admin Lavanya = new Admin("lavanya", "123a");

        Customer Archana = new Customer("archana", "123a", false);
        Customer Tanmay = new Customer("tanmay", "123a", false);
        Customer Jagdish = new Customer("jagdish", "123a",false);

        customersList.add(Archana);
        customersList.add(Tanmay);
        customersList.add(Jagdish);

        Food Pasta = new Food("Pasta", 12, "Available", "Italian");
        Food Pizza = new Food("Pizza", 125, "Available", "Italian");

        Food Noodles = new Food("Noodles", 100, "Available", "Chinese");
        Food Manchurian = new Food("Manchurian", 56, "Available", "Chinese");

        Food Lemonade = new Food("Lemonade", 40, "Available", "Beverage");
        Food Icetea = new Food("Icetea", 60, "Available", "Beverage");

        foodlist.add(Pasta);
        foodlist.add(Pizza);
        foodlist.add(Noodles);
        foodlist.add(Manchurian);
        foodlist.add(Lemonade);
        foodlist.add(Icetea);

        System.out.println("Welcome to Byte Me!");
        String usertype;
        Scanner object = new Scanner(System.in);

        do {
            System.out.println("Login as: ");
            System.out.println("1. Customer. ");
            System.out.println("2. Admin. ");
            System.out.println("3. Exit Application. ");
            System.out.print("Your Choice: ");
            usertype = object.nextLine();

            if (usertype.equals("1")) {
                System.out.print("Enter Email Id: ");
                String emailid = object.nextLine();
                System.out.print("Enter Password: ");
                String password = object.nextLine();
                try {
                    Customer customer = customeremail(customersList,emailid,password);
                    String choice1;
                    do {
                        System.out.println("1. Browse Menu. ");
                        System.out.println("2. Cart Operations. ");
                        System.out.println("3. Order Tracking. ");
                        System.out.println("4. Item Reviews. ");
                        System.out.println("5. Become a VIP customer.");
                        System.out.println("6. Logout. ");
                        choice1 = object.nextLine();
                        switch (choice1) {
                            case "1" -> {
                                String customer_1;
                                do {
                                    System.out.println("1. View All Items.");
                                    System.out.println("2. Search By Name.");
                                    System.out.println("3. Filter By Category.");
                                    System.out.println("4. Sort By Price. ");
                                    System.out.println("5. Go Back.");
                                    System.out.println("Your Choice.");
                                    customer_1 = object.nextLine();
                                    switch (customer_1) {
                                        case "1" -> Archana.View_All_Food_Items();
                                        case "2" -> {
                                            System.out.println("Enter Name of the Food Item: ");
                                            String fooditemname = object.nextLine();
                                            customer.Search_Food_Item_By_Name(fooditemname);
                                        }
                                        case "3" -> {
                                            System.out.println("Enter Category of the Food Item: ");
                                            String category = object.nextLine();
                                            customer.Search_Food_Item_By_Category(category);
                                        }
                                        case "4" -> {
                                            System.out.println("1. Sort In Ascending Order. ");
                                            System.out.println("2. Sort In Descending Order. ");
                                            String customer_1_4 = object.nextLine();
                                            if (customer_1_4.equals("1")) {
                                                customer.Sort_Food_Item_By_Price_Ascending();
                                            } else if (customer_1_4.equals("2")) {
                                                customer.Sort_Food_Item_By_Price_Descending();
                                            }
                                        }
                                    }
                                } while (!customer_1.equals("5"));
                            }


                            case "2" -> {
                                String choice2;
                                do {
                                    System.out.println("1. Add Items To Cart.");
                                    System.out.println("2. Modify Quantities Of Items In The Cart.");
                                    System.out.println("3. Remove Items From The Cart.");
                                    System.out.println("4. View Cart.");
                                    System.out.println("5. View Total.");
                                    System.out.println("6. Payment And Checkout. ");
                                    System.out.println("7. Go Back.");
                                    System.out.println("Your Choice: ");
                                    choice2 = object.nextLine();
                                    switch (choice2) {
                                        case "1" -> {
                                            System.out.println("Enter Name Of The Food Item: ");
                                            String fooditemname = object.nextLine();
                                            Food name = menu_Item_Name_Finder(fooditemname);
                                            System.out.println("Enter Quantity Of The Food Item: ");
                                            int quantity = object.nextInt();
                                            object.nextLine();
                                            customer.Add_To_Cart(name, quantity);
                                        }
                                        case "2" -> {
                                            System.out.println("Enter Name Of The Food Item: ");
                                            String fooditemname = object.nextLine();
                                            Food name = menu_Item_Name_Finder(fooditemname);
                                            System.out.println("Enter Quantity Of The Food Item: ");
                                            int quantity = object.nextInt();
                                            object.nextLine();
                                            customer.Modify_Quantity_Cart(name, quantity);
                                        }
                                        case "3" -> {
                                            System.out.println("Enter Name Of The Food Item To Be Removed: ");
                                            String fooditemname = object.nextLine();
                                            Food name = menu_Item_Name_Finder(fooditemname);
                                            customer.Remove_From_Cart(name);
                                        }
                                        case "4" -> {
                                            customer.View_Cart();
                                        }
                                        case "5" -> {
                                            int total_amount = customer.View_Total();
                                            System.out.println("Your Total Amount: " + total_amount);
                                        }
                                        case "6" -> {
                                            customer.Checkout();
                                        }
                                    }

                                } while (!choice2.equals("7"));
                            }
                            case "3" -> {
                                System.out.println("1. View Order Status. ");
                                System.out.println("2. Cancel Order. ");
                                System.out.println("3. Order History. ");
                                System.out.println("4. Go Back. ");
                                System.out.println("Your Choice: ");
                                String choice3 = object.nextLine();
                                switch (choice3) {
                                    case "1" -> {
                                        customer.View_Order_Status();
                                    }
                                    case "2" -> {
                                        customer.Cancel_Order();
                                    }
                                    case "3" -> {
                                        customer.View_Order_History();
                                    }
                                }
                            }
                            case "4" ->{
                                String choice4;
                                do {
                                    System.out.println("1. Give Review.");
                                    System.out.println("2. View Reviews.");
                                    System.out.println("3. Go Back. ");
                                    System.out.println("Your Choice: ");
                                    choice4 = object.nextLine();

                                        switch (choice4) {
                                            case "1" -> {
                                                System.out.println("Enter Name Of The Item: ");
                                                String itemname = object.nextLine();
                                                Food name = menu_Item_Name_Finder(itemname);
                                                System.out.println("Give Review: ");
                                                String review = object.nextLine();
                                                customer.Add_Review(name, customer, review);
                                            }
                                            case "2" -> {
                                                System.out.println("Enter Name of The Item: ");
                                                String itemname = object.nextLine();
                                                Food name = menu_Item_Name_Finder(itemname);
                                                customer.View_All_Reviews(name);
                                            }
                                        }
                                }while (!choice4.equals("3"));
                            }
                            case "5" -> {
                                System.out.println("To Become A VIP Customer, You Have To Pay Rs. 500.");
                                System.out.println("Enter Your Card Details Here: ");
                                String card = object.nextLine();
                                customer.VIP_Becomer();
                            }
                        }
                    } while (!choice1.equals("6"));
                }catch (InvalidLoginCredentials e){
                    System.out.println(e.getMessage());
                }

            } else if (usertype.equals("2")) {
                System.out.print("Enter Email Id: ");
                String emailidadmin = object.nextLine();
                System.out.print("Enter Password: ");
                String passwordadmin = object.nextLine();
                String choice;
                do {
                    System.out.println("1. Menu Management. ");
                    System.out.println("2. Order Management. ");
                    System.out.println("3. Report Generation. ");
                    System.out.println("4. Logout. ");
                    System.out.print("Your Choice: ");
                    choice = object.nextLine();

                    switch (choice) {
                        case "1" -> {
                            System.out.println("1. View Menu. ");
                            System.out.println("2. Add New Items.");
                            System.out.println("3. Update Existing Items.");
                            System.out.println("4. Remove Items. ");
                            System.out.print("Your Choice: ");
                            String choice1 = object.nextLine();

                            switch (choice1) {
                                case "1" -> Lavanya.View_All_Food_Items();
                                case "2" -> {
                                    System.out.print("Enter Name of The Item to be Added: ");
                                    String nameOfNewItem = object.nextLine();
                                    System.out.print("Enter Price of The Item to be Added: ");
                                    int price = Integer.parseInt(object.nextLine());
                                    System.out.println("Enter Category of The Item to be Added: ");
                                    String newcategory = object.nextLine();
                                    Food newItem = new Food(nameOfNewItem, price, "Available", newcategory);
                                    Lavanya.Add_Food_Item(newItem);
                                }
                                case "3" -> {
                                    Lavanya.View_All_Food_Items();
                                    System.out.println("Enter Name of the Item to be Updated from the Above List: ");
                                    String nameofitem = object.nextLine();
                                    System.out.println("Enter New Price of Item: ");
                                    int newprice = Integer.parseInt(object.nextLine());
                                    System.out.println("Enter Availability of the Item: ");
                                    String newavailability = object.nextLine();
                                    Food itemToBeUpdated = menu_Item_Name_Finder(nameofitem);
                                    Lavanya.Update_Item_Details(itemToBeUpdated, newprice, newavailability);

                                }
                                case "4" ->{
                                    Lavanya.View_All_Food_Items();
                                    System.out.println("Enter Name of the Item to be Removed: ");
                                    String nameofremoveditem = object.nextLine();
                                    Food itemToBeRemoved = menu_Item_Name_Finder(nameofremoveditem);
                                    Lavanya.Remove_Items(itemToBeRemoved);

                                }
                            }
                        }
                        case "2" ->{
                            System.out.println("1. View Pending Orders. ");
                            System.out.println("2. Update Order Status. ");
                            System.out.println("3. Process Refunds. ");
                            System.out.println("4. Handle Special Requests. ");
                            System.out.println("5. Go Back. ");
                            System.out.print("Your Choice: ");
                            String choice2 = object.nextLine();

                            while(true){
                                if(choice2.equals("1")){
                                    Lavanya.View_Pending_Orders();
                                    break;
                                }else if(choice2.equals("2")){
                                    Lavanya.Update_Order_Status();
                                    break;
                                }
                                else if(choice2.equals("3")){
                                    Lavanya.Process_Refunds();
                                    break;
                                }
                                else if(choice2.equals("4")){
                                    Lavanya.View_Special_Requests();
                                    break;
                                }
                                else if(choice2.equals("5")){
                                    break;
                                }
                            }

                        }
                        case "3" -> {
                            System.out.println("1. View Total Sales. ");
                            System.out.println("2. See Most Popular Items. ");
                            System.out.println("3. Total Orders. ");
                            System.out.print("Your Choice: ");
                            String choice3 = object.nextLine();
                            switch (choice3) {
                                case "1" -> {
                                    Lavanya.Total_Sales();
                                }
                                case "2" -> {
                                    Lavanya.Most_Popular_Item();
                                }
                                case "3" -> {
                                    Lavanya.Total_Orders();
                                }
                            }
                        }

//
                    }
                } while (!choice.equals("4"));

            } else if (!usertype.equals("3")) {
                System.out.println("Invalid Choice. Try Again.");
            }
        } while (!usertype.equals("3"));

        System.out.println("Exiting the Application.");
        object.close();
    }
    private static Food menu_Item_Name_Finder(String name) {
        for (Food food : foodlist) {
            if (food.itemname.equalsIgnoreCase(name)) {
                return food;
            }
        }
        return null;
    }
    private static Customer customeremail(List<Customer> customersList, String email, String password) {
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer_ = customersList.get(i);
            if (customer_.emailid.equals(email) && customer_.password.equals(password)) {
                return customer_;
            }
        }
        throw new InvalidLoginCredentials("Incorrect Credentials.");
    }
}
