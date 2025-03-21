package post;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.Response;
import url.UrlData;

import java.io.IOException;
import java.util.List;

public class PostUrlController {
    public static final String path = "posts";

    private final PostUrlService service;

    public PostUrlController(PostUrlService service) {
        this.service = service;
    }

    public boolean checkPath(UrlData urlData) {
        List<String> paths = urlData.getPath();
        if (!paths.get(0).equals(path)) return false;

        return switch (paths.get(1)) {
            case "add",
                 "view",
                 "remove",
                 "edit" -> true;
            default -> false;
        };
    }

    public Response enter(UrlData urlData) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException, NoExistPostException {
        List<String> paths = urlData.getPath();
        if (!paths.get(0).equals(path)) throw new InvalidUrlException();

        return switch (paths.get(1)) {
            case "add" -> {
                yield service.add(urlData);
            }
            case "view" -> {
                yield service.view(urlData);
            }
            case "remove" -> {
                yield service.remove(urlData);
            }
            case "edit" -> {
                yield service.edit(urlData);
            }
            default -> throw new InvalidUrlException();
        };
    }
}
