sudo apt -y  update
sudo apt -y install mysql-server
sudo mysql -u root -e \
  "CREATE USER 'user'@'%' IDENTIFIED BY 'root';"

sudo mysql -u root -e\
  "GRANT ALL PRIVILEGES ON * . * TO 'user'@'%';FLUSH PRIVILEGES;"
sudo mysql -u root -e "CREATE DATABASE crioMeme;" 

sudo service mysql start

sudo apt-get install maven -y
