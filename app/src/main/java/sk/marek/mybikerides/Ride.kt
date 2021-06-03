package sk.marek.mybikerides

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Ride (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rideName: String,
//    val note: String,
    val rating: Int,

    val date: Long,
    val rideDistance: Float,
    val maxSpeed: Float,
    val averageSpeed: Float,
    val ridingTime: String
) : Serializable

data class MaxSumPojo(var maxS: Float, var sumS: Float)