package jp.hiroshi.nov.m.kidsdrawingapp

import android.app.Dialog
import android.graphics.drawable.RippleDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawing_view.setSizeForBrush(20.toFloat())

        mImageButtonCurrentPaint = ll_paint_colors[1] as ImageButton //LinearLayout second ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_normal)
        )

        ib_brush.setOnClickListener{  //lesson 111
            showBrushSizeChooserDialog()
        }




    }

    private fun showBrushSizeChooserDialog(){  //lesson 111
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

    fun paintClicked(view: View){ //lesson113 View is what clicked color button
        if (view !== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton

            val colorTag = imageButton.tag.toString() // define paint color
            drawing_view.setColor(colorTag)
            imageButton.setImageDrawable( //change appearance into clicked button
                ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal)
            )
            mImageButtonCurrentPaint = view //CurrentPaint is changed to the clicked imageButton

        }
    }

}
