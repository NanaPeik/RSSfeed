package ge.tsu.android.rssfeed.data;

public class Currency {
    private String simbol;
    private String currencyName;
    private String firstNumber;
    private String arrowImg;
    private String secondNumber;

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public CharSequence getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getArrowImg() {
        return arrowImg;
    }

    public void setArrowImg(String arrowImg) {
        this.arrowImg = arrowImg;
    }

    public CharSequence getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }
}
