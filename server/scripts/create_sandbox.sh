#!/bin/sh
debug=false

#Affichage de l'aide si "-h"
if [ -n "$1" ] && [ $1 = "-h" ]; then
    echo "\n"
    echo "$0 -r receipt -e environment -v version -c configuration [-f fichier]"
    echo "\t-r : Nom du destinataire"
    echo "\t-e : Environnement (Testing)"
    echo "\t-v : Version à installer"
    echo "\t-c : Configuration client"
    echo "\t[-f] : Fichier dans lequel enregistré la sortie console"
    echo "\n"
    exit
fi

#Traitement des paramètres
while getopts "r:e:f:v:c:" opt; do
    case $opt in
    r)
      receipt=$OPTARG;;
    e)
      env=$OPTARG;;
    f)
      file=$OPTARG;;
    v)
      version=$OPTARG;;
    c)
      conf=$OPTARG;;
    \?)
      echo "Invalid option:" $OPTARG;;
    esac
done

#Affichage des variables si le debug est activé
if [ debug ]; then
    echo 'Receipt :' $receipt
    echo 'Version :' $version
    echo 'File :' $file
    echo 'Configuration :' $conf
    echo 'Environment :' $env
fi

#Création de la sandbox

#Envoi d'une commmande a Vagrant afin de récupérer l'adresse IP correspondant à l'adresse locale du réseau, soit 192.168.*.*
dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
parent_dir="$dir/..";
base_dir="$parent_dir/base";
version_dir="$parent_dir/vms/$version"
file="$parent_dir/pages/services/$file"
echo "$version_dir" >> $file

if [ ! -d "$version_dir" ]; then
    echo "Working directory : $dir" >> $file
	echo "Creating a new Sandbox" >> $file
	echo "Copying $base_dir to version $version_dir" >> $file
	cp -r $base_dir $version_dir

	echo "Positionning in : $version_dir" >> $file
	cd $version_dir

	echo "Setting up" >> $file
	CONF=$conf VERSION=$version vagrant up >> $file
else
	echo "Positionning in : $version_dir" >> $file
	cd $version_dir

	echo "Reloading VM" >> $file
	vagrant reload >> $file
fi

echo "Retrieve IP address" >> $file
n=$(vagrant ssh -c "ip addr | awk '/inet 192.168/ {sub(/\/.*/,\" \",\$2); print \$2}'")
echo "IP Address : $n" >> $file
if [ -n "$n" ]; then
    echo "Sending email with informations" >> $file
	#Envoi d'un email avec les informations relatives aux informatiosn de la sandox
	echo "Félicitations!\n 
	Votre environnement vient d'être créé\n\n
	Il se situe à cet emplacement : http://$n\n
	Il expirera dans 2 heures à partir de maintenant" | mail -s "Your Sandbox" arnaudgeiser@gmail.com
else
    echo "No email will be sent" >> $file
fi


#Création d'une tâche planifié pour la suppression de la VM
echo "sudo vagrant destroy -f && rm -R $version_dir" | at now + 2 hours
echo "sudo vagrant destroy -f && rm -R $version_dir" >> $file

#Suppression du fichier 
sleep 2
rm $file
