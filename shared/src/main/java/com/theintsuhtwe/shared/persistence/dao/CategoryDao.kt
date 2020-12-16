package com.theintsuhtwe.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.theintsuhtwe.shared.data.vos.CategoryVO

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<List<CategoryVO>>

    @Query("SELECT * FROM category WHERE name = :noteId")
    fun getCategoryById(noteId: String): LiveData<CategoryVO>

    @Query("DELETE FROM category")
    fun deleteAll()

    @Delete
    fun deleteCategory(note: CategoryVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(noteVO: CategoryVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategory(category: List<CategoryVO>)

}


