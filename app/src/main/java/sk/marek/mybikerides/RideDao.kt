package sk.marek.mybikerides

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RideDao {
    @Insert
    fun insert(ride: Ride)

    @Query("SELECT * FROM ride")
    fun list(): LiveData<List<Ride>>

    @Delete
    fun deleteRide(ride: Ride)

    @Query("SELECT MAX(maxSpeed) as maxS, SUM(rideDistance) as sumS FROM ride")
    fun getMaxSpeedSumDistance(): MaxSumPojo
}