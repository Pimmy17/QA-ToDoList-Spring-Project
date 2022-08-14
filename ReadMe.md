#QA Spring Project - Online Coin Collection

This project is the back-end aspect for a full stack project that uses all the knowledge I have gained during the 9 weeks at QA.
The front-end can be found [here](https://github.com/Pimmy17/QA-CoinCollection-FE).
Neither the front, nor back end have been hosted, so if you wish to see both in action, you will need to follow the steps on both repos for cloning and running.

---

###Cloning

To clone this repo, click on the <mark>Code</mark> button towards the top of the page.

Copy the desired link, in the below example it will be the SSH link.

Head to your terminal and type **'git clone'** followed by the link and then press enter.

`git clone git@github.com:Pimmy17/QA-ToDoList-Spring-Project.git`

---
###Open Repo for Modifying/Running
After the repo has cloned, head to Eclipse.

In Eclipse, click on the top left **'File'** button to open the drop-down menu.

Here, select **'Import'** >>> **'Git'** >>> **'Projects from Git'** >>> Click Next and follow the rest of the instructions.

---

###Run the Project
In order to run the project, head to the 'src/main/resources' folder in the project and check in **application-dev.properties** and set up your local database, or just update the username and password if you already have MySQL installed.

Then right click the project, select **Run As** and then **Spring Boot App**.

You will then see it load up in the Eclipse terminal and then it is ready to use.


If you are using the back-end project on it's own, then head to Postman, where you can use the following end points.

---
###End Points

If you are using localhost:8080 then you can copy these end-points straight in to Postman, or Insomnia, or just alter the port number so it works for you.

- Create: `localhost:8080/home/createCoin` <<< Creates a coin. Ensure all fields are entered
- Read: `localhost:8080/home/coins` <<< Reads all coins in database
- Read: `localhost:8080/home/coins/{id}` <<< Enter the ID of the coin you wish to view
- Read: `localhost:8080/home/coins/mycollection` <<< Reads all coins where inCollection = true
- Update: `localhost:8080/home/updateCoin/{id}` <<< Enter the ID of the coin you wish to update, by changing inCollection to either true or false
- Delete: `localhost:8080/home/deleteCoin/{id}` <<< Enter the ID of the coin you wish to remove from the database