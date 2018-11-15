package com.sniper.social.base.ui

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.dsl.module.Module
import org.koin.standalone.StandAloneContext

abstract class BaseActivity : AppCompatActivity() {
    //public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {

    //    protected abstract P getPresenter();
    abstract fun getScreenScope(): String

    abstract fun getScreenLayout(): Int
    abstract fun getModule(): Module

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getScreenLayout())
        StandAloneContext.getKoin().loadModules(listOf(getModule()))

        bindScope(getOrCreateScope(getScreenScope()))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            getKoin().getScope(getScreenScope()).close()
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
