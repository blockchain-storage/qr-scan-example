package com.devbridie.blockchainqr

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(object : ZXingScannerView.ResultHandler {
            override fun handleResult(result: Result) {
                with(result) {
                    AlertDialog.Builder(this@MainActivity)
                            .setTitle(barcodeFormat.toString())
                            .setMessage(text)
                            .setNeutralButton("OK", null)
                            .show()
                }
                vibrator.vibrate(100)
                scannerView.resumeCameraPreview(this)
            }
        })
        scannerView.startCamera()

    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()

    }
}
