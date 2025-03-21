package account;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.Request;
import url.Response;

import java.io.IOException;
import java.util.List;

public class AccountUrlController {
    public static final String path = "accounts";

    private final AccountUrlService service;

    public AccountUrlController(AccountUrlService service) {
        this.service = service;
    }

    public boolean checkPath(Request request) {
        List<String> paths = request.getPath();
        if (!paths.get(0).equals(path)) return false;

        return switch (paths.get(1)) {
            case "signup",
                 "signin",
                 "signout",
                 "edit",
                 "detail",
                 "remove" -> true;
            default -> false;
        };
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
