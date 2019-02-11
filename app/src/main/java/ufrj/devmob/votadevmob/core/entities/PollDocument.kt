package ufrj.devmob.votadevmob.core.entities

data class PollDocument(
        val title: String = "",
        val password: String = "",
        val options: Map<String, Int> = mapOf()
)