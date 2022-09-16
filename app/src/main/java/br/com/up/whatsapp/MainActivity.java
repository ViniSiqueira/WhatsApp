package br.com.up.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText inputTextNumber;
    private TextInputEditText inputTextMessage;
    private Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextNumber = findViewById(R.id.input_text_fone);
        inputTextMessage = findViewById(R.id.input_text_mensagem);
        buttonEnviar = findViewById(R.id.button_add_number);

        buttonEnviar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        enviarMensagem();
                    }
                });
    }

    private void enviarMensagem(){

        String numero = inputTextNumber.getText().toString();

        if(numero.isEmpty()){
            inputTextNumber.setError("Favor inserir o número. Exemplo: 55 + DDD + Numero");
            return;
        }

        if ((numero.contains("55") != false) && (numero.length() <= 10)){
            numero = "55" + numero;
        }

        String mensagem = inputTextMessage.getText().toString();

        if(mensagem.isEmpty()){
            inputTextMessage.setError("Favor inserir uma mensagem");
            return;
        }

        mensagem = mensagem.replace( " ", "%20" );
        String url = numero + "?text=" + mensagem;

        Uri webpage = Uri.parse("https://wa.me/"+url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);

    }

}