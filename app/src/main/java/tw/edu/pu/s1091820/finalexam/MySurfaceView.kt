package tw.edu.pu.s1091820.finalexam

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context?, attrs: AttributeSet?)
    : SurfaceView(context, attrs) ,SurfaceHolder.Callback{

    var surfaceHolder: SurfaceHolder
    var BG: Bitmap
    //lateinit var SuperMan:Bitmap
    var BGmoveX:Int = 0


    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        //SuperMan = BitmapFactory.decodeResource(getResources(), R.drawable.superman)
        surfaceHolder.addCallback(this)
    }



    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }
    override fun surfaceCreated(p0: SurfaceHolder) {
        var canvas: Canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)

    }
    fun drawSomething(canvas:Canvas) {
        canvas.drawBitmap(BG, 0f, 0f, null)
        //canvas.drawBitmap(SuperMan, 100f, 100f, null)

    }

}