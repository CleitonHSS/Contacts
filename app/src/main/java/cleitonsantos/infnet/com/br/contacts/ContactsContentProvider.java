package cleitonsantos.infnet.com.br.contacts;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ContactsContentProvider extends ContentProvider {
    private static final int CONTATO = 200;
    private static final int CONTATO_PDF = 201;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private ContatosDbHelper mOpenHelper;


    @Override
    public boolean onCreate() {
        mOpenHelper = new ContatosDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor retCursor;
        switch(sUriMatcher.match(uri)){
            case CONTATO:
                retCursor = db.query(
                        Contrato.Contatos.KEY_NOME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CONTATO_PDF:
                Long _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        Contrato.Contatos.KEY_NOME,
                        projection,
                        Contrato.Contatos._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(sUriMatcher.match(uri)){
            case CONTATO:
                return Contrato.Contatos.CONTENT_TYPE;
            case CONTATO_PDF:
                return Contrato.Contatos.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static UriMatcher buildUriMatcher(){
        String content = Contrato.AUTHORITY;

        // All paths to the UriMatcher have a corresponding code to return
        // when a match is found (the ints above).
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(content, Contrato.CONTENT_PATH, CONTATO);
        matcher.addURI(content, Contrato.CONTENT_PATH + "/#", CONTATO_PDF);

        return matcher;
    }
}
