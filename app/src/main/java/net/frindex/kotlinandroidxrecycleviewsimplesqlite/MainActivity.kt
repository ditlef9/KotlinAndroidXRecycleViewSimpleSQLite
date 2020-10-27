package net.frindex.kotlinandroidxrecycleviewsimplesqlite

/**
 *
 * File: MainActivity.kt
 * Version 1.0
 * Date 24.10.2020
 * Copyright (c) 2020 S Ditlefsen
 * License: http://opensource.org/licenses/gpl-license.php GNU Public License
 *
 */

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import net.frindex.kotlinandroidxrecycleviewsimplesqlite.db.DatabaseHelper
import net.frindex.kotlinandroidxrecycleviewsimplesqlite.recyclerViewAdapters.IdTitleAdapter
import net.frindex.kotlinandroidxrecycleviewsimplesqlite.recyclerViewAdapters.IdTitleDataClass
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // Class variables
    var db: DatabaseHelper? = null
    private lateinit var categoriesList: ArrayList<IdTitleDataClass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DB :: Open Chrome and go to URL chrome://inspect/#devices
        Stetho.initializeWithDefaults(this)

        // List categories
        listCategories()


    } // onCreate

    /*- List categories -------------------------------------------------------------------------- */
    fun listCategories(){

        // DB
        db = DatabaseHelper(this)

        // Fetch categories
        var categoriesCursor: Cursor? = db!!.rawQuery("SELECT category_id, category_name FROM categories WHERE category_id > 4")
        var categoriesSize: Int = categoriesCursor!!.count
        Log.d("listCategories()", "categoriesSize=" + categoriesSize)

        // Add a list of categories
        categoriesList = ArrayList<IdTitleDataClass>()
        while (categoriesCursor.moveToNext()) {
            val categoryId = categoriesCursor.getInt(0)
            val categoryName = categoriesCursor.getString(1)
            Log.d("listCategories()", "categoryId=" + categoryId + " categoryName=" + categoryName)
            categoriesList.add(IdTitleDataClass(categoryId, categoryName))

        }

        // Add to list
        recycler_view.adapter = IdTitleAdapter(categoriesList) {
            categoriesList[it]
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = IdTitleAdapter(categoriesList) {
            listOnClick(it.toInt())
        }

    } // listCategories

    /*- List on click ---------------------------------------------------------------------------- */
    fun listOnClick(itemID: Int){
        Toast.makeText(this@MainActivity, "Item clicked has ID " + itemID, Toast.LENGTH_SHORT).show()
    } // listOnClick
} // MainActivity