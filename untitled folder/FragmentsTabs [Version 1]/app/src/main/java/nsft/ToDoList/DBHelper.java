package nsft.ToDoList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final static String Tag = "EDMDev";

    // DECLARING THE VARIABLES FOR THE DB  //
    private static String DB_Name   = "EDMTDev";
    private static int    DB_Ver    = 1;
    private static String DB_Table  = "TASK";
    private static String DB_Col    = "TaskName";

    // DECLARING THE CONTEXT //
    private Context context;

    public DBHelper(Context context) {

        super(context, DB_Name, null, DB_Ver);
        Log.d(Tag, "CONSTRUCTOR OF THE DATABASE CREATED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL QUERY //
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY, %s TEXT)", DB_Table, DB_Col);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //
        String query = String.format("DELETE TABLE IF EXIST %s", DB_Table);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertTask(String task) {
        Log.d("DbHelper", "INSERTING TASK '" + task + "'");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // PUTTING THE DATA INTO ITS PERSPECTIVE COLUMN //
        values.put(DB_Col, task);

        //
        db.insertWithOnConflict(DB_Table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        // CLOSING THE DATABASE //
        db.close();
    }

    public void deleteTask(String task) {
        Log.d("DbHelper", "DELETING TASK '" + task + "'");
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETING THE TASK FROM THE DATABASE AND FROM THE TABLE AND COLUMN //
        db.delete(DB_Table, DB_Col + "- ?", new String[]{task});

        // CLOSING THE DATABASE //
        db.close();
    }

    public ArrayList<String> getTaskList() {
        // NEW OBJECTS
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Log.d("DbHelper", "GETTING THE ARRAYLIST '" + taskList + "'");

        //
        Cursor cursor = db.query(DB_Table, new String[]{DB_Col}, null, null, null, null, null);

        //
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_Col);
            taskList.add(cursor.getString(index));
        }

        // CLOSING THE CURSOR AND THE DATABASE //
        cursor.close();
        db.close();

        // RETURNING THE TASKLIST //
        return taskList;
    }
}
