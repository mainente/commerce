package teste.com.commerce;

/**
 * Created by mainente on 15/10/16.
 */
public class Card {
    private String card_holder;
    private String card_number;
    private String card_brand;
    private int expiration_month;
    private int expiration_year;
    private String CVV;
    private int selected;

    public Card(String card_holder, String card_number, String card_brand, int expiration_month, int expiration_year, String CVV,int selected) {
        this.card_holder = card_holder;
        this.card_number = card_number;
        this.card_brand = card_brand;
        this.expiration_month = expiration_month;
        this.expiration_year = expiration_year;
        this.CVV = CVV;
        this.selected=selected;
    }



    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_brand() {
        return card_brand;
    }

    public void setCard_brand(String card_brand) {
        this.card_brand = card_brand;
    }

    public int getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(int expiration_month) {
        this.expiration_month = expiration_month;
    }

    public int getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(int expiration_year) {
        this.expiration_year = expiration_year;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
