package com.saquib.gmaildrawer2.SqLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        /*
         * The query to create our table
         * It is same as we had in the previous post
         * The only difference here is we have changed the
         * hardcoded string values with String Variables
         * */
        val sql = """CREATE TABLE IF NOT EXISTS $TABLE_SAVED (
    $COLUMN_SAVED_ID varchar(200) NOT NULL CONSTRAINT table_saved_pk PRIMARY KEY
,    $COLUMN_SAVED_NAME varchar(200) NOT NULL
);"""
        sqLiteDatabase.execSQL(sql)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        /*
         * We are doing nothing here
         * Just dropping and creating the table
         * */
        val sql = "DROP TABLE IF EXISTS $TABLE_SAVED;"
        sqLiteDatabase.execSQL(sql)
        onCreate(sqLiteDatabase)
    }

    fun addMatches(id: String?, name: String?, table_name: String?, column_id: String?, column_name: String?): Boolean {
        val contentValues = ContentValues()
        contentValues.put(column_id, id)
        contentValues.put(column_name, name)
        val db = writableDatabase
        return db.insert(table_name, null, contentValues) != -1L
    }

    fun getAllMatches(table_name: String): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $table_name", null)
    }

    fun deleteItemByID(id: String, table_name: String, column_name: String) {
        val sqLiteDatabase = writableDatabase
        sqLiteDatabase.execSQL("DELETE FROM $table_name WHERE $column_name='$id'")
        //sqLiteDatabase.rawQuery("DELETE FROM table_bookmark WHERE column_bookmark_path "= '"+pdfPath+"'");
        sqLiteDatabase.close()
    }

    fun columnExists(id: String): Boolean {
        val sqLiteDatabase = writableDatabase
        val sql = "SELECT EXISTS (SELECT * FROM table_saved WHERE column_saved_id='$id' LIMIT 1)"
        val cursor = sqLiteDatabase.rawQuery(sql, null)
        cursor.moveToFirst()
        // cursor.getInt(0) is 1 if column with value exists
        return if (cursor.getInt(0) == 1) {
            cursor.close()
            sqLiteDatabase.close()
            true
        } else {
            cursor.close()
            sqLiteDatabase.close()
            false
        }
    }

    companion object {
        private const val DATABASE_NAME = "GmailDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_SAVED = "table_saved"
        private const val COLUMN_SAVED_ID = "column_saved_id"
        private const val COLUMN_SAVED_NAME = "column_saved_name"
    }
}