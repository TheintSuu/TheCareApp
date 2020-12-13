package com.theintsuhtwe.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.QuestionVO

@Dao
interface  CaseSummaryDao{
    @Query("SELECT * FROM case_summary")
    fun getAllcase_summary(): LiveData<List<CaseSummaryVO>>

    @Query("SELECT * FROM case_summary WHERE id = :noteId")
    fun getcase_summaryById(noteId: String): LiveData<CaseSummaryVO>

    @Query("DELETE FROM case_summary")
    fun deleteAll()

    @Delete
    fun deletecase_summary(note: CaseSummaryVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertcase_summary(noteVO: CaseSummaryVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllcase_summary(case_summary: ArrayList<CaseSummaryVO>)

}
