<?php
include_once("../config.php");

$string = file_get_contents($_web_host . "/web-app/subscription.json");
$_subscriptionId = json_decode($string, true)['subscription'];
echo "json: " . $string;


// Do not edit below this line.
// ----------------------------
$myfile = fopen("notification_data.json", "w") or die("Unable to open file!");
$txt = '{ "data":{"title":"' . urldecode($_GET['title']) . '", "message":"' . urldecode($_GET['message']) . '", "icon":" '. urldecode($_GET['icon']) . '", "url":"'. urldecode($_GET['url']) .'"} }';
fwrite($myfile, $txt);
fclose($myfile);

$cmd = 'curl --header "Authorization: key=' . $_authorization . '" --header "Content-Type: application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"' . $_subscriptionId . '\"]}"';
exec($cmd);
?>