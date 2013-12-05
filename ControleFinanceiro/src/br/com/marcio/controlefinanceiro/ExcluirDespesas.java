package br.com.marcio.controlefinanceiro;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class ExcluirDespesas extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excluir_despesa);
		
		Intent it = getIntent();
		
		int id =it.getIntExtra("id", 0);

		SQLiteDatabase db = openOrCreateDatabase("controleFinanceiro.db",
				Context.MODE_PRIVATE, null);

		ContentValues ctv = new ContentValues();

		if (db.delete("despesas", "_id=?", new String[] { String.valueOf(id) }) > 0) {
			Toast.makeText(getBaseContext(), "despesa excluida",
					Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(getBaseContext(), "despesa excluida",
					Toast.LENGTH_SHORT).show();

		}
	}
}
