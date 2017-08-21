
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kumanik on 10/22/16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //HashMap userMap = ParseData.parseUserData();
       // HashMap businessMap = ParseData.parseBusinessData();
        ParseData.createNewReviewsJSON();
        //System.out.println(reviewMap);
        //HashMap newReviewsMap = createReviewsJson(userMap, businessMap, reviewMap);
    }

//    public static HashMap createReviewsJson(HashMap userMap, HashMap businessMap, HashMap reviewMap){
//        for (Map.Entry<String, Object> entry : reviewMap.entrySet()) {
//            if(Constants.REVIEWS_REQUIRED_KEYS.contains(entry.getKey())){
//                singleUserMap.put(entry.getKey(), entry.getValue());
//            }
//        }
//    }
}
