package com.example.jstyle

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var signInBtn: Button

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    private lateinit var forgotPassword: TextView


    private lateinit var firebaseAuth: FirebaseAuth


    private val emailRegex = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    private lateinit var parentFrameLayout: FrameLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        forgotPassword = view.findViewById(R.id.forgotSigIn)

        parentFrameLayout = activity!!.findViewById(R.id.register_framelayout)
        email = view.findViewById(R.id.emailText)
        password = view.findViewById(R.id.passwordText)

        signInBtn = view.findViewById(R.id.SignInButton)

        progressBar = view.findViewById(R.id.progressBar)
        textView = view.findViewById(R.id.buttonText)

        firebaseAuth = FirebaseAuth.getInstance()



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonView: Button = view.findViewById(R.id.SignUpButton)
        textView.visibility = View.GONE
        Handler().postDelayed({
            textView.visibility = View.VISIBLE

        }, 10)

        forgotPassword.setOnClickListener{

            activity!!.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit, R.anim.fragment_open_enter, R.anim.fragment_open_exit )
                .replace(parentFrameLayout.id, ResetPasswordFragment())
                .addToBackStack(null)
                .commit()


        }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputs()
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputs()
            }
        })



        view.SignInButton.setOnClickListener {

            checkEmailAndPassword()



        }


        view.SignUpButton.setOnClickListener {
            textView.visibility = View.GONE


            setFragment(SignUpFragment(), buttonView)


        }


    }

    private fun setFragment(fragment: Fragment, buttonView: Button) {
        val fragmentTransaction =
            activity!!.supportFragmentManager.beginTransaction().addSharedElement(buttonView, getString(R.string.signUpAnim))
        fragmentTransaction.replace(parentFrameLayout.id, fragment)
        fragmentTransaction.commit()
    }

    private fun checkInputs() {
        //TAKNAK BAGI NAMPAK TERUS KELABU

        if (!TextUtils.isEmpty(email.text)) {
            if (!TextUtils.isEmpty(password.text)) {
                signInBtn.isEnabled = true
                textView.setTextColor(Color.parseColor("#FFFFFF"))


            } else {
                signInBtn.isEnabled = false
                textView.setTextColor(Color.parseColor("#AEAEAE"))


            }

        } else {
            signInBtn.isEnabled = false
            textView.setTextColor(Color.parseColor("#AEAEAE"))


        }

    }

    private fun String.isEmail(): Boolean {
        return emailRegex.matcher(this).matches()
    }


    private fun checkEmailAndPassword() {

        val icon = context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_error) }
        icon!!.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)



        if (email.text.toString().isEmail()) {
            if (password.length() >= 8) {

                textView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                signInBtn.isEnabled = false

                firebaseAuth.signInWithEmailAndPassword(email.text.toString(),
                    password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            startActivity(Intent(activity, MainActivity::class.java))
                            activity?.finish()


                        } else {
                            textView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            signInBtn.isEnabled = true

                            var error: String? = task.exception?.message
                            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()

                        }
                    }


            } else {
                Toast.makeText(activity, "Incorrect email or password!", Toast.LENGTH_SHORT).show()

            }

        } else {
            Toast.makeText(activity, "Incorrect email or password!", Toast.LENGTH_SHORT).show()

        }

    }


}
