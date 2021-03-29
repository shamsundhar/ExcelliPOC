import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.excelli.databinding.FragmentMovieInfoBinding
import com.app.excelli.ui.common.Constants
import com.app.excelli.ui.movieinfo.MovieInfoViewModel
import com.app.excelli.ui.movieinfo.MovieInfoViewModelFactory
import com.app.excelli.utils.ImageUtils
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


private var _binding: FragmentMovieInfoBinding? = null
private val binding get() = _binding!!

class MovieInfoFragment : Fragment(), MovieInfoViewModel.MovieInfoListener {

    private lateinit var viewModel: MovieInfoViewModel

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
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        val view = binding.root

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
        _binding = null
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

    override fun updateMovieInfo(movieInfoData: MovieInfoResponse?) {
        if (movieInfoData != null) {
            Picasso.with(context)
                .load(movieInfoData.poster)
                .into(binding.moviePoster)

            binding.mvTitle1.text = movieInfoData.title
            binding.description.text = movieInfoData.plot
            binding.rating.text = movieInfoData.ratings[0].value
            updateBackgroundImage(movieInfoData.poster)
        }
    }


}