package post;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.Request;
import url.Response;

import java.io.IOException;
import java.util.List;

public class PostUrlController {
    public static final String path = "posts";

    private final PostUrlService service;

    public PostUrlController(PostUrlService service) {
        this.service = service;
    }

    public boolean checkPath(Request request) {
        List<String> paths = request.getPath();
        if (!paths.get(0).equals(path)) return false;

        return switch (paths.get(1)) {
            case "add",
                 "view",
                 "remove",
                 "edit" -> true;
            default -> false;
        };
    }

    public Response enter(Request request) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException, NoExistPostException {
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
