package com.objectgraph.JavascriptTest;

import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: kiichi
 * Date: 3/15/12
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 *
 * Reference:
 *
 * Call Java
 * http://developer.android.com/guide/webapps/webview.html
 *
 * Call Javascript
 * http://developer.android.com/resources/articles/using-webviews.html
 *
 */
public class JavaScriptHandler {
    MyActivity parentActivity;
    public JavaScriptHandler(MyActivity activity)  {
        parentActivity = activity;
    }
    public void setResult(int val){
        Log.v("mylog","JavaScriptHandler.setResult is called : " + val);
        this.parentActivity.javascriptCallFinished(val);
    }
    public void calcSomething(int x, int y){
        this.parentActivity.changeText("Result is : " + (x * y));
    }
}
