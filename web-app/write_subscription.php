<?php
require("config.php");

if ($_GET['password'] == $_password) {
	$file = fopen("subscription.json", "w") or die("Unable to open file!");
	$txt = $txt = '{ "subscription":"' . $_GET['subscription'] . '" }';

	fwrite($file, $txt);
	fclose($file);

	echo "Subscribed to notifications. You can close this window.";
} else {
	echo "Subscription failed. Your password is incorrect.";
}

?>