Customer Complaints RESTFul API
===============================

This is a simple demo rest api for customer complaints.

It is deployed on heroku.
You can check all of the endpoints at:
https://documenter.getpostman.com/view/14552178/Tz5m6yYB

Instructions in case you want to download the code and deploy it yourself on your own heroku account:

1. Download the code from github
git clone https://github.com/juankfe/restfulapiconsumercomplaints.git

2. Open the application on Spring Tool Suite IDE (use JDK 11.0.8)

3. Comment out automated tests. They run and pass on local environment.
But will not work on heroku because application.properties will be configured to heroku db that doensn't yet have data.
\src\test\java\complaintsapi\controllers\ComplaintsControllerTests.java

Note: If you want to run the automated tests locally before deploying to heroku:
a) install a local PostgreDB and add a "restfulapiconsumercomplaints" database.
b) rename application.propertiesLocal to application.properties informing the db credentials on this file
c) run the app on localhost

When you are ready to deploy to heroku, change the application.properties file back to the original version (same content as application.propertiesHeroku) and continue to step 4 below.

4. Create your account on heroku.com

5. Install heroku CLI in case you don't have it yet
https://devcenter.heroku.com/articles/heroku-cli

6. Create your app on heroku main panel
Eg.: restfulapiconsumercomplaints (choose your name. This one is taken already)

7. Open a terminal on STS:
Right click the app root folder Show in > Local Terminal

8. Log into heroku:
heroku login

6. Initiate a git repository
git init 

7. Create the repo on heroku
heroku git:remote –a restfulapiconsumercomplaints

8. Change heroku's branch from master to main
git checkout -b main 
git branch -D master  

9. Create heroku database
heroku addons:create heroku-postgresql:hobby-dev 

10. Build app
mvn clean package

11. Push to heroku
git add . 
git commit -m "initial commit"
git push heroku main   

Done! With this you will be able to access the app endpoint on your installation.
Just like it is already available here:
https://documenter.getpostman.com/view/14552178/Tz5m6yYB

Just replace "restfulapiconsumercomplaints" by the name you gave to your app.
And start by adding data to the database because it will be empty at the begining. 

The version that is already deployed on the link above already has several records in the db.