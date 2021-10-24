package mx.tecnm.tepic.ladm_u2_practica_juegomoscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo (p:MainActivity) : View(p){
    val principal = p
    val cMoscas = 80
    val Gemosca = BitmapFactory.decodeResource(principal.resources, R.drawable.mosca)
    val ganar = BitmapFactory.decodeResource(principal.resources, R.drawable.ganaste)
    val perder = BitmapFactory.decodeResource(principal.resources, R.drawable.perdiste)
    val manchar = BitmapFactory.decodeResource(principal.resources, R.drawable.manchar)

    val moscas = Hilo(this)
    val time = Hilo2()
    var contaplast = 0
    var cont = 0

    init {
        moscas.start()
        time.start()
    }


    override fun onDraw(o: Canvas){
        super.onDraw(o)
        val p=Paint()

        (0..(cMoscas-1)).forEach{

            if((cont==0 && time.contador==59 || contaplast==cMoscas)) {
                moscas.detenerJuego()
                cont =1

                if(contaplast == cMoscas ){
                    o.drawBitmap(ganar,500f,1000f,p)
                }else{
                    o.drawBitmap(perder,500f,1000f,p)
                }

            }else{
                if (moscas.mosca[it].Aplastada == 1) {
                    o.drawBitmap(manchar, moscas.mosca[it].x, moscas.mosca[it].y, Paint())
                } else {
                    o.drawBitmap(Gemosca, moscas.mosca[it].x, moscas.mosca[it].y, Paint())
                }
            }
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        super.onTouchEvent(e)
        val accion = e.action

        when(accion){
            MotionEvent.ACTION_DOWN->{
                (0..(cMoscas-1)).forEach{
                    if(moscas.mosca[it].Area(e.x,e.y)){
                        moscas.mosca[it].moscaAplastada()
                        contaplast++
                    }
                }
            }
        }
        invalidate()
        return true
    }
}

class Hilo2():Thread(){
    var contador = 0
    override fun run(){
        super.run()
        while(contador<60) {
            contador++
            sleep(1000)
        }
    }
}
class Hilo(p:Lienzo):Thread(){
    val puntero = p
    val mosca = ArrayList<GenMosc>()
    val vel = 8
    val canMoscas = 80
    var detener = true

    fun detenerJuego(){
        detener = false
    }

    init{
        (1..canMoscas).forEach {
            val cur = GenMosc(p,R.drawable.mosca)
            mosca.add(cur)
        }
    }
    override fun run(){
        super.run()
        while(detener) {
            (0..(canMoscas-1)).forEach{
                mosca[it].moveMoscas()
            }
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }
            sleep(vel.toLong())
        }
    }
}
