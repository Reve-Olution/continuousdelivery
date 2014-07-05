echo "Param1"
echo $1
echo $2 
apt-get install -y apache2
apt-key adv --recv-keys --keyserver keyserver.ubuntu.com 1BB943DB
add-apt-repository -y ppa:webupd8team/java
echo deb http://ftp.igh.cnrs.fr/pub/mariadb//repo/5.5/ubuntu $(lsb_release -sc) main | sudo tee /etc/apt/sources.list.d/MariaDB.list
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get update

sudo debconf-set-selections <<< 'mariadb-server-10.0 mysql-server/root_password password PASS'
sudo debconf-set-selections <<< 'mariadb-server-10.0 mysql-server/root_password_again password PASS'
sudo apt-get install -y mariadb-server
mysql -uroot -pPASS -e "SET PASSWORD = PASSWORD('');"

echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get install -y oracle-java8-installer

sudo debconf-set-selections <<< "chalets chalets/version password $1"
sudo debconf-set-selections <<< "chalets chalets/env password $2"
wget http://www.ludine.ch/debianPackages/chalets-$1.deb
dpkg -i chalets-$1.deb
