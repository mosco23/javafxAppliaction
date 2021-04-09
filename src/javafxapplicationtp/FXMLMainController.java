/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtp;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import models.PersonneController;
import models.Personne;

/**
 *
 * @author mosco
 */
public class FXMLMainController implements Initializable {
    
    private PersonneController pc;
    
    @FXML
    private Label labelInfo;
    
    @FXML
    private TextField inputNom, inputPrenoms;
    
    @FXML
    private DatePicker datepicker;
    
    @FXML
    private Button btnSave, btnUpdate, btnClean, btnRemove;
    
    @FXML
    private TableView<Personne> table;
    
    @FXML
    private TableColumn<Personne,String> colNom, colPrenoms, colDateNaiss;
    
    @FXML
    ObservableList<Personne> observeListe;
    
    private Personne p;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = new PersonneController();
        observeListe = FXCollections.observableArrayList(pc.personnes());
        table.setItems(observeListe);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenoms.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
        colDateNaiss.setCellValueFactory(new PropertyValueFactory<>("datanaiss"));
        
        datepicker.autosize();
    }


    
    public void onActionSave(ActionEvent event){
        if ((is_empty()))
            message(Alert.AlertType.ERROR, "Veillez remplire tous les champs !");
        
        else if (observeListe.contains(p)) {
            p.setNom(inputNom.getText());
            p.setPrenoms(inputPrenoms.getText());
            p.setDatanaiss(datepicker.getValue().toString());
            
            if(new PersonneController().update(p, p.getId())){
                refresh();
                message(Alert.AlertType.INFORMATION, "Informations de l'etudiant modifiee avec succes !");
                clean();
            }else{
                message(Alert.AlertType.ERROR, "OOps !\n"
                    + "Une erreur est survenue lors de la modification.\n"
                    + "Veillez verifier que tous les champs sont bien remplis.");
            }
            
        }
        
        else{
            p = new Personne();
            p.setNom(inputNom.getText());
            p.setPrenoms(inputPrenoms.getText());
            p.setDatanaiss(datepicker.getValue().toString());
            
            if(new PersonneController().add(p)){
                refresh();
                message(Alert.AlertType.INFORMATION, "Etudiant enregistre avec succes !");
                clean();
            }
                
            
            else
                message(Alert.AlertType.ERROR, "OOps !\n"
                        + "Une erreur est survenue lors de l'enregistrement.\n"
                        + "Veillez verifier que tous les champs sont bien remplis.");
        }
        
    }
    
    
    public void onActionUpdate(ActionEvent event){
        p = table.getSelectionModel().getSelectedItem();
        inputNom.setText(p.getNom());
        inputPrenoms.setText(p.getPrenoms());
        datepicker.setValue(LocalDate.parse(p.getDatanaiss()));
        Popup popup = new Popup();
        
    }
    
    
    
    public void onActionRemove(ActionEvent event){
        p = table.getSelectionModel().getSelectedItem();
        if (pc.remove(p.getId())) {
            p = new Personne();
            refresh();
            message(Alert.AlertType.INFORMATION, "Etudiant supprime avec succes !");
        }
        
    }
    
    
    @FXML
    private void onTableItemSelected(MouseEvent event){
        p = table.getSelectionModel().getSelectedItem();
        inputNom.setText(p.getNom());
        inputPrenoms.setText(p.getPrenoms());
        datepicker.setValue(LocalDate.parse(p.getDatanaiss()));
    }
    
    
    
    
    
    public boolean is_empty(){
        if (inputNom.getText().isEmpty() || inputPrenoms.getText().isEmpty() || datepicker.getValue() == null || datepicker.getValue().toString().isEmpty())
            return true;
        
        return false;

    }
    
    
    
    
    private void message(Alert.AlertType at, String msg){
        Alert alert = new Alert(at, msg, ButtonType.CLOSE);
        alert.showAndWait();
    }
    
    
    public void clean(){
        inputNom.clear();
        inputPrenoms.clear();
        datepicker.setValue(null);
    }
    
    
    private void refresh(){
        observeListe.clear();
        observeListe.addAll(pc.personnes());
    }
    
    
    
}