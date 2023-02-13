package googlecodelabs.keyboarddialphone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var phoneEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneEditText = findViewById(R.id.phone_edit_text)
        phoneEditText.apply {
            setOnEditorActionListener { _, actionId, _ ->
                var handled = false
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    dialNumber()
                    handled = true
                }
                return@setOnEditorActionListener handled
            }
        }
    }

    private fun dialNumber(){
        val phoneNum : String = "tel: ${phoneEditText.text}"

        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNum))

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }else{
            Log.d("Implicit Intents", "Can't handle this intent!")
        }
    }
}