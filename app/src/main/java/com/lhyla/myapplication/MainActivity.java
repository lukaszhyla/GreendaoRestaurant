package com.lhyla.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhyla.myapplication.db.DaoMaster;
import com.lhyla.myapplication.db.DaoSession;
import com.lhyla.myapplication.db.Product;
import com.lhyla.myapplication.db.ProductDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_act_name_edit_text)
    protected EditText nameEditText;

    @BindView(R.id.main_act_price_edit_text)
    protected EditText priceEditText;

    public DaoSession daoSession;
    public ProductDao productDao;
    public List<Product> productList;
    public RecyclerView recyclerView;
    public RestaurantAdapter restaurantAdapter;

    private static final String TAG = "LH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        productList = new ArrayList<>();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "users-db");
        Database db = devOpenHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        productDao = daoSession.getProductDao();

        recyclerView = (RecyclerView) findViewById(R.id.main_act_recycle_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        addItemsToRecycleView();

    }

    @OnClick(R.id.main_act_add_btn)
    protected void addBtnOnClick() {

        boolean priceIsEmpty = priceEditText.getText().toString().isEmpty();
        boolean nameIsEmpty = nameEditText.getText().toString().isEmpty();

        if (!(priceIsEmpty || nameIsEmpty)) {
            float price = Float.parseFloat(priceEditText.getText().toString());
            String name = nameEditText.getText().toString();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            productDao.insert(product);
            addItemsToRecycleView();
        }
    }

    @OnClick(R.id.main_act_load_bnt)
    protected void loadDataBtnOnClick() {
        addItemsToRecycleView();
    }

    private void addItemsToRecycleView(){
        productList = productDao.queryBuilder().list();
        restaurantAdapter = new RestaurantAdapter(productList, getApplicationContext());
        recyclerView.setAdapter(restaurantAdapter);
    }
}