package org.febrinomahardiva.mobileprogrammingcompose2.ui.screen.main

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.febrinomahardiva.mobpro2s.model.Kelas

class MainViewModel(private val uid: String) : ViewModel() {

    private val db = Firebase.firestore

    fun insert(nama: String) {
        db.collection(Kelas.COLLECTION).add(Kelas(uid, nama))
    }
}