/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.btn;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author mosco
 */
public class MyButton extends Button{
    
    public MyButton(String title, EventHandler<ActionEvent> event){
        super(title);
        setOnAction(event);
    }
}
