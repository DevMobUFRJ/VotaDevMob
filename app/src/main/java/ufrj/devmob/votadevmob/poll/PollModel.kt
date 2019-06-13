package ufrj.devmob.votadevmob.poll

import ufrj.devmob.votadevmob.core.base.BaseModel
import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Vote

class PollModel: BaseModel() {

    fun vote(pollId: Int, chosenOption: String, callback: Callback<Unit>) {
        firestore.collection(POLLS_KEY_ADDRESS)
            .document(pollId.toString())
            .collection(chosenOption)
            .add(Vote(2345678)) //get user unique id
            .addOnSuccessListener { callback.onSuccess(Unit) }
            .addOnFailureListener { callback.onError(it) }
    }
}