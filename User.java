import java.time.LocalDate;

public class User {

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

    //Constructor
    public User(String firstname, String lastname, String username, String password, String email, String phonenumber, String country, int year, int month, int day) {

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


    //other Getters to be written
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    //other Setters to be written

    
    // unecessary fields will be removed from ToString later
    @Override
    public String toString() {
        return "User [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
                + password + ", email=" + email + ", phonenumber=" + phonenumber + ", country=" + country + ", year="
                + year + ", month=" + month + ", day=" + day + ", joinedyear=" + joinedyear + ", joinedmonth="
                + joinedmonth + ", joinedday=" + joinedday + "]";
    }

    

}
