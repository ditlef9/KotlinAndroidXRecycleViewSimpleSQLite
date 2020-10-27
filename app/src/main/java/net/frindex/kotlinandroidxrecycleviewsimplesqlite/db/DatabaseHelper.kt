package net.frindex.kotlinandroidxrecycleviewsimplesqlite.db

import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context):
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {


    companion object {
        private val DB_NAME = "kotlinandroidxrecycleviewsimplesqlite"
        private val DB_VERSIOM = 2;
    }


    override fun onCreate(db: SQLiteDatabase?) {

        val q1 = "CREATE TABLE categories" +
                "(category_id Integer PRIMARY KEY, " +
                "category_name TEXT)"
        db?.execSQL(q1)

        val q2 = "INSERT INTO categories(category_id, category_name) " +
                "VALUES " +
                "(NULL, 'A')," +
                "(NULL, 'B')," +
                "(NULL, 'C')," +
                "(NULL, 'D')," +
                "(NULL, 'Bread')," +
                "(NULL, 'Cereals')," +
                "(NULL, 'Frozen bread and rolls')," +
                "(NULL, 'Crispbread')," +
                "(NULL, 'Sausage bread and lumps')," +
                "(NULL, 'Dessert and baking')," +
                "(NULL, 'Baking')," +
                "(NULL, 'Biscuit')," +
                "(NULL, 'Cakes')," +
                "(NULL, 'Buns')"
        db?.execSQL(q2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS categories")

        onCreate(db)

    }

    // Query database - insert or update
    fun query(query: String): Boolean {
        val db = this.writableDatabase
        db.execSQL(query)
        db.close()
        return true;
    } // query

    // Count
    fun count(query: String): Int {
        val db = this.writableDatabase
        val numRows =
            DatabaseUtils.longForQuery(db, query, null).toInt()
        db.close()
        return numRows;
    }

    // Get
    fun rawQuery(query: String?): Cursor? {
        val db = this.writableDatabase
        val mCursor: Cursor = db.rawQuery(query, null)
        mCursor?.moveToFirst()
        return mCursor
    }


}