package com.qy.app;



import com.example.fcpfapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends Activity {

	private TextView qyding_view;
	private RadioGroup qyqyactors_rgp;
	private RadioButton qyradioButtonThree;
	private int qyactors=4;
	private EditText editText[][]=new EditText[3][4];
	private int qyhuoNiao[]={0,0,0,0};
	private EditText qyprice_edit;
	private double qyprice=0.5;
	private int qytuoNiao[]={0,0,0,0};
	private int qyhuXi[]={0,0,0,0};
	double qyresult[]={0,0,0,0};
	private TextView qyresult_view[]=new TextView[4];
	private Button calculate_btn,clean_btn,exit_btn;
	private qymyCalculateOnClickListener qymyCal=new qymyCalculateOnClickListener();
	private qymyOnFocusChangListener qymyOnFocusChangListener=new qymyOnFocusChangListener();
	private qymyClearOnClickListener qymyClearOnClickListener=new qymyClearOnClickListener();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*findObjeck();*/
		calculate_btn=(Button)findViewById(R.id.buttonJiSuan);
		exit_btn=(Button)findViewById(R.id.buttonexit);
		clean_btn=(Button)findViewById(R.id.qybuttonclean);
		qyresult_view[0]=(TextView)findViewById(R.id.TextViewJGJia);
		qyresult_view[1]=(TextView)findViewById(R.id.TextViewJGYi);
		qyresult_view[2]=(TextView)findViewById(R.id.TextViewJGBin);
		qyresult_view[3]=(TextView)findViewById(R.id.TextViewJGDing);
		editText[0][0]=(EditText)findViewById(R.id.qyedthnJia);
		editText[0][1]=(EditText)findViewById(R.id.qyedthnYi);
		editText[0][2]=(EditText)findViewById(R.id.qyedthnBin);
		editText[0][3]=(EditText)findViewById(R.id.qyedthnDing);
		editText[1][0]=(EditText)findViewById(R.id.qyedttnJia);
		editText[1][1]=(EditText)findViewById(R.id.qyedttnYi);
		editText[1][2]=(EditText)findViewById(R.id.qyedttnBin);
		editText[1][3]=(EditText)findViewById(R.id.qyedttnDing);
		editText[2][0]=(EditText)findViewById(R.id.qyedthxJia);
		editText[2][1]=(EditText)findViewById(R.id.qyedthxYi);
		editText[2][2]=(EditText)findViewById(R.id.qyedthxBin);
		editText[2][3]=(EditText)findViewById(R.id.qyedthxDing);
		qyqyactors_rgp=(RadioGroup)findViewById(R.id.qy1);
		qyradioButtonThree=(RadioButton)findViewById(R.id.qyr1);
		qyprice_edit=(EditText)findViewById(R.id.qyed);
		qyding_view=(TextView)findViewById(R.id.qyDin);
		qyqyactors_rgp.setOnCheckedChangeListener(new MyOncheckChangListener());
		
		calculate_btn.setOnClickListener(qymyCal);
		setEditFocusListener();
		
		exit_btn.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		clean_btn.setOnClickListener(qymyClearOnClickListener);
	}

	

	/*private void findObjeck() {
		// TODO Auto-generated method stub
		
	}*/
	private void init()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(editText[i][j].getText().toString().equals(""))
					editText[i][j].setText("0");
			}
		}
		if(qyprice_edit.getText().toString().equals(""))
			qyprice_edit.setText("0.5");
		try{for(int i=0;i<qyactors; i++){
			
				qyhuoNiao[i]=Integer.parseInt(editText[0][i].getText().toString());
				qytuoNiao[i]=Integer.parseInt(editText[1][i].getText().toString());
				qyhuXi[i]=Integer.parseInt(editText[2][i].getText().toString());
			}
		
		qyprice=Double.parseDouble(qyprice_edit.getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {
				
            }
	private void calculate()
	{
		qyresult[0]=qyresult[1]=qyresult[2]=qyresult[3]=0;
		for(int i=0;i<qyactors;i++)
		{
			for(int j=0; j<qyactors;j++)
			{
				qyresult[i]+=compare(qyhuXi[i],qyhuXi[j])*(qytuoNiao[i]+qytuoNiao[j]);
				qyresult[i]+=(myInt(qyhuXi[i])-myInt(qyhuXi[j]))*(qyhuoNiao[i]+1)*(qyhuoNiao[j]+1)*qyprice;
				
			}
		}
	}
	private void view()
	{
		for(int i=0;i<qyactors;i++){
			qyresult_view[i].setText(String.valueOf(Math.round(qyresult[i]*10/10.0)));
			
		}
	}
	class MyOncheckChangListener implements RadioGroup.OnCheckedChangeListener{
		
		@Override
		public void onCheckedChanged(RadioGroup group,int checkedId)
		{
			clear();
			boolean tag;
			if(qyradioButtonThree.getId()==checkedId){
			qyactors=3;
			tag=false;
		}else
			{
				qyactors=4;
				tag=true;
			}
			for (int i=0;i<3;i++)
			{
				editText[i][3].setEnabled(tag);
			}
			qyresult_view[3].setEnabled(tag);
			qyding_view.setEnabled(tag);
		}
	}
	 class qymyClearOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			clear();
		}

	}
	 private void clear()
	 {
		 for(int i =0;i<3;i++)
		 {
			 for(int j=0;j<4;j++){
				 editText[i][j].setText("0");
			 }
		 }
		 for (int j=0;j<4;j++)
		 {
			 qyresult_view[j].setText("0");
		 }
	 }
	 private void setEditFocusListener()
	 {
		 for(int i=0;i<3;i++)
		 {
			 for(int j=0;j<4;j++)
			 {
				 editText[i][j].setOnFocusChangeListener(qymyOnFocusChangListener);
			 }
		 }
		 qyprice_edit.setOnFocusChangeListener(qymyOnFocusChangListener);
	 }
	 class qymyCalculateOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			init();
			calculate();
			view();
		}
		 
	}
	 private int compare(int x ,int y){
		if(x==y)return 0;
		 return x>y?1:-1;
		}
	 private int myInt(int qyhuXi)
	 {
		 int tag=1,qyhuXi_abs;
		 qyhuXi_abs=Math.abs(qyhuXi);
		 if(qyhuXi<0)tag=-1;
		 if(qyhuXi_abs%10>=5)
			 qyhuXi_abs=(qyhuXi_abs/10+1)*10;
		 else
			 qyhuXi_abs=qyhuXi_abs/10*10;
		 return qyhuXi_abs*tag;
	 }
	  class qymyOnFocusChangListener implements View.OnFocusChangeListener{

		@Override
		public void onFocusChange(View v, boolean hanFocus) {
			// TODO Auto-generated method stub
			EditText editText=(EditText)v;
			if(hanFocus)
			{
				editText.setText("");
			}
			else {
				if(editText.getText().toString().equals(""))
				{
					if(editText.getId()==R.id.qyed)
						editText.setText("0.5");
					else
						editText.setText("0");
					
				}
			}
			
		}
		  
	  }

}
