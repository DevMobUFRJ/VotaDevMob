package ufrj.devmob.votadevmob.result

import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.Callback

class PollResultModel {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }

    fun getOptionNumberOfVotes(pollId: Int, currentOption: String, callback: Callback<Int>) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId.toString())
            .collection(currentOption)
            .get()
            .addOnSuccessListener { callback.onSuccess(it.size()) }
            .addOnFailureListener { callback.onError(it) }
    }
}