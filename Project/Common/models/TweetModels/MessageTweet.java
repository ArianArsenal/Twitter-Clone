package Common.models.TweetModels;

public class MessageTweet extends Tweet {
    private int tweetid; //Generated by hashcode

    public MessageTweet(String text) {
        super(text);
        this.tweetid = super.hashCode();
    }

    public int getTweetid() {
        return tweetid;
    }

    public void setTweetid(int tweetid) {
        this.tweetid = tweetid;
    }
    
    
    
}