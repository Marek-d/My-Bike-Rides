package sk.marek.mybikerides

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class AddRideActivity : AppCompatActivity() {
    val rideViewModel: RideViewModel by viewModels()

    private lateinit var rideNameEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var ridingTimeEditText: EditText
    private lateinit var rideDistanceEditText: EditText
    private lateinit var maxSpeedEditText: EditText
    private lateinit var averageSpeedEditText: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var saveRideButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride)

        rideNameEditText = findViewById(R.id.rideNameEditText)
        dateEditText = findViewById(R.id.dateEditText)
        ridingTimeEditText = findViewById(R.id.ridingTimeEditText)
        rideDistanceEditText = findViewById(R.id.rideDistanceEditText)
        maxSpeedEditText = findViewById(R.id.maxSpeedEditText)
        averageSpeedEditText = findViewById(R.id.averageSpeedEditText)
        ratingBar = findViewById(R.id.ratingBar)
        saveRideButton = findViewById(R.id.saveRideButton)

        val regex = Regex("^[0-3][0-9]\\/[0-1][0-9]\\/[0-9]{4}")

        dateEditText.doAfterTextChanged {
            if (!regex.matches(dateEditText.text.toString())) {
                dateEditText.error = "Neplatný formát vstupu!"
                saveRideButton.isEnabled = false
            } else {
                saveRideButton.isEnabled = true
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveRide(view: View) {
        var dateInLong: Long = 0
        val dateInString = dateEditText.text.toString()
        var maxSpeed: Float = 0F
        var averageSpeed: Float = 0F

        if (dateInString != "") {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = formatter.parse(dateInString)
            dateInLong = date.time
        } else {
            dateEditText.error = "Toto pole je povinné!"
            return
        }

        if (rideNameEditText.text.toString() == "") {
            rideNameEditText.error = "Toto pole je povinné!"
            return
        }

        if (rideDistanceEditText.text.toString() == "") {
            rideDistanceEditText.error = "Toto pole je povinné!"
            return
        }

        if (maxSpeedEditText.text.toString() == "") {
            maxSpeed = 0F
        } else {
            maxSpeed = maxSpeedEditText.text.toString().toFloat()
        }

        if (averageSpeedEditText.text.toString() == "") {
            averageSpeed = 0F
        } else {
            averageSpeed = averageSpeedEditText.text.toString().toFloat()
        }

        rideViewModel.addRide(Ride(
                rideName = rideNameEditText.text.toString(),
                rating = ratingBar.rating.toInt(),
                date = dateInLong,
                rideDistance = rideDistanceEditText.text.toString().toFloat(),
                maxSpeed = maxSpeed,
                averageSpeed = averageSpeed,
                ridingTime = ridingTimeEditText.text.toString()))

        val toast = Toast.makeText(this, "Úspešne uložené.", Toast.LENGTH_LONG)
        toast.show()

        this.finish()
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