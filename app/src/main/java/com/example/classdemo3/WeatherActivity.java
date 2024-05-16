package com.example.classdemo3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private OkHttpClient client;
    private TextView tvOk;
    private Response response;
    private String responseOk;
    private EditText locationEditText;
    private TableLayout tableLayout;

    private TableRow.LayoutParams lp;
    //
    private String key = "a4edd06dacb24c7d9e3b010c2212c30e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initView();
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }

    private void initView() {

// 获取布局中的TableLayout
        tableLayout = findViewById(R.id.tableLayout);

        // 创建一行数据
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        // TODO 初始化布局
//        tvOk = (TextView) findViewById(R.id.tv_ok);
        locationEditText = findViewById(R.id.et_location);

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWeather();
            }
        });

        Button resetButton = findViewById(R.id.btn_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        Button btnOpenNewActivity = findViewById(R.id.btn_map);
        btnOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, GaodeMapActivity.class);
                startActivity(intent);
            }
        });


        Button btnLianLianActivity = findViewById(R.id.btn_lianlian);
        btnLianLianActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, LianLianActivity.class);
                startActivity(intent);
            }
        });

        Button btnSqlLiteActivity = findViewById(R.id.btn_sqllite);
        btnSqlLiteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, SqlLiteActivity.class);
                startActivity(intent);
            }
        });
    }


    // 添加数据行
    private void addDataRow(TableLayout tableLayout, TableRow.LayoutParams lp, String obsTime, String temp, String feelsLike, String icon, String text, String wind360, String windDir) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(lp);
        row.addView(createTextView(obsTime));
        row.addView(createTextView(temp));
        row.addView(createTextView(feelsLike));
        row.addView(createTextView(icon));
        row.addView(createTextView(text));
        row.addView(createTextView(wind360));
        row.addView(createTextView(windDir));

        // 将行添加到表格中
        tableLayout.addView(row);
    }

//    private String getLocationIdFromExcel(String location) {
//        try {
//            // 从 assets 目录下打开 Excel 文件
//            AssetManager assetManager = getAssets();
//            InputStream inputStream = assetManager.open("China-City-List-latest.xlsx");
//            Workbook workbook = WorkbookFactory.create(inputStream);
//
//            // 获取第一个工作表
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 遍历每一行，查找匹配的地点
//            for (Row row : sheet) {
//                Cell cell = row.getCell(2); // Location_Name_EN 在第二列
//                if (cell != null && cell.getStringCellValue().equals(location)) {
//                    // 找到匹配的地点，返回对应的 Location_ID
//                    return row.getCell(0).getStringCellValue(); // Location_ID 在第一列
//                }
//            }
//
//            // 如果未找到匹配的地点，返回空字符串或其他默认值
//            return "";
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 处理异常情况
//            return "";
//        }
//    }

    private void searchWeather() {
        String location = locationEditText.getText().toString();
        // 在这里执行天气搜索的操作，可以使用OkHttp请求网络
        // 这里只是示例，可以替换为实际的搜索逻辑
//        String locationId = getLocationIdFromExcel(location);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO 在线程中请求网络
                client = new OkHttpClient();
                String url = "https://devapi.qweather.com/v7/weather/now?key=" + key + "&location=101010100";
                Request request = new Request.Builder().url(url).build();

                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        // 返回数据
                        responseOk = response.body().string();
                        // 解析JSON数据并提取所需字段
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(responseOk, JsonObject.class);
                        JsonObject nowObject = jsonObject.getAsJsonObject("now");
                        WeatherInfo nowInfo = gson.fromJson(nowObject, WeatherInfo.class);
                        System.out.println(nowInfo.getText());
                        if (nowInfo != null) {
                            String obsTime = nowInfo.getObsTime();
                            String temp = nowInfo.getTemp();
                            String feelsLike = nowInfo.getFeelsLike();
                            String icon = nowInfo.getIcon();
                            String text = nowInfo.getText();
                            String wind360 = nowInfo.getWind360();
                            String windDir = nowInfo.getWindDir();

                            // 在主线程中更新UI
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addDataRow(tableLayout, lp, obsTime, temp, feelsLike, icon, text, wind360, windDir);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (!location.isEmpty()) {
            Toast.makeText(this, "Searching weather for " + location, Toast.LENGTH_SHORT).show();
            // 这里可以添加调用天气API等的代码
        } else {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
        }
    }


    private void resetFields() {
        locationEditText.setText("");
    }

}