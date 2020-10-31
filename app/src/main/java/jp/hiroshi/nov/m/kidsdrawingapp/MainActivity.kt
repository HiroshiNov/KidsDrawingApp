package jp.hiroshi.nov.m.kidsdrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawing_view.setSizeForBrush(20.toFloat())

        ib_brush.setOnClickListener{
            showBrushSizeChooserDialog()
        }

    }

    private fun showBrushSizeChooserDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size) //set what you want to show as dialog
        brushDialog.setTitle("Brush size:")
        val smallBtn = brushDialog.ib_small_brush // once we click the brush btn dialog disappear and brushsize is changed
        smallBtn.setOnClickListener{
            drawing_view.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.ib_medium_brush // once we click the brush btn dialog disappear and brushsize is changed
        mediumBtn.setOnClickListener{
            drawing_view.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.ib_large_brush // once we click the brush btn dialog disappear and brushsize is changed
        largeBtn.setOnClickListener{
            drawing_view.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()



    }
}
