package ufrj.devmob.votadevmob.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.poll.PollActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        createPollButton.setOnClickListener { startActivity(Intent(this, CreatePollActivity::class.java)) }
        voteButton.setOnClickListener { startActivity(Intent(this, PollActivity::class.java)) }
//        resultPollButton.setOnClickListener { startActivity(Intent(this, PollResultActivity::class.java)) }
    }
}
