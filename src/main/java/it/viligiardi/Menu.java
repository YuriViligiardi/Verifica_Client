package it.viligiardi;

import java.util.Scanner;

public class Menu {
    //metodo per prendere un input da tastiera
    public String ottieniStringa(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Client: " + input);
        return input;
    }

    //menu di scelta
    public int ottieniscelta(){
        int scelta;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("----------------------------------------");
            System.out.println("1) Inserire una nota da mamorizzare");
            System.out.println("2) Ricevere la lista delle note");
            System.out.println("3) cancellare una nota");
            System.out.println("0) Esci");
            System.err.println("Scegli l'operazione che desideri:");
            scelta = scanner.nextInt();
            System.out.println("Hai scelto L'operazione " + scelta);
            System.out.println("----------------------------------------");
        } while (!(scelta>= 0 || scelta<=3));
        
        return scelta;
    }

    //metodo che guarda cosa ha scelto l'utente e returna la stringa adeguata da inviare al server
    public String operations(int scelta){
        switch (scelta) {
            case 1:
            System.out.println("Inserisci una nota: ");
                String sOut = ottieniStringa();
                return sOut;
            case 2:
                System.out.println("LISTA:");
                return "?";
            case 3: 
                System.out.println("DELETE:");
                return "-";
            default:
                System.out.println("DISCONESSIONE DAL SERVER");
                return "!";
        }
    }
}
