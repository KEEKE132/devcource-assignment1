package me.feel5n.url;

import me.feel5n.customException.NoExistParameterException;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Map<String, String> parameter;
    private Session session;

    public Response() {
        parameter = new HashMap<>();
    }

    public Response(Session session) {
        this();
        this.session = session;
    }

    public Response(String key, String value, Session session) {
        this(session);
        parameter.put(key, value);
    }

    public Response addParameter(Map<String, String> parameter) {
        this.parameter.putAll(parameter);
        return this;
    }

    public boolean hasSession() {
        if (session == null) {
            return false;
        }
        return true;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public static Response of(Session session) {
        return new Response(session);
    }

    public static Response of() {
        return new Response();
    }
}
