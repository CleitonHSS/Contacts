package cleitonsantos.infnet.com.br.contacts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatosList extends AppCompatActivity {

    public ContatosAdapter adapter;
    public RecyclerView recyclerView;
    public List<Contato> contatosList;
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    public int cont = 1;
    @Override
    public void postponeEnterTransition() {
        super.postponeEnterTransition();
    }

    Boolean dataNull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ActiveContatos");

        ChildEventListener child =  myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                contatosList.add( dataSnapshot.getValue(Contato.class));
                adapter.notifyDataSetChanged();
                TextView textview = findViewById(R.id.no_contact);
                if(dataSnapshot.child("nome").exists()){
                    textview.setText("");
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        contatosList = new ArrayList<>();
        //textview.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContatosAdapter(contatosList);
        recyclerView.setAdapter(adapter);



    }

    public void teste(){

        for (int i = 0; i<10; i++) {
            contatosList.add(new Contato ("Cleiton", "Cleiton@gmail", "123456", "21344334","12441221","1232323","Rio"));
        }
    }

    public void getData() {
        myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    contatosList.add( dataSnapshot.getValue(Contato.class));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
        });
    }


    public void navegarCadastro(View view){
        Intent intent = new Intent(this, Cadastrar.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
