package sk.marek.mybikerides

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RideClickListener {
    private lateinit var rideRecyclerView: RecyclerView

    val rideViewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rideRecyclerView = findViewById(R.id.rideRecyclerView)
//        rideRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemDecor = DividerItemDecoration(this, HORIZONTAL)
        rideRecyclerView.addItemDecoration(itemDecor)

        refresh()

        ItemTouchHelper(SwipeHelper()).attachToRecyclerView(rideRecyclerView)
    }

    private fun refresh() {
        RideListAdapter(this).also {
            rideRecyclerView.adapter = it
            rideViewModel.getRides().observe(this, it::submitList)
        }
    }

    inner class SwipeHelper : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewHolder.itemView.tag
            AlertDialog.Builder(this@MainActivity).setTitle("Vymazanie záznamu").setMessage("Naozaj si prajete vymazať záznam?").setPositiveButton("OK") { _, _, ->
                rideViewModel.deleteRide(viewHolder.itemView.tag as Ride)
            }.setNegativeButton("Zrušiť") {_,_ ->
                refresh()

            }.show()
        }
    }

    fun addRide(view: View) {
        val intent = Intent(this, AddRideActivity::class.java)
        startActivity(intent)
    }

    override fun onRideClick(ride: Ride) {
        val intent = Intent(this, RideDetailActivity::class.java)
        intent.putExtra("ride", ride)

        startActivity(intent)
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