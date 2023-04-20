package eu.tutorials.crudsql_lite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import eu.tutorials.crudsql_lite.R;

public class MainActivity2 extends AppCompatActivity {
    TextView txt_hien_thi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt_hien_thi = findViewById(R.id.txt_hien_thi);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String hoTen = bundle.getString("hoTen");
            String ngaySinh = bundle.getString("ngaySinh");
            String diaChi = bundle.getString("diaChi");
            String gioiTinh = bundle.getString("gioiTinh");
            String truongYeuThich = bundle.getString("truongYeuThich");
            txt_hien_thi.setText(hoTen + "|" + ngaySinh + "|" + diaChi + "|" + gioiTinh + "|" + truongYeuThich);


        }

    }
}