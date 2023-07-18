package sg.rp.edu.rp.c346.id22038845.ndp_songs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INT,"
                + COLUMN_STARS + " INT)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public void insertSong(String title, String singers, String year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR,Integer.parseInt(year));
        values.put(COLUMN_STARS,stars);
        db.insert(TABLE_SONG, null, values);
        db.close();
    }

    public int updateSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGERS, song.getSingers());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_STARS, song.getStars());
        String condition = COLUMN_ID + "= ?"; //? one variable/parameter to check
        String[] args = {String.valueOf(song.getId())}; //if more then one will be ? and name = ?, arg is the value to be place in the args, if using condition with null arguments it is bad practice as it will make the code vulnerable to sql injection
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getSongContent() {
        ArrayList<String> songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int colType = cursor.getType(1);
                if(colType == Cursor.FIELD_TYPE_STRING) {
                    songs.add(cursor.getString(1));
                }else if(colType == Cursor.FIELD_TYPE_INTEGER){
                    songs.add(String.valueOf(cursor.getInt(1)));
                }

            } while (cursor.moveToNext());
        }
        //close connection
        cursor.close();
        db.close();

        return songs;
    }

    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db =this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        Cursor cursor;
        cursor =db.query(TABLE_SONG, columns, null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(0);
                String title =cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id,title,singers,year,stars);
                songs.add(song);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getAllSongs(int filter){
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db =this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        String condition = COLUMN_STARS + "= ?";
        String[] args = {filter + ""};
        Cursor cursor;
        cursor =db.query(TABLE_SONG, columns, condition,args,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(0);
                String title =cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id,title,singers,year,stars);
                songs.add(song);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getSongByYr(int year){ //int year
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db =this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        String condition = COLUMN_YEAR + "= ?";
        String[] args = {year + ""};
        Cursor cursor;
        cursor =db.query(TABLE_SONG, columns, condition,args,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(0);
                String title =cursor.getString(1);
                String singers = cursor.getString(2);
                int year2 = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id,title,singers,year2,stars);
                songs.add(song);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}
