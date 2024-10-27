package org.febrinomahardiva.mobpro2s.model

data class Kelas(
    val dosenId : String,
    val nama : String
) {
    constructor() : this("", "")

    companion object {
        const val COLLECTION = "kelas"
        const val DOSEN_ID = "dosenId"
        const val NAMA = "nama"
    }
}
