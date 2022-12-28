package eu.tutrorials.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

   private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// set up an immutable variable val which accesses the button
// to access the button use findViewById(R.id......)
val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

  // add an event listener
        btnDatePicker.setOnClickListener {

          clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        // to get the date we need the calendar object from the java util library
        val myCalendar = Calendar.getInstance() // instanciate - object
        // to get the year
        val year = myCalendar.get(Calendar.YEAR)
        // month
        val month = myCalendar.get(Calendar.MONTH)
        // day
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        // to use the date picker dialog inbuilt in Android

        val dpd = DatePickerDialog(this,
            { view, selectedYear, selectedMonth,selectedDayOfMonth ->
//  - > to pop the result on dialog
                // toast display a popup just like alert on javaScript
                // remember to add show to show it on the screen.
                Toast.makeText(this,//this means on this activity
                    "The Year was $selectedYear the Month was ${selectedMonth+1} the day was $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                // lets store the selected date
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                // assign the text ?-nullabble
                tvSelectedDate?.setText(selectedDate)
                // create the date format
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time/ 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/ 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                // would reutrn a double so you need to convert it to a string
                tvAgeInMinutes?.text = differenceInMinutes.toString()

            },

            year,
            month,
            day
        )
        // this would only allow you to select dates in the past
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
      dpd.show()

    }
}

