package com.facens.ac1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BancoHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "despesas.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "despesas";

    public BancoHelper(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao TEXT," +
                "categoria TEXT," +
                "valor INTEGER," +
                "data INTEGER," +
                "pagamento TEXT," +
                "foipaga INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void inserir(Despesa d){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put("descricao", d.getDescricao());
        v.put("categoria", d.getCategoria());
        v.put("valor", d.getValor());
        v.put("data", d.getData());
        v.put("pagamento", d.getPagamento());
        v.put("foipaga", d.isPaga() ? 1 : 0);

        db.insert(TABLE, null, v);

    }

    public Cursor listar() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE, null);
    }

    public void excluir(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id=?", new String[]{String.valueOf(id)});
    }

    public long atualizar(int id, String descricao, String categoria, String pagamento){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(descricao, descricao);
        v.put(categoria, categoria);
        v.put(pagamento, pagamento);
        return db.update(TABLE,v, id + "=?", new String[]{String.valueOf(id)});
    }

}
