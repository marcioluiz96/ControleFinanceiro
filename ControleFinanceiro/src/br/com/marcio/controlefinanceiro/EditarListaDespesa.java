package br.com.marcio.controlefinanceiro;

import java.security.PublicKey;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

public class EditarListaDespesa extends Activity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_listar_despesa);
		
		Intent it= getIntent();
		if(it!= null){
			
			Bundle params=it.getExtras();
			
			String txt1 = params.getString("txt1");
			String txt2 = params.getString("txt2");
			
		SQLiteDatabase db = openOrCreateDatabase("controleFinanceiro.db",
				Context.MODE_PRIVATE, null);

		Cursor cursor = db.rawQuery("SELECT * FROM 	despesas ", null);

		String[] from = { "descricao", "local", "valor", "data" };
		int[] to = { R.id.texvDescricao, R.id.texvLocal, R.id.texvValor,
				R.id.texvData };

		android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(
				getBaseContext(), R.layout.listar_despesas_model, cursor, from,
				to);

		ListView ltwDados = (ListView) findViewById(R.id.listView1);
		ltwDados.setAdapter(ad);
			
				
			
		
		}
		}

}
