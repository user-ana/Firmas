package com.uth.Ejercicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uth.Ejercicio.adapter.adapterSignature;
import com.uth.Ejercicio.database.database_firma;
import com.uth.Ejercicio.models.cSignature;
import com.uth.e24.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private FloatingActionButton oFloatingActionButton;
    private RecyclerView oRecyclerView;

    private adapterSignature oAdapterSignature;

    private database_firma oDBSIGNATURE;
    private ArrayList<cSignature> oSignatureArrayList = new ArrayList<>();

    static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.oFloatingActionButton = findViewById(R.id.floatingButtonSignature);
        this.oRecyclerView = findViewById(R.id.RecyclerViewSignature);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        oDBSIGNATURE = new database_firma(MainActivity.this);

        oSignatureArrayList = oDBSIGNATURE.leerSignature();
        oAdapterSignature = new adapterSignature(oSignatureArrayList);

        this.oFloatingActionButton.setOnClickListener(this);
        oRecyclerView.setLayoutManager(mLinearLayoutManager);

        oRecyclerView.setAdapter(oAdapterSignature);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateSignature();
        super.onResume();
    }

    public void updateSignature()
    {
        oSignatureArrayList.clear();
        ArrayList<cSignature> oA = oDBSIGNATURE.leerSignature();
        for (int i = 0;i<oA.size();i++)
        {
            cSignature oS = new cSignature();
            oS.setDescripcion(oA.get(i).getDescripcion());
            oS.setFirma_digital(oA.get(i).getFirma_digital());
            oSignatureArrayList.add(oS);
        }
        oAdapterSignature.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.floatingButtonSignature)
        {
            Intent oIntent = new Intent(MainActivity.this, NewSignature.class);
            startActivityForResult(oIntent,REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
        {
            updateSignature();
        }
    }

}