package ge.tsu.android.rssfeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ge.tsu.android.rssfeed.data.Currency;
import ge.tsu.android.rssfeed.data.GetNBGCurrencydataAsyncTask;

public class MainActivity extends Activity {

    private ListView mCurrencys;
    private CurrencyArrayAdapter currencyArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrencys=findViewById(R.id.currency);

        currencyArrayAdapter = new CurrencyArrayAdapter(this,0,new ArrayList<Currency>());
    }

    /// method for getting routes
    public void getCurrencies(View view){
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        GetNBGCurrencydataAsyncTask getTTCRoutdataAsyncTask=new GetNBGCurrencydataAsyncTask();
        GetNBGCurrencydataAsyncTask.CallBack callBack=new GetNBGCurrencydataAsyncTask.CallBack() {
            @Override
            public void onDataReceived(ArrayList<Currency> currencies) {
                mCurrencys.setAdapter(currencyArrayAdapter);
                currencyArrayAdapter.addAll(currencies);
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.getCurrencyBtn).setVisibility(View.GONE);
            }
        };
        getTTCRoutdataAsyncTask.setCallBack(callBack);
        getTTCRoutdataAsyncTask.execute();

    }
    class CurrencyArrayAdapter extends ArrayAdapter<Currency>{

        private Context context;
        public CurrencyArrayAdapter(@NonNull Context context, int resource, @NonNull List<Currency> objects) {
            super(context, resource, objects);
            this.context=context;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.tablo_item,parent,false);
            final Currency currentCurrency = getItem(position);
            TextView textViewSimbol = view.findViewById(R.id.simbol);
            TextView textViewName = view.findViewById(R.id.name);
            TextView textViewFNumber = view.findViewById(R.id.firstNumber);
            ImageView imageView =view.findViewById(R.id.arrowIcon);
            TextView textViewSNumber = view.findViewById(R.id.secondNumber);
            textViewSimbol.setText(currentCurrency.getSimbol());
            textViewName.setText(currentCurrency.getCurrencyName());
            textViewFNumber.setText(currentCurrency.getFirstNumber());
            if(currentCurrency.getArrowImg().contains("red")){
                imageView.setImageResource(R.drawable.uparrow);
            }else if(currentCurrency.getArrowImg().contains("green")){
                imageView.setImageResource(R.drawable.downarrow);
            }

            textViewSNumber.setText(currentCurrency.getSecondNumber());

            return view;
        }
    }
}



