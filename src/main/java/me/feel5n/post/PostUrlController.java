package me.feel5n.post;

import me.feel5n.board.Controller;
import me.feel5n.customException.InvalidUrlException;
import me.feel5n.customException.InvalidValueException;
import me.feel5n.customException.NoExistBoardException;
import me.feel5n.customException.NoExistParameterException;
import me.feel5n.customException.NoExistPostException;
import me.feel5n.customException.NotAllowedAuthorityException;
import me.feel5n.url.Request;
import me.feel5n.url.Response;

import java.io.IOException;
import java.util.List;

public class PostUrlController implements Controller {
    public static final String path = "posts";

    private final PostUrlService service;

    public PostUrlController(PostUrlService service) {
        this.service = service;
    }

    public Response enter(Request request) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException, NoExistPostException, NotAllowedAuthorityException {
        List<String> paths = request.getPath();
        if (!paths.get(0).equals(path)) throw new InvalidUrlException();

        return switch (paths.get(1)) {
            case "add" -> {
                yield service.add(request);
            }
            case "view" -> {
                yield service.view(request);
            }
            case "remove" -> {
                yield service.remove(request);
            }
            case "edit" -> {
                yield service.edit(request);
            }
            default -> throw new InvalidUrlException();
        };
    }
}
