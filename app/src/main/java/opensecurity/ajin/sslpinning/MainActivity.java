package opensecurity.ajin.sslpinning;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.thoughtcrime.ssl.pinning.util.PinningHelper;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends ActionBarActivity {
    public Button request, requestErr;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        request = (Button) findViewById(R.id.button);
        requestErr = (Button) findViewById(R.id.button1);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] pins = new String[]{"3YR8V3Idv7kaWKK2hJn0KCacuBKONvPi8BDAB"}; //Valid till 28-01-2028
                    URL url = new URL("https://www.bailoremahishamardhini.com");
                    HttpsURLConnection connection = PinningHelper.getPinnedHttpsURLConnection(getBaseContext(), pins, url);
                    byte[] data = new byte[4096];
                    connection.getInputStream().read(data);
                    String str = new String(data);
                    Toast.makeText(mContext, "Success No MITM" + str, Toast.LENGTH_LONG).show();

                    Log.i("SSL-PINNING", "Success No MITM" + data);
                    Log.d("exe", "Success No MITM" + data);
                } catch (Exception e) {

                    Toast.makeText(mContext, "MITM Detected !!!" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    Log.i("SSL-PINNING", " MITM Detected!!!!!: " + e.getMessage().toString());
                }
            }
        });

        requestErr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] pins = new String[]{"3YR8V3Idv7kaWKK2hJn0KCacuBKONvPi8BDA"}; //Valid till 28-01-2028
                    URL url = new URL("https://www.bailoremahishamardhini.com");
                    HttpsURLConnection connection = PinningHelper.getPinnedHttpsURLConnection(getBaseContext(), pins, url);
                    byte[] data = new byte[4096];
                    connection.getInputStream().read(data);
                    String str = new String(data);
                    Toast.makeText(mContext, "Success No MITM" + str, Toast.LENGTH_LONG).show();

                    Log.i("SSL-PINNING", "Success No MITM" + data);

                } catch (Exception e) {
                    Toast.makeText(mContext, "MITM Detected !!!" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    Log.i("SSL-PINNING", " MITM Detected!!!!!: " + e.getMessage().toString());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
