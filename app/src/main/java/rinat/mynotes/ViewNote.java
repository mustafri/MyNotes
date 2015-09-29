package rinat.mynotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewNote extends AppCompatActivity {
    Context ctx=ViewNote.this;
    private static final String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note);
        Log.d(TAG, "View: onCreate()");
        final Intent intent = getIntent();

        final TextView setTopic= (TextView)findViewById(R.id.viewTopicForNote);
        final TextView setNote= (TextView)findViewById(R.id.viewTextForNote);

        setTopic.setText(intent.getStringExtra("Topic"));
        setNote.setText(intent.getStringExtra("Note"));

        LinearLayout viewNoteLayer=(LinearLayout )findViewById(R.id.viewNoteLayer);
        viewNoteLayer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intentChange=new Intent(ctx, AddNote.class);
                intentChange.putExtra("Topic",setTopic.getText());
                intentChange.putExtra("Note",setNote.getText());
                intentChange.setAction("ViewNote");
                startActivity(intentChange);
                finish();
            return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
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
        Log.d(TAG, "View: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "View: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "View: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "View: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "View: onDestroy()");
    }
}