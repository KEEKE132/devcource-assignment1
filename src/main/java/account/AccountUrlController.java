package account;

import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.Response;
import url.UrlData;

import java.io.IOException;
import java.util.List;

public class AccountUrlController {
    public static final String path = "accounts";

    private final AccountUrlService service;

    public AccountUrlController(AccountUrlService service) {
        this.service = service;
    }

    public boolean checkPath(UrlData urlData) {
        List<String> paths = urlData.getPath();
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

    public Response enter(UrlData urlData) throws InvalidValueException, NoExistParameterException, IOException, NoExistAccountException, SignException, InvalidUrlException {
        List<String> paths = urlData.getPath();
        if (!paths.get(0).equals(path)) throw new InvalidUrlException();

        return switch (paths.get(1)) {
            case "signup" -> {
                yield service.signUp(urlData);
            }
            case "signin" -> {

                yield service.signIn(urlData);
            }
            case "signout" -> {

                yield service.signOut(urlData);
            }
            case "edit" -> {

                yield service.edit(urlData);
            }
            case "detail" -> {

                yield service.detail(urlData);
            }
            case "remove" -> {

                yield service.remove(urlData);
            }
            default -> throw new InvalidUrlException();
        };
    }
}
