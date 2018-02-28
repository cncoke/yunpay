package com.yc.examination;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.mob.paysdk.MobPayAPI;
import com.mob.paysdk.OnPayListener;
import com.mob.paysdk.PayOrder;
import com.mob.paysdk.PayResult;
import com.mob.paysdk.PaySDK;
import com.mob.paysdk.WXPayAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        init();
    }

    TextView tv_result;

    private void init() {

        tv_result = (TextView) findViewById(R.id.tv_result);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPay();
            }
        });


    }

    private void onPay() {


        PayOrder order = new PayOrder();
        order.setOrderNo("PAY" + System.currentTimeMillis());
        order.setAmount(1);
        order.setSubject("测试支付");
        order.setBody("支付主体");
        tv_result.append("开始...\n");
        WXPayAPI wxpay = PaySDK.createMobPayAPI(WXPayAPI.class);

        wxpay.pay(order, new OnPayListener<PayOrder>() {
            @Override
            public boolean onWillPay(String ticketId, PayOrder order, MobPayAPI api) {
                // TODO 保存本次支付操作的 ticketId
                // 返回false表示不阻止本次支付
                tv_result.append("ticketId:"+ticketId+"\n");
                return false;
            }

            @Override
            public void onPayEnd(PayResult payResult, PayOrder order, MobPayAPI api) {
                // TODO 处理支付的结果，成功或失败可以在payResult中获取

                tv_result.append("payResult:"+payResult.toString()+"\n order No:"+order.getOrderNo());
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
