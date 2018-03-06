package ch.fp.fp_kks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * the DatabaseHelper is a class for doing all things with a database
 * <p>
 * Created by Patrick on 24.08.2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME1 = "QuestionAnswer";
    private static final String COL11 = "ID";
    private static final String COL12 = "Question";
    private static final String COL13 = "Answer";
    private static final String COL14 = "KategoryFk";

    private static final String TABLE_NAME2 = "Kategory";
    private static final String COL21 = "ID";
    private static final String COL22 = "Name";

    Cursor data;

    /**
     * @param context;
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME1, null, 1);
    }

    /**
     * creataes the database with te specific colums
     *
     * @param db;
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COL22 + " TEXT1)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + TABLE_NAME1 + " (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COL12 + " TEXT1, " + COL13 + " TEXT1, " + COL14 + " INTEGER, Foreign key(KategoryFk) references Kategory(ID))";
        db.execSQL(createTable);
    }

    /**
     * delets the database
     *
     * @param db;
     * @param i;
     * @param i1;
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    /**
     * Adding data to FragenAntworten Table with item1, item2 and FK
     *
     * @param item1
     * @param item2
     * @param KarteiFk
     * @return
     */
    public boolean addData(String item1, String item2, int KarteiFk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL12, item1);
        contentValues.put(COL13, item2);
        contentValues.put(COL14, KarteiFk);

        Log.d(TAG, "addData: Adding " + item1 + " and " + item2 + " and " + KarteiFk + " to " + TABLE_NAME1);

        long result = db.insert(TABLE_NAME1, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * givs all data from the database back
     */
    public Cursor getEditData(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        data = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " where " + COL11 + " = " + ID, null);
        return data;
    }

    /**
     * Selects the Ancer and Question from FrageAntwort where KarteienFk is XXX
     *
     * @param KategoryFk
     * @return
     */
    public Cursor getData(Integer KategoryFk) {
        SQLiteDatabase db = this.getWritableDatabase();
        data = db.rawQuery("SELECT Question, Answer FROM " + TABLE_NAME1 + " where KategoryFk = " + KategoryFk, null);
        return data;
    }

    /**
     * wriths data in the database in Karteien
     *
     * @param text;
     */
    public boolean addDataKartei(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL22, text);

        Log.d(TAG, "addData: Adding " + text + " to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * get the ID where the Karteiname is XXX
     *
     * @param Name
     * @return
     */
    public Cursor getSavedKartei(String Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COL21 + " FROM " + TABLE_NAME2 + " WHERE " + COL22 + " = '" + Name + "'", null);

        if (data != null) {
            data.moveToFirst();
        }
        Background.ids = data.getInt(0);
        return data;
    }

    /**
     * gets the ID where the Text is XXX
     *
     * @param text
     * @return
     */
    public Cursor getDataForDelete(String text) {
        System.out.println(text);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COL11 + " FROM " + TABLE_NAME1 + " WHERE " + COL12 + " = '" + text + "'", null);
        if (data != null) {
            data.moveToFirst();
        }
        Background.ids = data.getInt(0);
        return data;
    }

    /**
     * gets all Karteien
     *
     * @return
     */
    public Cursor getKarteien() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        return data;
    }

    /**
     * update a row with the new text
     */
    public void getUpdate(String text1, String text2, Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL12, text1);
        cv.put(COL13, text2);
        db.update(TABLE_NAME1, cv, "" + COL11 + "=" + ID, null);
        db.close();
    }


    /**
     * deletes data with a specific id
     */
    public void deleteDate(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " WHERE " + COL11 + " = " + ID);
        db.close();
    }

    /**
     * deletes an Kategory
     *
     * @param ID
     */
    public void deleteKartei(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE " + COL21 + " = " + ID);
        db.close();
        deleteFKs(ID);
    }

    /**
     * deletes all data with the FK
     *
     * @param ID
     */
    public void deleteFKs(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " WHERE " + COL14 + " = " + ID);
        db.close();
    }
}
