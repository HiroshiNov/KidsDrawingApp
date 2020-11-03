package jp.hiroshi.nov.m.kidsdrawingapp

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.RippleDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
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

        ib_gallery.setOnClickListener {  //lesson 121
            if(isReadStorageAllowed()){
                //run our code to get the image from gallery
            }else{
                requestStoragePermission()
            }
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

    private fun requestStoragePermission(){  //lesson 121
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE).toString())){
            Toast.makeText(this, "Need permission to add a Background",Toast.LENGTH_LONG).show()
        }
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE),
            STORAGE_PERMISSINO_CODE)
    }

    override fun onRequestPermissionsResult(  //lesson 121
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == STORAGE_PERMISSINO_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Permission granted now you can read the storage.",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Oops you just denied the permission.",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun isReadStorageAllowed():Boolean{  //lesson 121
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    companion object{  //lesson 121
        private const val STORAGE_PERMISSINO_CODE = 1
    }
}
