package com.saquib.gmaildrawer2

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.saquib.gmaildrawer2.fragments.MatchesFragment
import com.saquib.gmaildrawer2.fragments.SaveFragment
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var navigationView: NavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)
        Objects.requireNonNull(supportActionBar)?.setTitle("Gmail")
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView = findViewById(R.id.nav_view)
        navigationView!!.setNavigationItemSelectedListener(this)
        navigationView!!.getMenu().getItem(0).isChecked = true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id = item.itemId
        when (id) {
            R.id.all_matches -> replaceFragment(MatchesFragment())
            R.id.saved_matches -> replaceFragment(SaveFragment())
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}