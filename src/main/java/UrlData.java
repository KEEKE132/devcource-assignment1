import CustomException.InvalidURLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlData {
    private String url;
    private List<String> path;
    private Map<String, String> parameter;

    public UrlData(String url) throws InvalidURLException {
        this.url = url;
        path = new ArrayList<>();
        parameter = new HashMap<>();
        path.addAll(UrlParser.parsePath(url));
        parameter.putAll(UrlParser.parseParameter(url));
    }

    public String getURL() {
        return url;
    }

    public List<String> getPath() {
        List<String> result = this.path;
        return new ArrayList<>(result);
    }

    public void addPath(String path) {
        this.path.add(path);
    }

    public void addPath(Iterable<String> path) {
        for (String p : path) this.path.add(p);
    }

    public void addParameter(String key, String value) {
        this.parameter.put(key, value);
    }

    public void addParameter(Map<String, String> parameter) {
        this.parameter.putAll(parameter);
    }

    public Map<String, String> getParameter() {
        return new HashMap<>(this.parameter);
    }

    public String getParameterValue(String key) {
        return this.parameter.get(key);
    }
}
