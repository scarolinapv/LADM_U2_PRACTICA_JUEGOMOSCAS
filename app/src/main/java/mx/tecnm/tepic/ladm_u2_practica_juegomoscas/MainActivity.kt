package mx.tecnm.tepic.ladm_u2_practica_juegomoscas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Lienzo(this))
        startTimer()
    }

    fun startTimer(){
        object : CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                start()
            }
        }
    }


}