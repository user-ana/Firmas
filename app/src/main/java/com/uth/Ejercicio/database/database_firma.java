package com.uth.Ejercicio.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.uth.Ejercicio.models.cSignature;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class database_firma
{
    private signature_helper oSignature_helper;
    private Context oContext;

    public database_firma(Context oContext_)
    {
        oContext = oContext_;
        oSignature_helper = new signature_helper(oContext);
    }

    public ArrayList<cSignature> leerSignature()
    {
        ArrayList<cSignature> oSignatureArrayList = new ArrayList<>();
        SQLiteDatabase oSqLiteDatabase = oSignature_helper.getReadableDatabase();
        if (oSqLiteDatabase != null)
        {
            Cursor oC = oSqLiteDatabase.rawQuery("select descripcion,signature from signature",null);

            while (oC.moveToNext())
            {
                cSignature oS = new cSignature();

                byte[] blobData = oC.getBlob(1);

                Bitmap bitmap = BitmapFactory.decodeByteArray(blobData, 0, blobData.length);

                oS.setFirma_digital(bitmap);

                //Log.e("FIRMA",oS.getFirma_digital().toString());

                oS.setDescripcion(oC.getString(oC.getColumnIndex("descripcion")));
                oSignatureArrayList.add(oS);

            }


        }else{
            Toast.makeText(oContext, "DATABASE NO CREADA", Toast.LENGTH_SHORT).show();
        }

        return oSignatureArrayList;
    }


    public boolean createSignature(cSignature oS)
    {
        try {

            Log.e("FIRMA",oS.getFirma_digital().toString());

            Bitmap firmaBitmap = oS.getFirma_digital();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            firmaBitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] firmaBytes = stream.toByteArray();

            SQLiteDatabase oSqLiteDatabase = oSignature_helper.getWritableDatabase();
            String sql = "INSERT INTO signature (descripcion,signature) VALUES (?,?)";
            SQLiteStatement statement = oSqLiteDatabase.compileStatement(sql);

            statement.clearBindings();
            statement.bindString(1, oS.getDescripcion());
            statement.bindBlob(2, firmaBytes);
            statement.executeInsert();
            statement.close();
            oSqLiteDatabase.close();

            return true;
        }catch (Exception e){
            Log.e("DB",e.toString());
            Toast.makeText(oContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    };


}
