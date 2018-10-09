package cleitonsantos.infnet.com.br.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContatosDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contatos.db";

    public ContatosDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        addContatosTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addContatosTable(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE " + Contrato.Contatos.CONTATOS_TABLE + " (" +
                        Contrato.Contatos.KEY_PDF + " PRIMARY KEY, " +
                        Contrato.Contatos.KEY_NOME + " TEXT UNIQUE NOT NULL," +
                        Contrato.Contatos.KEY_EMAIL + " TEXT UNIQUE NOT NULL," +
                        Contrato.Contatos.KEY_SENHA + " TEXT UNIQUE NOT NULL);"
        );
    }
}
