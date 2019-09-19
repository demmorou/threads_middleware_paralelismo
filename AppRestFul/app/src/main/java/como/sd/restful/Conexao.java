package como.sd.restful;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class Conexao extends AsyncTask<String, Integer, String> {
    private static final String hostname = "10.0.0.108";
    private static final int portaServidor = 8000;

    private IAsyncHandler mHandler;

    public Conexao(IAsyncHandler mHandler) {
        this.mHandler = mHandler;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Socket socket = new Socket(hostname, portaServidor);

            //dados enviados para o servidor
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //Estou usando Arrays.toString(), somente para ele pegar todas as strings que passar no
            //método.
            bw.write(Arrays.toString(params));
            bw.newLine();
            bw.flush();

            //dados reebidos pelo servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String retorno =  br.readLine(); //retornar ok
            socket.close();

            return retorno;
        }
        catch(IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Aqui você utiliza o retorno do doInBackground
        //Aqui é interessante usar um EventBus, ou alguma outra classe para poder enviar essa
        //informação para a interface.
        mHandler.postResult(result);
    }
}


