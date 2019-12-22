package com.datainparis.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BottomBarFragment : Fragment() {
    private var listenerBottom: OnBottomFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.datainparis.R.layout.fragment_bottom_bar, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBottomFragmentInteractionListener) {
            listenerBottom = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnBottomFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerBottom = null
    }


    interface OnBottomFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomBarFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}
