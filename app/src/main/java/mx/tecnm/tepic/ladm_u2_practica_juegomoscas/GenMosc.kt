package mx.tecnm.tepic.ladm_u2_practica_juegomoscas

import android.graphics.BitmapFactory

class GenMosc(l:Lienzo,ima:Int) {
    var x = 0f
    var y = 0f
    var moverx = 0
    var movery = 0
    var ram = 0
    var imagen = BitmapFactory.decodeResource(l.resources,ima)
    var Aplastada = 0
    var aplas = true

    init{
        x = (Math.random()*2100).toFloat()
        y = (Math.random()*1080).toFloat()
        moverx = (Math.random()*5).toInt()
        movery = (Math.random()*5).toInt()
    }

    fun moveMoscas(){
        if(Aplastada==0){
            if (moverx == 1) {
                x += (Math.random()*5).toInt()+(Math.random()*5).toInt()
            } else {
                x -= (Math.random()*5).toInt()+(Math.random()*5).toInt()
            }
            if (movery == 1) {
                y += (Math.random()*5).toInt()+(Math.random()*5).toInt()
            } else {
                y -= (Math.random()*5).toInt()+(Math.random()*5).toInt()
            }
            if(y>800){movery=2}
            if(y<0){movery=1}
            if(x>2100){moverx=2}
            if(x<0){moverx=1}
            ram++
            if(ram==700){
                moverx = (Math.random()*4).toInt()
                movery = (Math.random()*4).toInt()
                ram=0
            }
        }
    }

    fun Area(toqueX:Float,toqueY:Float):Boolean{

        var areax = x+imagen.width
        var areay = y+imagen.height

        if(toqueX >=x && toqueX <=areax && aplas){
            if(toqueY >=y && toqueY <=areay && aplas){
                aplas = false
                return true
            }
        }
        return false
    }

    fun moscaAplastada(){
        Aplastada=1
    }

}