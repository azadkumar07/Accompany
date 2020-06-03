package com.example.chandrakant.accompanyapp

import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.app_bar_sign_in.*
import kotlinx.android.synthetic.main.content_sign_in.*

class SignIn : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var useremailFromFB : ArrayList<String> = ArrayList<String>()
    var userImageFromFB : ArrayList<String> = ArrayList<String>()
    var userCommentFromFB : ArrayList<String> = ArrayList<String>()
    var firebaseDatabase: FirebaseDatabase? = null
    var myRef : DatabaseReference? = null
    var adapter : PostClass? = null


    var locationManager : LocationManager? = null
    var locationListener : LocationListener? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)




        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()

        adapter = PostClass(useremailFromFB,userImageFromFB,userCommentFromFB,this)

       listview.adapter = adapter

        getDataFromFirebase()





        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Toast.makeText(applicationContext,"Reload Successful", Toast.LENGTH_LONG).show()

            val intent = Intent(applicationContext,SignIn::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }



    fun getDataFromFirebase() {

        val newReference = firebaseDatabase!!.getReference().child("Posts")

        newReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {


                //println(p0)
                //println("children: " + p0!!.children)
                //println("key:" + p0!!.key)
                //println("value:" + p0!!.value)

                adapter!!.clear()
                userImageFromFB.clear()
                userCommentFromFB.clear()
                useremailFromFB.clear()


                for (snapshot in p0.children) {

                    val hashMap = snapshot.value as HashMap<String,String>

                    if (hashMap.size > 0 ) {

                        val email = hashMap["useremail"]
                        val comment = hashMap["comment"]
                        val image = hashMap["downloadurl"]

                        if (email != null) {
                            useremailFromFB.add(email)
                        }

                        if (comment != null) {
                            userCommentFromFB.add(comment)
                        }

                        if (image != null) {
                            userImageFromFB.add(image)
                        }

                        adapter!!.notifyDataSetChanged()


                    }



                }




            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }



        })

    }





















    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.sign_in, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.addpost -> {
                val intent = Intent(applicationContext,upload::class.java)
                startActivity(intent)

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {
                val intent = Intent(applicationContext,rate::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                val intent = Intent(applicationContext,aboutus::class.java)
                startActivity(intent)
            }
            R.id.Sign_out -> {
                FirebaseAuth.getInstance().signOut()
                finish()
                Toast.makeText(applicationContext,"You have been signed out successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,Realsignin::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }




}
