# Proton - Java project with Gherkin

The project is a Maven one using Java as a programming language. The IDE used to develop it is IntelliJ.

It is using BDD for the tests, so Cucumber and Gherkin plugins are needed.

The driver I used is ChromeDriver for Windows.

Sonar results can be found in a snapshot located in _src/test/resources/sonar_ folder.

To run it, by using the plugins, you can run the feature files located in _src/test/resources/features/automated_
folder. There is an image with the config to be used in _src/test/resources/config_ folder.

Once the test has started to run, logs are showed in the console and saved into _log/logging.log_ file. When test
execution has been finished, the console will show a box at the end with a link for a 24h html report.

The step definition is inside the library (_src/test/java/library_ folder) and the logic is inside the controller
(_src/test/java/controller_ folder). In order to complete the POM structure, I should have moved the locators 
(_src/test/java/locators_ folder) from the controller class to locators one, but I have no available time to do it prior
to technical test deadline, so I've explained it in here.

In the end I created 3 feature files containing 11 cases that checked plan pages accessibility, currency modifications 
and different billing plans.