import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) throws IOException {
        File transactionsFile = new File("transactions.csv");
        List<Transaction> transactionsList = new BufferedReader(new FileReader(transactionsFile))
                .lines()
                .skip(1)
                .map(f -> new Transaction(f.split(";")))
                .collect(Collectors.toList());
        //procedure style
        int maxCountTransactions = 0;
        PrintWriter pw = new PrintWriter("Purchase.csv");
        //в файле все корзины отсортированы
        Map<String, Integer> productMap = new HashMap<>(); //ключ - id продукта, значение - сколько раз покупали
        Map<String, Double> purchasingPowerForProduct = new HashMap<>(); // ключ - id продукта,
        // значение - покупательская способность
        Map<String, Map<String, Double>> purchasingPowerForBasket = new HashMap<>();
        // для каждой храним каждой корзины покупательскую способность каждого продукта
        String curBasketId = "";  //текущая корзина
        int allCountTransactionsForBasket = 0; //количество покупок для корзины
        for (Transaction transaction :
                transactionsList
        ) {
            if (!transaction.getBASKET_ID().equals(curBasketId)) {
                if (!curBasketId.isEmpty()) {
                    for (Map.Entry<String, Integer> product : productMap.entrySet()) {
                        String key = product.getKey();
                        double purchasing = (double) product.getValue() / allCountTransactionsForBasket;
                        purchasingPowerForProduct.put(key,
                                purchasing);
                        //считаем покупательскую способность для кажого продукта в корзине
                    }
                    purchasingPowerForBasket.put(curBasketId, purchasingPowerForProduct);
                    //выводим в файл покупательскую способность в корзине
                    for (Map.Entry<String, Map<String, Double>> pair : purchasingPowerForBasket.entrySet()) {
                        pw.println(pair.getKey());
                        for (Map.Entry<String, Double> purchasing : pair.getValue().entrySet())
                            pw.println(purchasing.getKey() + ";" + purchasing.getValue()
                                    .toString().replace(".", ","));
                    }
                }
                purchasingPowerForBasket.clear();
                productMap.clear();
                purchasingPowerForProduct.clear();
                allCountTransactionsForBasket = 0;
            }
            curBasketId = transaction.getBASKET_ID();
            int countTransactions;
            //считаем сколько покупался товар в корзине
            if (!productMap.containsKey(transaction.getPROD_CODE())) {
                countTransactions = 1;
                productMap.put(transaction.getPROD_CODE(), countTransactions);
            } else {
                countTransactions = productMap.get(transaction.getPROD_CODE()) + 1;
                productMap.replace(transaction.getPROD_CODE(), countTransactions);
            }
            allCountTransactionsForBasket++;
        }
        pw.close();
    }
}
