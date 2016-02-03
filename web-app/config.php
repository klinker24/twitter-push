<?php

// This is your API Key from the Google Developer Console.
// Go to "Enable and manage APIs", then, in the drawer on the left, there is a credentials option
// Hit the "New Credentials" button and select "API Key", then select "Browser Key" from the new window
// Don't change any of the settings, and just hit create, then copy the key to this line.

// Also, remember that under the "Enable and Manage APIs" section, you enable "Google Cloud Messaging".

$_google_api_key = "AIzaSyB46bQF9oL9QdOb48LWvbrBfvToik_tyRw"; 


// This is simply the URL of the web host. Remember that it NEEDS to use SSL (https) or it won't
// let you subscribe to notifications.

$_web_host = "https://push-klinkerapps.rhcloud.com"; // (without the "/" at the end)

// This is a silly way to do passwords. I will leave the real implementation up to you.
// This is the password you type in when you are going to hit "subscribe".
// If it is incorrect, the subscription ID will not be saved.

// If you do not authenticate, anyone who knows the URL will be able to grab your notifications. 
// since only one device can receive them at a time, the "hacker's" will be able to override your notifications.
$_password = "password1234";
?>