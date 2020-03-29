package com.game.nanthabalane.captcha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String captchaStr;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captchaStr = CaptchaCode.generateCaptchaString();
        LinearLayout relativeLayout = findViewById(R.id.main);
        CaptchaCodeView captchaCode = new CaptchaCodeView(this, captchaStr);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utils.dpToPx(getApplicationContext(), 200.0f), Utils.dpToPx(getApplicationContext(), 60.0f));
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        relativeLayout.addView(captchaCode, 0, layoutParams);
        editText = findViewById(R.id.et_captcha);
        Button button = findViewById(R.id.bt_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck()) {
                    Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not valid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isCheck() {
        return captchaStr.equals(editText.getText().toString());
    }

    public class CaptchaCodeView extends View {

        private Paint paint;
        private String value;

        public CaptchaCodeView(Context context, String value) {
            super(context);
            this.value = value; paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(Utils.sptoPx(getApplicationContext(), 20.0f));
            int width = 50, height = 150;
            if (value != null)
                for (int i = 0; i < value.length(); i++) {
                    canvas.drawText("" + value.charAt(i), width, height, paint);
                    width += 100;
                    height = height == 100 ? 150 : 100;
                }
//            canvas.drawText("S", 50, 150, paint);
//            canvas.drawText("S", 150, 100, paint);
//            canvas.drawText("S", 250, 150, paint);
//            canvas.drawText("S", 350, 100, paint);
//            canvas.drawText("S", 450, 150, paint);
//            canvas.drawText("S", 550, 100, paint);
        }
    }
}
