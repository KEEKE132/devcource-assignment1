package board;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import url.Request;
import url.Response;

import java.io.IOException;
import java.util.List;

public class BoardUrlController {
    public static final String path = "boards";

    private final BoardUrlService service;

    public BoardUrlController(BoardUrlService service) {
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

    public Response enter(Request request) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException {
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
