package com.example.app_localisation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment

class OneFragment : Fragment() {

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateAppLocales("en_rGB")
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    private fun updateAppLocales(locales: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(locales)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}