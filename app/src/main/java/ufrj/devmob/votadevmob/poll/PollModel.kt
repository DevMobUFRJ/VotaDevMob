package ufrj.devmob.votadevmob.poll

import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.Callback
import ufrj.devmob.votadevmob.model.Vote

class PollModel {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }

    fun vote(pollId: Int, chosenOption: String, callback: Callback) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId.toString())
            .collection(chosenOption)
            .add(Vote(2345678)) //get user unique id
            .addOnSuccessListener { callback.onSuccess() }
            .addOnFailureListener { callback.onError(it) }
    }
}