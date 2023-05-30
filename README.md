"# CMSC137-ShapeCraft" 


# for chat feature 
1. Since we are using JavaFX make sure to install JavaFX on your development environment in java

this is the path to my javafx sdk in my pc however
--module-path C:\openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib  --add-modules=javafx.controls,javafx.fxml

use this when you are going to try this on your own machine
--module-path /path/to/JavaFX/lib  --add-modules=javafx.controls,javafx.fxml

2. Since we want to use multiple clients when running our chat feature what I did was build it using the terminal.
   First make sure to change your directory to src folder of the java project, once you are in that directory
   type the command below

javac --module-path /path/to/JavaFX/lib --add-modules javafx.controls,javafx.fxml Main.java ClientStart.java

sample: javac --module-path C:\openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib --add-modules javafx.controls,javafx.fxml Main.java ClientStart.java

3. Now we will run the Main.java just go to the directory where the root of the folder where the Main is located
   First make sure to change your directory to src folder of the java project, once you are in that directory
   type the command below

java --module-path /path/to/JavaFX/lib --add-modules javafx.controls,javafx.fxml ChatFeatureTest2.Main
sample: java --module-path C:\openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib --add-modules javafx.controls,javafx.fxml ChatFeatureTest2.Main

