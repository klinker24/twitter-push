<?php
$_authorization = "AIzaSyB46bQF9oL9QdOb48LWvbrBfvToik_tyRw";  // from the google developer console (API Key)
$_subscriptionId = "f6d7HRBpTmI:APA91bEdf7pTwHMqJZGxgulpbmo0gBFEOZvkoUK1ReuWGgfsMEVio2s_NZcLrhtTN-jVHxdjO_RirnGba7nzn31mpqmf2u8Sdm_r9jEZ0oXDqMkhoqzYfwmYU7Lp5lBGDZ93KSx0Rn6r";


// Do not edit below this line.
// ----------------------------
$myfile = fopen("notification_data.json", "w") or die("Unable to open file!");
$txt = '{ "data":{"title":"' . $_GET['title'] . '", "message":"' . $_GET['message'] . '", "icon":" '. $_GET['icon'] . '", "url":"'. $_GET['url'] .'"} }';
fwrite($myfile, $txt);
fclose($myfile);

$cmd = 'curl --header "Authorization: key=' . $_authorization . '" --header "Content-Type: application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"' . $_subscriptionId . '\"]}"';
exec($cmd);
?>