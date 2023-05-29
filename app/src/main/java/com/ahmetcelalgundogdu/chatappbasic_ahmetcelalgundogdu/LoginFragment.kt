package com.ahmetcelalgundogdu.chatappbasic_ahmetcelalgundogdu

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ahmetcelalgundogdu.chatappbasic_ahmetcelalgundogdu.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupbutton.setOnClickListener{

            auth.createUserWithEmailAndPassword(binding.emailtext.text.toString(),binding.password.text.toString()).addOnSuccessListener {

                findNavController().navigate(R.id.action_loginFragment_to_chatFragment)

            }.addOnFailureListener{ exception ->

                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

        binding.loginbutton.setOnClickListener{

            auth.signInWithEmailAndPassword(binding.emailtext.text.toString(),binding.password.text.toString()).addOnSuccessListener{

                findNavController().navigate(R.id.action_loginFragment_to_chatFragment)

            }.addOnFailureListener{
                Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}