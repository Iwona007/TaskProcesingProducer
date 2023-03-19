# TaskProcessingProducer
## About project
This id a demo application for CDQ interview purposes. 

## Prerequisites
The following tools are required to start the application:
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) / [VSC](https://code.visualstudio.com/) / [Eclipse](https://www.eclipse.org/)
- [Java 17 LTS](https://openjdk.org/projects/jdk/17/)
- [MySql Workbench](https://www.mysql.com/products/workbench/) / [DBeaver](https://dbeaver.io/)
- [Git Bash](https://git-scm.com/downloads)
- [Maven 3.x](https://maven.apache.org/download.cgi)
- [Postman](https://www.postman.com/)
- [Docker](https://docs.docker.com/get-docker/) - please refer to [Setting up Docker]()

## How to run

### 1. Clone the repository
Please clone the repository by https or ssh (below I used the https method).
```
git clone https://github.com/Iwona007/TaskProcesingProducer.git
```

### 2. Run docker
Then you need to run: docker-compose up from command line (Linux) or start docker-compose.yaml from your IDEA
From here we can start:
- zookeeper
- kafka
- database

### 2.1 Run the database
You need a working mysql server to run this application.
You can use your local server installation or use the docker compose file from this project.
**Remember:** if you are using your local server instance, change parameters for the database connection.

Wait until the database server starts completely, it may take a while.

## 3 How it works
After your docker and database was started, you can run application form IDEA on server port 8585.
The default profile is "dev", but there is prepared prod profile as well. 
When TaskProcessingProducer is running please use Postman to post request:
http://localhost:8585/app/tasks?pattern=ABC&input=ADBCDEF
than in the log console you can see log information 
for example: Message Sent SuccessFully for the key : 1 and the value is 
{"taskId":1,"input":"ADBCDEF","pattern":"ABC","taskType":"NEW","result":null,"status":"0%"} , 
partition is 0.
Next please go to the TaskProcessingConsumer



