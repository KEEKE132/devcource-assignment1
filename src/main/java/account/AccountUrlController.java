package account;

import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.UrlData;

import java.io.IOException;
import java.util.List;

public class AccountUrlController {
    public static final String path = "accounts";

    private final AccountUrlService service;

    public AccountUrlController(AccountUrlService service) {
        this.service = service;
    }

    public boolean checkPath(UrlData urlData) throws InvalidValueException, NoExistParameterException, IOException, NoExistAccountException, SignException {
        List<String> paths = urlData.getPath();
        if (!paths.get(0).equals(path)) return false;

        return switch (paths.get(1)) {
            case "signup" -> {
                service.signUp();
                yield true;
            }
            case "signin" -> {
                service.signIn();
                yield true;
            }
            case "signout" -> {
                service.signOut();
                yield true;
            }
            case "edit" -> {
                service.edit(urlData);
                yield true;
            }
            case "detail" -> {
                service.detail(urlData);
                yield true;
            }
            case "remove" -> {
                service.remove(urlData);
                yield true;
            }
            default -> false;
        };
    }
}
