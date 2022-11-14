package com.nowiczenkoandrzej.e_bazarek.domain

import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.nowiczenkoandrzej.e_bazarek.data.models.ProductResponse
import com.nowiczenkoandrzej.e_bazarek.utils.DataState
import com.nowiczenkoandrzej.e_bazarek.data.models.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FirebaseRepository
@Inject constructor(
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth,
    private val cloud: FirebaseFirestore
){

    fun getCurrentUserData(): MutableStateFlow<DataState<UserResponse>> {
        val id = auth.currentUser?.uid
        val result = MutableStateFlow<DataState<UserResponse>>(DataState.Loading())

        cloud.collection("users")
            .document(id!!)
            .get()
            .addOnSuccessListener { response ->
                val user = response.toObject(UserResponse::class.java)
                result.value = DataState.Success(user!!)
            }
            .addOnFailureListener { exc ->
                result.value = DataState.Error(exc.message.toString())
            }

        return result
    }

    fun getAllProducts(): MutableStateFlow<DataState<List<ProductResponse>>> {
        val result = MutableStateFlow<DataState<List<ProductResponse>>>(DataState.Loading())

        cloud.collection("products")
            .get()
            .addOnSuccessListener { response ->
                val products = response.toObjects(ProductResponse::class.java)
                result.value = DataState.Success(products)
            }
            .addOnFailureListener { exc ->
                result.value = DataState.Error(exc.message.toString())
            }

        return result
    }

    fun logout(){
        auth.signOut()
    }

}