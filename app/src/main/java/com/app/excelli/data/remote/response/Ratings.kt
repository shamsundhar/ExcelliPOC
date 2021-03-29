import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ratings(

    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
) : Serializable