package Common.Menus;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import Common.connection;
import Common.tools;
import Common.models.Poll;
import Common.models.TimeLine;
import Common.models.Tweet;
import Common.models.User;
import Server.Logs.logs;

public class timelineMenus {
    
    //server side
    public static void ServerTimeLine(OutputStream out , InputStream in,ObjectInputStream objectIn,ObjectOutputStream objectOut,User user) throws IOException{


        TimeLine timeLine = new TimeLine(user.getUsername());
        timeLine.retrieveFollowings(user.getUsername());

        ArrayList<Tweet> tweets = timeLine.getFollowTweets();

        //sends the arraylist to client
        //connection.ServerSendObject(objectOut, tweets);


    
        // for (Tweet tost : tweets) {

        //     String text = tost.getText();
        //     int likeCount = tost.getLikeCount();
        //     int retweetCount = tost.getReplyCount();
        //     int replyCount = tost.getReplyCount();
        //     int quoteCount = tost.getQuoteCount();
        //     int tweetId = tost.hashCode();
        //     boolean isFavStar = tost.getIsFavStar();
        //     String username = tost.getUsername();
        //     String firstname = tost.getFirstname();
        //     String tweetDate = tost.getTweetDate();
        //     String tweetTime = tost.getTweetTime();
        //     String tweetImage = tost.getTweetImageString();
        //     String profilePic = tost.getProfilePicString();

        //     String datas = text + "$" + likeCount + "$" + retweetCount + "$" + replyCount + "$" + quoteCount + "$" + tweetId + "$" + isFavStar + "$" + username + "$" + firstname + "$" +tweetDate + "$" + tweetTime + "$" + tweetImage + "$" + profilePic + "*E" ;

        //     connection.ServerSend(out, datas);

        // }

        //objectOut.writeObject(tweets);

        

        // TODO should show the profile pic
        // TODO should show the tweets related to this person
        // TODO Add his own tweets to timeline as well

        
        while(true){


            //send tweets based on the timeline controller

            String option = connection.ServerRecieve(in);
            //test
            System.out.println(option + "Selected");

            switch (option) {

                case "Home":
                    
                
                    break;

                case "Tweet" : //goes into TweetController

                    String datas4 = connection.ServerRecieve(in);

                    String[] tokens = datas4.split("$");

                    String text = tokens[0];
                    String imagepath = tokens[1];

                    if(imagepath.isEmpty()){
                        imagepath = null;
                    }

                    //create tweet 
                    Tweet newTweet = new Tweet(text, user.getUsername() , user.getFirstname() , imagepath , user.getProfilePicString());

                    tools.InsertTweet(newTweet);

                    logs.TweetLog(user.getUsername(), text , imagepath);

                    //goes to Timeline again
                    break;

                

                case "Explore":

                    //todo 
                    //! client side not handled yet 

                    String key = connection.ServerRecieve(in);

                    ArrayList<User> temp = tools.searchUser(key);

                    connection.ServerSendObject(objectOut,temp);



                    break;
                case "Direct":
                    
                    break;
                case "Profile":
                    
                    break;
                case "Poll":
                    
                    String datas = connection.ServerRecieve(in);

                    if(datas.equals("return")){
                        continue;
                    }

                    String[] token = datas.split(",");

                    String question = token[0];
                    String option1 = token[1];
                    String option2 = token[2];
                    String option3 = token[3];
                    String option4 = token[4];

                    //should create a new poll object and send it to the database
                    Poll poll = new Poll(user.getUsername(), question, option1, option2, option3, option4);
                    
                    tools.InsertPoll(poll);

                    //add logs
                    logs.PollLog(user.getUsername());

                    System.out.println("Poll Created Successfully");

                    break;
                case "Exit":

                    return;
                default:
                    System.out.println("Unkowns Option - ERROR 404");
                    break;
            }

        }        

    }

    public static void ProfileMenuServer(OutputStream out , InputStream in) throws IOException{
        

       

        String profileoption = connection.ServerRecieve(in);

        switch (profileoption) {

            case "1":
                
                break;
            case "2":

                break;

            case "3":

                break;
            case "4":

                return;

            default:
                break;
        }

    }

    public static void HomeMenuServer(OutputStream out , InputStream in, String username){

        //!TEST
        //add new tweet 
        

        //makes a timeline object 
        // TimeLine userTimeLine = new TimeLine(username);

        // userTimeLine.retrieveFollowings(username);

        // ArrayList<Tweet> tweets = userTimeLine.getFollowTweets();
        
        // for (Tweet tweet : tweets) {

        //     // Process each tweet here
        //     System.out.println("=====" + tweet.getUsername() + "=====");
        //     System.out.println(tweet.getText());
        //     System.out.println("Likes: " + tweet.getLikeCount());
        //     System.out.println("Retweets: " + tweet.getRetweetCount());
        //     System.out.println("Comments: " + tweet.getReplyCount());
            
        //     //should now calculate the time passed from the tweet

        //     String tweetTime = tools.CalculateTimePassed(tweet.getTweetTime(),tweet.getTweetDate());



        //     System.out.println("============" + tweetTime + "========================");
        // }

        
        

    }

}

