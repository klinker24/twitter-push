# Twitter Push Notifications

![image](https://raw.githubusercontent.com/klinker24/twitter-push/master/header.png)

This project is for hosting your own Twitter push notification server. Tweets are streamed using Twitter's Streaming API and delivered to your device with Google Chrome's Push API.

## General Info and Project Structure

This project really comes in 2 different parts:

1. The Chrome GCM (Google Cloud Messaging) server that handles the notifications
2. The Twitter Stream listener that handles the Twitter side of things and getting your tweets

You need both parts working for the push notifications to work.

## Notes Before Starting

This project is not difficult to set up, you don't have to do any coding, and I step you through the advanced portions of the setup. It does take a bit of time though.

1. You NEED to use SSL (https) when you are hosting the webpage. This is a requirement of subscribing for the notifications. This is why I set up the app to run on OpenShift rather than my personal server. If you do not know what SSL is, then just use OpenShift as your host.
2. You can run the listener service (the one that actually streams the tweets) on any server you like. Simple execute ```$ ./listen``` on the terminal prompt after giving the file execute permissions.

## Editing the Code

I would not fork this repo and put it on your public GitHub. It will contain API and user keys for your accounts. The keys that I have on here are real keys, but for dummy applications.

There are 3 files that you will need to edit. They all have instructions in them for what needs to be edited. The hardest part is generating an API key from Twitter and this is trivial. This shouldn't take more than 10 mins and is the extent of your coding.

1. [config.php](https://github.com/klinker24/twitter-push/blob/master/web-app/notification.php)
2. [manifest.json](https://github.com/klinker24/twitter-push/blob/master/web-app/manifest.json)
3. [UserListener.java](https://github.com/klinker24/twitter-push/blob/master/twitter-client/UserListener.java)

## Hosting the GCM Server

Since there is a requirement that this server must use SSL, I was not able to set it up on my personal server. I opted for [OpenShift](https://www.openshift.com/) as my server of choice. It is free and hosts php projects easily. I will be walking through the setup with this server, but it is just a basic setup. If you have a host in mind, feel free to use whatever you want. The only requirement is SSL.

1. Create an account on [OpenShift](https://www.openshift.com/).
2. Log in and go to the [OpenShift Web Console](https://openshift.redhat.com/app/console/applications).
3. Select "Create your first application now"
4. Select PHP 5.4
5. Fill in the public URL with anything you choose. You need a application name and namespace.
6. Leave the rest of the settings as is, and select "Create Application". It will take some time.
7. Head to the "Settings" tab on the OpenShift Web Console.
8. You need to add your SSH keys. If you do not have them, then you can Google how to generate them.
9. Go back to "Applications" and open the one you just created.
10. On the right side, you will see "Source Code". Use the link below it to clone the repo onto your machine.
11. Copy and paste the code that you edited directly into the folder you just cloned. It will now have a ```twitter-client/``` and ```web-app/``` directories right in the root of the project.
12. ```$ git add --all```
13. ```$ git commit -m "initial commit" -a```
14. ```$ git push```
15. wait for it to finish, then open the https url for the project (Ex: https://push-klinkerapps.rhcloud.com/web-app).
16. OpenShift is now set up as your Chrome GCM Server.

## Hosting the GCM server on localhost

If you have your own server and would rather just run the GCM server on localhost, that is perfectly fine, you just need to make sure you know the static IP address for the server so that your phone can access the port hosting the server on your wifi. If you are going to run it on localhost, then I will assume you already know these types of things.

You might have to modify the path of some of the files on the php scripts so you do not get 404 errors.

You can start up the localhost server on port 8181 with:

```bash
$ ./run_local
```

## Running the Twitter Stream Listener

You can set this up on any server you want. Just make sure the process isn't going to get killed. You can even run it from your regular computer if you want.

Simply clone the project from OpenShift, give execution permission to the "listen" file, and then execute:

```bash
$ ./listen
```

You should see the following text come up on your command prompt if you input your credentials correctly into UserListner:
```
---- Twitter Push Server ----
[Tue Feb 02 15:39:45 CST 2016]Establishing connection.
[Tue Feb 02 15:39:46 CST 2016]Connection established.
[Tue Feb 02 15:39:46 CST 2016]Receiving status stream.
```

If you want to check whether or not it is working, simply go onto Twitter and mention yourself (or wait for someone else to like your tweet or something similar). You will a URL come up on the command prompt.

## Connecting to your phone

Now is the easy part. Simply navigate to your website, wherever you set it up at, on your android phone.

Example: https://push-klinkerapps.rhcloud.com/web-app

Now hit the "Subscribe" button. Done. Any notifications that show up in the command prompt logs on your Twitter Stream listener will now be forwarded to your android device.


Note: You will have to re-subscribe every time you push up changes to your server.

Tip: If you would rather get notifications on your computer instead, you should go to the webpage on your computer browser and hit subscribe there. This application doesn't handle multiple subscriptions at this time, so you will stop receiving notifications on your phone.
