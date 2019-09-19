package como.sd.restful;

import android.content.AsyncQueryHandler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Socket;
import java.util.Random;

public class InterfaceActivity extends AppCompatActivity implements IAsyncHandler {
    TextView media, mediana, msg_gerado;
    Button btn_enviar;
    private EditText qtd_n;
    StringBuilder valor = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qtd_n = (EditText) findViewById(R.id.qtd_n);

        media = (TextView) findViewById(R.id.media);
        mediana = (TextView) findViewById(R.id.mediana);
        msg_gerado = (TextView) findViewById(R.id.mensagem_gerado);
        btn_enviar = (Button) findViewById(R.id.botaoEnviar);


        //Cria a tarefa para ser executada em background

        //Aqui eu chamo a execução. Como é em background, por isso citei de usar um EventBus ou outra
        //forma de poder recuperar a informação. Não é a melhor forma, mas de forma ilustrativa, vou
        //criar um método para receber a informação e aproveitar na activity.

    }

    @Override
    public void postResult(String result) {
        //Neste método você pega o resultado da asynctask e aproveita de alguma forma
        Log.d("iai",result);
        media.setText(result.split(";")[0]);
        mediana.setText(result.split(";")[1]);
        msg_gerado.setText("Seu resultado foi gerado com sucesso =)");
    }

    public void enviar(View view){
        try {
            Conexao task = new Conexao(InterfaceActivity.this);
            task.execute(valor.toString());
        }catch (Exception ignored){
        }

    }

    public void gerarCadeia(View v){
        Random gerador = new Random();
        int i;
        valor.delete(0, valor.length());
        for(i = 0; i< Integer.valueOf(qtd_n.getText().toString()); i++){
            valor.append(gerador.nextInt(30000)).append(";");
        }
        Log.d("gerado", valor.substring(0, 10));
        msg_gerado.setText("Cadeia de numeros Gerada: "+i+" números.");
        btn_enviar.setEnabled(true);
    }
}

interface IAsyncHandler {
    void postResult(String result);
}