package com.example.myapplication3.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication3.models.getAll.Root
import com.example.myapplication3.ApiInterfaces
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest


class CharacterListRepository(val apiInterfaces: ApiInterfaces) {
    private val characterList =MutableLiveData<Root>()
    val characterListData: LiveData<Root> = characterList

    private val characterDetail= MutableLiveData<com.example.myapplication.models.getById.Root>()
    val characterDetailData: LiveData<com.example.myapplication.models.getById.Root> = characterDetail

    fun getCharacterList(){
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val all = apiInterfaces.getAll(ts,hash)
        all.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                if (response.isSuccessful) {
                    characterList.value = response.body()
                }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Log.i("CharacterListRepository", t.message.toString())
            }

        })
    }

    fun getCharacterDetail(id: Int){
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val characterById = apiInterfaces.getById(id,ts, hash)

        characterById.enqueue(object: Callback<com.example.myapplication.models.getById.Root>{
            override fun onResponse(
                call: Call<com.example.myapplication.models.getById.Root>,
                response: Response<com.example.myapplication.models.getById.Root>
            ) {
                if (response.isSuccessful){
                    characterDetail.value= response.body()
                }
            }

            override fun onFailure(
                call: Call<com.example.myapplication.models.getById.Root>,
                t: Throwable
            ) {
                Log.i("CharacterListRepository", "getCharacterDetail")
            }

        })
    }
    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}