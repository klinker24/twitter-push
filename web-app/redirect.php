<?php
// we want to use this redirect to access twitter rather than the original link
// from the notification so that the twitter page has the chance to open in a third party app.

header("Location: " . $_GET['url']);
exit;
?>