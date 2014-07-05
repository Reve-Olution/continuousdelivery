<?php
$filename = $_GET['fichier'];

//Si le fichier existe, on retourne ses lignes
//Dans le cas contraire, on retourne false
if(file_exists($filename)) {
    $lines = file($filename);
    echo json_encode($lines);
}
else {
    echo "false";
}
?>
