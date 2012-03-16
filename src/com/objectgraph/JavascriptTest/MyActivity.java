package com.objectgraph.JavascriptTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity{
    WebView myWebView;
    TextView myResult;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myResult = (TextView)this.findViewById(R.id.myResult);

        myWebView = (WebView)this.findViewById(R.id.myWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/index.html");

        myWebView.addJavascriptInterface(new JavaScriptHandler(this), "MyHandler");

        Button btnSet = (Button)this.findViewById(R.id.btnCalc);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callJavaScriptFunctionAndGetResultBack(333, 444);
            }
        });

        Button btnSimple = (Button)this.findViewById(R.id.btnSimple);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeText("Gooooood Mooorning!");
            }
        });
    }

    public void changeText(String someText){
        Log.v("mylog","changeText is called");
        myWebView.loadUrl("javascript:document.getElementById('test1').innerHTML = '<strong>"+someText+"</strong>'");
    }

    public void callJavaScriptFunctionAndGetResultBack(int val1, int val2){
        Log.v("mylog","MyActivity.callJavascriptFunction is called");
        myWebView.loadUrl("javascript:window.MyHandler.setResult( addSomething("+val1+","+val2+") )");
    }

    public void javascriptCallFinished(final int val){
        Log.v("mylog","MyActivity.javascriptCallFinished is called : " + val);
        Toast.makeText(this, "Callback got val: " + val, 5).show();

        // I need to run set operation of UI on the main thread.
        // therefore, the above parameter "val" must be final
        runOnUiThread(new Runnable() {
            public void run() {
                myResult.setText("Callback got val: " + val);
            }
        });
    }
}
