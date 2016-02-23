package com.gdky005.testsecretkeyinputdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 匹配数字和字母的正则
     */
    String regularExpression = "^[A-Za-z0-9]+$";
    /**
     * 用户存入的5个key
     */
    String[] inputKey = new String[5];

    private LinearLayout secretKeyLl;
    private TextView secret1;
    private TextView secret2;
    private TextView secret3;
    private TextView secret4;
    private TextView secret5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secretKeyLl = (LinearLayout) findViewById(R.id.secret_key_ll);
        secret1 = (TextView) findViewById(R.id.secret_1);
        secret2 = (TextView) findViewById(R.id.secret_2);
        secret3 = (TextView) findViewById(R.id.secret_3);
        secret4 = (TextView) findViewById(R.id.secret_4);
        secret5 = (TextView) findViewById(R.id.secret_5);

        secretKeyLl.setOnClickListener(this);
        secretKeyLl.setFocusable(true);
        secretKeyLl.requestFocus();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (deleteSecretKey(keyCode, event))
            return super.onKeyDown(keyCode, event);

        String userKey = String.valueOf(event.getDisplayLabel());
        log("用户的输入的key:" + userKey);

        setSecretKey(userKey);

        return super.onKeyDown(keyCode, event);
    }

    private boolean deleteSecretKey(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            for (int i = inputKey.length - 1; i >= 0; i--) {
                String temp = inputKey[i];

                if (!TextUtils.isEmpty(temp)) {
                    inputKey[i] = null;
                    break;
                }
            }

            setSecretKeyTextView(true);
            return true;
        }
        return false;
    }

    /**
     * 设置用户输入的key
     * @param userKey
     */
    private void setSecretKey(String userKey) {
        boolean isMatch = userKey.matches(regularExpression);

        //将用户输入的key 存储到数组里面
        if (userKey != null && userKey.length() == 1 && isMatch) {

            for (int i = 0; i < inputKey.length; i++) {
                String tempKey = inputKey[i];

                if (TextUtils.isEmpty(tempKey)) {
                    inputKey[i] = userKey;
                    break;
                }
            }
        }

        setSecretKeyTextView(false);
    }

    /**
     * 将用户输入的key 设置到 TextView 上
     */
    private void setSecretKeyTextView(boolean isDelete) {
        for (int i = 0; i < inputKey.length; i++) {
            String tempKey = inputKey[i];

            if (!isDelete && TextUtils.isEmpty(tempKey)) {
                break;
            }

            switch (i) {
                case 0:
                    secret1.setText(tempKey);
                    break;
                case 1:
                    secret2.setText(tempKey);
                    break;
                case 2:
                    secret3.setText(tempKey);
                    break;
                case 3:
                    secret4.setText(tempKey);
                    break;
                case 4:
                    secret5.setText(tempKey);
                    break;
            }

        }
    }

    private void log(String msg) {
        Log.i(MainActivity.class.getSimpleName(), msg);
    }

    /**
     * 转换软件输入法在窗体中的显示状态。如果输入法当前是显示状态，那么该方法设置输入法隐藏。如果输入法当前是隐藏状态，则该方法设置输入法显示。
     */
    private void switchSoftInput() {

        // 隐藏输入法
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        // 显示或者隐藏输入法
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.secret_key_ll:
                switchSoftInput();
                break;
        }
    }
}
