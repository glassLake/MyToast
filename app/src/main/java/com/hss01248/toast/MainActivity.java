package com.hss01248.toast;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyToast.init(getApplication(),new Handler(),true,true);
    }

    String text = "金鳞岂是池中物,一遇风云便化龙";

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                MyToast.showToast("short");
                break;
            case R.id.button2:
                MyToast.showLongToast(text+"long");
                break;
            case R.id.button3:
                MyToast.showDebugToast(text);
                break;
            case R.id.button4:
                MyToast.showSuccessToast("fdkshfdshfkdshfjsdhkfsdhjfhdskfhdfdgfdgfdgfdgdrdskhfkds");
                break;
            case R.id.button5:
                MyToast.showFailToast("56");
                break;
        }
    }
}
