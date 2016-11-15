/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author chrysippus
 */
public class Ex2 {

    /**
     * @param message 
     * @param key 
     * -> xor operate between message and key
     * Return String xorMessage
     */
    
    public static String xorFunction(String key, String message){ //Calcul du xor de 2 messages de taille égale (5)
        String xorMessage = "";
        String bakKey = key;
        String bakMessage = message;
        
        if (key.length() != message.length()){
            return "errorStringLength";
        }else{
            for (int i = 0; i < message.length(); i++ ){
                xorMessage += key.charAt(i) ^ message.charAt(i);
            }
            return xorMessage;
        }
        
    } // FIN xorFunction()
    
    
    
    /**
     * @param args the command line arguments
     * These arguments are accepted : 
     *      -msg
     *      -key
     *      -op
     *      -mode
     */
    
    public static void main(String[] args) {
        
        //************** Initialisation Booléen de break ***********************
        boolean exit = false;
        
        
        // ******************* Capture des options *****************************
        HashMap<String, String> progOptions = new HashMap<String, String>();
                
        for (int k = 0; k < args.length; k++){
            switch (args[k]){
                case "-msg" :   if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("message",args[k+1]);
                                    }
                                };
                                break;
                case "-key" :   if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("key", args[k+1]);
                                    }
                                };
                                break;
                case "-op" :    if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("option", args[k+1]);
                                    }
                                };
                                break;
                case "-mode" :  if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("mode", args[k+1]);
                                    }
                                };
                                break;
                case "-iv" :  if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("initVector", args[k+1]);
                                    }
                                };
                                break;
                case "-r" :  if(k+1< args.length){
                                    if(args[k+1] != null && args[k+1].charAt(0) != '-'){
                                        progOptions.put("feedBack", args[k+1]);
                                    }
                                };
                                break;
            }
	}
        
        //******************* Traitement des options ****************************
        
        String message = progOptions.get("message");
        String key = progOptions.get("key");
        String option = progOptions.get("option");
        String mode = progOptions.get("mode");
        String initVector = "";
        int feedBack = 0;
        
        // Traitement du message
        
        if(progOptions.get("message").length()%5 != 0){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument message est doit etre une une suite binaire ayant une taille multiple de 5");
        }
        
        // Traitement de la clef
        
        if(progOptions.get("key").length() != 5){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument clef est doit etre une une suite binaire ayant une taille 5");
        }
        
        // Traitement du vecteur d'initialisation
        
        if (progOptions.get("initVector") != null){
            initVector = progOptions.get("initVector");
        }else{
            for (int i=0; i<5; i++){
                if (Math.random() < 0.5 ) // génération du vecteur si il n'est pas indiqué ar l'utilisateur
                    initVector += "1";
                else
                    initVector += "0";
            }
        }
        
        
        //******************** Affichage des erreurs d'options *****************
        //System.out.println("***************Affichage des erreurs ***************");
        
        if (message == null){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument message est manquant. Veuillez l'indiquer comme suit : -msg \"message\"");
        }
        if (key == null){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument key est manquant. Veuillez l'indiquer comme suit : -key \"key\"");
        }
        if (option == null){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument option est manquant. Veuillez l'indiquer comme suit : -op \"enc / dec\"");
        }
        if (mode == null){
            exit = true;
            System.out.println("\n \n /!\\ Erreur\n");
            System.out.println("- L'argument mode est manquant. Veuillez l'indiquer comme suit : -mode \"ECB / CBC / CFB / OFB / CTR\"");
        }
        
        // Traitement de la valeur de rétroaction
        
        if(mode.equals("CFB") || mode.equals("OFB")){ // test des modes CFB et OFB
            if(progOptions.get("feedBack") == null){
                exit = true;
                System.out.println("\n \n /!\\ Erreur\n");
                System.out.println("- L'argument retroaction : \"-r\" est manquant. Veuillez l'indiquer comme suit : -r \"1 <= r <= 5\"\n");
            }else{
                feedBack = Integer.parseInt(progOptions.get("feedBack"));
                if(feedBack<1 || feedBack>5){ // la valeur doit etre comprise entre 1 et 5
                    exit = true;
                    System.out.println("\n \n /!\\ Erreur\n");
                    System.out.println("- L'argument retroaction : \"-r\" est faux. 1<= r <=5\n");
                }
                
            }
        }
        
        //************** Erreur Manque d'arguments *****************************
        // 8 arguments minimum
        if (args.length < 8){
            exit = true;
            System.out.println("- Vous devez entrer au minimum 4 arguments : -msg -op -key -mode \n");
	}
        
        //******************** Check exeptions *********************************
        if(exit == true){
            return;
        }
        
        //******************** Affichage des options ***************************
        System.out.println("\n**** Affichage des options ****\n");
        System.out.println("Le message est : "+message+"\nLa clef est : "+key+"\nL'option est : "+option+"\nLe mode est : "+mode);
        
        
        
        
        //**********************************************************************************************************
        //***************** Début traitement ***********************************************************************
        //**********************************************************************************************************
        
        
        
        
        
        
        
        //**************** Découpage du message ********************************
        
        int decoupe;                        //Test mode CFB : OFB découpe de taille de la rétroaction
        if(feedBack == 0){
            decoupe = 5;
        }else{
            decoupe = feedBack;
        }
        
        
        String bakMessage = message;        //Sauvegarde message initial
        boolean end = false;                //Booléen de fin du découpage
        String cx;                          //cx + ck variables de traitement des Strings
        String ck = ""; 
        
        ArrayList<String> cutMessage = new ArrayList(); //Liste de messages de taille n bits
        
        
        while ( end == false){
            if (message.length() > decoupe){
                cx = message.substring(0,decoupe);
                cutMessage.add(cx);
                message = message.substring(decoupe);
            }
            else { 
                
                
                // Complémment du dernier bloc de n bit par des 0
                /*for (int l = 0; l < decoupe-message.length(); l++)
                    ck += "0";
                    
                cx = message+ck;*/
                                
                cx = message;
                cutMessage.add(cx);
                message = bakMessage;
                end = true;
            }
        }        
        
        //********************** Chiffrage du message **************************
        
        String finalMessage = "";           //Message final = chiffré/déchiffré
        
        
        //**************************** Traitement ECB **************************
        
        if (mode.equals("ECB")){
            String xorMessageDraft;         //Sortie fonction xorFunction
            for (int m = 0; m < cutMessage.size(); m++ ){
                xorMessageDraft = xorFunction(key, cutMessage.get(m));
                finalMessage += xorMessageDraft;
            }
        }
        
        
        
        //**************************** Traitement CBC **************************
        
        if (mode.equals("CBC")){ 
            String firstTreatmentMessage;   // Message transitoire mi^ci-1
            String partMessage = "";        // Messace ci après traitement Ek(mi^ci-1)
            
            if (option.equals("enc")){      // Traitement option enc = Chiffrage
                for (int i=0; i < cutMessage.size(); i++){
                    if (i == 0){
                        firstTreatmentMessage = xorFunction(initVector, cutMessage.get(i));
                        partMessage = xorFunction(key, firstTreatmentMessage);
                        finalMessage += partMessage;
                    }else{
                        firstTreatmentMessage = xorFunction(partMessage, cutMessage.get(i));
                        partMessage = xorFunction(key, firstTreatmentMessage);
                        finalMessage += partMessage;                    
                    }
                }
            }else if (option.equals("dec")){    // Traitement option dec = Déchiffrage
                for (int i=0; i< cutMessage.size(); i++){
                    if (i == 0){
                        firstTreatmentMessage = xorFunction(key, cutMessage.get(i));
                        partMessage = xorFunction(initVector, firstTreatmentMessage);
                        finalMessage += partMessage;
                    }else{
                        firstTreatmentMessage = xorFunction(key, cutMessage.get(i));
                        partMessage = xorFunction(cutMessage.get(i-1), firstTreatmentMessage);
                        finalMessage += partMessage;                   
                    }
                }
            }
        }
        
        
        //************************** Traitement CFB ****************************
        
        if (mode.equals("CFB")){
            String partMessage ;            // Messace ci après traitement Ek(mi^ci-1)
            String input = initVector;   // Initialisation de la variable Ij (input)
            String output;                // Initialisation de la variable Oj (output)
            String outputCut;              // Variable Oj découpée à la taille de la retroaction
            
            
            for (int i=0; i < cutMessage.size()-1; i++){
                    output = xorFunction(input, key);                                  //calcul de Oj
                    outputCut = output.substring(0,feedBack);                            //découpe de Oj
                    partMessage = xorFunction(outputCut, cutMessage.get(i));               //calcul de Cj
                    finalMessage += partMessage;                                            //ajout de Cj au message final
                    if(option.equals("enc")){
                        input = input.substring(5-feedBack+1, 5)+partMessage;         //calcul de Ij+1 Mode enc
                    }else if (option.equals("dec")){
                        input = input.substring(5-feedBack+1, 5)+cutMessage.get(i);   //calcul de Ij+1 Mode dec
                    }
                }
                //********* Calcul du dernier morceau du message***********
                output = xorFunction(input, key);                                      //calcul de Oj
                outputCut = output.substring(0,feedBack);                                //découpe de Oj
                int lastMessageLength = cutMessage.get(cutMessage.size()-1).length();       //calcul taille de la dernière partie du message découpé
                partMessage = xorFunction(outputCut.substring(0, lastMessageLength), cutMessage.get(cutMessage.size()-1));//calcul de CJ
                finalMessage += partMessage;
            
        }
        
        //************************** Traitement OFB ****************************
        
        if (mode.equals("OFB")){
            String partMessage ;            // Messace ci après traitement Ek(mi^ci-1)
            String input = initVector;   // Initialisation de la variable Ij (input)
            String output;                // Initialisation de la variable Oj (output)
            String outputCut;              // Variable Oj découpée à la taille de la retroaction
            
            for (int i=0; i < cutMessage.size()-1; i++){
                    output = xorFunction(input, key);                                  //calcul de Oj
                    outputCut = output.substring(0,feedBack);                            //découpe de Oj                    
                    partMessage = xorFunction(outputCut, cutMessage.get(i));               //calcul de Cj                    
                    finalMessage += partMessage;                                            //ajout de Cj au message final
                    input = output;                                                    //calcul de Ij+1 tout Mode
                }
                //********* Calcul du dernier morceau du message***********
                output = xorFunction(input, key);                                      //calcul de Oj
                outputCut = output.substring(0,feedBack);                                //découpe de Oj
                int lastMessageLength = cutMessage.get(cutMessage.size()-1).length();       //calcul taille de la dernière partie du message découpé
                partMessage = xorFunction(outputCut.substring(0, lastMessageLength), cutMessage.get(cutMessage.size()-1));//calcul de CJ
                finalMessage += partMessage;
            
        }
        
        //************************** Traitement CTR ****************************
        
        if (mode.equals("CTR")){
            String partMessage ;             // Messace Ci après traitement Ek(mi^ci-1)
            String counter = initVector;     // Initialisation de ctr (counter)
            String output;                   // Initialisation de la variable output
            int decimalCounter; //   toBinaryString(int1)
            for (int i=0; i < cutMessage.size(); i++){
                output = xorFunction(counter, key);                        //découpe de Oj
                partMessage = xorFunction(output, cutMessage.get(i));      //calcul de Cj
                finalMessage += partMessage;
                
                decimalCounter = Integer.parseInt(counter, 2)+1;                // calcul de ctr+1
                if (decimalCounter == 32 )
                    decimalCounter = 0;
                counter = Integer.toBinaryString(decimalCounter);               //ctr+1
                while(counter.length()!=5){                                     //compléter les bits les plus forts par des 5 jusqu'à ctr de taile 5
                    for (int j = 0; j< 5-counter.length(); j++){
                        counter = "0" + counter;
                    }
                }
            }
            
            
        }
        
        //*************************** Affichage fin ****************************
        
        System.out.println("\n**** Résultat ****\n");
        System.out.println("Vecteur d'initialisation :"+initVector);
        System.out.println("message final            :"+finalMessage);
        
    }// !!!!!!!!!!!! FIN MAIN !!!!!!!!!!!
    
}