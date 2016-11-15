/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Aurélien DUVAL 
 */
public class Controller implements Initializable {
    
    @FXML
    private BorderPane pane;
    @FXML
    private Button openFileButton;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button addLineButton;
    @FXML
    private Button updateLineButton;
    @FXML
    private Button deleteLineButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button cipherButton;
    @FXML
    private Button decipherButton;
    
    private File file;
    JSONParser parser = new JSONParser();
    private JSONObject object = new JSONObject();               //objet JSON contenant les entrées
    private JSONArray entityListXtract;
    
    private JSONArray[] entityListArray = null;

    
    @FXML
    private void openFile(){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        file = fileChooser.showOpenDialog(pane.getScene().getWindow());
        System.out.println("Fichier choisi : "+file.getName()+"\nEmplacement : "+file.getPath());
        
    }
    
    @FXML
    private void saveFile(){
        if(fileChecker()){
            try {
		FileWriter fileToWrite = new FileWriter(file.getPath());
		fileToWrite.write(object.toJSONString());
		fileToWrite.flush();
		fileToWrite.close();

            }catch (IOException e) {
                    e.printStackTrace();
            }
        }
        object = null;
    }
    
    @FXML
    private void addLine(){
        JSONArray newEntityX = new JSONArray();
        newEntityX.add("servicex");
        newEntityX.add("URLx");
        newEntityX.add("userNamex");
        newEntityX.add("passwordx");
        newEntityX.add("commentx");
        
        if(entityListArray == null){
            entityListArray[0] = new JSONArray();
            entityListArray[0] = newEntityX;
        }else{
            entityListArray[entityListArray.length] = newEntityX; 
        }
        
        
        show();
    }
    
    @FXML
    private void updateLine(){
        extractJson();
    }
    
    @FXML
    private void deleteLine(){
        
    }
    
    @FXML
    private void quit(){
        
    }
    
    @FXML
    private void cipher(){
        
    }
    
    @FXML
    private void decipher(){
        
    }
    
    private boolean fileChecker(){
        if( file == null){
            System.out.println("\n/!\\ Vous devez choisir un fichier txt contenant vos mots de passe!!\nUtilisez le bouton \"Ouvrir\"!!");
            return false;
        }else
            return true;
    }
    
    private void show(){
        for(int i = 0; i<= entityListArray.length-1; i++){
            for(int j = 0; i<= 4; j++)
                System.out.println(entityListArray[i].get(j));
        }
    }
    
    private void jsonLorem(){
        
	JSONArray entityList = new JSONArray();
        
        JSONArray entity1 = new JSONArray();
        entity1.add("service1");
        entity1.add("URL");
        entity1.add("userName1");
        entity1.add("password1");
        entity1.add("comment1");
        
        JSONArray entity2 = new JSONArray();
        entity2.add("service1");
        entity2.add("URL");
        entity2.add("userName1");
        entity2.add("password1");
        entity2.add("comment1");
        
        entityList.add(entity1);
        entityList.add(entity2);
        
        object.put("EntityList", entityList);
        
	

        
        System.out.println(object.size());
        System.out.println(object.values());
        
    }
    
    
    private void extractJson(){
        if(fileChecker()){
            try {
                FileReader readerFile = new FileReader(file.getPath());
                object = (JSONObject) parser.parse(readerFile);;
                
                if(object != null)
                    entityListXtract = (JSONArray) object.get("entityList");
                
                if(entityListXtract != null){
                    for (int i = 0; i <= entityListXtract.size(); i++){
                        entityListArray[i] = (JSONArray) object.get("entityList");
                    }
                }
                    
                
                
                
                
            } catch (FileNotFoundException e) {
		e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            } catch (ParseException e) {
                    e.printStackTrace();
            }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChecker();
        jsonLorem();
    }    
    
}
