package com.theintsuhtwe.shared.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.theintsuhtwe.shared.data.vos.Patient

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    fun getAllpatient(): LiveData<List<Patient>>

    @Query("SELECT * FROM patient WHERE email = :noteId")
    fun getpatientByEmail(noteId: String): LiveData<Patient>

    @Query("DELETE FROM patient")
    fun deleteAll()

    @Delete
    fun deletepatient(note: Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertpatient(noteVO: Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllpatient(patient: List<Patient>)

}