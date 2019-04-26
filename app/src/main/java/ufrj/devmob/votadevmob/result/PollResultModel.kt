package ufrj.devmob.votadevmob.result

import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ufrj.devmob.votadevmob.core.callback.Callback

class PollResultModel {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }

    fun getOptionNumberOfVotes(pollId: Int, currentOption: String, callback: Callback<Int>) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId.toString())
            .collection(currentOption)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, exception ->
                if (exception != null) {
                    callback.onError(exception)
                    return@EventListener
                }
                callback.onSuccess(value?.size() ?: 0)
            }
        )
    }
}