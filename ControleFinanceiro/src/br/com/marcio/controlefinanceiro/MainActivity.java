package br.com.marcio.controlefinanceiro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	ExpandableListView listaMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// criando banco de dados
		SQLiteDatabase db = openOrCreateDatabase("controleFinanceiro.db",
				Context.MODE_PRIVATE, null);

		// Tabela de despesas
		StringBuilder sqlDespesas = new StringBuilder();
		sqlDespesas.append("CREATE TABLE IF NOT EXISTS despesas (");
		sqlDespesas.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
		sqlDespesas.append("descricao VARCHAR(120),");
		sqlDespesas.append("local VARCHAR(100),");
		sqlDespesas.append("valor BIGDECIMAL,");
		sqlDespesas.append("data DATE)");
		db.execSQL(sqlDespesas.toString());

		// tabela de ganho
		StringBuilder sqlGanho = new StringBuilder();
		sqlGanho.append("CREATE TABLE IF NOT EXISTS ganhos (");
		sqlGanho.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
		sqlGanho.append("descricao VARCHAR(120),");
		sqlGanho.append("local VARCHAR(100),");
		sqlGanho.append("valor BIGDECIMAL,");
		sqlGanho.append("data DATE)");
		db.execSQL(sqlGanho.toString());

		// referência a ExpandableList criado no layout
		listaMenu = (ExpandableListView) findViewById(R.id.expandableListView1);
		// Adapter que conterá as informações que formarão a lista
		listaMenu.setAdapter(new MenuAdaptador(this));

		listaMenu
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						// TODO Auto-generated method stub
						return listaMenu_onChildClick(parent, v, groupPosition,
								childPosition, id);
					}
				});

	}

	public boolean listaMenu_onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// chamar a activity cadastrar dispesa
		if (groupPosition == 0 && childPosition == 0) {
			Intent it = new Intent(getBaseContext(), CadastrarDispesa.class);
			startActivity(it);
		}
		if (groupPosition == 0 && childPosition == 1) {
			Intent it = new Intent(getBaseContext(),
					ListarExcluirDespesas.class);
			startActivity(it);
		}
		if (groupPosition == 0 && childPosition == 2) {
			Intent it = new Intent(getBaseContext(), ListarDespesas.class);
			startActivity(it);
		}
		// chamar a activity cadastrar ganho
		if (groupPosition == 1 && childPosition == 0) {
			Intent it = new Intent(getBaseContext(), CadastrarGanho.class);
			startActivity(it);
		}
		if (groupPosition == 1 && childPosition == 1) {
			Intent it = new Intent(getBaseContext(), ListarExcluirGanhos.class);
			startActivity(it);
		}
		if (groupPosition == 1 && childPosition == 2) {
			Intent it = new Intent(getBaseContext(), ListarGanhos.class);
			startActivity(it);
		}
		if (groupPosition == 2 && childPosition == 0) {
			Intent it = new Intent(getBaseContext(), MostrarSaldo.class);
			startActivity(it);
		}


		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}