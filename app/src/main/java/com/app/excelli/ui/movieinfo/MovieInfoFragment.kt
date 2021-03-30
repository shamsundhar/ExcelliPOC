import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.excelli.R
import com.app.excelli.databinding.FragmentMovieInfoBinding
import com.app.excelli.ui.common.Constants
import com.app.excelli.ui.movieinfo.MovieInfoViewModel
import com.app.excelli.ui.movieinfo.MovieInfoViewModelFactory
import com.app.excelli.utils.ImageUtils
import com.app.excelli.utils.ProgressDialog
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


private var mbinding: FragmentMovieInfoBinding? = null
private val binding get() = mbinding!!

class MovieInfoFragment : Fragment(), MovieInfoViewModel.MovieInfoListener {

    private lateinit var viewModel: MovieInfoViewModel
    private lateinit var dialog: Dialog

    companion object {

        fun newInstance(movieID: String): MovieInfoFragment {
            val args = Bundle()
            val fragment = MovieInfoFragment()
            args.putSerializable(Constants.BUNDLE_KEY_MOVIE_ID, movieID)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog = ProgressDialog.progressDialog(context)

        val factory = MovieInfoViewModelFactory(
            arguments!!,
            this
        )
        viewModel = ViewModelProvider(this, factory).get(MovieInfoViewModel::class.java)

        viewModel.getMovieInfo()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }

    override fun displayLoading(){
        dialog.show()
    }

    override fun hideLoading(){
        dialog.hide()
    }

    private fun updateBackgroundImage(url: String) {
        Picasso.with(context)
            .load(url)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val bitmapDrawable =
                        BitmapDrawable(resources, bitmap?.let { ImageUtils.blur(it, context) })
                    binding.movieInfoParent.setBackgroundDrawable(bitmapDrawable)
                }

                override fun onBitmapFailed(errorDrawable: Drawable?) {}

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            })
    }

    override fun updateMovieInfo(movieInfoResponse: MovieInfoResponse?) {
        if (movieInfoResponse != null) {
            Picasso.with(context)
                .load(movieInfoResponse.poster)
                .into(binding.moviePoster)

            binding.mvTitle1.text = movieInfoResponse.title
            binding.description.text = movieInfoResponse.plot
            binding.rating.text = movieInfoResponse.ratings[0].value
            updateBackgroundImage(movieInfoResponse.poster)
        }
    }


}