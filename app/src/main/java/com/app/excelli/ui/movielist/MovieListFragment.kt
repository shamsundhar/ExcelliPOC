import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.excelli.MovieListAdapter
import com.app.excelli.R
import com.app.excelli.databinding.CustomLayoutBinding
import com.app.excelli.databinding.FragmentMoviesListBinding
import com.app.excelli.ui.activities.MainActivity
import com.app.excelli.ui.common.Constants.Companion.API_KEY
import com.app.excelli.ui.movielist.MovieListViewModel
import com.app.excelli.ui.movielist.MovieListViewModelFactory
import com.app.excelli.utils.ProgressDialog


class MovieListFragment : Fragment(), MovieListViewModel.MovieListener {
    private val MOVIE_INFO_TAG = "MovieInfoFragment"
    private var mbinding: FragmentMoviesListBinding? = null
    private val binding get() = mbinding!!

    private lateinit var viewModel: MovieListViewModel
    private lateinit var dialog: Dialog
    private lateinit var adapter: MovieListAdapter

    companion object {

        fun newInstance(): MovieListFragment {
            val args = Bundle()
            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentMoviesListBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog = ProgressDialog.progressDialog(context)
        val factory = MovieListViewModelFactory(
            this
        )
        viewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)


        adapter = context?.let { MovieListAdapter(ArrayList(), it) }!!
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.onItemClick = { movieItem ->
            openMovieInfo(movieItem.imdbID)
        }

        binding.searchIcon.setOnClickListener {
            openSearchDialog()
        }

        viewModel.loadMoviesList(API_KEY, "Spider")

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

    private fun openSearchDialog() {

        context?.let {
            val binding2 = CustomLayoutBinding.inflate(LayoutInflater.from(it))
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(binding2.root)
            binding2.searchBtn.setOnClickListener {
                if (binding2.searchTv.text == null || binding2.searchTv.text.toString()
                        .equals("", ignoreCase = true)
                ) {
                    Toast.makeText(context, getString(R.string.text_search_empty), Toast.LENGTH_SHORT).show()
                } else {
                    dialog.dismiss()
                    viewModel.loadMoviesList(API_KEY, binding2.searchTv.text.toString())
                }

            }
            dialog.show()
        }

    }

    private fun openMovieInfo(movieID: String) {
        val movieInfoFragment =
            MovieInfoFragment.newInstance(movieID)
        val mainActivity: MainActivity = context as MainActivity
        mainActivity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, movieInfoFragment, MOVIE_INFO_TAG)
            .addToBackStack(MOVIE_INFO_TAG)
            .commit()
    }

    override fun updateMoviesList(movieListResponse: MovieListResponse) {
        adapter.setNewData(movieListResponse.search)
    }
}