package io.hamed.callrecorder.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuCompat
import androidx.navigation.Navigation
import butterknife.BindView
import io.hamed.callrecorder.BaseFragment
import io.hamed.callrecorder.MainActivity
import io.hamed.callrecorder.MediaPlayerWave
import io.hamed.callrecorder.R
import rm.com.audiowave.AudioWaveView


class DetailFragment : BaseFragment() {

    @BindView(R.id.wave)
    lateinit var waveView: AudioWaveView
    @BindView(R.id.tv_name)
    lateinit var tvName: TextView
    @BindView(R.id.tv_phone)
    lateinit var tvPhone: TextView
    @BindView(R.id.iv_profile)
    lateinit var ivProfile: ImageView
    @BindView(R.id.btn_play)
    lateinit var btnPlay: AppCompatImageButton
    @BindView(R.id.tv_date)
    lateinit var tvDate: TextView
    @BindView(R.id.tv_description)
    lateinit var tvDescription: TextView
    @BindView(R.id.tv_timer)
    lateinit var tvTimer: TextView
    @BindView(R.id.tv_final_timer)
    lateinit var tvFinalTimer: TextView
    @BindView(R.id.btn_selected_favorite)
    lateinit var btnSelectedFavorite: AppCompatImageButton
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    var mediaPlayerWave: MediaPlayerWave? = null

    override fun initView() {
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).setTitle("")
        setHasOptionsMenu(true)
    }

    override fun initData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val navController =
                    Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                navController.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
