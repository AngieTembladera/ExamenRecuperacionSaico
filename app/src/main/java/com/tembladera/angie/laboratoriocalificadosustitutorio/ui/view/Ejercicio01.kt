package com.tembladera.angie.laboratoriocalificadosustitutorio.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tembladera.angie.laboratoriocalificadosustitutorio.data.network.RetrofitClient
import com.tembladera.angie.laboratoriocalificadosustitutorio.data.model.Post
import com.tembladera.angie.laboratoriocalificadosustitutorio.databinding.ActivityEjercicio01Binding
import com.tembladera.angie.laboratoriocalificadosustitutorio.ui.adapter.PostAdapter
import kotlinx.coroutines.launch

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val posts = RetrofitClient.apiService.getPosts()
                binding.recyclerView.adapter = PostAdapter(posts, ::clickSimple, ::clickLargo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun clickSimple(post: Post) {
        try {
            val url = "https://api.whatsapp.com/send?phone=+51925137361&text=${Uri.encode(post.title)}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            //Se prioriza whatsApp y si no se encuentra se envia el mensaje con sms
            val smsIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("smsto:+51925137361")
                putExtra("sms_body", post.title)
            }
            if (smsIntent.resolveActivity(packageManager) != null) {
                startActivity(smsIntent)
            }
        }
    }

    private fun clickLargo(post: Post) {
        try {
            val mailto = "mailto:victor.saico@tecsup.edu.pe?subject=${Uri.encode("Post Body")}&body=${Uri.encode(post.body)}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(mailto)
            startActivity(intent)
        } catch (e: Exception) {
        }
    }
}