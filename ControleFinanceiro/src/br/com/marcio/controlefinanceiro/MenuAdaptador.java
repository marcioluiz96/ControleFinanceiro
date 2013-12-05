package br.com.marcio.controlefinanceiro;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuAdaptador extends BaseExpandableListAdapter {

	private Activity activity;

	String[] listaPai = { "Despesas", "Ganhos",  "Mostrar Saldo" };
	String[][] listafilho = { { "Adicionar", "Excluir", "Listar" },
			{ "Adicionar", "Excluir", "Listar" }, { "Por Data" } };

	public MenuAdaptador(Activity activity) {
		this.activity = activity;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return listafilho[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		// Criamos um TextView que conterá as informações da listafilho que
		// criamos
		TextView textViewSubLista = new TextView(activity);
		textViewSubLista.setText(listafilho[groupPosition][childPosition]);
		textViewSubLista.setTextSize(20);
		// Definimos um alinhamento
		textViewSubLista.setPadding(20, 10, 0, 10);

		return textViewSubLista;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return listafilho[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return listaPai[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return listaPai.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		// Criamos um TextView que conterá as informações da listaPai que
		TextView textViewTitulos = new TextView(activity);
		TextView textViewSubtitulos = new TextView(activity);
		
		
		textViewTitulos.setText(listaPai[groupPosition]);
		// Definimos um alinhamento
		textViewTitulos.setPadding(30, 0, 0, 0);
		// Definimos o tamanho do texto
		textViewTitulos.setTextSize(30);
		// Definimos que o texto estará em negrito
		textViewTitulos.setTypeface(null, Typeface.BOLD);
		
		//inserindo a imagem no menu
		ImageView img = new ImageView(this.activity);
		img.setPadding(50, 0, 0, 0);
		if(groupPosition==0)
			img.setImageResource(R.drawable.despesa);
		if(groupPosition==1)
			img.setImageResource(R.drawable.ganhos);
		if(groupPosition==2)
			img.setImageResource(R.drawable.busca);

		
		textViewSubtitulos.setText("Opções para: "+listaPai[groupPosition]);
		textViewSubtitulos.setPadding(30, 0, 0, 0);
		textViewSubtitulos.setTextSize(15);
		textViewSubtitulos.setTypeface(null, Typeface.ITALIC);

		LinearLayout linear = new LinearLayout(this.activity);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout linear1= new LinearLayout(this.activity);
		linear1.setOrientation(LinearLayout.VERTICAL);
		
		linear.addView(img);
		linear1.addView(textViewTitulos);
		linear1.addView(textViewSubtitulos);
		linear.addView(linear1);
		
		return linear;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {

		// Defina o return como sendo true se vc desejar que sua sublista seja
		// selecionável
		return true;
	}

}
