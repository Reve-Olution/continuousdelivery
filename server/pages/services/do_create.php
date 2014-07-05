<?php
$file = uniqid();

$configuration = $_GET['configuration'];
$environment = $_GET['environment'];
$version = $_GET['version'];
$email = $_GET['email'];
    
$exe_command = "sudo ../../scripts/create_sandbox.sh -v $version -e $environment -r $email -c $configuration -f $file > /dev/null &";
shell_exec($exe_command);

touch($file);
echo $file;
?>
