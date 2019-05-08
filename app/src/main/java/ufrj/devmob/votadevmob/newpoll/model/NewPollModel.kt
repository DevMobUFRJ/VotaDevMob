package ufrj.devmob.votadevmob.newpoll.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import ufrj.devmob.votadevmob.core.entities.PollDocument


class NewPollModel {

    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun createNewPoll(poll: PollDocument){
        database.collection("Polls")
                .add(poll)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Result", "Poll was created successfully")
                    } else {
                        Log.i("Result", "Poll was not created")
                    }
                }
    }

    fun createNewPollWithId(id: String, poll: PollDocument){
        database.collection("Polls")
                .document(id)
                .set(poll)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Result", "Poll was created successfully")
                    } else {
                        Log.i("Result", "Poll was not created")
                    }
                }
    }

//    fun getCollection(collection: String){
//        db.collection(collection).get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                task.result?.let {
//                    for (document: QueryDocumentSnapshot in it){
//                        Log.d("Document: ", document.id + " => " + document.data)
//                    }
//                }
//            } else {
//                Log.w("Exception: ", "error getting documents", task.exception)
//            }
//        }
//    }
}