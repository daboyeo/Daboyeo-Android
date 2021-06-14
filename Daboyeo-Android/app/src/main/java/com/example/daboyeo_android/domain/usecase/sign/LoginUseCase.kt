package com.example.daboyeo_android.domain.usecase.sign

import com.example.daboyeo_android.domain.repository.SignRepository
import java.util.HashMap

class LoginUseCase(private val repository: SignRepository) {
    suspend fun execute(hashMap: HashMap<String, String>) = repository.signAuth(hashMap)
}