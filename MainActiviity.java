package com.rekuza.cs;

 import android.app.*;
 import android.os.*;
 import android.view.*;
 import android.view.View.*;
 import android.widget.*;
 import android.content.*;
import    android.content.ClipboardManager;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;

public class MainActivity extends Activity {

private LinearLayout linear1;
private EditText enterhereuridforkey;
private EditText enterhereuridvalue;
private Button buttonforgenakey;
private EditText idforgetvaluefromkey;
private Button buttonforgetvalue;
private TextView valuewillloadhere;
private WebView webviewforloading;

private String key = "";
private String value = "";


private Timer _timer = new Timer();
private TimerTask timer;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    initialize();
    initializeLogic();
}

private void  initialize() {
    linear1 = (LinearLayout) findViewById(R.id.linear1);
    enterhereuridforkey = (EditText) findViewById(R.id.enterhereuridforkey);
    enterhereuridvalue = (EditText) findViewById(R.id.enterhereuridvalue);
    buttonforgenakey = (Button) findViewById(R.id.buttonforgenakey);
    idforgetvaluefromkey = (EditText) findViewById(R.id.idforgetvaluefromkey);
    buttonforgetvalue = (Button) findViewById(R.id.buttonforgetvalue);
    valuewillloadhere = (TextView) findViewById(R.id.valuewillloadhere);
    webviewforloading = (WebView) findViewById(R.id.webviewforloading);
    webviewforloading.getSettings().setJavaScriptEnabled(true);
    webviewforloading.getSettings().setSupportZoom(true);
    webviewforloading.setWebViewClient(new WebViewClient() {
        @Override
        public void onPageStarted(WebView _view,final String _url, Bitmap _favicon) {

            super.onPageStarted(_view, _url, _favicon);
        }
        @Override
        public void onPageFinished(WebView _view,final String _url) {

            super.onPageFinished(_view, _url);
        }
    });



    buttonforgenakey.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View _v) { 
            key = enterhereuridforkey.getText().toString();
            value = enterhereuridforkey.getText().toString();
            enterhereuridforkey.setText("");
            enterhereuridvalue.setText("");
            webviewforloading.loadUrl("https://rekuza.000webhostapp.com/setdata.php?key=".concat("yourappid".concat(key).concat("&value".concat(value))));
            timer = new TimerTask() {
                        @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                @Override
                                    public void run() {
                                                    if (webviewforloading.getUrl().equals("https://rekuza.000webhostappsaved.php/?saved=".concat(key.concat("-".concat(value))))) {
                                        showMessage("Succesfully Saved!");
                                    }
                                    }
                                });
                            }
                        };
                        _timer.scheduleAtFixedRate(timer, (int)(1000), (int)(1500));
        }
    });
    buttonforgetvalue.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View _v) { 

        }
    });

}

private void  initializeLogic() {
    webviewforloading.setVisibility(View.GONE);
}






// created automatically
private void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
}

private int getLocationX(View _v) {
     int _location[] = new int[2];
     _v.getLocationInWindow(_location);
     return _location[0];
}

private int getLocationY(View _v) {
     int _location[] = new int[2];
     _v.getLocationInWindow(_location);
     return _location[1];
}

private int getRandom(int _minValue ,int _maxValue){
    Random random = new Random();
    return random.nextInt(_maxValue - _minValue + 1) + _minValue;
}

public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
    ArrayList<Double> _result = new ArrayList<Double>();
    SparseBooleanArray _arr = _list.getCheckedItemPositions();
    for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
        if (_arr.valueAt(_iIdx))
            _result.add((double)_arr.keyAt(_iIdx));
    }
    return _result;
}

private float getDip(int _input){
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
}

private int getDisplayWidthPixels(){
    return getResources().getDisplayMetrics().widthPixels;
}

private int getDisplayHeightPixels(){
    return getResources().getDisplayMetrics().heightPixels;
}


 }
