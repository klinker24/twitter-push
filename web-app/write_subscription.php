<?php
require("config.php");

echo "password from GET: " . $_GET['password'] . ", password: " . $password;
if (strcmp($_GET['password'], $_password) == 0) {
	$file = fopen("subscription.json", "w") or die("Unable to open file!");
	$txt = $txt = '{ "subscription":"' . $_GET['subscription'] . '" }';

	fwrite($file, $txt);
	fclose($file);

	echo "Subscribed to notifications. You can close this window.";
} else {
	echo "Subscription failed. Your password is incorrect.";
}

?>