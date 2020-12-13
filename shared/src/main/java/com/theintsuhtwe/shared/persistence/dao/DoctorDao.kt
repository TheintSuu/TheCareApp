package com.theintsuhtwe.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.theintsuhtwe.shared.data.vos.DoctorVO

@Dao
interface DoctorDao {
    @Query("SELECT * FROM  doctor")
    fun getAllDoctor():  LiveData<List<DoctorVO>>

    @Query("SELECT * FROM doctor WHERE email = :noteId")
    fun getDoctorByemail(noteId: String): LiveData<DoctorVO>

    @Query("DELETE FROM doctor")
    fun deleteAll()

    @Delete
    fun deleteDoctor(note: DoctorVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(noteVO: DoctorVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDoctor(Doctor: List<DoctorVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewDoctor(doctorVO: DoctorVO)




}
