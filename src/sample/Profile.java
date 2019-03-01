package sample;

public class Profile {
    String username, password;
    String firstName, lastName, email;

    public Profile(String username, String password, String firstName, String lastName, String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return  this.email;
    }

    public boolean isNull(){
        if(this.username == null || this.password == null || this.firstName == null || this.lastName == null || this.email == null) return false;
        return true;
    }
}
