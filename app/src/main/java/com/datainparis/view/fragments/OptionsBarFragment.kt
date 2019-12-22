package com.datainparis.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datainparis.R


class OptionsBarFragment : Fragment() {

    private var listenerOptionsBar: OnOptionsBarFragmentInteractionListener? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options_bar, container, false)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnOptionsBarFragmentInteractionListener) {
            listenerOptionsBar = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnBottomFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerOptionsBar = null
    }


    interface OnOptionsBarFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onOptionsBarFragmentInteraction(uri: Uri)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionsBarFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    fun setOptionBarSelectedListener(callback: OnOptionsBarFragmentInteractionListener) {
        this.listenerOptionsBar = callback
    }




}
