package sk.marek.mybikerides

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels

class StatisticsActivity : AppCompatActivity() {
    lateinit var textView3: TextView
    lateinit var textView4: TextView

    private val rideViewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)

        textView3.text = rideViewModel.getMaxSpeed().toString()
        textView4.text = rideViewModel.getMaxSpeed().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_statistics) {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}