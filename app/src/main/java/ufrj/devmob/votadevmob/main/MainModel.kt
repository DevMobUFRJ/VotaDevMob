package ufrj.devmob.votadevmob.main

import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll
import java.lang.Exception

class MainModel {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }

    fun getPoll(pollId: String, callback: Callback<Poll>) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId)
            .get()
            .addOnSuccessListener {
                val poll = it.toObject(Poll::class.java)

                if (poll != null)
                    callback.onSuccess(poll)
                else
                    callback.onError(Exception("falha no parse"))
            }
            .addOnFailureListener { callback.onError(it) }
    }
}