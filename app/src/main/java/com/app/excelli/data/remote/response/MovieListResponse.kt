import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieListResponse(

    @SerializedName("Search") val search: ArrayList<Search>,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("response") val response: Boolean
) : Serializable

data class Search(

    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("completeMovieData") var completeMovieData: MovieInfoResponse
) : Serializable