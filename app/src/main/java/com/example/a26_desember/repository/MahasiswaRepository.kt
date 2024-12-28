package com.example.a26_desember.repository

import com.example.a26_desember.model.Mahasiswa
import com.example.a26_desember.service.MahasiswaService
import java.io.IOException

interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswabyNim(nim: String): Mahasiswa

    class NetworkMahasiswaRepository(
        private val mahasiswaApiService: MahasiswaService
    ) : MahasiswaRepository {
        override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
            mahasiswaApiService.insertMahasiswa(mahasiswa)
        }

        override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
            mahasiswaApiService.updateMahasiswa(nim, mahasiswa)
        }

        override suspend fun deleteMahasiswa(nim: String) {
            try {
                val response = mahasiswaApiService.deleteMahasiswa(nim)
                if (!response.isSuccessful) {
                    throw IOException("false to delete mahasiswa. HTTP Status Code :" +
                    "${response.code()}")
                } else {
                    response.message()
                    println(response.message())
                }
            }catch (e:Exception){
                throw e
            }
        }

        override suspend fun getMahasiswa(): List<Mahasiswa> =
            mahasiswaApiService.getAllMahasiswa()

        override suspend fun getMahasiswabyNim(nim: String): Mahasiswa {
            return mahasiswaApiService.getMahasiswabyNim(nim)
        }
    }
}