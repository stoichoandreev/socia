package com.sniper.social.base.ui

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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

    abstract val screenScope: String
    abstract val screenLayout: Int
    abstract val screenModule: Module

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenLayout)

        loadKoinModules(screenModule)
        bindScope(getOrCreateScope(screenScope))
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onStop() {
        super.onStop()
        if (isFinishing) {
            getKoin().getScope(screenScope).close()
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
