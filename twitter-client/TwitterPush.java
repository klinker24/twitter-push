import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserMentionEntity;
import twitter4j.UserStreamListener;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public abstract class TwitterPush {

    public abstract String getWebHost();
    public abstract String getScreenName();
    public abstract String getAuthenticationToken();
    public abstract String getAuthenticationSecretToken();
    public abstract boolean mentionNotifications();
    public abstract boolean retweetNotifications();
    public abstract boolean followerNotifications();
    public abstract boolean favoriteNotifications();
    public abstract boolean quoteNotifications();

    public TwitterStream pushStream;

    public void start() {
        pushStream = getStreamingTwitter();

        pushStream.addListener(userStream);
        pushStream.user(new String[] { getScreenName() } );
    }

    public TwitterStream getStreamingTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(UserListener.TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(UserListener.TWITTER_CONSUMER_SECRET)
                .setOAuthAccessToken(getAuthenticationToken())
                .setOAuthAccessTokenSecret(getAuthenticationSecretToken());

        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        return tf.getInstance();
    }

    public UserStreamListener userStream = new UserStreamListener() {
        @Override
        public void onStatus(final Status status) {
            UserMentionEntity[] entities = status.getUserMentionEntities();

            ArrayList<String> names = new ArrayList<String>();
            for (UserMentionEntity e : entities) {
                names.add(e.getScreenName());
            }

            if (names.contains(getScreenName())) {
                if (!status.isRetweet() && mentionNotifications()) { // it is a normal mention
                    String title = "@" + status.getUser().getScreenName() + " mentioned you";
                    String message = status.getText();
                    String icon = status.getUser().getOriginalProfileImageURL();
                    String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();

                    try {
                        sendMessage(title, message, icon, url);
                    } catch (IOException e) { e.printStackTrace(); }
                } else { // it is a retweet
                    if (!status.getUser().getScreenName().equals(getScreenName()) &&
                            status.getRetweetedStatus().getUser().getScreenName().equals(getScreenName()) &&
                            retweetNotifications()) {
                        String title = "@" + status.getUser().getScreenName() + " retweeted you";
                        String message = status.getText();
                        String icon = status.getUser().getOriginalProfileImageURL();
                        String url = "https://twitter.com/" + status.getRetweetedStatus().getUser().getScreenName() + "/status/" + status.getId();

                        try {
                            sendMessage(title, message, icon, url);
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                }
            }
        }

        @Override
        public void onFavorite(User source, User target, Status favoritedStatus) {
            if(!source.getScreenName().equals(getScreenName()) &&
                    target.getScreenName().equals(getScreenName()) &&
                    favoriteNotifications()) {

                String title = "@" + source.getScreenName() + " liked your status";
                String message = favoritedStatus.getText();
                String icon = source.getOriginalProfileImageURL();
                String url = "https://twitter.com/" + target.getScreenName() + "/status/" + favoritedStatus.getId();

                try {
                    sendMessage(title, message, icon, url);
                } catch (IOException e) { e.printStackTrace(); }
            }
        }

        @Override
        public void onQuotedTweet(User source, User target, Status quotingTweet) {
            if(!source.getScreenName().equals(getScreenName()) &&
                    target.getScreenName().equals(getScreenName()) &&
                    quoteNotifications()) {
                String title = "@" + source.getScreenName() + " quoted your status";
                String message = quotingTweet.getText();
                String icon = source.getOriginalProfileImageURL();
                String url = "https://twitter.com/" + target.getScreenName() + "/status/" + quotingTweet.getId();

                try {
                    sendMessage(title, message, icon, url);
                } catch (IOException e) { e.printStackTrace(); }
            }
        }

        @Override
        public void onFollow(User source, User followedUser) {
            if (followedUser.getScreenName().equals(getScreenName()) &&
                    followerNotifications()) {
                String title = "@" + source.getScreenName();
                String message = "Followed you";
                String icon = source.getOriginalProfileImageURL();
                String url = source.getURL();

                try {
                    sendMessage(title, message, icon, url);
                } catch (IOException e) { e.printStackTrace(); }
            }
        }

        @Override
        public void onDirectMessage(DirectMessage directMessage) {
            // todo
        }

        @Override
        public void onUnfavorite(User source, User target, Status unfavoritedStatus) { }

        @Override
        public void onUnfollow(User user, User user2) { }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) { }

        @Override
        public void onDeletionNotice(long directMessageId, long userId) { }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) { }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) { }

        @Override
        public void onStallWarning(StallWarning warning) { }

        @Override
        public void onFriendList(long[] friendIds) { }

        @Override
        public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) { }

        @Override
        public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) { }

        @Override
        public void onUserListSubscription(User subscriber, User listOwner, UserList list) { }

        @Override
        public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) { }

        @Override
        public void onUserListCreation(User listOwner, UserList list) { }

        @Override
        public void onUserListUpdate(User listOwner, UserList list) { }

        @Override
        public void onUserListDeletion(User listOwner, UserList list) { }

        @Override
        public void onUserProfileUpdate(User updatedUser) { }

        @Override
        public void onUserSuspension(long suspendedUser) { }

        @Override
        public void onUserDeletion(long deletedUser) { }

        @Override
        public void onBlock(User source, User blockedUser) { }

        @Override
        public void onUnblock(User source, User unblockedUser) { }

        @Override
        public void onRetweetedRetweet(User source, User target, Status retweetedStatus) { }

        @Override
        public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) { }

        @Override
        public void onException(Exception ex) { }
    };

    public void sendMessage(String title, String message, String icon, String url) throws IOException {
        String arguements = "title=" + title + "&message=" + message + "&icon=" + icon + "&url=" + url;
        String post = getWebHost() + "/web-app/notification.php?" + java.net.URLEncoder.encode(arguements, "UTF-8");
        URL obj = new URL(post);

        System.out.println("POST to: " + post);

        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write("");
        wr.close();

        System.out.println("Post complete, code: " + con.getResponseCode() + " " + con.getResponseMessage());
    }
}
