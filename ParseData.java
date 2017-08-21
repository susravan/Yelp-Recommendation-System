
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Created by kumanik on 10/22/16.
 */
public class ParseData {

    public static final ObjectMapper MAPPER = new ObjectMapper();


    public static void createNewReviewsJSON() throws IOException {
        int count = 0;
        HashMap<String, Object> businessMap = ParseData.parseBusinessData();
        HashMap<String, Object> userMap = ParseData.parseUserData();
        PrintWriter out = new PrintWriter(Constants.REVIEWS_OUTPUT_FILE);
        BufferedReader br = new BufferedReader(new FileReader(Constants.REVIEW_JSON));
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String, Object> reviewMap = new HashMap<String, Object>();
        String line;
        while ((line = br.readLine()) != null) {
            HashMap<String,Object> tempMap = MAPPER.readValue(line, typeRef);
            if(businessMap.containsKey(tempMap.get("business_id")) && userMap.containsKey(tempMap.get("user_id"))){
                HashMap<String, Object> singleUserMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if(Constants.REVIEWS_REQUIRED_KEYS.contains(entry.getKey())){
                        if(entry.getKey().equalsIgnoreCase("votes")){
                            LinkedHashMap votesMap = (LinkedHashMap) entry.getValue();
                            singleUserMap.put("funny", votesMap.get("funny"));
                            singleUserMap.put("useful", votesMap.get("useful"));
                            singleUserMap.put("cool", votesMap.get("cool"));
                        }
                        else {
                            singleUserMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
                Map<String, String> tmpBusinessMap = (Map<String, String>)businessMap.get(tempMap.get("business_id"));
                for (Map.Entry<String, String> entry : tmpBusinessMap.entrySet()) {
                    singleUserMap.put(entry.getKey(), entry.getValue());
                }
                reviewMap.put((String)tempMap.get(Constants.REVIEWS_PRIMARY_KEY), singleUserMap);
            }
            count += 1;
            if(count == Constants.REVIEW_PROCESS_SIZE){
                break;
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(reviewMap);
        out.println(json);
        out.close();
    }

    public static HashMap parseBusinessData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Constants.BUSINESS_JSON));
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String, Object> businessMap = new HashMap<String, Object>();
        String line;
        while ((line = br.readLine()) != null) {
            HashMap<String,Object> tempMap = MAPPER.readValue(line, typeRef);
            if(!Collections.disjoint(Constants.CATEGORIES_MATCHING_RESTAURANTS, (List) tempMap.get("categories"))) {
                HashMap<String, Object> singleUserMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if (Constants.BUSINESS_REQUIRED_KEYS.contains(entry.getKey())) {
                        singleUserMap.put(entry.getKey(), entry.getValue());
                    }
                }
                businessMap.put((String) tempMap.get(Constants.BUSINESS_PRIMARY_KEY), singleUserMap);
            }
        }
        return businessMap;
    }

    public static HashMap parseUserData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Constants.USER_JSON));
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String, Object> userMap = new HashMap<String, Object>();
        String line;
        while ((line = br.readLine()) != null) {
            HashMap<String,Object> tempMap = MAPPER.readValue(line, typeRef);
            if((Integer)tempMap.get("review_count") >= Constants.MIN_USER_REVIEW_COUNT){
                HashMap<String, Object> singleUserMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if(Constants.USER_REQUIRED_KEYS.contains(entry.getKey())){
                        singleUserMap.put(entry.getKey(), entry.getValue());
                    }
                }
                userMap.put((String) tempMap.get(Constants.USER_DATA_PRIMARY_KEY), singleUserMap);
            }
        }
        return userMap;
    }
}
