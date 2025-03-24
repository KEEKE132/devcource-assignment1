package me.feel5n.board;

import me.feel5n.customException.InvalidUrlException;
import me.feel5n.customException.InvalidValueException;
import me.feel5n.customException.NoExistBoardException;
import me.feel5n.customException.NoExistParameterException;
import me.feel5n.customException.NotAllowedAuthorityException;
import me.feel5n.url.Request;
import me.feel5n.url.Response;

import java.io.IOException;
import java.util.List;

public class BoardUrlController implements Controller {

    private final BoardUrlService service;

    public BoardUrlController(BoardUrlService service) {
        this.service = service;
    }

    @Override
    public Response enter(Request request) throws InvalidValueException, NoExistParameterException, IOException, NoExistBoardException, InvalidUrlException, NotAllowedAuthorityException {
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
