package url;

import customException.InvalidUrlException;
import customException.NoExistParameterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private String url;
    private List<String> path;
    private Map<String, String> parameter;
    private Session session;
    private final static UrlParser urlParser = new UrlParserImpl();

    public Request(String url) throws InvalidUrlException {
        this.url = url;
        path = new ArrayList<>();
        parameter = new HashMap<>();
        path.addAll(urlParser.parsePath(url));
        parameter.putAll(urlParser.parseParameter(url));
    }

    public Request(String url, Session session) throws InvalidUrlException {
        this(url);
        this.session = session;
    }

    public String getURL() {
        return url;
    }

    public Session getSession() {
        return session;
    }


    public List<String> getPath() {
        List<String> result = this.path;
        return new ArrayList<>(result);
    }

    public void addPath(Iterable<String> path) {
        for (String p : path) this.path.add(p);
    }

    public void addParameter(Map<String, String> parameter) {
        this.parameter.putAll(parameter);
    }

    public void addParameter(String key, String value) {
        this.parameter.put(key, value);
    }

    public Map<String, String> getParameters() {
        return new HashMap<>(this.parameter);
    }

    public String getParameter(String param) throws NoExistParameterException {
        if (!parameter.containsKey(param)) {
            throw new NoExistParameterException(param);
        }
        return parameter.get(param);
    }
}
