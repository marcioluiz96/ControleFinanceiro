package br.com.marcio.controlefinanceiro;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarGanho extends Activity {

	EditText txtData;

	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_ganho);

		txtData = (EditText) findViewById(R.id.textoData);

		txtData.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();

		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);

		switch (id) {
		case DATE_DIALOG_ID:

			return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	private void updateDisplay() {
		txtData.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mDay).append("/").append(mMonth + 1).append("/")
				.append(mYear));
	}

	// metodo para enserir um ganho
	public void inserirGanho(View cadastro) {
		EditText descricao = (EditText) findViewById(R.id.textoDescricao);
		EditText local = (EditText) findViewById(R.id.textoLocal);
		EditText data = (EditText) findViewById(R.id.textoData);
		EditText valor = (EditText) findViewById(R.id.textoValor);

		if (descricao.getText().toString().length() <= 0) {
			descricao.setError("Preencha o campo Descrição.");
			descricao.requestFocus();
		} else if (valor.getText().toString().length() <= 0) {
			valor.setError("Preencha o campo valor.");
			valor.requestFocus();
		} else if (local.getText().toString().length() <= 0) {
			local.setError("Preencha o campo local.");
			local.requestFocus();
		} else if (data.getText().toString().length() <= 0) {
			data.setError("Preencha o campo Data.");
			data.requestFocus();
		} else {
			try {

				SQLiteDatabase db = openOrCreateDatabase(
						"controleFinanceiro.db", Context.MODE_PRIVATE, null);
				ContentValues ctv = new ContentValues();
				ctv.put("descricao", descricao.getText().toString());
				ctv.put("local", local.getText().toString());
				String v = valor.getText().toString();
				v = v.replace(',', '.');
				ctv.put("valor", v);
				ctv.put("data", data.getText().toString());

				if (db.insert("ganhos", "_id", ctv) > 0) {
					Toast.makeText(getBaseContext(), "Ganho cadastrado",
							Toast.LENGTH_SHORT).show();
					finish();
				} else {
					Toast.makeText(getBaseContext(),
							"ERRO!!! Ganho Não cadastrada", Toast.LENGTH_SHORT);

				}
			} catch (Exception exception) {
				Toast.makeText(getBaseContext(), exception.getMessage(),
						Toast.LENGTH_SHORT).show();

			}

		}
	}
}
