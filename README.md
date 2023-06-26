Swag labs automation testing
==========================

### How to set up?
* Install Java 
* Install [InteliJ IDEA Community Edition](https://www.jetbrains.com/idea/download)
* Install [git](https://git-scm.com/downloads)
* Download and install Maven [Apache Maven](https://maven.apache.org/download.cgi)
* Clone the project from the git repo
* Open the project using Intellij
* Download [selenium drivers](https://selenium.dev/documentation/en/webdriver/driver_requirements/#quick-reference)
and add them in the bin/{```your_OS```} folder (just for Linux and MacOS, for windows will be downloaded automatically).

### Source is structured follow Page Object Model design pattern
### Framework: Selenium Webdriver + TestNG
### How to run the tests?
You can run each Test case that functions have annotation @Test in classes in folder tests,
Or right click **src/test/resources/xmlsuites/SwagLabProductTestSuite.xml** and run. 
Edit Configurations in Intelij to generate test-output
