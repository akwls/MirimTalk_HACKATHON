package com.example.mirimtalk_template;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;

    TextView userName, userLocation, userBio, userPublicRepos, userPublicGists, userFollowers, userFollowing;
    ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        userName = findViewById(R.id.userName);
        userLocation = findViewById(R.id.userLocation);
        userImage = findViewById(R.id.userImage);
        userBio = findViewById(R.id.userBio);
        userPublicRepos = findViewById(R.id.userPublicRepos);
        userPublicGists = findViewById(R.id.userPublicGists);
        userFollowers = findViewById(R.id.userFollowers);
        userFollowing = findViewById(R.id.userFollowing);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final JsonApi jsonApi = retrofit.create(JsonApi.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = jsonApi.getPosts(edtSearch.getText().toString());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            // Log.d("Test", "성공");
                            User user = response.body();
                            userName.setText(user.getName());
                            userLocation.setText(user.getLocation());
                            Glide.with(getApplicationContext()).load(user.getAvatar_url()).into(userImage);
                            userBio.setText(user.getBio());
                            userPublicRepos.setText(Integer.toString(user.getPublic_repos()));
                            userPublicGists.setText(Integer.toString(user.getPublic_gists()));
                            userFollowers.setText(Integer.toString(user.getFollowers()));
                            userFollowing.setText(Integer.toString(user.getFollowing()));
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });



    }
}
