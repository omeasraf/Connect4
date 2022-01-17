# Connect4 Game

This is a simple Connect4 Game made using Java and JavaFX. This game uses a webserver to parse the data or in this case the wining combo to determine if the user won the game or not. 

## Tech

Connect4 uses a number of open source projects to work properly:

* [Java](https://www.java.com/en/) - The #1 programming language and development platform.
* [JavaFX](https://openjfx.io/) - A software platform for creating and delivering desktop applications.
* [GSON](https://github.com/google/gson) - Java library that can be used to convert Java Objects into their JSON representation.
* [Maven](https://maven.apache.org/) - Software project management and comprehension tool.
* [Spring Framework](https://spring.io/) - Provides a comprehensive programming and configuration model for modern Java-based enterprise applications on any kind of deployment platform.

## Installation

Connect4 requires [Java](https://www.java.com/en/) v10+ to run.

Install the required IDE to start the project, I recommend using [IntelliJ IDEA](https://www.jetbrains.com/idea/).

## Instructions

- Open GUI project in IntelliJ IDEA
	> Before running the project, make make sure you have JavaFX downloaded and in a place which you can easily access. 
	> Copy the lib directory of JavaFX and edit the configuration setting of the GUI project and replace the JavaFX directory with the one you just copied and then run the project
- Open WebService project in a new window of IntelliJ IDEA
	> Make sure to navigate to the "Required Packages" directory first and install "client-to-http" package before trying to run the project. Make sure to maven reload the project before trying to run it.

## Gameplay

![Gameplay](/game.gif)

