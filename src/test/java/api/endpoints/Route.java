package api.endpoints;

public class Route {
    public static String baseUrl = "https://petstore.swagger.io/v2";

    /* pet model routes have below patters  
        1. /pet         --> patternOne 
        2. /pet/{petId} --> patternTwo
        3. /pet/findByStatus --> findByStatus
        4. /pet/{petId}/uploadImage --> uploadImage
        */ 
    public static String urlPatternOne = baseUrl + "/pet";
    public static String urlPatternTwo = urlPatternOne + "/{petId}";
    public static String uploadImageUrl = urlPatternTwo + "/uploadImage";
    public static String findByStatusUrl = urlPatternOne + "/findByStatus";

    // user model routes
	public static String postUrl  = baseUrl + "/user";
	public static String getUrl  = baseUrl + "/user/{username}";
	public static String updateUrl  = baseUrl + "/user/{username}";
	public static String deleteUrl  = baseUrl + "/user/{username}";

    // store model routes
	
}
