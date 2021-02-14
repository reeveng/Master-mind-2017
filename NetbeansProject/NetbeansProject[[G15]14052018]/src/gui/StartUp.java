
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartUp extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        DomeinController controller = new DomeinController();
        Scene scene = new Scene(new AanMeldSchermController(controller), 600, 600);
        
        scene.getStylesheets().add("/gui/styles.css");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
      

    }
    
      public static void main(String... args)
    {
       Application.launch(StartUp.class,args);
    }
    
}
