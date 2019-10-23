package io.hamed.callrecorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife


/**
 * Author: Hamed Taherpour
 * *
 * Created: 10/23/2019
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, view)
        initView()
        initData()
        return view
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun getLayoutId(): Int

}