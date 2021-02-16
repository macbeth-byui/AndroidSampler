package macbeth.androidsampler.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import macbeth.androidsampler.R;

public class EmailActivity extends AppCompatActivity {

    TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        tvEmail = findViewById(R.id.etEmail);

        Button bSendEmail = findViewById(R.id.bSendEmail);
        bSendEmail.setOnClickListener((view) -> { sendEmail(); });
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String [] emails = {tvEmail.getText().toString()};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message from AndroidSampler");
        String message = "<b>Hello From AndroidSampler!</b><p>I hope you are having a <b>GREAT</b> day!";
        Log.d("email",Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY).toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
        emailIntent.setType("text/html");
        startActivity(emailIntent);
    }
}