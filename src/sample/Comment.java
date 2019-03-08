package sample;

public class Comment {
    Profile profile;
    String comment;
    Restaurant restaurant;

    public Comment( String comment, Profile profile, Restaurant restaurant){
        this.profile = profile;
        this.comment = comment;
        this.restaurant = restaurant;
    }

    public Comment(String comment, Restaurant restaurant) {
        this.comment = comment;
        this.restaurant = restaurant;
        this.profile = null;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public Restaurant getRestaurant() { return this.restaurant; }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
