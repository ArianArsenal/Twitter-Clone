package Common.models;


import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;

import javafx.scene.image.Image;

public class User implements Serializable{

    private String firstname;
    private String lastname;
    private String username; //unique 
    private String password; //check conditions

    private String email; //unique //check Regex
    private String phonenumber; //unique
    private String country; 

    //Birthdates 
    private int year;
    private int month;
    private int day;

    //Date Joined
    //gets the date from system
    private int joinedyear;
    private int joinedmonth;
    private int joinedday;

    private String profileImage;

    //Constructor
    public User(String firstname, String lastname, String username, String password, String email, String phonenumber, String country, int year, int month, int day, String profileImage) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;   //is checked
        this.email = email;  //is checked 
        this.phonenumber = phonenumber;
        this.country = country;

        this.year = year;
        this.month = month;
        this.day = day;

        //gets the system time
        LocalDate currentDate = LocalDate.now();

        this.joinedyear = currentDate.getYear();
        this.joinedmonth = currentDate.getMonthValue();
        this.joinedday = currentDate.getDayOfMonth();

        //! ???????
        //todo
        if (profileImage == null){
            //Image image = new Image(Path.of("D:\\Programs\\FirstScene\\src\\assests\\5.png").toUri().toString());
            //this.profileImage = image.toString();

            this.profileImage = "D:\\Programs\\FirstScene\\src\\assests\\5.png";
        }
        else {
            this.profileImage = profileImage;
        }
        
    }

    //Constructor for searching users
    public User(String firstname , String username , String profileImage){
        this.firstname = firstname;
        this.username = username;
        this.profileImage = profileImage;

        if (profileImage == null){
            this.profileImage = "D:\\Programs\\FirstScene\\src\\assests\\5.png";
        }
        else {
            this.profileImage = profileImage;
        }

    }

    //Necessary Getters
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getCountry() {
        return country;
    }

    public int getJoinedyear() {
        return joinedyear;
    }
    public int getJoinedmonth() {
        return joinedmonth;
    }
    public int getJoinedday() {
        return joinedday;
    }
    public String getDatejoined(){
        return joinedyear + "/" + joinedmonth + "/" + joinedday;
    }

    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public String getBirthdate(){
        return year + "/" + month + "/" + day;
    }


    public Image getProfilePic() {

        Image pic = new Image(Path.of(this.profileImage).toUri().toString());

        return pic;
    }

    public String getProfilePicString() {
        return profileImage;
    }

    


    


    
    

    
    

    
    

}
