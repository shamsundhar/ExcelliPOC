import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieInfoResponse(

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Ratings") val ratings: List<Ratings>,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String
) : Serializable