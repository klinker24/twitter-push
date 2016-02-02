<?php
$myfile = fopen("subscription.json", "w") or die("Unable to open file!");
$txt = $txt = '{ "subscription":"' . $_GET['subscription' . '" }';

fwrite($myfile, $txt);
fclose($myfile);

echo "Subscribed to notifications.";
?>