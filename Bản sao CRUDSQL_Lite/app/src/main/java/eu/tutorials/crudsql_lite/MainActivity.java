package eu.tutorials.crudsql_lite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import eu.tutorials.crudsql_lite.activity.MainActivity2;
import eu.tutorials.crudsql_lite.database.Databse;

public class MainActivity extends AppCompatActivity {

    Databse database;

    EditText edt_ho_va_ten, edt_ngay_sinh, edt_dia_chi;
    RadioButton rd_nam, rd_nu;
    CheckBox chk_kt, chk_cntt, chk_sp;
    Button btn_luu, btn_hien_thi;

    String hoTen, ngaySinh, diaChi, gioiTinh, truongYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        xuLySuKien();
        themDuLieu();
        //tao database
        database = new Databse(this, "thongtin.sqlite", null, 1);

        //tao bang
        database.QueryData("CREATE TABLE IF NOT EXISTS ThongTin(Id INTEGER PRIMARY KEY AUTOINCREMENT, HoVaTen VARCHAR(15), NgaySinh VARCHAR(15), DiaChi VARCHAR(30), GioiTinh VARCHAR(3), TruongHoc VARCHAR(30))");


    }

    private void themDuLieu() {
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoTen = edt_ho_va_ten.getText().toString().trim();
                diaChi = edt_dia_chi.getText().toString().trim();
                ngaySinh = edt_ngay_sinh.getText().toString().trim();
                gioiTinh = "";
                if (rd_nam.isChecked()) {
                    gioiTinh = "Nam";
                } else {
                    gioiTinh = "Nữ";
                }
                truongYeuThich = "";
                if (chk_kt.isChecked()) {
                    truongYeuThich = "ĐH Kinh Tế";
                } else if (chk_cntt.isChecked()) {
                    truongYeuThich = truongYeuThich + "ĐH CNTT";
                } else if (chk_sp.isChecked()) {
                    truongYeuThich = truongYeuThich + "ĐH SP";
                }


                //them du lieu
                database.QueryData("INSERT INTO ThongTin VALUES(null, '" + hoTen + "', '" + ngaySinh + "', '" + diaChi + "', '" + gioiTinh + "', '" + truongYeuThich + "')");

                //lay du lieu
                Cursor dataThongTin = database.GetData("SELECT * FROM ThongTin");
                while (dataThongTin.moveToNext()) {
                    hoTen = dataThongTin.getString(1);//id thu 0 trong hang
                    ngaySinh = dataThongTin.getString(2);
                    diaChi = dataThongTin.getString(3);
                    gioiTinh = dataThongTin.getString(4);
                    truongYeuThich = dataThongTin.getString(5);
                    Toast.makeText(MainActivity.this, hoTen + ngaySinh + diaChi + gioiTinh + truongYeuThich, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void anhXa() {
        edt_ho_va_ten = findViewById(R.id.edt_ho_va_ten);
        edt_ngay_sinh = findViewById(R.id.edt_ngay_sinh);
        edt_dia_chi = findViewById(R.id.edt_dia_chi);
        rd_nam = findViewById(R.id.rd_nam);
        rd_nu = findViewById(R.id.rd_nu);
        chk_kt = findViewById(R.id.chk_kt);
        chk_cntt = findViewById(R.id.chk_cntt);
        chk_sp = findViewById(R.id.chk_sp);
        btn_luu = findViewById(R.id.btn_luu);
        btn_hien_thi = findViewById(R.id.btn_hien_thi);
    }

    private void xuLySuKien() {


        btn_hien_thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoTen = edt_ho_va_ten.getText().toString().trim();
                diaChi = edt_dia_chi.getText().toString().trim();
                ngaySinh = edt_ngay_sinh.getText().toString().trim();
                gioiTinh = "";
                if (rd_nam.isChecked()) {
                    gioiTinh = "Nam";
                } else {
                    gioiTinh = "Nữ";
                }
                truongYeuThich = "";
                if (chk_kt.isChecked()) {
                    truongYeuThich = "ĐH Kinh Tế";
                } else if (chk_cntt.isChecked()) {
                    truongYeuThich = truongYeuThich + "ĐH CNTT";
                } else if (chk_sp.isChecked()) {
                    truongYeuThich = truongYeuThich + "ĐH SP";
                }
                //chuyen du lieu
                Bundle bundle = new Bundle();
                bundle.putString("hoTen", hoTen);
                bundle.putString("diaChi", diaChi);
                bundle.putString("ngaySinh", ngaySinh);
                bundle.putString("gioiTinh", gioiTinh);
                bundle.putString("truongYeuThich", truongYeuThich);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Toast.makeText(MainActivity.this, "Chuyển dữ liệu thành công", Toast.LENGTH_SHORT).show();
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}