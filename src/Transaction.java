public class Transaction {
    private String PROD_CODE;
    private String BASKET_ID;

    public Transaction(String[] split) {
        this.PROD_CODE = split[0];
        this.BASKET_ID = split[1];
    }

    public String getPROD_CODE() {
        return PROD_CODE;
    }

    public String getBASKET_ID() {
        return BASKET_ID;
    }
}
