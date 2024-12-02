package com.rida.labo5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    val STATE_FRAGMENT = "state_of_fragment"
    private lateinit var mButton : Button
    private var isFragmentDisplayed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton = findViewById(R.id.open_button);


        mButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (!isFragmentDisplayed)
                {
                    displayFragment()
                } else {
                    closeFragment()
                }
            }
        })
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    fun displayFragment() {
        val simpleFragment = SimpleFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit()

        mButton.setText(R.string.close)

        isFragmentDisplayed = true
    }

    fun closeFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)

        // Si le fragment existe, le retirer
        if (simpleFragment != null) {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        mButton.setText(R.string.open)

        isFragmentDisplayed = false
    }

}