package cleitonsantos.infnet.com.br.contacts;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cadastrar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

    }

    private List<TextInputEditText> textEditList = new ArrayList<>();

    public void saveContato(View view){
        //pega referências para as views na interface
        TextInputEditText nomeField = findViewById(R.id.nome_edit_text);
        TextInputEditText emailField = findViewById(R.id.email_edit_text);
        TextInputEditText senhaField = findViewById(R.id.senha_edit_text);
        TextInputEditText telefoneField = findViewById(R.id.telefone_edit_text);
        TextInputEditText celularField = findViewById(R.id.celular_edit_text);
        TextInputEditText cpfField = findViewById(R.id.cpf_edit_text);
        TextInputEditText cidadeField = findViewById(R.id.cidade_edit_text);

        // pega os valores necessários para construir uma QuestionCard a partir das views
        String nomeBody = nomeField.getText().toString();
        String emailBody = emailField.getText().toString();
        String senhaBody = senhaField.getText().toString();
        String telefoneBody = telefoneField.getText().toString();
        String celularBody = celularField.getText().toString();
        String cpfBody = cpfField.getText().toString();
        String cidadeBody = cidadeField.getText().toString();

        if(!isValid(nomeField.getText())|| !isValid(emailField.getText())|| !isValid(senhaField.getText())||
                !isValid(telefoneField.getText())|| !isValid(celularField.getText())|| !isValid(cpfField.getText())|| !isValid(cidadeField.getText())){
            Toast.makeText(this, "Preencha todos os campos!",
                    Toast.LENGTH_LONG).show();

        } else {
            final Contato contato = new Contato(nomeBody,emailBody,senhaBody,telefoneBody,celularBody,cpfBody,cidadeBody);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ActiveContatos");
            databaseReference = databaseReference.push();
            databaseReference.setValue(contato);

            clearForm(nomeField, emailField, senhaField,telefoneField,celularField,cpfField,cidadeField);

            Toast.makeText(this, "Contato salvo com sucesso!",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void clearForm(TextInputEditText a, TextInputEditText b, TextInputEditText c, TextInputEditText d, TextInputEditText e, TextInputEditText f,TextInputEditText g) {
        textEditList.add(0,a);
        textEditList.add(1,b);
        textEditList.add(2,c);
        textEditList.add(3,d);
        textEditList.add(4,e);
        textEditList.add(5,f);
        textEditList.add(6,g);
        for(int i = 0; i<7;i++){
            TextInputEditText v = textEditList.get(i);
            v.setText("");
        }
    }

    private boolean isValid(@Nullable Editable text) {
        return text != null && text.length() > 0 ;
    }


    public void navegarContatos(View view){
        Intent intent = new Intent(this, ContatosList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
