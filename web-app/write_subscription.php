<?php
$file = fopen("subscription.json", "w") or die("Unable to open file!");
$txt = $txt = '{ "subscription":"' . $_GET['subscription'] . '" }';

fwrite($file, $txt);
fclose($file);

echo "Subscribed to notifications. You can close this window.";
?>