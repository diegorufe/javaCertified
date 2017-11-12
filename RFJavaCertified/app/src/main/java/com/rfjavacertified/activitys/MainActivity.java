package com.rfjavacertified.activitys;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rfjavacertified.R;
import com.rfjavacertified.beans.ItemListCerfied;
import com.rfjavacertified.components.ViewHolderActionCertified;
import com.rfjavacertified.constantes.IConstantesApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import core.media.rf.com.rfmediacore.components.recicleview.BasicRecicleViewApadter;
import core.media.rf.com.rfmediacore.components.recicleview.OnItemClickListener;
import core.media.rf.com.rfmediacore.components.recicleview.RecicleViewAdapter;
import core.media.rf.com.rfmediacore.utils.RFUtilsFile;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {

        RFUtilsFile.createFolderApp(IConstantesApp.APP_NAME, this);

        recyclerView = (RecyclerView) findViewById(R.id.recileview_certifieds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ItemListCerfied> items = new ArrayList<>();

        items.add(new ItemListCerfied(getResources().getString(R.string.certificado_java7)));

        BasicRecicleViewApadter apdater = new BasicRecicleViewApadter(R.layout.item_recicle_certifieds, this.getApplicationContext(), new ViewHolderActionCertified(null), items,this);

        recyclerView.setAdapter(apdater);

        ((BasicRecicleViewApadter) recyclerView.getAdapter()).setListener(this);
        recyclerView.getAdapter().notifyDataSetChanged();


    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View view, int position) {
        try {
            ItemListCerfied item = (ItemListCerfied) ((BasicRecicleViewApadter) recyclerView.getAdapter())
                    .getItemsAdapters().get(position);
            if (item != null) {
                switch (position) {
                    // Java certified 7
                    case 0:
                        startActivityForResult(new Intent(MainActivity.this, JavaCertified7Activity.class),0);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
