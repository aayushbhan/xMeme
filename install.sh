#Setting up the database
sudo apt update -y

wget https://dev.mysql.com/get/mysql-apt-config_0.8.12-1_all.deb -y

sudo dpkg -i mysql-apt-config_0.8.12-1_all.deb -y

sudo apt-get install mysql-server -y

sudo mysql

CREATE USER 'user'@'%' IDENTIFIED by 'root';

GRANT ALL PRIVILEGES ON * . * TO 'user'@'%';

CREATE DATABASE crioMeme;

exit

sudo service mysql start

#installing maven

sudo apt-get install maven -y
