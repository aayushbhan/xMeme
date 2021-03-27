Xmeme is a meme streaming application where users can post memes using image urls along with the name and caption given by the user. 
The most recent 100 memes are displayed by the application. The meme captions can be updated anytime by the user.

# To see the application in the browser, the url would be - http://localhost:8081/home

git clone the repo

cd to the cloned repo directory


# Run the user’s installation steps which will install any necessary dependencies required for the server to run, with sudo permission

chmod +x install.sh

sudo ./install.sh


# Run the user’s server execution steps which will bring up the server

chmod +x server_run.sh

./server_run.sh


# Execute the GET /memes endpoint using curl to ensure your DB is in a clean slate

Should return an empty array.

curl --location --request GET 'http://localhost:8081/memes'


# Execute the POST /memes endpoint using curl

curl --location --request POST 'http://<Server_URL>/memes' \

--header 'Content-Type: application/json' \

--data-raw '{

"name": "xyz",

"url": "abc.com",

"caption": "This is a meme"

}'


# Execute the GET /memes endpoint using curl

curl --location --request GET 'http://localhost:8081/memes'
