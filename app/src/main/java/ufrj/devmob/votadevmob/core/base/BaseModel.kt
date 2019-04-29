package ufrj.devmob.votadevmob.core.base

import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by victor.cruz on 29/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
open class BaseModel {

    internal val firestore by lazy { FirebaseFirestore.getInstance() }

    companion object {
        const val POLLS_KEY_ADDRESS = "Polls"
    }
}