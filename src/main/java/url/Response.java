package url;

import customException.NoExistParameterException;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Map<String, String> parameter;

    public Response() {
        parameter = new HashMap<>();
    }

    public Response(String key, String value) {
        this();
        parameter.put(key, value);
    }

    public Response addParameter(Map<String, String> parameter) {
        this.parameter.putAll(parameter);
        return this;
    }

    public Response addParameter(String key, String value) {
        this.parameter.put(key, value);
        return this;
    }

    public boolean hasParameter(String key) {
        if (parameter.containsKey(key)) return true;
        return false;
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

    public static Response of(String key, String value) {
        return new Response(key, value);
    }

    public static Response of() {
        return new Response();
    }
}
