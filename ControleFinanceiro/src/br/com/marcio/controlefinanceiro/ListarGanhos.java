package br.com.marcio.controlefinanceiro;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;



public class ListarGanhos extends Activity  {
	
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.listar_ganhos);
			
		SQLiteDatabase db = openOrCreateDatabase("controleFinanceiro.db",
				Context.MODE_PRIVATE, null);

		Cursor cursor = db.rawQuery("SELECT * FROM 	ganhos", null);
		
		String[] from = {"descricao","local","valor","data"};
		int[] to = {R.id.texvDescricao, R.id.texvLocal,R.id.texvValor,R.id.texvData};
		
		android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(
				getBaseContext(), R.layout.listar_ganhos_model, cursor, from, to);
		
		ListView ltwDados = (ListView)findViewById(R.id.listView1);
		
		ltwDados.setAdapter(ad);
		}
	}

