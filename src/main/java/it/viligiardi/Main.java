package it.viligiardi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        int port = 3000;
        Socket s = new Socket("localhost", port);
        System.out.println("Client connesso al server");
        Menu m = new Menu();
        int scelta;

        do {
            scelta = m.ottieniscelta();
            String stringaInviare = m.operations(scelta);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                out.writeBytes(stringaInviare + "\n");

                String stringaRicevuta;
                if (scelta == 2) {
                    //funzioni del client per la stampa della lista di note mandata dal server
                    do {
                        stringaRicevuta = in.readLine();
                        System.out.println(stringaRicevuta);
                    } while (!(stringaRicevuta.equals("@")));
                    System.out.println("Adesso puoi continuare ad inserire altre note");

                } else if (scelta == 3) {
                    //funzioni del client per la cancellazione di una nota
                    stringaRicevuta = in.readLine();
                    System.out.println("Server: " + stringaRicevuta);
                    System.out.println("Inserisci la nota da eliminare: ");
                    String ClientDelete = m.ottieniStringa();
                    out.writeBytes(ClientDelete + "\n");
                    String serverDelete = in.readLine();
                    System.out.println("Cancellazione: " + serverDelete);
                    
                } else {
                    //funzioni del client per inviare una nota al server
                    stringaRicevuta = in.readLine();
                    System.out.println("Server: " + stringaRicevuta);
                }
                
            } catch (Exception e) {
                System.out.println("Errore, comunicazione fallita");
            }
        } while (!(scelta == 0));

        s.close();
    }
}