public class UserListener extends TwitterPush {

    // These keys are obtained from Twitter.
    // Go to https://apps.twitter.com/ and create a new application.
    // Name it whatever you want, give it a description, and put the website to anything. 
    // These will never be seen and do not matter.
    // Agree to the terms of service, then create the app.
    // Open the app you just created and select the tab for "Keys and Access Tokens".
    // Go to the bottom and "Generage My Access Token", then fill in the fields:

    // Under "Application Settings", "Consumer Key (API Key)"
    public static final String TWITTER_CONSUMER_KEY = "e6nGcJahlY8cAKiyaT903p4Rc";
    // Under "Application Settings", "Consumer Secret (API Secret)"
    public static final String TWITTER_CONSUMER_SECRET = "XYCjRnKgd4Osxv2fZKv2x45lqKLiDSdoK08kX3pdT7Jc9UX2g8";
    // Under "Your Access Token", "Access Token"
    public static final String ACCESS_TOKEN = "4850548903-iZdZsSr0nlsxgCeCZpLbSHzB9g7iZ1b1zOx6u72";
    // Under "Your Access Token", "Access Token Secret"
    public static final String ACCESS_TOKEN_SECRET = "rWPIHnZx0yKOji1siXMgKPzAguBMFjmMzYv6hxlCtCz5i";


    // This is simply the URL of the web host. Remember that it NEEDS to use SSL (https) or it won't
    // let you subscribe to notifications.
    public static final String WEB_HOST = "https://push-klinkerapps.rhcloud.com";  // No "/" at the end

    // This is just your twitter screen name
    public static final String TWITTER_SCREEN_NAME = "test_for_talon";  // No "@" symbol

    // What do you want to be notified for (true or false):
    public static final boolean MENTION_NOTIFICATIONS = true;
    public static final boolean NEW_FOLLOWER_NOTIFICATIONS = true;
    public static final boolean QUOTE_NOTIFICATIONS = true;
    public static final boolean RETWEET_NOTIFICATIONS = true;
    public static final boolean FAVORITE_NOTIFICATIONS = true;

    public String getWebHost() {
        return WEB_HOST;
    }

    public String getScreenName() {
        return TWITTER_SCREEN_NAME;
    }

    public String getAuthenticationToken() {
        return ACCESS_TOKEN;
    }

    public String getAuthenticationSecretToken() {
        return ACCESS_TOKEN_SECRET;
    }

    public boolean mentionNotifications() {
        return MENTION_NOTIFICATIONS;
    }

    public boolean retweetNotifications() {
        return RETWEET_NOTIFICATIONS;
    }

    public boolean followerNotifications() {
        return NEW_FOLLOWER_NOTIFICATIONS;
    }

    public boolean favoriteNotifications() {
        return FAVORITE_NOTIFICATIONS;
    }

    public boolean quoteNotifications() {
        return QUOTE_NOTIFICATIONS;
    }
}