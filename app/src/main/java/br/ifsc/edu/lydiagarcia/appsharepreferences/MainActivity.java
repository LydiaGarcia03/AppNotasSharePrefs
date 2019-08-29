package br.ifsc.edu.lydiagarcia.appsharepreferences;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSalvar;
    private TextView txtResultado;
    private TextInputEditText editTxt;

    // Arquivo XML
    private static final String PREFERENCES_FILE = "Notas";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnSalvar = findViewById(R.id.btnSalvar);
        editTxt = findViewById(R.id.edit);
        txtResultado = findViewById(R.id.txtResultado);

        //btnSalvar.setOnClickListener(clickSalvar);
        preferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("teste", "Texto texto texto");
        editor.commit();

        preferences.getString("teste", "Erro ao recuperar a string");

        Toast.makeText(this, preferences.getString("teste", "Erro ao recuperar a string"), Toast.LENGTH_LONG).show();

    }


    public void salvarTexto(View view) {

        if (editTxt.getText().toString().trim().equals("")){
            // getResources() está chamando notas vazias para internacionalização
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.notasVazias), Toast.LENGTH_LONG).show();
        } else{
            editor.putString("nota", editTxt.getText().toString());
            editor.commit();
        }

    }

    public void recuperarValor(View view) {

        if (preferences.contains("nota")){
            txtResultado.setText(preferences.getString("nota", "").toString());
        } else{
            txtResultado.setText(getResources().getString(R.string.default_menssage));
        }

    }
}
