package me.feel5n.board;

import me.feel5n.url.Request;
import me.feel5n.url.Response;

public interface Controller {
    String path = "boards";

    Response enter(Request request) throws Exception;
}
