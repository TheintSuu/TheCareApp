package com.theintsuhtwe.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theintsuhtwe.shared.data.vos.QuestionVO

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(QuestionVO: QuestionVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestionList(generalQuestionList: List<QuestionVO>)

    @Query("select * from questions")
    fun getAllQuestionData(): LiveData<List<QuestionVO>>

    @Query("select * from questions WHERE id = :id")
    fun getAllQuestion(id: String): LiveData<QuestionVO>

    @Query("DELETE FROM questions")
    fun deleteAllQuestion()
}