package post;

import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.UrlData;

import java.io.IOException;
import java.util.List;

public class PostUrlController {
    public static final String path = "posts";

    private final PostUrlService service;

    public PostUrlController(PostUrlService service) {
        this.service = service;
    }

    public boolean checkPath(UrlData urlData) throws InvalidValueException, NoExistPostException, NoExistParameterException, IOException, NoExistBoardException {
        List<String> paths = urlData.getPath();
        if (!paths.get(0).equals(path)) return false;

        return switch (paths.get(1)) {
            case "add" -> {
                service.checkWrite(urlData);
                yield true;
            }
            case "view" -> {
                service.checkRead(urlData);
                yield true;
            }
            case "remove" -> {
                service.checkDelete(urlData);
                yield true;
            }
            case "edit" -> {
                service.checkUpdate(urlData);
                yield true;
            }
            default -> false;
        };
    }
}
