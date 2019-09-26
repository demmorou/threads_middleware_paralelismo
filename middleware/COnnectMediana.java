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
 * COnnectMediana
 */
public class COnnectMediana extends Thread{
    private String dados;
    private String resposta;
    
    public COnnectMediana(String dados){
        this.dados = dados;
    }

    /**
     * @return the resposta
     */
    public String getResposta() {
        return resposta;
    }

    public void run(){
        System.out.println("Mediana iniciada");
        try {
            // String URL_MEDIA = "http://10.0.0.104:8080/ServerMediana/webresources/mediana/pegar/12345;3;5;6;8;9;12;15;16";
            String URL_MEDIA = "http://192.168.43.134:8080/ServerMediana/webresources/mediana/pegar/12345;"+dados;
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
            // System.out.println("response from server mediana: "+response.toString());
            System.out.println("Mediana terminada, reposta do servidor: "+response.toString());
            resposta =  response.toString();
        } catch (Exception e) {
            System.out.println("Error in sent request");
            resposta = "";
        }
    }
}