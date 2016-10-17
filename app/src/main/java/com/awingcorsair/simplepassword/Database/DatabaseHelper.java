package com.awingcorsair.simplepassword.Database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.awingcorsair.simplepassword.Model.Record;
import com.awingcorsair.simplepassword.R;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mao on 2016/9/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simpleDatabase";
    private static final String TABLE_NAME = "record";
    private static final int version = 1;
    private static final String KEY_ID = "id";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NOTE = "note";
    private static String database_password = "miaomiaomiao";
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+"("+KEY_ID+" integer primary key autoincrement,"+KEY_WEBSITE+" text not null,"+KEY_USERNAME+" text not null,"+KEY_PASSWORD+" text not null,"+KEY_NOTE+");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase(database_password);
        ContentValues values = new ContentValues();
        values.put(KEY_ID,record.getId());
        values.put(KEY_WEBSITE, record.getWebsite());
        values.put(KEY_USERNAME, record.getUsername());
        values.put(KEY_PASSWORD, record.getPassword());
        values.put(KEY_NOTE, record.getNote());
        Log.d("database",values.toString());

        long rowid=db.insert(TABLE_NAME, null, values);
        Log.d("database",""+rowid);

        db.close();
    }

    //    public void getRecord(Record record){
//        SQLiteDatabase db=this.getWritableDatabase(database_password);
//        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_WEBSITE,KEY_USERNAME,KEY_PASSWORD,KEY_NOTE},KEY_NAME+=?,new String[]{name},null,null,null,null);
//    }
    public int getRecordCounts() {
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase(database_password);
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count=cursor.getCount();
        cursor.close();

        return count;
    }

    public List<Record> getAllRecord(){
        List<Record> recordList=new ArrayList<Record>();
        String selectQuary="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase(database_password);
        Cursor cursor=db.rawQuery(selectQuary,null);
        if(cursor.moveToFirst()){
            do{
                Record record=new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setWebsite(cursor.getString(1));
                record.setUsername(cursor.getString(2));
                record.setPassword(cursor.getString(3));
                record.setNote(cursor.getString(4));
                recordList.add(record);
            }while (cursor.moveToNext());
        }
        //close cursor
        cursor.close();
        return recordList;
    }

    public int updateRecord(Record record){
        SQLiteDatabase db=this.getWritableDatabase(database_password);
        ContentValues values=new ContentValues();
        values.put(KEY_WEBSITE,record.getWebsite());
        values.put(KEY_USERNAME,record.getUsername());
        values.put(KEY_PASSWORD,record.getPassword());
        values.put(KEY_NOTE,record.getNote());
        Log.d("update","1:"+values);
        int result=db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(record.getId())});
        Log.d("update","2:"+result);
        return result;

    }

//    public void deleteRecord(Record record){
//        SQLiteDatabase db=this.getWritableDatabase(database_password);
//        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(record.getId())});
//        db.close();
//    }

    public void deleteRecord(int id){
        SQLiteDatabase db=this.getWritableDatabase(database_password);
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
