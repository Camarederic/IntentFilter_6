package com.example.intentfilter_6

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.example.intentfilter_6.databinding.ActivityMainBinding
import java.security.Permission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            granted ->
            if (granted){
                getAndSetImage(binding.imageView)
            }
        }

        if (PermissionChecker.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED){
            getAndSetImage(binding.imageView)
        }else{
            permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun getAndSetImage(image: ImageView){
        intent?.let {
            if (it.action == Intent.ACTION_VIEW){
                val selectedImage: Uri? = intent.data
                binding.imageView.setImageURI(selectedImage)
            }
        }
    }
}