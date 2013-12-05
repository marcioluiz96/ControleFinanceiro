package br.com.marcio.controlefinanceiro;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListarExcluirDespesas extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excluir_despesa);
	}
		public void onResume(){
			super.onResume();
		
		SQLiteDatabase db = openOrCreateDatabase("controleFinanceiro.db",
				Context.MODE_PRIVATE, null);

		Cursor cursor = db.rawQuery("SELECT * FROM 	despesas", null);

		String[] from = {"descricao", "local", "valor", "data" };
		int[] to = { R.id.texvDescricao, R.id.texvLocal, R.id.texvValor,
				R.id.texvData };

		android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(
				getBaseContext(), R.layout.listar_despesas_model, cursor, from,
				to);

		ListView ltwDados = (ListView) findViewById(R.id.listExcluir);

		ltwDados.setAdapter(ad);

		ltwDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView adapter, View view,
					int position, long id) {

				SQLiteCursor c = (SQLiteCursor) adapter.getAdapter().getItem(
						position);

				Intent it = new Intent(getBaseContext(), ExcluirDespesas.class);
				it.putExtra("id", c.getInt(0));
				startActivity(it);
			}

		});

		db.close();
	}
			
}
