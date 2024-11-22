import java.util.*;
public class Food {
    String itemname;
    int price;
    String availabilty;
    String category;
    private List<Review> reviewsList;

    Food(String itemname, int price, String availability, String category){
        this.itemname = itemname;
        this.price = price;
        this.availabilty = availability;
        this.category = category;
        this.reviewsList = new ArrayList<>();
    }

    public void setAvailabilty(String availabilty) {
        this.availabilty = availabilty;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    void addReview(Review review){
        reviewsList.add(review);
    }

    public List<Review> getReviewsList() {
        return reviewsList;
    }
    @Override
    public String toString(){
        return "Item Name: " + itemname + "\n"
                + "Price " + price + "\n"
                + "Availability: " + availabilty + "\n"
                + "Category: " + category;
    }
}
