package com.atguigu.shoppingmall.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.db.User;
import com.atguigu.shoppingmall.db.UserDao;
import com.atguigu.shoppingmall.home.activity.GoodsListActivity;
import com.atguigu.shoppingmall.utils.JsonParser;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.atguigu.shoppingmall.R.id.ll_history;


public class SearchActivity extends AppCompatActivity {

    @InjectView(R.id.tv_search)
    EditText tvSearch;
    @InjectView(R.id.iv_search_voice)
    ImageView ivSearchVoice;
    @InjectView(R.id.tv_search_go)
    TextView tvSearchGo;
    @InjectView(R.id.ll_hot_search)
    LinearLayout llHotSearch;
    @InjectView(R.id.hsl_hot_search)
    HorizontalScrollView hslHotSearch;
    @InjectView(R.id.lv_search)
    ListView lvSearch;
    @InjectView(R.id.btn_clear)
    Button btnClear;
    @InjectView(ll_history)
    LinearLayout llHistory;
    private UserDao userDao;
    private List<User> users;
    private ArrayAdapter<String> adapter;
    private List<String> datas;
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=58bf7cb7");
        initView();
        initData();
    }

    private void initView() { ButterKnife.inject(this);}

    private void initData() {
        userDao = Application.getInstances().getDaoSession().getUserDao();

        users = userDao.loadAll();
        if(users ==null|| users.size()==0) {
            llHistory.setVisibility(View.GONE);
        }
        datas = new ArrayList<>();
        for(int i = 0; i <users.size() ; i++) {
          datas.add(users.get(i).getName());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.test_list_item, datas);
        lvSearch.setAdapter(adapter);

    }

    @OnClick({R.id.iv_search_voice, R.id.tv_search_go, R.id.btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_voice:
//                Toast.makeText(SearchActivity.this, "科大讯飞", Toast.LENGTH_SHORT).show();
                showDialogVoice();
                break;
            case R.id.tv_search_go:
                String search = tvSearch.getText().toString().trim();
                if(TextUtils.isEmpty(search)) {
                    Toast.makeText(SearchActivity.this, "搜索不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(null,search);
                    userDao.insert(user);
                    tvSearch.setText("");
                    datas.add(search);
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(this, GoodsListActivity.class).putExtra("position",0));
                }
                break;
            case R.id.btn_clear:
                Toast.makeText(SearchActivity.this, "清除数据", Toast.LENGTH_SHORT).show();
                for(int i = 0; i < users.size(); i++) {
                    userDao.deleteByKey(users.get(i).getId());
                }
                initData();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showDialogVoice() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener());
//2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
//结果
// mDialog.setParameter("asr_sch", "1");
// mDialog.setParameter("nlp_version", "2.0");
//3.设置回调接口
        mDialog.setListener(new MyRecognizerDialogListener());
//4.显示dialog，接收语音输入
        mDialog.show();
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            String result = recognizerResult.getResultString();
            System.out.println(result);
            String text = JsonParser.parseIatResult(recognizerResult.getResultString());

            String sn = null;
            // 读取json结果中的sn字段
            try {
                JSONObject resultJson = new JSONObject(recognizerResult.getResultString());
                sn = resultJson.optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults.put(sn, text);

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults.get(key));
            }
            String reulst = resultBuffer.toString();
            reulst = reulst.replace("。", "");
            tvSearch.setText(reulst);
            tvSearch.setSelection(tvSearch.length());

        }

        @Override
        public void onError(SpeechError speechError) {

            Toast.makeText(SearchActivity.this, "出错了哦", Toast.LENGTH_SHORT).show();
        }

    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int i) {


        }
    }

}
