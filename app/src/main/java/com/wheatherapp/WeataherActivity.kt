package com.wheatherapp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.wheatherapp.Adapters.WeatherAdapter
import com.wheatherapp.databinding.ActivityWeaaherBinding
import com.wheatherapp.helper.AppController
import com.wheatherapp.helper.Weather
import com.wheatherapp.helper.WeatherSubModel
import org.json.JSONException
import org.json.JSONObject
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class WeataherActivity : AppCompatActivity() {

    var listitems: ArrayList<Weather> = ArrayList<Weather>()
    var listitems2: ArrayList<Weather> = ArrayList<Weather>()
    var listitems3: ArrayList<Weather> = ArrayList<Weather>()
    var listitems4: ArrayList<Weather> = ArrayList<Weather>()
    var listitems5: ArrayList<Weather> = ArrayList<Weather>()

    var stringFirstDate = ""
    var stringSecondDate = ""
    var stringThirdDate = ""
    var stringFourthDate = ""
    var stringFifthDate = ""
    var doubleTodayTemp = 0.0
    var doubleTempSecondDay = 0.0
    var doubleThirdDayTemp = 0.0
    var doubleFourthDayTemp = 0.0
    var doubleFifthDayTemp = 0.0
    var doubleTempTodayMin = 0.0
    var doubleTempTodayMax = 0.0

    var list: ArrayList<WeatherSubModel> = ArrayList<WeatherSubModel>()
    var listitemsTemp: ArrayList<WeatherSubModel> = ArrayList<WeatherSubModel>()
    var listitemsTempThirdDay: ArrayList<WeatherSubModel> = ArrayList<WeatherSubModel>()
    var listitemsTempFourthDay: ArrayList<WeatherSubModel> = ArrayList<WeatherSubModel>()
    var listitemsTempFifthDay: ArrayList<WeatherSubModel> = ArrayList<WeatherSubModel>()
    var prog: ProgressDialog? = null
    var Curremttime = 0
    var SecondDayCount = 0
    private lateinit var myAdapter: WeatherAdapter
    private lateinit var binding: ActivityWeaaherBinding
    var LAT = "";
    var LONG = "";
    var Loc = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weaaher)
        binding = ActivityWeaaherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("MM/dd/yyyy")
        stringFirstDate = mdformat.format(calendar.time)


        Loc = intent.getStringExtra("LOCATION").toString()
        LAT = intent.getStringExtra("LAT").toString()
        LONG = intent.getStringExtra("LONG").toString()

        binding.tvCurrentLocation.setText(Loc)
        //for second date
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val mdformat2 = SimpleDateFormat("MM/dd/yyyy")
        stringSecondDate = mdformat2.format(calendar.time)


        //for third date
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val mdformat3 = SimpleDateFormat("MM/dd/yyyy")
        stringThirdDate = mdformat3.format(calendar.time)

          //fourth date
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val mdformat4 = SimpleDateFormat("MM/dd/yyyy")
        stringFourthDate = mdformat4.format(calendar.time)

        //fifth date
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val mdformat5 = SimpleDateFormat("MM/dd/yyyy")
        stringFifthDate = mdformat5.format(calendar.time)


        println("stringFirstDate:$stringFirstDate")
        println("stringSecondDate:$stringSecondDate")
        println("stringThirdDate:$stringThirdDate")
        println("stringFourthDate:$stringFourthDate")
        println("stringFifthDate:$stringFifthDate")

        var date = Date() // wherever you get this from
        val cal = Calendar.getInstance()
        date = cal.time
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val date1 = sdf.format(date)
        var date2: Date? = Date()
        date2 = cal.time
        val sdf1 = SimpleDateFormat("HH")
        val currentDateTimeString = sdf1.format(date2)
        Curremttime = currentDateTimeString.toInt()



        binding.rvHour.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDAYYY.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFourth.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFifth.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        getWeatherData();
    }

    fun getWeatherData() {
        val stringUrl: String =
            "http://api.openweathermap.org/data/2.5/forecast?lat=" +LAT + "&lon=" + LONG + "&appid=f63af5e4778e517ec5e7599d6172eae5"

        println("Weather Response :$stringUrl")

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, stringUrl, null,
            { response ->
                try {
                    Log.d("Weather Response", response.toString())
                    val jsonArray = response.getJSONArray("list")
                    var tempToday = "0"
                    for (index in 0 until jsonArray.length()) {

                        val jsonObject = jsonArray.getJSONObject(index)
                        val s = jsonObject.getString("dt")
                        val s1 = jsonObject.getString("dt_txt")
                        val dateFormat = s1.replace("-", "/")
                        var output = ""
                        var date1 = ""
                        var time = ""
                        var time1 = 0
                        try {
                            val inputFormatter: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            val date: Date = inputFormatter.parse(dateFormat)
                            val outputFormatter: DateFormat = SimpleDateFormat("MM/dd/yyyy")
                            val outputFormatterTime: DateFormat = SimpleDateFormat("HH:mm")
                            output = outputFormatter.format(date) // Output : 01/20/2012
                            date1 = outputFormatterTime.format(date) // Output : 01/20/2012
                            val outputTime: DateFormat = SimpleDateFormat("HH")
                            time = outputTime.format(date) // Output : 01/20/2012
                            time1 = time.toInt()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        println("s1:$s1")
                        println("output:$output")
                        println("date1:$date1")
                        if (output == stringFirstDate) {
                            tempToday = ""
                            val dateFormat1 = output.trim { it <= ' ' }.replace("-", "/")
                            val input1 = SimpleDateFormat("MM/dd/yyyy")
                            val output1 = SimpleDateFormat("dd/MM/yyyy")
                            try {
                                val dateInput = input1.parse(dateFormat1) // parse input

                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }
                            val jsonObject1 = jsonObject.getJSONObject("main")
                            val WEATHER_TEMP = jsonObject1.getString("temp")
                            val WEATHER_TEMP_MIN = jsonObject1.getString("temp_min")
                            val WEATHER_TEMP_MAX = jsonObject1.getString("temp_max")
                            val WEATHER_PRESSURE = jsonObject1.getString("pressure")
                            val WEATHER_SEA_LEVEL = jsonObject1.getString("sea_level")
                            val WEATHER_GRAND_LEVEL =
                                jsonObject1.getString("grnd_level")
                            val WEATHER_HUMIDITY = jsonObject1.getString("humidity")
                            val WEATHER_TEMP_KF = jsonObject1.getString("temp_kf")

                            //for min temp
                            doubleTodayTemp = java.lang.Float.valueOf(WEATHER_TEMP) - 273.15
                            val stringTempToday = String.format("%.2f", doubleTodayTemp) + " ℃"
                            //                                    textViewTodayTemp.setText(stringTempToday);
                            doubleTempTodayMin = java.lang.Float.valueOf(WEATHER_TEMP_MIN) - 273.15
                            val stringTempTodayMin =
                                String.format("%.2f", doubleTempTodayMin) + " ℃"
                            // for max temp
                            doubleTempTodayMax = java.lang.Float.valueOf(WEATHER_TEMP_MAX) - 273.15
                            val stringTempTodayMax =
                                String.format("%.2f", doubleTempTodayMax) + " ℃"
                            //for humidity
                            binding.tvHumidity.setText(WEATHER_HUMIDITY + " " + "%");
                            binding.tvMinmax.setText(stringTempTodayMin + "/ " + stringTempTodayMax);
                            //for pressure
                            binding.tvPressure.setText(WEATHER_PRESSURE + " " + "hpa");
                            // for weather description
                            val jsonArray1 = jsonObject.getJSONArray("weather")
                            parseObjectWeather(jsonArray1.getJSONObject(0))?.let { listitems.add(it) }
                          var WEATHER_DESCRIPTION: String? = ""

                            for (j in listitems.indices) {
                                WEATHER_DESCRIPTION = listitems[j].description

                            }


                            //for wind speed
                            val jsonObject2 = jsonObject.getJSONObject("wind")
                            val WEATHER_WIND_SPEED =
                                jsonObject2.getString("speed")

                            var stringTime = ""
                            try {
                                val _24HourTime = date1
                                val _24HourSDF = SimpleDateFormat("HH:mm")
                                val _12HourSDF = SimpleDateFormat("hh:mm a")
                                val _24HourDt = _24HourSDF.parse(_24HourTime)
                                println(_24HourDt)
                                println(_12HourSDF.format(_24HourDt))
                                stringTime = _12HourSDF.format(_24HourDt)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val weatherSubModel =
                                WeatherSubModel(
                                    stringTime,
                                    time,
                                    stringTempToday,
                                    WEATHER_HUMIDITY,
                                    stringTempTodayMin,
                                    stringTempTodayMax,
                                    WEATHER_PRESSURE,
                                    WEATHER_WIND_SPEED,
                                    WEATHER_DESCRIPTION,
                                )
                            list.add(weatherSubModel)

                            for (i in list.indices) {
                                val time2 = list[i].getTime()
                                val t = time2.toInt()
                                val t2 = t + 3
                                if (Curremttime >= t && Curremttime < t2) {
                                    val CTime = list[i].getCurrentTime()
                                    val temp = list[i].getTemp()
                                    val des = list[i].getTempDescription()
                                    val spedd = list[i].getWindSpeed()
                                    binding.tvTemp.setText(temp)
                                    binding.tvTempTittle.setText(des)
                                    binding.tvWind.setText("$spedd mph")
                                    binding.tvWeatherNewModifiedTodayDate.setText("Last Updated On - "+CTime)

                                }
                            }


                        } else if (output == stringSecondDate) {

                            //                                tempSecondDay="";
                            SecondDayCount = SecondDayCount + 1
                            val dateFormat1 = output.trim { it <= ' ' }.replace("-", "/")
                            val input1 = SimpleDateFormat("MM/dd/yyyy")
                            val output1 = SimpleDateFormat("dd/MM/yyyy")
                            try {
                                val dateInput = input1.parse(dateFormat1) // parse input
                                binding.tvSecandDate.setText(output1.format(dateInput)) // format output
                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }

                            //                                textViewSecondDayDate.setText(output);
                            val jsonObject1 = jsonObject.getJSONObject("main")
                            val WEATHER_TEMP = jsonObject1.getString("temp")
                            val WEATHER_TEMP_MIN = jsonObject1.getString("temp_min")
                            val WEATHER_TEMP_MAX = jsonObject1.getString("temp_max")
                            val WEATHER_PRESSURE = jsonObject1.getString("pressure")
                            val WEATHER_SEA_LEVEL = jsonObject1.getString("sea_level")
                            val WEATHER_GRAND_LEVEL =
                                jsonObject1.getString("grnd_level")
                            val WEATHER_HUMIDITY = jsonObject1.getString("humidity")
                            val WEATHER_TEMP_KF = jsonObject1.getString("temp_kf")
                            val jsonObject3 = jsonObject.getJSONArray("weather")
                            parseObjectWeather(jsonObject3.getJSONObject(0))?.let {
                                listitems2.add(
                                    it
                                )
                            }
                            var WEATHER_DESCRIPTION = ""
                            for (j in listitems2.indices) {
                                WEATHER_DESCRIPTION = listitems2[j].description
                            }
                            doubleTempSecondDay = java.lang.Float.valueOf(WEATHER_TEMP) - 273.15
                            val stringSecondDayTemp =
                                String.format("%.2f", doubleTempSecondDay) + " ℃"
                            println("WEATHER_DESCRIPTION:$WEATHER_DESCRIPTION")


                            // to convert 24 hrs time in 12 hrs time
                            var stringTime = ""
                            try {
                                val _24HourTime = date1
                                val _24HourSDF = SimpleDateFormat("HH:mm")
                                val _12HourSDF = SimpleDateFormat("hh:mm a")
                                val _24HourDt = _24HourSDF.parse(_24HourTime)
                                println(_24HourDt)
                                println(_12HourSDF.format(_24HourDt))
                                stringTime = _12HourSDF.format(_24HourDt)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val weatherSubModel =
                                WeatherSubModel(
                                    "",
                                    stringTime,
                                    stringSecondDayTemp,
                                    WEATHER_HUMIDITY,
                                    "",
                                    "",
                                    "",
                                    "",
                                    WEATHER_DESCRIPTION,
                                )
                            listitemsTemp.add(weatherSubModel)
                            myAdapter = WeatherAdapter(
                                listitemsTemp,
                                this
                            )
                            binding.rvHour.setAdapter(myAdapter)
                            myAdapter.notifyDataSetChanged()

                        } else if (output == stringThirdDate) {

                            //                                tempThirdDay="";
                            val dateFormat1 = output.trim { it <= ' ' }.replace("-", "/")
                            val input1 = SimpleDateFormat("MM/dd/yyyy")
                            val output1 = SimpleDateFormat("dd/MM/yyyy")
                            try {
                                val dateInput = input1.parse(dateFormat1) // parse input
                                binding.tvThirdDate.setText(output1.format(dateInput)) // format output
                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }

                            //                                textViewThirdDayDate.setText(output);
                            val jsonObject1 = jsonObject.getJSONObject("main")
                            val WEATHER_TEMP = jsonObject1.getString("temp")
                            val WEATHER_TEMP_MIN = jsonObject1.getString("temp_min")
                            val WEATHER_TEMP_MAX = jsonObject1.getString("temp_max")
                            val WEATHER_PRESSURE = jsonObject1.getString("pressure")
                            val WEATHER_SEA_LEVEL = jsonObject1.getString("sea_level")
                            val WEATHER_GRAND_LEVEL =
                                jsonObject1.getString("grnd_level")
                            val WEATHER_HUMIDITY = jsonObject1.getString("humidity")
                            val WEATHER_TEMP_KF = jsonObject1.getString("temp_kf")


                            doubleThirdDayTemp = java.lang.Float.valueOf(WEATHER_TEMP) - 273.15
                            val stringThirdDayTemp =
                                String.format("%.2f", doubleThirdDayTemp) + " ℃"


                            val jsonObject4 = jsonObject.getJSONArray("weather")

                            parseObjectWeather(jsonObject4.getJSONObject(0))?.let {
                                listitems3.add(
                                    it
                                )
                            }
                            var WEATHER_DESCRIPTION: String? = ""

                            for (j in listitems3.indices) {
                                WEATHER_DESCRIPTION = listitems3[j].description
                            }


                            var stringTime = ""
                            try {
                                val _24HourTime = date1
                                val _24HourSDF = SimpleDateFormat("HH:mm")
                                val _12HourSDF = SimpleDateFormat("hh:mm a")
                                val _24HourDt = _24HourSDF.parse(_24HourTime)
                                println(_24HourDt)
                                println(_12HourSDF.format(_24HourDt))
                                stringTime = _12HourSDF.format(_24HourDt)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val weatherSubModel =
                                WeatherSubModel(
                                    "",
                                    stringTime,
                                    stringThirdDayTemp,
                                    WEATHER_HUMIDITY,
                                    "",
                                    "",
                                    "",
                                    "",
                                    WEATHER_DESCRIPTION,
                                )
                            listitemsTempThirdDay.add(weatherSubModel)
                            myAdapter = WeatherAdapter(
                                listitemsTempThirdDay,
                                this
                            )
                            binding.rvDAYYY.setAdapter(myAdapter)
                            myAdapter.notifyDataSetChanged()

                        } else if (output == stringFourthDate) {

                            val dateFormat1 = output.trim { it <= ' ' }.replace("-", "/")
                            val input1 = SimpleDateFormat("MM/dd/yyyy")
                            val output1 = SimpleDateFormat("dd/MM/yyyy")
                            try {
                                val dateInput = input1.parse(dateFormat1) // parse input
                                binding.tvFourthdDate.setText(output1.format(dateInput)) // format output
                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }

                            val jsonObject1 = jsonObject.getJSONObject("main")
                            val WEATHER_TEMP = jsonObject1.getString("temp")
                            val WEATHER_TEMP_MIN = jsonObject1.getString("temp_min")
                            val WEATHER_TEMP_MAX = jsonObject1.getString("temp_max")
                            val WEATHER_PRESSURE = jsonObject1.getString("pressure")
                            val WEATHER_SEA_LEVEL = jsonObject1.getString("sea_level")
                            val WEATHER_GRAND_LEVEL =
                                jsonObject1.getString("grnd_level")
                            val WEATHER_HUMIDITY = jsonObject1.getString("humidity")
                            val WEATHER_TEMP_KF = jsonObject1.getString("temp_kf")

                            val jsonObject5 = jsonObject.getJSONArray("weather")

                            parseObjectWeather(jsonObject5.getJSONObject(0))?.let {
                                listitems4.add(
                                    it
                                )
                            }
                            doubleFourthDayTemp = java.lang.Float.valueOf(WEATHER_TEMP) - 273.15
                            val stringFourthDayTemp =
                                String.format("%.2f", doubleFourthDayTemp) + " ℃"
                            var WEATHER_DESCRIPTION: String? = ""

                            for (j in listitems4.indices) {
                                WEATHER_DESCRIPTION = listitems4[j].description

                            }


                            var stringTime = ""
                            try {
                                val _24HourTime = date1
                                val _24HourSDF = SimpleDateFormat("HH:mm")
                                val _12HourSDF = SimpleDateFormat("hh:mm a")
                                val _24HourDt = _24HourSDF.parse(_24HourTime)
                                println(_24HourDt)
                                println(_12HourSDF.format(_24HourDt))
                                stringTime = _12HourSDF.format(_24HourDt)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val weatherSubModel =
                                WeatherSubModel(
                                    "",
                                    stringTime,
                                    stringFourthDayTemp,
                                    WEATHER_HUMIDITY,
                                    "",
                                    "",
                                    "",
                                    "",
                                    WEATHER_DESCRIPTION,
                                )
                            listitemsTempFourthDay.add(weatherSubModel)
                            myAdapter = WeatherAdapter(
                                listitemsTempFourthDay,
                                this
                            )
                            binding.rvFourth.setAdapter(myAdapter)
                            myAdapter.notifyDataSetChanged()

                        } else if (output == stringFifthDate) {


                            val dateFormat1 = output.trim { it <= ' ' }.replace("-", "/")
                            val input1 = SimpleDateFormat("MM/dd/yyyy")
                            val output1 = SimpleDateFormat("dd/MM/yyyy")
                            try {
                                val dateInput = input1.parse(dateFormat1) // parse input
                                binding.tvFifthdDate.setText(output1.format(dateInput)) // format output
                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }
                            val jsonObject1 = jsonObject.getJSONObject("main")
                            val WEATHER_TEMP = jsonObject1.getString("temp")
                            val WEATHER_TEMP_MIN = jsonObject1.getString("temp_min")
                            val WEATHER_TEMP_MAX = jsonObject1.getString("temp_max")
                            val WEATHER_PRESSURE = jsonObject1.getString("pressure")
                            val WEATHER_SEA_LEVEL = jsonObject1.getString("sea_level")
                            val WEATHER_GRAND_LEVEL =
                                jsonObject1.getString("grnd_level")
                            val WEATHER_HUMIDITY = jsonObject1.getString("humidity")
                            val WEATHER_TEMP_KF = jsonObject1.getString("temp_kf")

                            //                                textViewTemp5.setText(WEATHER_TEMP);
                            doubleFifthDayTemp = java.lang.Float.valueOf(WEATHER_TEMP) - 273.15
                            val stringFifthDayTemp =
                                String.format("%.2f", doubleFifthDayTemp) + " ℃"


                            val jsonObject6 = jsonObject.getJSONArray("weather")
                            parseObjectWeather(jsonObject6.getJSONObject(0))?.let {
                                listitems5.add(
                                    it
                                )
                            }

                            var WEATHER_DESCRIPTION: String? = ""
                            var WEATHER_ICON: String? = ""
                            for (j in listitems5.indices) {
                                WEATHER_DESCRIPTION = listitems5[j].description

                            }


                            var stringTime = ""
                            try {
                                val _24HourTime = date1
                                val _24HourSDF = SimpleDateFormat("HH:mm")
                                val _12HourSDF = SimpleDateFormat("hh:mm a")
                                val _24HourDt = _24HourSDF.parse(_24HourTime)
                                println(_24HourDt)
                                println(_12HourSDF.format(_24HourDt))
                                stringTime = _12HourSDF.format(_24HourDt)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            val weatherSubModel =
                                WeatherSubModel(
                                    "",
                                    stringTime,
                                    stringFifthDayTemp,
                                    WEATHER_HUMIDITY,
                                    "",
                                    "",
                                    "",
                                    "",
                                    WEATHER_DESCRIPTION,
                                )
                            listitemsTempFifthDay.add(weatherSubModel)
                            myAdapter = WeatherAdapter(
                                listitemsTempFifthDay,
                                this
                            )
                            binding.rvFifth.setAdapter(myAdapter)
                            myAdapter.notifyDataSetChanged()

                        }


                    }
                } catch (e: JSONException) {

                    e.printStackTrace()
                }
            }) { error ->
            println("Weather Response :$error")

        }



        AppController.getInstance().addToRequestQueueMax(jsonObjectRequest)

    }

    fun parseObjectWeather(jsonObject: JSONObject): Weather? {
        val weather = Weather()
        try {
            weather.id = jsonObject.getString("id")
            weather.main = jsonObject.getString("main")
            weather.description = jsonObject.getString("description")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return weather
    }


}