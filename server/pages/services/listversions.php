<?php
$versionDirectory = "/var/www/debianPackages";

$handle =  opendir($versionDirectory);

$versions = array();

if($handle) {
    /* This is the correct way to loop over the directory. */
    while (false !== ($entry = readdir($handle))) {
        $matches = array();
        preg_match('/-(.*).deb/',$entry, $matches);
        if($matches[1]) {
            array_push($versions, $matches[1]);
        }
    }
    closedir($handle);
}

sort($versions);
echo json_encode($versions);
?>
