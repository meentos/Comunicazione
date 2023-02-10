
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MC
 * @version dic 2022
 */
public class Server {

    ServerSocket connection;
    Socket clientSocket;
    int porta;
    InputStream is;
    OutputStream os;

    public Server(int porta) {
        this.porta = porta;
        try {
            connection = new ServerSocket(porta);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void attendi() {

        try {
            clientSocket = connection.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leggi() {

        String messaggioRicevuto;

        try {

            BufferedReader br = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
            messaggioRicevuto = br.readLine();
            System.out.println("CLIENT: " + messaggioRicevuto);
            if (messaggioRicevuto.equals("ciao")) {
                try {
                    //Scrittura sullo stream di output
                    os = clientSocket.getOutputStream();
                    os.write("ciao mentos\n".getBytes());
                    //Invio del messaggio al server
                    os.flush();

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Errore nella scrittura e/o nell'invio dei dati al server!");
                }
                
            }else{
                                   //Scrittura sullo stream di output
                    os = clientSocket.getOutputStream();
                    os.write("non cpaisco cosa mi dici\n".getBytes());
                    //Invio del messaggio al server
                    os.flush(); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
