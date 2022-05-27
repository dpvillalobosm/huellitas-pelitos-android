package co.edu.udistrital.espingsw.huellitaspelitos.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeTypeAdapter: TypeAdapter<LocalDateTime>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun write(out: JsonWriter, value: LocalDateTime) {
        out.value(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun read(input: JsonReader): LocalDateTime {
        return LocalDateTime.parse(input.nextString())
    }
}