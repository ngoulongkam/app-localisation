package com.example.app_localisation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {
    private var languageMenuItem: MenuItem? = null
    private var shouldShowLangToggle: Boolean = true
    private var isInEng: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isInEng = savedInstanceState.getBoolean("isInEng")
        }
        setContentView(R.layout.activity_main)

        title = getString(R.string.screen_title)
        val dialogButton = findViewById<Button>(R.id.dialog_button)

        dialogButton.setOnClickListener {
            AlertDialog.Builder(this).create().apply {
                setTitle(getString(R.string.dialog_title))
                setMessage(getString(R.string.dialog_text))
                show()
            }
        }

        val switchButton = findViewById<Button>(R.id.switch_fragment_button)
        switchButton.setOnClickListener {
            loadFragment(OneFragment())
        }

        val switchTwoButton = findViewById<Button>(R.id.switch_fragment_two_button)
        switchTwoButton.setOnClickListener {
            loadFragment(TwoFragment())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isInEng", isInEng)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (shouldShowLangToggle) {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.icon, menu)
        if (menu != null) {
            languageMenuItem = menu.findItem(R.id.action_language)
                val setIcon = if (isInEng) R.drawable.ic_welsh else R.drawable.ic_english
                languageMenuItem?.icon = ResourcesCompat.getDrawable(resources, setIcon, null)
            }
        } else {
            updateAppLocales("en_rGB")
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language -> {
                val lang = if (isInEng) "cy" else "en_rGB"
                updateAppLocales(lang)
                isInEng = !isInEng
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateAppLocales(locales: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(locales)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    private fun loadFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
}