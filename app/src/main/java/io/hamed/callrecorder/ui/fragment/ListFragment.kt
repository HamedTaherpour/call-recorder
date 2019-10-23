package io.hamed.callrecorder.ui.fragment

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import io.hamed.callrecorder.BaseFragment
import io.hamed.callrecorder.ContactEntity

import io.hamed.callrecorder.R
import io.hamed.callrecorder.ui.adapter.ContactAdapter


class ListFragment : BaseFragment() {


    @BindView(R.id.rv)
    lateinit  var recyclerView: RecyclerView

    private lateinit var adapter: ContactAdapter

    override fun initView() {
//        nextF.setOnClickListener { view ->
//            view.findNavController().navigate(R.id.next_action)
//        }
        recyclerViewHandler()
    }

    override fun initData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    fun recyclerViewHandler(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ContactAdapter()
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(item: ContactEntity) {

            }
        })
        val list = listOf(
            ContactEntity(3,"09371508751","Monica Bellucci","WhatsApp",true,true,"http://uupload.ir/files/pfwz_photo_2019-10-23_16-56-46.jpg"),
            ContactEntity(1,"09371508754","Pavel Durov","WhatsApp",true,false,"http://uupload.ir/files/m6r_photo_2019-10-22_01-41-09.jpg"),
            ContactEntity(4,"09371508753","Emma Watson","WhatsApp",false,true,"http://uupload.ir/files/ukkf_26152827_382793632132975_3672520410462683136_n.jpg"),
            ContactEntity(2,"09371508752","David Beckham","WhatsApp",false,false,"http://uupload.ir/files/cro0_photo_2019-10-20_23-20-26.jpg"),
            ContactEntity(4,"09371508753","Margot Elise Robbie","WhatsApp",false,true,"http://uupload.ir/files/qhmx_photo_2019-10-23_17-03-04.jpg"),
            ContactEntity(4,"09371508753","Tom Hardy","WhatsApp",false,true,"http://uupload.ir/files/fdmd_75311726_521610801729566_2373465154581774971_n.jpg"),
            ContactEntity(4,"09371508753","Evan Rachel Wood","WhatsApp",false,true,"http://uupload.ir/files/equc_67712822_431992684325174_5503876979468970474_n.jpg")
        )
        adapter.submitList(list)
    }

}
