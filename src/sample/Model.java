package sample;

public class Model {

    public Model(){
    }

    public String compute(String button){
        String answer="";
        switch(button) {
            case "history": answer = "Accessing to your history.."; break;
            case "favorites": answer = "Accessing to your favorites..";break;
            case "myRatings": answer = "Accessing to your ratings..";break;
            case "addLocation": answer ="You will be redirected to the new location system..";break;
            case "logOut": answer = "You are getting disconnected..";break;
        }
        return answer;
    }
}
