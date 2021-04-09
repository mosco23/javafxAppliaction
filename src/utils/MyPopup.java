/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author mosco
 */
// Java program to create a popup and
// add it to the stage
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Popup;

public class MyPopup extends Popup
{
    Button supprimer, modifier;
    TilePane tilepane;
        
    // Constructor
    
    public MyPopup(){
        modifier = new Button("Modifier");
        supprimer = new Button("Supprimer");
        tilepane = new TilePane();
        // label.setStyle(" -fx-background-color: white;");

        // action event
        EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e)
                {
                        if (!isShowing())
                           hide();
                                //how(stage);
                        else
                                hide();
                }
        };

        // when button is pressed
        modifier.setOnAction(event);
        supprimer.setOnAction(event);

        // add button
        tilepane.getChildren().add(modifier);
        tilepane.getChildren().add(supprimer);

        // create a scene
        Scene scene = new Scene(tilepane, 200, 200);

    }

}
