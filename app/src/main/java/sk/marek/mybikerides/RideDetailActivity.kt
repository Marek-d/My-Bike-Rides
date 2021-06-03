package sk.marek.mybikerides

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.TextView
import java.text.DateFormat
import java.util.*

class RideDetailActivity : AppCompatActivity() {
    lateinit var rideNameDetailTextView: TextView
    lateinit var dateDetailTextView: TextView
    lateinit var ridingTimeDetailTextView: TextView
    lateinit var rideDistanceDetailTextView: TextView
    lateinit var maxSpeedDetailTextView: TextView
    lateinit var averageSpeedDetailTextView: TextView
    lateinit var detailRatingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_detail)

        rideNameDetailTextView = findViewById(R.id.rideNameDetailTextView)
        dateDetailTextView = findViewById(R.id.dateDetailTextView)
        ridingTimeDetailTextView = findViewById(R.id.ridingTimeDetailTextView)
        rideDistanceDetailTextView = findViewById(R.id.rideDistanceDetailTextView)
        maxSpeedDetailTextView = findViewById(R.id.maxSpeedDetailTextView)
        averageSpeedDetailTextView = findViewById(R.id.averageSpeedDetailTextView)
        detailRatingBar = findViewById(R.id.detailRatingBar)

        val ride = intent.extras!!.get("ride") as Ride

        rideNameDetailTextView.text = ride.rideName
        val df = DateFormat.getDateInstance().format(Date(ride.date))
        dateDetailTextView.text = df.toString()
        ridingTimeDetailTextView.text = ride.ridingTime
        rideDistanceDetailTextView.text = ride.rideDistance.toString() + " km"
        maxSpeedDetailTextView.text = ride.maxSpeed.toString() + " km/h"
        averageSpeedDetailTextView.text = ride.averageSpeed.toString() + " km/h"
        detailRatingBar.rating= ride.rating.toFloat()
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