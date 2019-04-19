package ufrj.devmob.votadevmob

import java.lang.Exception

interface Callback<T> {

    fun onSuccess(result: T)

    fun onError(exception: Exception)

}