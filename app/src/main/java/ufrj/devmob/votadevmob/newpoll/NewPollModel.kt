package ufrj.devmob.votadevmob.newpoll

import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.core.base.BaseModel
import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll


class NewPollModel : BaseModel() {

    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun createNewPoll(poll: Poll, callback: Callback<Poll>) {
        database.collection(POLLS_KEY_ADDRESS)
            .document(poll.id.toString())
            .set(poll)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(poll)
                } else {
                    callback.onError(Exception("Não foi possível criar a votação"))
                }
            }
    }
}