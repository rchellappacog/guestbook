Guestbook Service
Guestbook Service is a service that allows guests to leave some comments

Instructions
This exercise focuses on the configuration of Docker to manage the deployment of your application both locally and remotely. Keep it simple. Use the stories and acceptance criteria to develop the Guestbook Service.

Create a repository.
Submit the link to your repository.
Submit the URL to your live app.
Hint: You should set up auto deployment ASAP. Every commit you make to main/master will be deployed to Heroku. Baby Steps!!

Requirements
Must have API specs for all endpoints, use Spring RestDocs.
Must have integration tests and unit tests.
As always TDD is expected.

Utilize Spring Profiles and Docker enable multiple run environments.

A local application running in Docker that uses Docker Postgres as DB. Provide instructions on how to run this.

A deployed application on Heroku that uses Heroku Postgres as DB.

Features
Any visitor can post their name and a comment to the Guestbook.
All visitors can see a list of every entry in the Guestbook.

** 
Endpoints:
https://guestbook-sampleapp.herokuapp.com/guestbook

//Adding a comment
````
JSON format
{
    "name" : "peter",
    "comment": "nice artifacts!"
}

