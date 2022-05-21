package tw.edu.pu.s1091820.finalexam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import tw.edu.pu.s1091820.finalexam.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
@GlideModule
public final class MyAppGlideModule : AppGlideModule()


class MainActivity : AppCompatActivity() ,View.OnTouchListener,GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    var flag:Boolean=false
    lateinit var mper: MediaPlayer
    lateinit var gDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        binding.imgfly.setOnTouchListener(this)
       // mper = MediaPlayer.create(this,R.drawable.fly1)
        //mper = MediaPlayer.create(this,R.drawable.fly2)
        mper = MediaPlayer.create(this, R.raw.shoot)
        gDetector = GestureDetector(this, this)


        GlideApp.with(this)
            .load(R.drawable.me1)
            .circleCrop()
            .override(800, 600)
            .into(img)

        img1.setOnClickListener(object:View.OnClickListener {
            override fun onClick(p0:View?){
                if(flag){
                    flag=false
                    img1.setImageResource(R.drawable.start)
                    imgfly.setImageResource(R.drawable.fly1)
                    job.cancel()

                }
                else{

                    flag=true
                    img1.setImageResource(R.drawable.stop)
                    job =GlobalScope.launch(Dispatchers.Main){
                        while(flag){

                            delay(25)
                            imgfly.setImageResource(R.drawable.fly1)
                            delay(10)
                            imgfly.setImageResource(R.drawable.fly2)
                            val canvas: Canvas = binding.mysv.holder.lockCanvas()
                            binding.mysv.drawSomething(canvas)
                            binding.mysv.holder.unlockCanvasAndPost(canvas)
                        }
                    }
                }
            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)

        if(flag==true){
            if (event?.action == MotionEvent.ACTION_MOVE){
                v?.y = event.rawY - v!!.height/2
            }
            return true
        }
        else{
            return false
        }
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        mper.start()
        job =GlobalScope.launch(Dispatchers.Main){
            delay(10)
            imgfly.setImageResource(R.drawable.shoot1)
            delay(10)
            imgfly.setImageResource(R.drawable.shoot2)
            delay(10)
            imgfly.setImageResource(R.drawable.shoot3)
            delay(10)
            imgfly.setImageResource(R.drawable.shoot4)
            delay(10)
            imgfly.setImageResource(R.drawable.shoot5)
        }
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        return true
    }

}