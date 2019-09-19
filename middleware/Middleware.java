import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;

/**
 * Middleware
 */
public class Middleware{

    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String line;

    public Middleware(){
        line = "";
        listenSocket();
    }

    public void listenSocket(){
        // Colocar o porto em escuta (Porto = 4500)
        try {
            server = new ServerSocket(8000);
        } catch (IOException e) {
            System.out.println("Could not listen on port 8000");
            System.exit(-1);
        }
        System.out.println("Listen on Port 8000");
        
        try {
            while(true){
                client = server.accept();
                System.out.println("Client Accepted");
                System.out.println("IP: " + client.getInetAddress().getHostAddress() + " Conectado");
                
                // receive from client
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String a = br.readLine();
                String b = a.substring(1, a.length()-2);
                System.out.println("Mensage from client: "+b);
                
                // send to servers (RESTFUL)
                Thread t1 = new Thread( getMedia(b));
                
                String resultMedia = getMedia(b);
                String resultMediana = getMediana(b);

                // send to client
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bw.write(resultMedia+";"+resultMediana);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println("erro 2");
        }
    }

    // computador do helio sera o server da media
    public String getMedia(String dados){
        try {
            // String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/pegar/12345;3;5;6;8;9;12;15;16";
            String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/pegar/12345;"+dados;
            URL obj = new URL(URL_MEDIA);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            // System.out.println("response code: "+responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println("response from server media: "+response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error in sent request");
            return "";
        }
    }

    // computador de chaguinha sera o server da mediana
    public String getMediana(String dados){
        try {
            // String URL_MEDIA = "http://10.0.0.104:8080/ServerMediana/webresources/mediana/pegar/12345;3;5;6;8;9;12;15;16";
            String URL_MEDIA = "http://10.0.0.104:8080/ServerMediana/webresources/mediana/pegar/12345;"+dados;
            URL obj = new URL(URL_MEDIA);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("response code: "+responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println("response from server mediana: "+response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error in sent request");
            return "";
        }
    }
}