Restaurant Management Project

* *Date Created*: 20 JUN 2021
* *Last Modification Date*: 27 JUL 2021


## Running the application

To run the application follow the procedure provided below.
1. Run the command for loading the .env file
```
source .env
```
2. Run the command to check if the variables have loaded
```
printenv JDBC_DRIVER
```
3. If you see an output goto Step 4, else run the commands given below.
```
export JDBC_DRIVER
```
```
export DOMAIN_NAME
```
```
export USER_NAME
```
```
export PASSWORD
```
```
export DATABASE
```
```
export HOST
```
4. Start the application by running the command
```
java -cp target/group1-1.0-SNAPSHOT-jar-with-dependencies.jar user.view.UserLandingView
```
