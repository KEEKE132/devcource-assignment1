package url;

import customException.InvalidURLException;

import java.util.List;
import java.util.Map;

public interface UrlParser {
    UrlData parseUrlData(String url) throws InvalidURLException;

    List<String> parsePath(String url) throws InvalidURLException;

    Map<String, String> parseParameter(String url) throws InvalidURLException;
}
