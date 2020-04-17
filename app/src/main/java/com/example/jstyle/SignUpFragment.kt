package com.example.jstyle

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
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
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import java.util.regex.Pattern.compile


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    private lateinit var parentFrameLayout: FrameLayout
    private lateinit var email: EditText
    private lateinit var fullName: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText

    private lateinit var signUpBtn: Button

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFireStore: FirebaseFirestore

    private val emailRegex = compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        parentFrameLayout = activity!!.findViewById(R.id.register_framelayout)
        email = view.findViewById(R.id.emailSignUpText)
        fullName = view.findViewById(R.id.nameSignUpText)
        password = view.findViewById(R.id.passwordSignUpText)
        confirmPassword = view.findViewById(R.id.confirmPasswordText)

        signUpBtn = view.findViewById(R.id.SignUpButton2)

        progressBar = view.findViewById(R.id.progressBar2)
        textView = view.findViewById(R.id.buttonText2)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonView: Button = view.findViewById(R.id.SignInButton2)
        textView.visibility = View.GONE
        //naksuruh button disable terus awal2
        checkInputs()



        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputs()
            }
        })
        fullName.addTextChangedListener(object : TextWatcher {
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
        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputs()
            }
        })



        view.SignUpButton2.setOnClickListener {
            setColorFilter(
                view.progressBar2.indeterminateDrawable,
                ResourcesCompat.getColor(context!!.resources, R.color.colorPrimary, null)
            )
            checkEmailAndPassword()


        }

        view.SignInButton2.setOnClickListener {
            setFragment(SignInFragment(), buttonView)
        }

    }

    private fun setFragment(fragment: Fragment, buttonView: Button) {
        val fragmentTransaction =
            activity!!.supportFragmentManager.beginTransaction()
                .addSharedElement(buttonView, getString(R.string.signInAnim))
        fragmentTransaction.replace(parentFrameLayout.id, fragment)
        fragmentTransaction.commit()
    }

    private fun checkInputs() {
        //TAKNAK BAGI NAMPAK TERUS KELABU
        Handler().postDelayed({
            textView.visibility = View.VISIBLE

        }, 1)

        if (!TextUtils.isEmpty(email.text)) {
            if (!TextUtils.isEmpty(fullName.text)) {
                if (!TextUtils.isEmpty(password.text) && password.length() >= 8 && !TextUtils.isEmpty(
                        confirmPassword.text
                    )
                ) {
                    signUpBtn.isEnabled = true
                    textView.setTextColor(Color.parseColor("#FFFFFF"))

                } else {
                    signUpBtn.isEnabled = false
                    textView.setTextColor(Color.parseColor("#AEAEAE"))

                }

            } else {
                signUpBtn.isEnabled = false
                textView.setTextColor(Color.parseColor("#AEAEAE"))


            }

        } else {
            signUpBtn.isEnabled = false
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
            if (password.text.toString() == confirmPassword.text.toString()) {

                textView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                signUpBtn.isEnabled = false



                firebaseAuth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val userdata = HashMap<Any, String>()
                            userdata.put("fullname", fullName.text.toString())

                            firebaseFireStore.collection("USERS")
                                .add(userdata)
                                .addOnCompleteListener {
                                    if (task.isSuccessful) {

                                        startActivity(Intent(activity, MainActivity::class.java))
                                        activity?.finish()

                                    } else {
                                        textView.visibility = View.VISIBLE
                                        progressBar.visibility = View.GONE
                                        signUpBtn.isEnabled = true

                                        var error: String? = task.exception?.message
                                        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()

                                    }
                                }

                        } else {
                            textView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            signUpBtn.isEnabled = true
                            textView.setTextColor(Color.parseColor("#FFFFFF"))

                            var error: String? = task.exception?.message
                            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()

                        }
                    }


            } else {
                confirmPassword.setError("Password does not match!", icon)

            }

        } else {
            email.setError("This is not a valid email!", icon)

        }

    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

}
