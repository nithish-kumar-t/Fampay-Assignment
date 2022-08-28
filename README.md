# Fampay-Assignment

steps to run the project
1. Pull the docker image **_docker pull nithish136/fampay-assignment:fam-assign_**
2. To run the server use **_docker run -p 8080:8080 nithish136/fampay-assignment:fam-assign_**
3. Go to **localhost:8080/search/ind** to verify the API



It provides a GET API to query the DB and return all possible rows that match the video details.

localhost:8080//search/{Query String}?max Records

Here max Records is optional, it's default value is 10.

Query String is a path variable.

