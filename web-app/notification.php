<?php
$_authorization = "AIzaSyB46bQF9oL9QdOb48LWvbrBfvToik_tyRw";  // from the google developer console (API Key)
$_subscriptionId = "dBjMTfz-FUo:APA91bG2OP3HLLALwsCJT6SfgfS4dRr3RdDQIrM3bMOPfvUU2Rtoq97nUOOI3WieewPT1RmH2gvaJiqFFsSADiLBkqjms_vo2c3VxtD1e-enIx43pkdFdQcETf2rj0uoCsrr5lWLgold";


// Do not edit below this line.
// ----------------------------
$myfile = fopen("notification_data.json", "w") or die("Unable to open file!");
$txt = '{ "data":{"title":"' . urldecode($_GET['title']) . '", "message":"' . urldecode($_GET['message']) . '", "icon":" '. urldecode($_GET['icon']) . '", "url":"'. urldecode($_GET['url']) .'"} }';
fwrite($myfile, $txt);
fclose($myfile);

$cmd = 'curl --header "Authorization: key=' . $_authorization . '" --header "Content-Type: application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"' . $_subscriptionId . '\"]}"';
exec($cmd);
?>