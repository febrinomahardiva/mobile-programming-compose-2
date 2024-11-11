package org.febrinomahardiva.mobpro2s.model

import com.google.firebase.auth.FirebaseUser

data class Mahasiswa(
    val name: String,
    val email: String,
    val photoUrl: String
) {
    constructor() : this("", "", "")

    constructor(user: FirebaseUser) : this(
        user.displayName ?: "",
        user.email ?: "",
        user.photoUrl.toString()
    )

    companion object {
        const val COLLECTION = "mahasiswa"
        const val KELAS_ID = "kelasId"
    }
}
