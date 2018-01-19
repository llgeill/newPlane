package newPlane.util;

public class PathUtil {
    public static String getUri(String relativePath){
        return "file://"+System.getProperty("user.dir")+"/src/"+relativePath;
    }
}
