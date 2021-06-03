package sk.marek.mybikerides

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RideViewModel(application: Application) : AndroidViewModel(application){
    val appDatabase = AppDatabase(application)
    val rideDao = appDatabase.rideDao()

    fun getRides(): LiveData<List<Ride>> {
        return rideDao.list()
    }

    fun addRide(ride: Ride) {
        appDatabase.transactionExecutor.execute {
            rideDao.insert(ride)
        }
    }

    fun deleteRide(ride: Ride) {
        appDatabase.transactionExecutor.execute {
            rideDao.deleteRide(ride)
        }
    }

    fun getMaxSpeed(): Float {
        var msp = MaxSumPojo(0F,0F)
        appDatabase.transactionExecutor.execute {
            msp = rideDao.getMaxSpeedSumDistance()
        }
        return msp.maxS
    }

//    fun getMaxSpeed(): MaxSumPojo {
//        var msp = MaxSumPojo(1F,1F)
//        appDatabase.transactionExecutor.execute {
//           msp = rideDao.getMaxSpeedSumDistance()
//        }
//        return  msp
//    }

}