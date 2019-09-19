import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Middleware
 */
public class Middleware{

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket servidor = new ServerSocket(8000);
            System.out.println("Servidor ouvindo a porta 8000");
            ArrayList<Thread> slotes = new ArrayList<Thread>();

            while(true){
                Socket cliente = servidor.accept();
                if(slotes.isEmpty()){
                    HandleMiddleware handleMiddleware = new HandleMiddleware(cliente);
                    Thread c1 = new Thread(handleMiddleware);
                    c1.start();
                    slotes.add(c1);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}