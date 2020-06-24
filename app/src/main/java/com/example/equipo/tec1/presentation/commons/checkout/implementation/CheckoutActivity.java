package com.example.equipo.tec1.presentation.commons.checkout.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.equipo.tec1.BaseActivity;
import com.example.equipo.tec1.R;
import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;
import com.example.equipo.tec1.presentation.commons.checkout.interfaces.ICheckoutPresenter;
import com.example.equipo.tec1.presentation.commons.checkout.interfaces.ICheckoutView;
import com.example.equipo.tec1.presentation.commons.successview.implementation.SuccessView;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class CheckoutActivity  extends BaseActivity implements ICheckoutView {

    private ICheckoutPresenter presenter;
    Spinner payment_methods,card_issuer,sp_installment;
    EditText txt_amount;
    Button btn_pagar;
    final ArrayList<String> arrayList_paymentMehtods = new ArrayList<>();
    final ArrayList<String> arrayList_cardissuers = new ArrayList<>();
    final ArrayList<String> arrayList_installments = new ArrayList<>();
    private static final int REQUEST_SIGNUP = 0;
    boolean btn_ban=false;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        txt_amount=findViewById(R.id.txt_amount);
        payment_methods = findViewById(R.id.spinner);
        card_issuer =findViewById(R.id.spinner2);
        sp_installment=findViewById(R.id.spinner3);
        btn_pagar = findViewById(R.id.btn_pagar);



        presenter = new CheckoutPresenter( this);


        txt_amount.addTextChangedListener(new TextWatcher() {
            DecimalFormat dec = new DecimalFormat("0.00");

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
           /*     if(!s.toString().equals(current))
                {
                   txt_amount.removeTextChangedListener(this);

                    int selection = txt_amount.getSelectionStart();


                    // We strip off the currency symbol
                    String replaceable = String.format("[%s,\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double price;

                    // Parse the string
                    try
                    {
                        price = Double.parseDouble(cleanString);
                    }
                    catch(java.lang.NumberFormatException e)
                    {
                        price = 0;
                    }

                    // If we don't see a decimal, then the user must have deleted it.
                    // In that case, the number must be divided by 100, otherwise 1
                    int shrink = 1;
                    if(!(s.toString().contains(".")))
                    {
                        shrink = 100;
                    }

                    // Reformat the number
                    String formated = currencyFormat.format((price / shrink));

                    current = formated;
                   txt_amount.setText(formated);
                   txt_amount.setSelection(Math.min(selection, txt_amount.getText().length()));

                   txt_amount.addTextChangedListener(this);
                }*/


            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txt_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {


                } else {
                        String aux=txt_amount.getText().toString();
                    if(txt_amount.getText().toString().isEmpty()){
                        showToast("Debe ingresar un monto");
                    }else{
                        presenter.PaymentMethods();
                    }
                }
            }
        });

        txt_amount.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    presenter.PaymentMethods();
                    return true;
                }
                return false;
            }
        });

       btn_pagar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              if(btn_ban){
                   irSuccesView();
               }else{
                  showToast("Debe Elegir Metodo de Pago, Tajeta y Cuotas.");
               }



           }
       });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    void irSuccesView() {
        Intent intent = new Intent(getApplicationContext(), SuccessView.class); //SignupActivity
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
    }


    @Override
    public void ShowPaymentMethods(final List<PaymentMethod> paymentMethods) {
        arrayList_paymentMehtods.clear();

        for (int a = 0; a < paymentMethods.size(); a++) { //armo
            arrayList_paymentMehtods.add(paymentMethods.get(a).getName()+" - "+ paymentMethods.get(a).getPayment_type_id());
        }
        payment_methods.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList_paymentMehtods));
        payment_methods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
                String text = mySpinner.getSelectedItem().toString();
                String text1 = adapterView.getItemAtPosition(position).toString();
                String payment_method_id=paymentMethods.get(position).getId();
                presenter.CardIssuers(payment_method_id);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void ShowCardIssuers(final List<CardIssuer> cardIssuers, final String payment_method_id) {
        arrayList_cardissuers.clear();
        for (int a = 0; a < cardIssuers.size(); a++) {
            arrayList_cardissuers.add(cardIssuers.get(a).getName());
        }
        card_issuer.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList_cardissuers));
        card_issuer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
                String text = mySpinner.getSelectedItem().toString();
                String text1 = adapterView.getItemAtPosition(position).toString();
                String issuer_id =cardIssuers.get(position).getId();
                String aux_amount =txt_amount.getText().toString().replace("$","").replace("€","").replace(",",".");
                presenter.Installments(aux_amount,payment_method_id,issuer_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @Override
    public void ShowInstallments(List<Installment> installment) {
        arrayList_installments.clear();
        for (int a = 0; a < installment.get(0).getInstalments().size(); a++) {
            arrayList_installments.add(installment.get(0).getInstalments().get(a).getRecommended_message());
        }
        if(arrayList_installments.size()!=0){
           btn_ban=true;
        }

        sp_installment.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, arrayList_installments));
        sp_installment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner3);
                String text = mySpinner.getSelectedItem().toString();
                String text1 = adapterView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
