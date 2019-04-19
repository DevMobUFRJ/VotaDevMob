package ufrj.devmob.votadevmob

import java.lang.Exception

interface Callback {

    fun onSuccess()

    fun onError(exception: Exception)

}