package ge.tsu.android.rssfeed.data;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Array;
import java.util.ArrayList;


public class GetNBGCurrencydataAsyncTask extends AsyncTask<Void,Void, ArrayList<Currency>> {
    private CallBack callBack;

    @Override
    protected ArrayList<Currency> doInBackground(Void... voids) {
        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        try{
            Document document =  Jsoup.connect("http://www.nbg.ge/rss.php").get();
            String element =  document.getElementsByTag("description").get(1).data();

            int begin=0,end=element.length();
           // String temElement = element.substring(begin,end);

            int lastInd = 0;
            int count = 0;

            while(lastInd != -1){

                lastInd = element.indexOf("<tr>",lastInd);

                if(lastInd != -1){
                    count ++;
                    lastInd += "<tr>".length();
                }
            }

            for(int j=0; j<count; j++){
                String temElement = element.substring(begin,end);

                int firstIndex = temElement.indexOf("<tr>");
                int lastIndex = temElement.indexOf("</tr>");
                String string=temElement.substring(firstIndex,lastIndex+5);
                begin+=lastIndex+5;

                int beginTd=0,endTd=string.length();

                ArrayList<String> stringArrayList=new ArrayList<>();
                for(int i=0; i<5; i++){
                    String temTdString = string.substring(beginTd,endTd);

                    int firstIndexTd = temTdString.indexOf("<td>");
                    int lastIndexTd = temTdString.indexOf("</td>");
                    String tdString = temTdString.substring(firstIndexTd+4,lastIndexTd);
                    beginTd+=lastIndexTd+5;
                    stringArrayList.add(tdString);
                }
                Currency currency=new Currency();
                currency.setSimbol(stringArrayList.get(0));
                currency.setCurrencyName(stringArrayList.get(1));
                currency.setFirstNumber(stringArrayList.get(2));
                currency.setArrowImg(stringArrayList.get(3));
                currency.setSecondNumber(stringArrayList.get(4));

                currencyArrayList.add(currency);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return currencyArrayList;
    }
    @Override
    protected void onPostExecute(ArrayList<Currency> currencies) {
        if(callBack!=null){
            callBack.onDataReceived(currencies);
        }
    }

    public interface CallBack{
        void onDataReceived(ArrayList<Currency> currencies);
    }
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }
}
