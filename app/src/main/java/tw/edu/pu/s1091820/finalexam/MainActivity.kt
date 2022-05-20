package tw.edu.pu.s1091820.finalexam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import tw.edu.pu.s1091820.finalexam.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    var flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        img1.setOnClickListener(object:View.OnClickListener {
            override fun onClick(p0:View?){
                if(flag){
                    flag=false
                    img1.setImageResource(R.drawable.start)
                    job.cancel()

                }
                else{
                    flag=true
                    img1.setImageResource(R.drawable.stop)
                    job =GlobalScope.launch(Dispatchers.Main){
                        while(flag){
                            delay(25)
                            val canvas: Canvas = binding.mysv.holder.lockCanvas()
                            binding.mysv.drawSomething(canvas)
                            binding.mysv.holder.unlockCanvasAndPost(canvas)

                        }
                    }
                }

            }
        })
    }
}