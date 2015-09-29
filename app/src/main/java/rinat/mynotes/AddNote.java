package rinat.mynotes;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity implements View.OnClickListener {
    Button cancelSaveButton;
    Button saveButton;
    final String ATTRIBUTE_NAME_TOPIC = "topic";
    final String ATTRIBUTE_NAME_TEXT = "text";
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        Log.d(TAG, "Add: onCreate()");

            Intent viewIntent = getIntent();
            String action =viewIntent.getAction();
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
            saveButton = (Button) findViewById(R.id.saveButton);
            saveButton.setOnClickListener(this);
            cancelSaveButton = (Button) findViewById(R.id.cancelSaveButton);
            cancelSaveButton.setOnClickListener(this);
    }
    public  void onClick(View v){
        EditText topic = (EditText) findViewById(R.id.addTopicForNote);
        EditText note = (EditText) findViewById(R.id.addTextForNote);
        String aTopic = topic.getText().toString();
        String aNote = note.getText().toString();
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.saveButton:
                if(aTopic.equals("")|| aNote.equals("")){
                    AlertDialog.Builder adb=new AlertDialog.Builder(this);
                    adb.setCancelable(false);  // чтобы пользователь не смог нажать назад
                    adb.setTitle("Error!");
                    adb.setMessage("You are not fill all fields");
                    adb.setPositiveButton("Ok", null);
                    adb.show();
                }
                else{
                    intent.putExtra("Topic", aTopic);
                    intent.putExtra("Note", aNote);
                    setResult(RESULT_OK, intent);
                finish();}
                break;
            case R.id.cancelSaveButton:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Add: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Add: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Add: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Add: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Add: onDestroy()");
    }
}
