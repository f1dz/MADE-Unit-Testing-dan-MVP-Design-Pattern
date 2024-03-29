package in.khofid.myunittesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{

    EditText edtWidth, edtHeight, edtLength;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        final MainPresenter presenter = new MainPresenter(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String length = edtLength.getText().toString().trim();
                String width = edtWidth.getText().toString().trim();
                String height = edtHeight.getText().toString().trim();

                boolean isEmpty = false;

                if(TextUtils.isEmpty(length)){
                    isEmpty = true;
                    edtLength.setError("Field ini tidak boleh kosong");
                }

                if(TextUtils.isEmpty(width)){
                    isEmpty = true;
                    edtWidth.setError("Field ini tidak boleh kosong");
                }

                if(TextUtils.isEmpty(height)){
                    isEmpty = true;
                    edtHeight.setError("Field ini tidak boleh kosong");
                }

                if(!isEmpty){
                    double l = Double.parseDouble(length);
                    double w = Double.parseDouble(width);
                    double h = Double.parseDouble(height);
                    presenter.calculateVolume(l,w,h);
                }
            }
        });
    }

    @Override
    public void showVolume(MainModel model) {
        tvResult.setText(String.valueOf(model.getVolume()));
    }
}
