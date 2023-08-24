package Common.models;



import java.time.LocalDate;

public class Poll {

    private String user;
    //private Date date;

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private int year;
    private int month;
    private int day;

    private int option1Votes;
    private int option2Votes;
    private int option3Votes;
    private int option4Votes;
    private int totalVotes;

    public Poll(String user, String question, String option1, String option2, String option3, String option4) {

        this.user = user;

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;

        this.option1Votes = 0;
        this.option2Votes = 0;
        this.option3Votes = 0;
        this.option4Votes = 0;

        this.totalVotes = 0;



        //gets the system time
        LocalDate currentDate = LocalDate.now();

        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
        this.day = currentDate.getDayOfMonth();

    }

    public String getUsername(){
        return user;
    }

    public void incrementOption1Votes() {
        option1Votes++;
    }

    public void incrementOption2Votes() {
        option2Votes++;
    }

    public void incrementOption3Votes() {
        option3Votes++;
    }

    public void incrementOption4Votes() {
        option4Votes++;
    }

    public String getDateCreated(){
        return year + "/" + month + "/" + day;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getOption1Votes() {
        return option1Votes;
    }

    public int getOption2Votes() {
        return option2Votes;
    }

    public int getOption3Votes() {
        return option3Votes;
    }

    public int getOption4Votes() {
        return option4Votes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }
}
