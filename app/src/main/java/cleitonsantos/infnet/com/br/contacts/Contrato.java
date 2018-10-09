package cleitonsantos.infnet.com.br.contacts;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contrato {
    private static final String TAG = Contrato.class.getSimpleName();

    // Prevents class from being instantiated.
    private Contrato() {}

    public static final int ALL_ITEMS = -5;
    public static final String COUNT = "count";


    public static final String AUTHORITY =
            "cleitonsantos.infnet.com.br.contacts.provider";

    // Only one public table.
    public static final String CONTENT_PATH = "contatos";

    // Content URI for this table. Returns all items.
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

    // URI to get the number of entries.
    public static final Uri ROW_COUNT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH + "/" + COUNT);

    static final String SINGLE_RECORD_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORDS_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";

    /*
     * Constants for the database are moved out of WordListOpenHelper into the contract.
     * A common way to organize a contract class is to put definitions that are global to your
     * database in the root level of the class. Then create an inner class for each table
     * that enumerates its columns.
     */


    public static final String DATABASE_NAME = "contatos.db";

    /**
     *  Inner class that defines the table contents.
     *
     * By implementing the BaseColumns interface, your inner class can inherit a primary
     * key field called _ID that some Android classes such as cursor adaptors will expect it to
     * have. It's not required, but this can help your database work harmoniously with the
     * Android framework.
     */
    public static abstract class Contatos implements BaseColumns {

        public static final String CONTATOS_TABLE = "contatosTable";

        public static final Uri CONTENT_URI =
                ROW_COUNT_URI.buildUpon().appendPath(CONTENT_PATH).build();

        // These are special type prefixes that specify if a URI returns a list or a specific item
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI  + "/" + CONTENT_PATH;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + CONTENT_PATH;


        // Column names
        public static final String KEY_NOME = "nome";
        public static final String KEY_EMAIL= "email";
        public static final String KEY_SENHA = "senha";
        public static final String KEY_PDF = "pdf";
    }
}

