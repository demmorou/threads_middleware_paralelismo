import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
/**
 * HandleMiddleware
 */
public class HandleMiddleware  extends Thread{

    private final String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/media/123/123";
    private Socket cliente;

    public HandleMiddleware(Socket cliente){
        this.cliente = cliente;
    }

    public void run() {
        // System.out.println("IP: " + this.cliente.getInetAddress().getHostAddress() + " Conectado");
        // System.out.println("Enviando dados a API Rest");

        InputStream is;
        
        try {
            is = this.cliente.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            while(true){
                String a = ois.readObject().toString();
                // System.out.println(a);
                String response = getMedia(a);
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println("Error listen input");
        }
    }

    public static void main(String[] args) throws IOException {
        
        
    }

    public void getMediana(String dados){
        try {
            String URL_MEDIA = "http://192.168.43.134:8080/ServerMediana/webresources/mediana/pegar/12345;";
            URL obj = new URL(URL_MEDIA);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            // System.out.println(responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Error in sent request");
        }
    }

    public String getMedia(String dados){
        try {
            String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/pegar/12345;"+dados;
            URL obj = new URL(URL_MEDIA);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            // System.out.println(responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            // System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error in sent request");
            return "";
        }
    }
}