package br.com.marcio.controlefinanceiro;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MostrarSaldo extends Activity {
	String txt1;
	String txt2;
	int aux;
	EditText txtDataIni;
	EditText txtDataFim;
	private Intent intent;
	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 0;
	static final int DATE_DIALOG_ID_2 =1;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrar_saldo);

		txtDataIni = (EditText) findViewById(R.id.textoDataIni);
		txtDataFim = (EditText) findViewById(R.id.textoDataFim);
		
		
		txtDataIni.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
				aux=1;

			}
		});
		
		
		txtDataFim.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID_2);
				aux=2;

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
		case DATE_DIALOG_ID_2:
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
		if(aux==1){
		txtDataIni.setText(new StringBuilder()
				// Month is 0 based so add 1
		.append(mYear).append("-").append(mMonth + 1)
		.append("-").append(mDay));
		txt1=txtDataIni.getText().toString();
		aux=0;
		}
		else if(aux==2){
		txtDataFim.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(mYear).append("-").append(mMonth + 1)
		.append("-").append(mDay));
		txt2=txtDataFim.getText().toString();
		aux=0;
		}
		
	}
	public void saldo(View saldo){
		try{
			
			SQLiteDatabase db = openOrCreateDatabase(
					"controleFinanceiro.db", Context.MODE_PRIVATE, null);
			
				Cursor cursor =db.rawQuery("SELECT sum(valor) FROM despesas WHERE data between '"+txt1+"' AND '"+txt2+"'", null);
				Cursor cursor1 =db.rawQuery("SELECT sum(valor) FROM ganhos WHERE data between '"+txt1+"' AND '"+txt2+"'", null);
				
				TextView texto = (TextView)findViewById(R.id.textLab);
				
				if (cursor.moveToFirst() && cursor1.moveToFirst()) {
					
					double total = cursor.getInt(0);
					double total1 = cursor1.getInt(0);
					double tTotal = total1-total;
					
					texto.setTextSize(20);
					texto.setText("Seu Saldo é De: R$ "+String.valueOf(tTotal));
					
					}
				
				db.close();
				intent = new Intent(this,MostrarSaldo.class);
		} catch (Exception exception) {
			Toast.makeText(getBaseContext(), exception.getMessage(),
					Toast.LENGTH_SHORT).show();

		}
	}

}
