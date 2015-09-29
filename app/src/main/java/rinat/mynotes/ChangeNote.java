package rinat.mynotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeNote extends AppCompatActivity implements View.OnClickListener {
    Button cancelSaveChangeButton;
    Button saveChangeButton;
    Context ctx=ChangeNote.this;
    private static final String TAG = "myLogs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_note);
        Log.d(TAG, "Change: onCreate()");
        final EditText topic = (EditText) findViewById(R.id.addTopicForNote);
        final EditText note = (EditText) findViewById(R.id.addTextForNote);

        final Intent viewIntent = getIntent();
        String action =viewIntent.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();

        topic.setText(viewIntent.getStringExtra("Topic"));
        note.setText(viewIntent.getStringExtra("Note"));

        saveChangeButton = (Button) findViewById(R.id.saveChangeButton);
        saveChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aTopic = topic.getText().toString();
                String aNote = note.getText().toString();
                Intent intent = new Intent(ctx, Main.class);
                if(aTopic.equals("")|| aNote.equals("")){
                    intent.putExtra("Topic", viewIntent.getStringExtra("Topic"));
                    intent.putExtra("Note", viewIntent.getStringExtra("Note"));
                }
                else{
                intent.putExtra("Topic", aTopic);
                intent.putExtra("Note", aNote);
                }
                intent.setAction("ChangeNote");
                setResult(RESULT_OK, intent);
                finish();
                }
        });

        cancelSaveChangeButton = (Button) findViewById(R.id.cancelSaveChangeButton);
        cancelSaveChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aTopic = topic.getText().toString();
                String aNote = note.getText().toString();
                Intent intent = new Intent(ctx, Main.class);
                intent.putExtra("Topic", viewIntent.getStringExtra("Topic"));
                intent.putExtra("Note", viewIntent.getStringExtra("Note"));
                intent.setAction("ChangeNote");
                startActivity(intent);
                finish();
            }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_note, menu);
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
    public void onClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Change: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Change: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Change: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Change: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Change: onDestroy()");
    }
}