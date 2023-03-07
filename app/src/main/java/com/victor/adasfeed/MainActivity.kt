package com.victor.adasfeed

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var buttonVerMais: Button
    private lateinit var textDescricao: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relative_layout_container)

        /** Conseguimos referenciar nossas views criadas no XML **/
//        val textTituloDoApp = findViewById<TextView>(R.id.textTituloDoAppAda)
//        val imageFotoDePerfil = findViewById<ImageView>()
//        val editTextSenha = findViewById<EditText>()

//        val buttonVerMenos = findViewById<Button>(R.id.buttonVerMenos)
//        buttonVerMais = findViewById(R.id.buttonVerMais)
        textDescricao = findViewById(R.id.textDescricao)

        val txt = ""
        val newText = "aaaa"
        print(txt)

        mostrarMaisDescricao()

        /** Conseguimos adicionar listeners de click para nossas views da seguinte forma: **/
//        buttonVerMenos.setOnClickListener {
//            textDescricao.maxLines = 2
//        }

        /** Conseguimos alterar o estado  **/
//        if (2 > 3) {
//            textTituloDoApp.text = "2 é maior do que 3???"
//            textTituloDoApp.visibility = View.GONE
//        } else {
//            textTituloDoApp.text = "Ah bom!"
//            textTituloDoApp.visibility = View.GONE
//        }

//        class MeuEditTextCustomizadoView : EditText(applicationContext) {
//            fun minhaFuncaoCustomizada() {}
//        }

//        CoroutineScope(Dispatchers.Main).launch {
//            delay(5000L)
//            textDescricao.text = "Quem leu leu, quem não leu não le mais"
//        }
    }

    /** Conseguimos criar funcoes para lidar com os eventos de clique e
     * organizar melhor nosso código **/
    fun mostrarMaisDescricao() {
        buttonVerMais.setOnClickListener {
            textDescricao.maxLines = Integer.MAX_VALUE
        }
    }
}