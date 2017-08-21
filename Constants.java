
import com.google.common.collect.ImmutableSet;

/**
 * Created by kumanik on 10/22/16.
 */
public class Constants {

    // File location
    public static final String USER_JSON = "/Users/kumanik/IdeaProjects/Yelp Recommendation System/dataset/yelp_academic_dataset_user.json";
    public static final String CHECKIN_JSON = "";
    public static final String REVIEW_JSON = "/Users/kumanik/IdeaProjects/SML - Yelp Recommendation System/src/dataset/yelp_academic_dataset_review.json";
    public static final String TIP_JSON = "";
    public static final String BUSINESS_JSON = "/Users/kumanik/IdeaProjects/SML - Yelp Recommendation System/src/dataset/yelp_academic_dataset_business.json";

    public static final String REVIEWS_OUTPUT_FILE = "/Users/kumanik/preprocessed_reviews.json";
    public static final int REVIEW_PROCESS_SIZE = 1000;
    /**
     * USER DATA
     * ----------
     * Keys in User Data:
     * elite
     * compliments
     * yelping_since
     * user_id
     * average_stars
     * name
     * review_count
     * votes
     * type
     * friends
     * fans
     */
    public static final String USER_DATA_PRIMARY_KEY = "user_id";
    public static final ImmutableSet<String> USER_REQUIRED_KEYS = ImmutableSet.of(
            "user_id",
            "name",
            "review_count");
    public static final int MIN_USER_REVIEW_COUNT = 17;

    /**
     * BUSINESS DATA
     * --------------
     * Keys in Business Data
     * hours
     * neighborhoods
     * city
     * latitude
     * review_count
     * full_address
     * stars
     * type
     * name
     * attributes
     * categories
     * state
     * business_id
     * open
     * longitude
     */
    public static final String BUSINESS_PRIMARY_KEY = "business_id";
    public static final ImmutableSet<String> BUSINESS_REQUIRED_KEYS = ImmutableSet.of(
            "name",
            "full_address");
    public static final ImmutableSet<String> CATEGORIES_MATCHING_RESTAURANTS = ImmutableSet.of(
            "Restaurants",
            "Fast Food");

    /**
     * REVIEWS DATA
     * ------------
     * Keys in reviews data
     * date
     * review_id
     * user_id
     * votes
     * stars
     * text
     * type
     * business_id
     */

    public static final String REVIEWS_PRIMARY_KEY = "review_id";
    public static final ImmutableSet<String> REVIEWS_REQUIRED_KEYS = ImmutableSet.of(
            "business_id",
            "text",
            "user_id",
            "votes");
}






