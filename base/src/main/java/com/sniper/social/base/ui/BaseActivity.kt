package com.sniper.social.base.ui

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.sniper.social.base.mvp.Presenter
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.dsl.module.Module
import org.koin.standalone.StandAloneContext.loadKoinModules

abstract class BaseActivity<P : Presenter> : AppCompatActivity() {

    abstract val presenter: P

    abstract fun getScreenScope(): String
    abstract fun getScreenLayout(): Int
    abstract fun getModule(): Module

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getScreenLayout())

        loadKoinModules(getModule())

        bindScope(getOrCreateScope(getScreenScope()))
    }

    override fun onStop() {
        super.onStop()
        if (isFinishing) {
            getKoin().getScope(getScreenScope()).close()
            presenter.destroy()
        }
    }

    protected fun displayError(errorMessage: String) {
        val view: View? = findViewById(android.R.id.content)
        when (view) {
            null -> Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            else -> Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}
