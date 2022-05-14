package co.edu.udistrital.espingsw.huellitaspelitos.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.Instant

class InstantTypeAdapter : TypeAdapter<Instant>() {

    override fun write(out: JsonWriter, value: Instant) {
        out.value(value.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun read(input: JsonReader): Instant {
        return Instant.parse(input.nextString())
    }
}