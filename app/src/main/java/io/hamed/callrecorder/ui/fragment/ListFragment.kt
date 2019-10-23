package io.hamed.callrecorder.ui.fragment

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import io.hamed.callrecorder.BaseFragment
import io.hamed.callrecorder.ContactEntity

import io.hamed.callrecorder.ui.adapter.ContactAdapter
import androidx.navigation.Navigation
import io.hamed.callrecorder.R


class ListFragment : BaseFragment() {

    @BindView(R.id.rv)
    lateinit var recyclerView: RecyclerView

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

    fun recyclerViewHandler() {
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
                val navController =
                    Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                navController.navigate(R.id.next_action)
            }
        })
        val list = listOf(
            ContactEntity(
                1,
                "(541) 754-3010",
                "Tom Hardy",
                "(541) 754-3010",
                false,
                true,
                "https://api.androidhive.info/json/images/tom_hardy.jpg"
            ),
            ContactEntity(
                1,
                "(452) 839-1210",
                "Johnny Depp",
                "(452) 839-1210",
                false,
                false,
                "https://api.androidhive.info/json/images/johnny.jpg"
            ),
            ContactEntity(
                1,
                "(541) 453-2311",
                "Tom Cruise",
                "(541) 453-2311",
                false,
                false,
                "https://api.androidhive.info/json/images/tom_cruise.jpg"
            ),
            ContactEntity(
                1,
                "(535) 324-4334",
                "Keira Knightley",
                "(535) 324-4334",
                false,
                true,
                "https://api.androidhive.info/json/images/keira.jpg"
            ),
            ContactEntity(
                1,
                "(767) 544-8867",
                "Robert De Niro",
                "(767) 544-8867",
                true,
                false,
                "https://api.androidhive.info/json/images/robert_de.jpg"
            ),
            ContactEntity(
                1,
                "(564) 333-2452",
                "Leonardo DiCaprio",
                "(564) 333-2452",
                false,
                false,
                "https://api.androidhive.info/json/images/leonardo.jpg"
            ),
            ContactEntity(
                1,
                "(541) 879-3453",
                "Will Smith",
                "(541) 879-3453",
                true,
                true,
                "https://api.androidhive.info/json/images/will.jpg"
            ),
            ContactEntity(
                1,
                "(234) 234-3321",
                "Russell Crowe",
                "(234) 234-3321",
                false,
                true,
                "https://api.androidhive.info/json/images/russell.jpg"
            ),
            ContactEntity(
                1,
                "(567) 754-8945",
                "Brad Pitt",
                "(567) 754-8945",
                false,
                true,
                "https://api.androidhive.info/json/images/brad.jpg"
            ),
            ContactEntity(
                1,
                "(324) 754-5433",
                "Angelina Jolie",
                "(324) 754-5433",
                false,
                true,
                "https://api.androidhive.info/json/images/angelina.jpg"
            ),
            ContactEntity(
                1,
                "(788) 343-3433",
                "Kate Winslet",
                "(788) 343-3433",
                true,
                true,
                "https://api.androidhive.info/json/images/kate.jpg"
            ),
            ContactEntity(
                1,
                "(865) 755-3555",
                "Christian Bale",
                "(865) 755-3555",
                false,
                false,
                "https://api.androidhive.info/json/images/christian.jpg"
            ),
            ContactEntity(
                1,
                "(445) 776-9076",
                "Morgan Freeman",
                "(445) 776-9076",
                false,
                true,
                "https://api.androidhive.info/json/images/morgan.jpg"
            ),
            ContactEntity(
                1,
                "(544) 454-4544",
                "Hugh Jackman",
                "(544) 454-4544",
                true,
                true,
                "https://api.androidhive.info/json/images/hugh.jpg"
            ),
            ContactEntity(
                1,
                "(454) 455-5445",
                "Keanu Reeves",
                "(454) 455-5445",
                false,
                true,
                "https://api.androidhive.info/json/images/keanu.jpg"
            ),
            ContactEntity(
                1,
                "(541) 454-4544",
                "Tom Hanks",
                "(541) 454-4544",
                true,
                true,
                "https://api.androidhive.info/json/images/tom.jpg"
            ),
            ContactEntity(
                1,
                "(545) 454-2567",
                "Scarlett Johansson",
                "(545) 454-2567",
                false,
                true,
                "https://api.androidhive.info/json/images/scarlett.jpg"
            ),
            ContactEntity(
                1,
                "(444) 444-4444",
                "Robert Downey Jr.",
                "(444) 444-4444",
                false,
                false,
                "https://api.androidhive.info/json/images/robert.jpg"
            )
        )
        adapter.submitList(list)
    }

}
