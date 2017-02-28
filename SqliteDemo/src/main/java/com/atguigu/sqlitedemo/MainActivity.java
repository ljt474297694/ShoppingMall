package com.atguigu.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.sqlitedemo.bean.Student;
import com.atguigu.sqlitedemo.dao.StudentDAO;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.bt_select)
    Button btSelect;
    @InjectView(R.id.et_add)
    EditText etAdd;
    @InjectView(R.id.bt_add)
    Button btAdd;
    @InjectView(R.id.et_updata_id)
    EditText etUpdataId;
    @InjectView(R.id.et_updata_name)
    EditText etUpdataName;
    @InjectView(R.id.bt_updata)
    Button btUpdata;
    @InjectView(R.id.et_delete_id)
    EditText etDeleteId;
    @InjectView(R.id.bt_delete)
    Button btDelete;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.activity_main)
    ScrollView activityMain;
    private StudentDAO studentDAO;

    private int version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        ButterKnife.inject(this);
    }

    private void initData() {
        version = getSharedPreferences("version", MODE_PRIVATE).getInt("version", 1);
        studentDAO = new StudentDAO(this, version);
    }

    @OnClick({R.id.bt_select, R.id.bt_add, R.id.bt_updata, R.id.bt_delete, R.id.bt_updata_table,R.id.bt_delete_table})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_delete_table:
                studentDAO.deleteTable();
                tvContent.setText("删除成功");
                getSharedPreferences("version", MODE_PRIVATE).edit().putInt("version", 1).commit();
                version = getSharedPreferences("version", MODE_PRIVATE).getInt("version", 1);
                break;
            case R.id.bt_updata_table:
                getSharedPreferences("version", MODE_PRIVATE).edit().putInt("version", 2).commit();
                version = getSharedPreferences("version", MODE_PRIVATE).getInt("version", 1);
                studentDAO = new StudentDAO(this, version);
                tvContent.setText("更新成功");
                break;
            case R.id.bt_select:
                List<Student> all = studentDAO.getAll();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < all.size(); i++) {
                    stringBuilder.append(all.get(i).toString() + "\n");
                }
                if (all.size() == 0) {
                    tvContent.setText("还没有数据");
                } else {
                    tvContent.setText(stringBuilder);
                }
                break;
            case R.id.bt_add:
                String name = etAdd.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                studentDAO.add(new Student(name));
                etAdd.setText("");
                tvContent.setText("添加成功");
                break;
            case R.id.bt_updata:
                name = etUpdataName.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(etUpdataId.getText().toString())) {
                    Toast.makeText(MainActivity.this, "姓名或id不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = Integer.parseInt(etUpdataId.getText().toString());
                int updata = studentDAO.updata(new Student(id, name));


                if (updata > 0) {
                    tvContent.setText("更新成功");
                } else {
                    tvContent.setText("更新失败 此学生不存在");
                }
                etUpdataId.setText("");
                etUpdataName.setText("");
                break;
            case R.id.bt_delete:
                if (TextUtils.isEmpty(etDeleteId.getText().toString())) {
                    Toast.makeText(MainActivity.this, "id不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                id = Integer.parseInt(etDeleteId.getText().toString());
                int delete = studentDAO.delete(new Student(id));
                if (delete > 0) {
                    tvContent.setText("删除成功");
                } else {
                    tvContent.setText("删除失败 此id不存在");
                }
                etDeleteId.setText("");
                break;
        }
    }
}
