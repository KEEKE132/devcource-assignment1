package board;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import url.Response;
import url.UrlData;

import java.io.IOException;
import java.util.List;

public class BoardUrlController {
    public static final String path = "boards";

    private final BoardUrlService service;

    public BoardUrlController(BoardUrlService service) {
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

    public Response enter(UrlData urlData) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException {
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
