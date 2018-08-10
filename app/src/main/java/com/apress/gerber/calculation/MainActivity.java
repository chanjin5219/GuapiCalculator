package com.apress.gerber.calculation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_about:
            {
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);//这里添加启动“关于”界面的代码。
            }
            break;
        }

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
            case R.id.btn_dot: {
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                String strNewContent = strContent + strAdded;
                formula.setText(strNewContent);
            }
            break;
            case R.id.btn_c:{
                TextView formula = (TextView) findViewById(R.id.formula_area);
                formula.setText("");

                TextView result = (TextView) findViewById(R.id.result_area);
                result.setText("");
            }

            break;

            case R.id.btn_del: {
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                if (strContent.length() > 0) {
                    strContent = strContent.substring(0, strContent.length() - 1);
                    formula.setText(strContent);
                }
            }
            break;
            case R.id.btn_equ: {
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();

                try {
                    Symbols s = new Symbols();
                    double res = s.eval(strContent);

                    TextView result = (TextView) findViewById(R.id.result_area);
                    result.setText(String.valueOf(res));

                    Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                    result.startAnimation(fadeIn);
                    formula.setText("");



                } catch (SyntaxException e) {
                    Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }

    }
}
