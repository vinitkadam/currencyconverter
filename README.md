# currencyconverter
## How to run this code?
This project uses google's gson library to parse the json response from the fixer.io api
You will have to download the json folder from [here](http://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.2/).

Open 2 terminals

Compile the MyClient.java file
    
    javac MyClient.java
Compile the MyServer.java file

    javac -cp /path/to/sourcecode/folder/:/path/to/gson-2.8.2.jar MyServer.java

Run the MyClient on one terminal

    java MyClient
    
Run the MyServer on another terminal
    
    javac -cp /path/to/sourcecode/folder/:/path/to/gson-2.8.2.jar MyServer.java
    
    
