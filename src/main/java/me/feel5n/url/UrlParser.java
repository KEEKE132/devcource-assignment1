package me.feel5n.url;

import me.feel5n.customException.InvalidUrlException;

import java.util.List;
import java.util.Map;

public interface UrlParser {
    Request parseUrlData(String url) throws InvalidUrlException;

    List<String> parsePath(String url) throws InvalidUrlException;

    Map<String, String> parseParameter(String url) throws InvalidUrlException;
}
