package ufrj.devmob.votadevmob.core.callback

import java.lang.Exception

interface Callback<T> {

    fun onSuccess(result: T)

    fun onError(exception: Exception)

}