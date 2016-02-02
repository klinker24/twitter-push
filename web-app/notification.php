<?php
require("config.php");

$fileContent = file_get_contents($_web_host . "/web-app/subscription.json");
$_subscriptionId = json_decode($fileContent, true)['subscription'];

$file = fopen("notification_data.json", "w") or die("Unable to open file!");
$txt = '{ "data":{"title":"' . urldecode($_GET['title']) . '", "message":"' . urldecode($_GET['message']) . '", "icon":" '. urldecode($_GET['icon']) . '", "url":"'. urldecode($_GET['url']) .'"} }';
fwrite($file, $txt);
fclose($file);

$cmd = 'curl --header "Authorization: key=' . $_google_api_key . '" --header "Content-Type: application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"' . $_subscriptionId . '\"]}"';
exec($cmd);
?>