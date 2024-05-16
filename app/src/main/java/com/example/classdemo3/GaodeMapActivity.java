package com.example.classdemo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.classdemo3.R;

public class GaodeMapActivity extends AppCompatActivity implements OnGeocodeSearchListener {

    private MapView mapView;
    private AMap aMap;
    private EditText searchEditText;
    private Button searchButton;
    private GeocodeSearch geocodeSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 初始化MapView
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        // 初始化AMap对象
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        // 初始化GeocodeSearch对象
        try {
            geocodeSearch = new GeocodeSearch(this);
        } catch (AMapException e) {
            throw new RuntimeException(e);
        }
        geocodeSearch.setOnGeocodeSearchListener(this);

        // 初始化搜索栏和按钮
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);

        // 设置搜索按钮点击事件
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = searchEditText.getText().toString().trim();
                if (!address.isEmpty()) {
                    // 发起地理编码搜索
                    GeocodeQuery query = new GeocodeQuery(address, null);
                    geocodeSearch.getFromLocationNameAsyn(query);
                } else {
                    Toast.makeText(GaodeMapActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getGeocodeAddressList() != null && result.getGeocodeAddressList().size() > 0) {
                LatLng latLng = new LatLng(result.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude(),
                        result.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude());
                aMap.clear();
                aMap.addMarker(new MarkerOptions().position(latLng).title(result.getGeocodeAddressList().get(0).getFormatAddress()));
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                Toast.makeText(GaodeMapActivity.this, "定位成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GaodeMapActivity.this, "未找到结果", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(GaodeMapActivity.this, "搜索失败, 错误码: " + rCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        // 逆地理编码结果回调
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
