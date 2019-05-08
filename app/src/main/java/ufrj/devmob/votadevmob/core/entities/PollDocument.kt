package ufrj.devmob.votadevmob.core.entities

data class PollDocument(val id: Int? = null,
                val password: String? = null,
                val title: String? = null,
                val optionsList: List<String>? = null)