package com.example.covid19app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19app.HomePage;
import com.example.covid19app.api.newsApi;
import com.example.covid19app.NewsModel.Article;
import com.example.covid19app.NewsModel.NewsModel;
import com.example.covid19app.R;
import com.example.covid19app.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Users users;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersData;
    private RecyclerView recyclerView;
    private String username;
    TextView textView;
    Adapter adapter;
    final String API = "706c519335bb462b82a24c992455cb5a";
    List<Article> articleList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {



        HomePage homePage = (HomePage) getActivity();
        username = homePage.getUsername();


        System.out.println("worked");

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        recyclerView = root.findViewById(R.id.newsScroll);
        getData();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(users.getFirstName());
            }
        });
        return root;
    }

    private void getData()
    {
        HomePage homePage = (HomePage) getActivity();
        textView.setText(String.format("Welcome: %s ",homePage.getUsername()));
        /*
        usersData.orderByChild("email").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                textView.setText(String.format("Welcome: %s ",homePage.getUsername()));
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homePage);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        final String country = getCountry();
        fetchJSON(country,API);




    }

    private void fetchJSON(String country, String api)
    {

        HomePage homePage = (HomePage) getActivity();

        retrofit2.Call<NewsModel> call = newsApi.getInstance().getApi().getHeadlines("covid",country,api);
        System.out.println("worked2");
        call.enqueue(new Callback<NewsModel>()
        {

            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response)
            {
                System.out.println("worked3");
                if(response.isSuccessful() && response.body().getArticleList() != null)
                {

                    articleList.clear();
                    articleList = response.body().getArticleList();
                    adapter =  new Adapter(homePage,articleList);
                    recyclerView.setAdapter(adapter);
                    System.out.println("worked4");
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t)
            {
                Toast.makeText(homePage,"Failed",Toast.LENGTH_LONG);
            }
        });
    }

    private String getCountry()
    {
        Locale locale = Locale.getDefault();
        String country  = locale.getCountry();
        return country.toLowerCase();
    }

}