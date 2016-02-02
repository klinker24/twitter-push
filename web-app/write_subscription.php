<?php
$myfile = fopen("subscription.php", "w") or die("Unable to open file!");
$txt = '<?php $_subscriptionId = ' . urldecode($_GET['subscription']) . '; ?>';
fwrite($myfile, $txt);
fclose($myfile);

echo "Subscribed to notifications.";
?>