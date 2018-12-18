package atina.zaima.portalberitanew;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import atina.zaima.portalberitanew.Helper.HelperListArtikel;
import atina.zaima.portalberitanew.Model.Artikel;
import atina.zaima.portalberitanew.Model.Artikel2;

public class ListInputArtikelActivity extends AppCompatActivity {

    HelperListArtikel helper;
    FloatingActionButton fab;
    Cursor cursor;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Artikel2> mArtikel2s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_input_artikel);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_input_artikel);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        helper = new HelperListArtikel(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 1 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            Intent intent = new Intent(getApplicationContext(),CameraActivity.class);
            intent.putExtra("BitmapImage",bitmap);
            startActivity(intent);
        }
    }

    private void RefreshList(){
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM artikel",null);

    }
}
