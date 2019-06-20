package com.pantaleon.imb_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        initTabNavigation()
    }

    private fun initTabNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)

        val animSlideRight = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
            }
        }
        val animSlideLeft = navOptions {
            anim {
                enter = R.anim.slide_in_left
                exit = R.anim.slide_out_right
            }
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text.toString()) {
                    getString(R.string.tab_text_1) -> navController.navigate(R.id.moviesFragment, null, animSlideLeft)
                    getString(R.string.tab_text_2) -> navController.navigate(R.id.favoritesFragment, null, animSlideRight)
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}