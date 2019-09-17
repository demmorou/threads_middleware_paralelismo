import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
/**
 * HandleMiddleware
 */
public class HandleMiddleware {

    private final String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/media/123/123";

    public static void main(String[] args) throws IOException {
        
        new HandleMiddleware().getMedia();
        
    }

    public void getMedia() throws IOException{
        ArrayList<Integer> num = new ArrayList<Integer>();
        num.add(1); num.add(2); num.add(3);
         
        String URL_MEDIA = "http://localhost:8080/ServerRestMedia/webresources/media/pegar/12345;10;20;30";
        URL obj = new URL(URL_MEDIA);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
    }
}