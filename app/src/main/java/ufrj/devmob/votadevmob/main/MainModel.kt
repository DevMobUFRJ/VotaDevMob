package ufrj.devmob.votadevmob.main

import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.Callback
import ufrj.devmob.votadevmob.model.Poll

class MainModel {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }

    fun getPoll(pollId: String, password: String, callback: Callback) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId)
            .get()
            .addOnSuccessListener { callback.onSuccess(it.toObject(Poll::class.java)) }
            .addOnFailureListener { callback.onError(it) }
    }

}