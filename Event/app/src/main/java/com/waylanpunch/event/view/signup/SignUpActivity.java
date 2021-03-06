package com.waylanpunch.event.view.signup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.waylanpunch.event.EventConstants;
import com.waylanpunch.event.R;
import com.waylanpunch.event.service.LeanCloudRetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.et_signup_username)
    public EditText etUserName;
    @BindView(R.id.et_signup_password)
    public EditText etPassword;
    @BindView(R.id.btn_signup)
    public Button btnSignUp;
    @BindView(R.id.tv_signup_result)
    public TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(EventConstants.LeanCloud_Restful_API)
                        .build();
                LeanCloudRetrofitService service = retrofit.create(LeanCloudRetrofitService.class);
                String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
                RequestBody body = RequestBody.create(MediaType.parse(EventConstants.LeanCloud_Restful_API_ContentType), json.toString());
                Call<Object> call = service.signUp(EventConstants.LeanCloud_AppID,
                        EventConstants.LeanCloud_AppKey,
                        EventConstants.LeanCloud_Restful_API_ContentType,
                        body);
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        tvResult.setText(response.body() + "");
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        tvResult.setText(t.getMessage() + "");
                    }
                });
            }
        });
    }

}
