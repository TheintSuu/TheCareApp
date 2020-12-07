package com.theintsuhtwe.shared.persistence.dao

import androidx.room.*
import com.theintsuhtwe.shared.data.vos.DoctorVO

//@Dao
//interface DoctorDao {
//    @Query("SELECT * FROM  doctor")
//    fun getAllDoctor(): List<DoctorVO>
//
//    @Query("SELECT * FROM doctor WHERE id = :noteId")
//    fun getDoctorById(noteId: Int): DoctorVO
//
//    @Query("DELETE FROM doctor")
//    fun deleteAll()
//
//    @Delete
//    fun deleteDoctor(note: DoctorVO)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertDoctor(noteVO: DoctorVO)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllDoctor(Doctor: List<DoctorVO>)
//
//}
