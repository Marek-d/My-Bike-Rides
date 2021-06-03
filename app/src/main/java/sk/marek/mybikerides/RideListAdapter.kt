package sk.marek.mybikerides

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object RideDiff: DiffUtil.ItemCallback<Ride>() {
    override fun areItemsTheSame(oldItem: Ride, newItem: Ride): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ride, newItem: Ride): Boolean {
        return oldItem.averageSpeed == newItem.averageSpeed
                && oldItem.maxSpeed == newItem.maxSpeed
                && oldItem.date == newItem.date
//                && oldItem.note == newItem.note
                && oldItem.rating == newItem.rating
                && oldItem.rideDistance == newItem.rideDistance
                && oldItem.rideName == newItem.rideName
                && oldItem.ridingTime == newItem.ridingTime
    }
}

class RideViewHolder(itemView: View, val clickListener: RideClickListener) : RecyclerView.ViewHolder(itemView) {
    val rideNameTextView: TextView = itemView.findViewById(R.id.rideNameTextView)
    val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    val rideDistanceTextView: TextView = itemView.findViewById(R.id.rideDistanceTextView)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(ride: Ride) {
        rideNameTextView.text = ride.rideName
        val df = DateFormat.getDateInstance().format(Date(ride.date))
        dateTextView.text = df.toString()
        rideDistanceTextView.text = ride.rideDistance.toString()

        itemView.setOnClickListener {
            clickListener.onRideClick(ride)
        }
    }
}

class RideListAdapter(val clickListener: RideClickListener) : ListAdapter<Ride, RideViewHolder>(RideDiff) {
//    private var rideClickListener = RideClickListener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.ride, parent, false)

        return RideViewHolder(layout, clickListener)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.tag = getItem(position)
    }

    override fun getItem(position: Int): Ride {
        return super.getItem(position)
    }
}