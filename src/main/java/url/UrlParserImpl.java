package url;

import customException.InvalidURLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UrlParserImpl implements UrlParser {
    @Override
    public UrlData parseUrlData(String url) throws InvalidURLException {
        UrlData result = new UrlData(url);
        result.addPath(parsePath(url));
        result.addParameter(parseParameter(url));
        return result;
    }

    @Override
    public List<String> parsePath(String url) throws InvalidURLException {
        if (!url.startsWith("/")) {
            throw new InvalidURLException("URL이 유효하지 않습니다.");
        }
        List<String> result = new ArrayList<>();
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        String[] splittedPath = url.split("/");
        for (String s : splittedPath) {
            if (s.isEmpty()) continue;
            result.add(s);
        }
        return result;
    }

    @Override
    public Map<String, String> parseParameter(String url) throws InvalidURLException {
        Map<String, String> result = new java.util.HashMap<>();
        if (!url.contains("?")) {
            return result;
        }
        url = url.substring(url.indexOf("?") + 1);
        String[] splittedParameter = url.split("&");
        for (String s : splittedParameter) {
            String[] keyValue = s.split("=");
            if (keyValue.length != 2) {
                throw new InvalidURLException("파라미터 형식이 유효하지 않습니다.");
            }
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }
}
