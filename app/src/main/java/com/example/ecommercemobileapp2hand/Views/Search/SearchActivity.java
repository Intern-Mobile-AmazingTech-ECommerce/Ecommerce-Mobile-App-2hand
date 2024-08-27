package com.example.ecommercemobileapp2hand.Views.Search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommercemobileapp2hand.Models.FakeModels.Category;
import com.example.ecommercemobileapp2hand.Models.FakeModels.Product;
import com.example.ecommercemobileapp2hand.R;
import com.example.ecommercemobileapp2hand.Views.Homepage.CustomAdapter.CategoriesAdapter;
import com.example.ecommercemobileapp2hand.Views.Homepage.CustomAdapter.ProductCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ImageView imgBack;
    SearchView searchView;
    ArrayList<Product> lstPro;
    ProductCardAdapter proAdapter;
    RecyclerView recyViewSearchPro;
    RecyclerView recyViewCateSearch;
    LinearLayout linearLayoutSearch;
    ScrollView scrollViewPro;
    List<Category> categoryList;
    CategoriesAdapter categoriesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        addControls();
        load();
        addEvent();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void addControls()
    {
        imgBack = (ImageView) findViewById(R.id.btnBack);
        searchView = (SearchView)  findViewById(R.id.searchView);
        searchView.clearFocus();

        recyViewSearchPro = (RecyclerView) findViewById(R.id.recyProductSearch);
        lstPro = Product.initProduct();
        proAdapter = new ProductCardAdapter(lstPro,SearchActivity.this);

        scrollViewPro = (ScrollView) findViewById(R.id.scrollViewProduct);
        recyViewCateSearch = (RecyclerView) findViewById(R.id.recyclerViewCategoriesSearch);
        linearLayoutSearch = (LinearLayout) findViewById(R.id.linearLayoutSearch);
        recyViewSearchPro.setLayoutManager(new GridLayoutManager(this,2));
        recyViewSearchPro.setItemAnimator(new DefaultItemAnimator());
        recyViewSearchPro.setAdapter(proAdapter);
        loadRecycleViewCategories();
    }
    private void loadRecycleViewCategories() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Hoodies", R.drawable.ic_hoodies));
        categoryList.add(new Category("Accessories", R.drawable.ic_accessories));
        categoryList.add(new Category("Shorts", R.drawable.ic_shorts));
        categoryList.add(new Category("Shoes", R.drawable.ic_shoes));
        categoryList.add(new Category("Bags", R.drawable.ic_bags));
        categoriesAdapter = new CategoriesAdapter(categoryList, this,R.layout.item_category);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyViewCateSearch.setLayoutManager(layoutManager);
        recyViewCateSearch.setAdapter(categoriesAdapter);
    }

    void addEvent()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterLíst(newText);
                return false;
            }
        });

    }
    void load()
    {
        recyViewCateSearch.setVisibility(View.VISIBLE);
        recyViewSearchPro.setVisibility(View.GONE);
        linearLayoutSearch.setVisibility(View.GONE);
    }
    void filterLíst(String text)
    {
        ArrayList <Product> filterList = new ArrayList<>();
        for(Product pro : lstPro)
        {
            if (pro.getProduct_name().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(pro);
            }
        }
        if (text.isEmpty())
        {
            recyViewCateSearch.setVisibility(View.VISIBLE);
            recyViewSearchPro.setVisibility(View.GONE);
            linearLayoutSearch.setVisibility(View.GONE);

        } else if (filterList.isEmpty()) {
            recyViewCateSearch.setVisibility(View.GONE);
            recyViewSearchPro.setVisibility(View.GONE);
            linearLayoutSearch.setVisibility(View.VISIBLE);
        }else {
            recyViewCateSearch.setVisibility(View.GONE);
            recyViewSearchPro.setVisibility(View.VISIBLE);
            linearLayoutSearch.setVisibility(View.GONE);
            proAdapter.setFilteredList(filterList);
        }

    }
}