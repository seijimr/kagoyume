package base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import ky.ItemDataBeans;

public class YahooApiManager {

    private static final String APP_ID = "dj0zaiZpPUl3V0RUSWRzWnVqOSZzPWNvbnN1bWVyc2VjcmV0Jng9NDQ-";
    private static final String ITEM_SEARCH_URI = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
    private static final String CODE_SEARCH_URI = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";

    //引数で検索ワードとページ数を受け取り
    public String productData(String searchword, String page) throws IOException {
        //クエリに数字以外が入力されていた場合はpageを0にする
        try {
            if (Integer.parseInt(page) < 0) {
                page = "0";
            }
        } catch (NumberFormatException e) {
            page = "0";
        }

        URL url = new URL(ITEM_SEARCH_URI + "?appid=" + APP_ID + "&query=" + searchword + "&hits=20" + "&offset=" + 20 * new Integer(page));

        HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();//接続するために穴をあける？
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);//リダイレクトを防ぐ

        urlconn.connect();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

        StringBuffer responseBuffer = new StringBuffer();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            responseBuffer.append(line);
        }

        reader.close();
        urlconn.disconnect();

        String response = responseBuffer.toString();

        return response;

    }

    //オーバーロード(引数にページがない時）
    public String productData(String searchword) throws IOException {
        return productData(searchword, "0");
    }

    //引数でjsonを受け取り検索結果をリストで渡すメソッド
    public ArrayList<ItemDataBeans> parse(String json) throws IOException {
        JsonNode resultSetNode = new ObjectMapper().readTree(json).get("ResultSet");
        JsonNode result = resultSetNode.get("0").get("Result");

        int totalresult = resultSetNode.get("totalResultsReturned").asInt();
        ArrayList<ItemDataBeans> resultData = new ArrayList<ItemDataBeans>();

        for (Integer i = 0; i < totalresult; i++) {
            ItemDataBeans idb = new ItemDataBeans();
            JsonNode item = result.get(i.toString());
            JsonNode img = item.get("Image");
            idb.setName(item.get("Name").asText());
            idb.setSmallimg(img.get("Small").asText());
            idb.setMedimg(img.get("Medium").asText());
            idb.setAvailability(item.get("Availability").asText());
            idb.setPrice(item.get("Price").get("_value").asText());
            idb.setCode(item.get("Code").asText());
            resultData.add(idb);
        }
        //リストに検索結果を格納1
        return resultData;
    }

    public String total(String json) throws IOException {
        String totalResult = new ObjectMapper().readTree(json).get("ResultSet").get("totalResultsAvailable").asText();
        return totalResult;
    }

    public ItemDataBeans codeSearch(String code) throws IOException {
        URL url = new URL(CODE_SEARCH_URI + "?appid=" + APP_ID + "&itemcode=" + code + "&responsegroup=medium");

        HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();//接続するために穴をあける？
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);//リダイレクトを防ぐ

        urlconn.connect();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

        StringBuffer responseBuffer = new StringBuffer();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            responseBuffer.append(line);
        }

        reader.close();
        urlconn.disconnect();

        String response = responseBuffer.toString();

        JsonNode result = new ObjectMapper().readTree(response).get("ResultSet").get("0").get("Result").get("0");

        ItemDataBeans idb = new ItemDataBeans();
        JsonNode img = result.get("Image");
        idb.setName(result.get("Name").asText());
        idb.setDescription(result.get("Description").asText());
        idb.setSmallimg(img.get("Small").asText());
        idb.setMedimg(img.get("Medium").asText());
        idb.setPrice(result.get("Price").get("_value").asText());
        idb.setCode(code);
        return idb;
    }
}
