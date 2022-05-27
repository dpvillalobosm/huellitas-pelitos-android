package co.edu.udistrital.espingsw.huellitaspelitos.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeTypeAdapter : TypeAdapter<LocalTime>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun write(out: JsonWriter, value: LocalTime) {
        out.value(DateTimeFormatter.ISO_LOCAL_TIME.format(value))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun read(input: JsonReader): LocalTime {
        return LocalTime.parse(input.nextString())
    }
}