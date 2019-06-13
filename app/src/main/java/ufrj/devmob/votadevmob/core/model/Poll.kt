package ufrj.devmob.votadevmob.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poll(val id: Int? = null,
                val password: String? = null,
                val title: String? = null,
                val optionsList: List<String>? = null) : Parcelable