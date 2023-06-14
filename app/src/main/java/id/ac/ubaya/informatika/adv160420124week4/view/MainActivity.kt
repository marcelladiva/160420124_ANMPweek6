package id.ac.ubaya.informatika.adv160420124week4.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ubaya.informatika.adv160420124week4.R
import id.ac.ubaya.informatika.adv160420124week4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        var instance:MainActivity ?= null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

        val observable = Observable.just("a stream of data","hellow","world")
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Log.d("observermessage", "Begin Subscribe")
            }

            override fun onNext(t: String?) {
                Log.e("observermessage", "Data: $t")
            }

            override fun onError(e: Throwable?) {
                Log.e("observermessage", "Error:  ${e!!.message.toString()}")
            }

            override fun onComplete() {
                Log.d("observermessage", "complete")
            }
        }

        observable.apply {
            subscribeOn(Schedulers.io())
            observeOn(AndroidSchedulers.mainThread())
            subscribe(observer)
        }
    }
}