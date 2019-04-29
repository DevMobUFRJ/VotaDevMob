package ufrj.devmob.votadevmob.main

import ufrj.devmob.votadevmob.core.base.BaseModel
import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll

class MainModel : BaseModel() {

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