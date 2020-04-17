package com.example.jstyle

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth


class ResetPasswordFragment : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    private lateinit var registeredEmail: EditText
    private lateinit var resetPasswordButton: Button

    private lateinit var test: LottieAnimationView


    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        registeredEmail = view.findViewById(R.id.emailForget)
        resetPasswordButton = view.findViewById(R.id.buttonForget)
        test = view.findViewById(R.id.animation)


        firebaseAuth = FirebaseAuth.getInstance()


        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkInputs()



        registeredEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputs()
            }
        })



        resetPasswordButton.setOnClickListener {
            resetPasswordButton.isEnabled = false
            resetPasswordButton.setTextColor(Color.parseColor("#AEAEAE"))


            firebaseAuth.sendPasswordResetEmail(registeredEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        test.playAnimation()


                        Toast.makeText(activity, "Email sent successfuly", Toast.LENGTH_LONG).show()



                    } else {
                        resetPasswordButton.isEnabled = true
                        resetPasswordButton.setTextColor(Color.parseColor("#FFFFFF"))

                        var error: String? = task.exception?.message
                        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()

                    }
                }



        }


    }

    private fun checkInputs() {

        if (!TextUtils.isEmpty(registeredEmail.text)) {
            resetPasswordButton.isEnabled = true
            resetPasswordButton.setTextColor(Color.parseColor("#FFFFFF"))


        } else {
            resetPasswordButton.isEnabled = false
            resetPasswordButton.setTextColor(Color.parseColor("#AEAEAE"))


        }

    }

}
