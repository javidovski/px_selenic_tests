# PX Selenic Repo

Welcome!
This is a repo for our UI automation.
The QA team is using a third party tool called Parasoft Selenic for our UI automation.

## 1. Setup Parasoft

Please have a look at this documentation on how to set the Parasoft tool,
https://paytronix.atlassian.net/wiki/spaces/QA/pages/1989935297/Installation+and+Setup

Once you are finished with all the steps from that document, 
download the Parasoft Recorder. 
https://paytronix.atlassian.net/wiki/spaces/QA/pages/1990393859/Parasoft+Recorder

<br>
<br>

## 2. Setup Java and Maven
### Install Java & set java_home path

Go to this URL to install the JDK
https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html


<details>
 <summary>🪟 For Windows</summary>
 
 <br>
 Once you installed the jdk, you will need to set up Environment variables in Java.

1. Search for Environment variables on your computer
2. click on the new button of USER variables
  * set the variable name as `PATH` 
  * set the variable value to where the bin folder of the jdk folder you installed
    it should be something similar to `C:\Program Files\Java\jdk...\bin` 
    (Navigate to that path to verify the bin folder is there)

3. Once you set that up, go on to your command prompt and enter `java -version`
and you should see the java version
 <br>
 </details>

<details>
 <summary>🍎 For Mac</summary>
 
  <br>
 1. Please install [Homebrew](#-install-homebrew-for-mac)

Once Homebrew is installed, pretty much any software installation from this point will be easier.

2. open a terminal and type `brew install --cask java` 
3. if that doesn't work, then you can try just `brew install java`

Once it's installed, verify by typing `java -version` on the terminal.

You should see the java version on the output.
4. Type `open ~/.zshrc` on the terminal
it should open the file on Mac's default text editor
5. Go all the way to the bottom and paste this command inside the file
```
export JAVA_HOME=$(/usr/libexec/java_home)
```
6. save and exit
  <br>
 </details>

<br>
<br>


## 3. Install Maven

<details>
 <summary>🍎 For Mac</summary>
 
 <br>
1. If you haven't, please install [Homebrew](#-install-homebrew-for-mac)

If you already installed it, continue

2. All you have to do is `brew install maven` to install maven to your machine

Homebrew will take care of all the installation and 
take care of setting env variables, paths, etc. 

<br>
</details>


<details>
 <summary>🪟 For Windows</summary>
 
 <br>
 
 Navigate to this URL https://maven.apache.org/download.cgi
* On the page, you will see a header named Files
* In that section, you can find the zip file 
* download the Binary Zip archive (apache-maven-3.8.5-bin.zip)
* extract the folder and move it to your C\\Prgoram files
* Press the windows key to search for Environment Variables
  Here you will need to create new user variables and system variables
  * Make a new USER variable
    * variable name as `MAVEN_HOME`
    * location is the path of that extracted folder you moved to the Program files
      ex: C:\Program Files\apache-maven-3.6.1-bin\apache-maven-3.6.1 
      (don't worry if these numbers don't match with your, they are just versions)
  * Make another USER variable
  * variable name as `M2_HOME`
    * location is the same as the above step
  * Make a SYSTEM variable
    * paste the location of the maven folder 
      (same as above) but also add the `\bin` 
      because we need the bin folder of the maven folder.

verify that your installation worked by typing 
* `mvn -version` on your terminal (command prompt)

  
## 4. Running the test from Command Line

Assuming you already did the steps for installing Maven,
navigate to the repo folder by typing `cd <location of the repo>`

Once you navigate on the root of the repo, type `mvn clean install`.
This should clean and build the project. 

Currently it will run the tests that are inside the `<include>` tag 
in our `pom.xml` file. 
You can add specific tests that you would like to run inside these `<include>` tags.
  <br>
 </details>

<br>
<br>
 
# 🍺 Install Homebrew (For Mac)

<details>
 <summary>Click to expand and close!</summary>
 
 Homebrew is a package manager that simplifies the installation 
of any software on Apple's operating system.

Let's first try and see if Homebrew is already installed on the machine:
1. Open a mac terminal 
2. type `brew` and hit enter

If you see an output that is similar to this
```
zsh: command not found: brew
```
then, we know that we don't have Homebrew!

Now, to install Homebrew:
1. On the terminal
2. Copy this command 
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
``` 
3. paste the copied command on the terminal and hit enter
4. It will ask you to enter your Mac user password 
   (This is the same password you use to sign into your Mac)

Notice that when you enter your password, it won't actually show any changes. 
But know that it is being typed. 

5. Once you are done entering your password, hit enter.

This should install homebrew on your mac 

If you are using Mac with Apple's silicon chip, 
there's one more step. Homebrew files are installed into the 
`/opt/homebrew` folder but that folder is not part of the default `$PATH`. 
So, we need to edit `~/.zprofile` file.

* On your terminal type `open ~/.zprofile`
  If you get an output that says something like 
  `The file /Users/<yourUserName>/.zprofile` does not exist,
  then we want to create that file.

* Type `mkdir ~/.zprofile` on your terminal
* open the file with `open ~/.zprofile` 
  this should open the file on the mac's defualt text editor
* copy and paste these 2 commands inside the file
```
echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
eval "$(/opt/homebrew/bin/brew shellenv)"
```
* save and exit the editor

Now, verify that your intallation worked by typing `brew doctor` on the terminal.
You should see (it may take few seconds):
```
Your system is ready to brew.
```
You are all set! 
Go back to the next step for installing [Java](#install-java--set-java_home-path)
 </details>


