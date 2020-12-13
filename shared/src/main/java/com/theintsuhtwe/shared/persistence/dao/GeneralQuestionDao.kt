//package com.theintsuhtwe.shared.persistence.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.theintsuhtwe.shared.data.vos.QuestionVO
//
//
//@Dao
//interface GeneralQuestionDao {
//
//        @Insert(onConflict = OnConflictStrategy.REPLACE)
//        fun insertAllQuestion(Question: ArrayList<QuestionVO>)
//
//
//
//        @Delete
//        fun deleteQuestion(note: QuestionVO)
//
//
//        @Query("DELETE FROM questions")
//        fun deleteAll()
//
//
//
//        @Insert(onConflict = OnConflictStrategy.REPLACE)
//        fun insertQuestion(noteVO: QuestionVO)
//
//
//
//}