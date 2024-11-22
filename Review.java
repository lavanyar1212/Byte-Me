public class Review {
    String emailid;
    String review;


    Review(String emailid,String review){
        this.review = review;
        this.emailid = emailid;
    }

    @Override
    public String toString() {
        return emailid + ": "+ review;
    }

}
