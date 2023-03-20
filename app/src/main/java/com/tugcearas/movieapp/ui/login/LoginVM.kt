package com.tugcearas.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.movieapp.data.model.login.CreateTokenModel
import com.tugcearas.movieapp.data.model.login.ValidationRequestModel
import com.tugcearas.movieapp.data.model.login.ValidationResponseModel
import com.tugcearas.movieapp.data.repo.LoginRepository
import com.tugcearas.movieapp.util.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val loginRepo: LoginRepository): ViewModel() {

    var username : MutableLiveData<String> = MutableLiveData("")
    var password : MutableLiveData<String> = MutableLiveData("")
    var message : MutableLiveData<String> = MutableLiveData()
    private var tokenResponse : MutableLiveData<CreateTokenModel>? = null
    private var validationResponseModel : MutableLiveData<ValidationResponseModel>? = null

    // variable to hold the callback instance
    var loginCallback: ClickLoginButton? = null

    fun createToken() = viewModelScope.launch {
        val response = loginRepo.getRequestToken()
        if (response.isSuccessful){
            response.body()?.let {
                tokenResponse?.postValue(it)
                validateWithLogin(it.requestToken)
            }
        }
        else{
            Log.e("LoginVM","Create token operation is failed!")
        }
    }

    private fun validateWithLogin(requestToken:String) = viewModelScope.launch {
        val validationRequestModel = ValidationRequestModel(
            username = username.value!!.toString(),
            password = password.value!!.toString(),
            requestToken = requestToken
        )
        val validateResponse = loginRepo.validateWithLogin(Constants.API_KEY, validationRequestModel)
        if (validateResponse.isSuccessful){
            validateResponse.body()?.let {
                validationResponseModel?.postValue(it)
                if(it.success){
                    message.postValue("Login successful!")
                    loginCallback?.onValidationSuccess()
                }
                else{
                    message.postValue("Validation unsuccessful!")
                }
            }
        } else{
            message.postValue("Login Unsuccessful!")
        }
    }
}