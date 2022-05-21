package tw.edu.pu.s1091820.finalexam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
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


class MainActivity : AppCompatActivity() ,View.OnTouchListener{

    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    var flag:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        binding.imgfly.setOnTouchListener(this)


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
        if(flag==true){
            if (event?.action == MotionEvent.ACTION_MOVE){
                //v?.x = (175 - v!!.width/2).toFloat()
                v?.y = event.rawY - v!!.height/2
            }
            return true
        }
        else{
            return false
        }
    }

}