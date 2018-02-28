package ch.fp.fp_kks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * the DatabaseHelper is a class for doing all things with a database
 *
 * Created by Patrick on 24.08.2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "PGKKS";
    private static final String COL1 = "ID";
    private static final String COL2 = "Text1";
    private static final String COL3 = "Text2";

    Cursor data;

    /**
     * @param context;
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    /**
     * creataes the database with te specific colums
     *
     * @param db;
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT1, " + COL3 +" TEXT2)";
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * wriths data in the database
     *
     * @param item1;
     * @param item2;
     */
    public boolean addData(String item1, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);

        Log.d(TAG, "addData: Adding " + item1 + " and " + item2 +" to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * givs all data from the database back
     *
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    /**
     * gives the data with a specific id back
     *
     */
    public Cursor getDataQuestion() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 +" FROM " + TABLE_NAME + " WHERE ID = " + Background.ids;
        data = db.rawQuery(query, null);
        return data;
    }

    /**
     * gets the id with a specific text back
     *
     */
    public Cursor getDataAnswer() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL3 +" FROM " + TABLE_NAME + " WHERE ID = " + Background.ids;
        data = db.rawQuery(query, null);
        return data;
    }

    /**
     * update a row with the new text
     *
     * does not work correct
     */
    public void getUpdate() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, Background.text);
        db.update(TABLE_NAME, cv, "" + COL1 + "=" + Background.ids, null);
        db.close();
    }

    /**
     * deletes data with a specific id
     */
    public void deleteDate() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + Background.ids);
        db.close();
    }
}
