package com.victor.adasfeed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var text: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // setup de injecao de dependencia
        // comportamento que nao de views e hierarquias

        Log.d("ciclo de vida", "onAttach")
        Log.d("contexto no onAttach()", activity?.applicationContext.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ciclo de vida", "onCreate")
        Log.d("contexto no onAttach()", activity?.applicationContext.toString())
    }

    /** PREFIRA USAR NO CONSTRUCTOR DO Fragment()**/
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View = inflater.inflate(R.layout.fragment_list, container, false)

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text = view.findViewById(R.id.textViewFragment)
        text.setOnClickListener { // faco oq quiser
        }
        Log.d("contexto no onAttach()", activity?.applicationContext.toString())
        Log.d("ciclo de vida", "onViewCreated")

//        activity?.applicationContext
//        activity?.finish()
//        activity?.actionBar?.customView?.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo de vida", "onStart")
        Log.d("contexto no onAttach()", activity?.applicationContext.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo de vida", "onResume")
        Log.d("contexto no onAttach()", activity?.applicationContext.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo de vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo de vida", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ciclo de vida", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo de vida", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ciclo de vida", "onDetach")
    }
}