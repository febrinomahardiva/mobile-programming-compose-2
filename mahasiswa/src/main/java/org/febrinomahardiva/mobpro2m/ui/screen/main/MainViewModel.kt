package org.febrinomahardiva.mobpro2m.ui.screen.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import org.febrinomahardiva.mobpro2s.model.Kelas
import org.febrinomahardiva.mobpro2s.model.Mahasiswa

class MainViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val dataKelasId = mutableStateListOf<String>()
    val dataKelas = mutableStateListOf<String>()

    fun getDataKelas() {
        db.collection(Kelas.COLLECTION)
            .orderBy(Kelas.NAMA)
            .get()
            .addOnCompleteListener { handleKelas(it) }
    }

    private fun handleKelas(task: Task<QuerySnapshot>) {
        dataKelas.clear()
        dataKelasId.clear()

        if (task.isSuccessful) {
            task.result.documents.forEach { kelas ->
                val nama = kelas.getString(Kelas.NAMA) ?: return@forEach
                dataKelas.add(nama)
                dataKelasId.add(kelas.id)
            }
        }
    }

    fun simpanData(kelasIndex: Int, user: FirebaseUser) {
        val kelasId = dataKelasId[kelasIndex]

        db.runBatch {
            db.collection(Mahasiswa.COLLECTION)
                .document(user.uid)
                .set(mapOf(Mahasiswa.KELAS_ID to kelasId))

            db.collection(Kelas.COLLECTION)
                .document(kelasId)
                .collection(Mahasiswa.COLLECTION)
                .document(user.uid)
                .set(Mahasiswa(user))
        }
    }
}