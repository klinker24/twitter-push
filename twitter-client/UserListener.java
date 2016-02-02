public class UserListener extends TwitterPush {

    public static final String TWITTER_CONSUMER_KEY = "e6nGcJahlY8cAKiyaT903p4Rc";
    public static final String TWITTER_CONSUMER_SECRET = "XYCjRnKgd4Osxv2fZKv2x45lqKLiDSdoK08kX3pdT7Jc9UX2g8";
    public static final String AUTH_TOKEN = "4850548903-iZdZsSr0nlsxgCeCZpLbSHzB9g7iZ1b1zOx6u72";
    public static final String SECRET_TOKEN = "rWPIHnZx0yKOji1siXMgKPzAguBMFjmMzYv6hxlCtCz5i";

    public static final String WEB_HOST = "https://push-klinkerapps.rhcloud.com";       // No "/" at the end
    public static final String TWITTER_SCREEN_NAME = "test_for_talon";                  // No "@" symbol

    // What do you want to be notified for:
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
        return AUTH_TOKEN;
    }

    public String getAuthenticationSecretToken() {
        return SECRET_TOKEN;
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