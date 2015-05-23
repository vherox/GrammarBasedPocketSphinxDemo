package demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;
import demo.R;

/**
 * Created by Verónica Santamaría on 22/05/2015.
 */
public class MainActivity extends Activity implements
        RecognitionListener {

    SpeechRecognizer recognizer;
    TextView topLbl, bottomLbl;
    ImageButton button;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.main);

        topLbl = (TextView)findViewById(R.id.topLbl);
        bottomLbl = (TextView)findViewById(R.id.bottomLbl);
        button = (ImageButton)findViewById(R.id.button);


        topLbl.setText("Preparing the recognizer");

        // Recognizer initialization is time-consuming and it involves IO,
        // so we execute it in async task
        new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    Assets assets = new Assets(MainActivity.this);
                    File assetDir = assets.syncAssets();
                    setupRecognizer(assetDir);
                } catch (IOException e) {
                    return e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception result) {
                if (result != null) {
                    topLbl.setText("Failed to init recognizer " + result);
                } else {
                    topLbl.setText("Click to speak!");
                    //if there are no errors in the initialization of the recognizer
                    //set listener to start recognition on button click
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            start();
                        }
                    });
                }
            }
        }.execute();
    }

    private void setupRecognizer(File assetsDir) throws IOException {
        // The recognizer can be configured to perform multiple searches
        // of different kind and switch between them
        // Here we use the grammar-based search

        recognizer = SpeechRecognizerSetup.defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))
                // To disable logging of raw audio comment out this call (takes a lot of space on the device)
                //.setRawLogDir(assetsDir)
                 // Threshold to tune for keyphrase to balance between false alarms and misses
                .setKeywordThreshold(1e-45f)
                 // Use context-independent phonetic search, context-dependent is too slow for mobile
                .setBoolean("-allphone_ci", true)
                .getRecognizer();
        recognizer.addListener(this);

        // Create grammar-based search for recognition
        File domainGrammar = new File(assetsDir, "domain.gram");
        recognizer.addGrammarSearch("domain", domainGrammar);
    }

    /**
    * startListening
    */
    private void start() {
        topLbl.setText("... Listening ...");
        button.setImageResource(R.drawable.button_disabled);
        bottomLbl.setText("");
        Log.e("DOMAIN", "start");
        //set timeout to 10000 ms or 10 seconds
        //if no audio is received within that time interval, program executes the onTimeout method
        recognizer.startListening("domain", 10000);
    }

    /**
     * If the recognizer returns a result before the EndOfSpeech state
     * the result is shown in the bottomLbl
     */
    @Override
    public void onPartialResult(Hypothesis hypothesis) {

        if (hypothesis == null)
            return;

        String text = hypothesis.getHypstr();
        Log.e("DOMAIN", "onPartialResult" + " " + text);
        bottomLbl.setText(text);
    }
    /**
     * The onResult method is executed automatically
     * with every call to recognizer.stop().
     * It has access to the final result of the recognition
     * We print that result on bottomLbl
     */
    @Override
    public void onResult(Hypothesis hypothesis) {
        bottomLbl.setText("");
        if (hypothesis != null) {
            String text = hypothesis.getHypstr();
            bottomLbl.setText(text);

            Log.e("DOMAIN", "onResult" + " " + text);
        }
        else {
            Log.e("DOMAIN", "onResult" + " " + "hypo NULL");
        }
        topLbl.setText("Click to speak!");
        button.setImageResource(R.drawable.button2);
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.e("DOMAIN", "onBeginningOfSpeech");
    }

    /**
     * When the EndOfSpeech is detected
     * we stop recognizer to get a final result
     */
    @Override
    public void onEndOfSpeech() {
        Log.e("DOMAIN", "onEndOfSpeech");
        recognizer.stop();
    }


    @Override
    public void onError(Exception error) {
        Log.e("DOMAIN","onError");
        topLbl.setText(error.getMessage());
    }

    /*
    * If no audio has been received during time interval set on recognizer.startListening()
    * we stop the recognizer
    */
    @Override
    public void onTimeout() {
        Log.e("DOMAIN", "onTimeout");
        recognizer.stop();
    }

    @Override
    public void onDestroy() {
        Log.e("DOMAIN","onDestroy");
        super.onDestroy();
        recognizer.cancel();
        recognizer.shutdown();
    }
}

