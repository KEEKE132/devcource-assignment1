package me.feel5n.account;

import me.feel5n.board.Controller;
import me.feel5n.customException.InvalidUrlException;
import me.feel5n.customException.InvalidValueException;
import me.feel5n.customException.NoExistAccountException;
import me.feel5n.customException.NoExistParameterException;
import me.feel5n.customException.SignException;
import me.feel5n.url.Request;
import me.feel5n.url.Response;

import java.io.IOException;
import java.util.List;

public class AccountUrlController implements Controller {
    public static final String path = "accounts";

    private final AccountUrlService service;

    public AccountUrlController(AccountUrlService service) {
        this.service = service;
    }

    public Response enter(Request request) throws InvalidValueException, NoExistParameterException, IOException, NoExistAccountException, SignException, InvalidUrlException {
        List<String> paths = request.getPath();
        if (!paths.get(0).equals(path)) throw new InvalidUrlException();

        return switch (paths.get(1)) {
            case "signup" -> {
                yield service.signUp(request);
            }
            case "signin" -> {

                yield service.signIn(request);
            }
            case "signout" -> {

                yield service.signOut(request);
            }
            case "edit" -> {

                yield service.edit(request);
            }
            case "detail" -> {

                yield service.detail(request);
            }
            case "remove" -> {

                yield service.remove(request);
            }
            default -> throw new InvalidUrlException();
        };
    }
}
