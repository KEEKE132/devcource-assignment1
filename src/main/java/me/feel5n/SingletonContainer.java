package me.feel5n;

import me.feel5n.account.Account;
import me.feel5n.account.AccountRepository;
import me.feel5n.account.AccountType;
import me.feel5n.account.AccountUrlController;
import me.feel5n.account.AccountUrlService;
import me.feel5n.board.Board;
import me.feel5n.board.BoardRepository;
import me.feel5n.board.BoardUrlController;
import me.feel5n.board.BoardUrlService;
import me.feel5n.post.Post;
import me.feel5n.post.PostRepository;
import me.feel5n.post.PostUrlController;
import me.feel5n.post.PostUrlService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SingletonContainer {
    private static Map<Class<?>, Object> map;

    static {
        map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PostRepository postRepository = new PostRepository();
        BoardRepository boardRepository = new BoardRepository();
        AccountRepository accountRepository = new AccountRepository();
        PostUrlService postUrlService = new PostUrlService(br, postRepository, boardRepository);
        BoardUrlService boardUrlService = new BoardUrlService(br, boardRepository, postRepository);
        AccountUrlService accountUrlService = new AccountUrlService(br, accountRepository);
        PostUrlController postUrlController = new PostUrlController(postUrlService);
        BoardUrlController boardUrlController = new BoardUrlController(boardUrlService);
        AccountUrlController accountUrlController = new AccountUrlController(accountUrlService);
        map.put(BufferedReader.class, br);
        map.put(PostRepository.class, postRepository);
        map.put(BoardRepository.class, boardRepository);
        map.put(AccountRepository.class, accountRepository);
        map.put(PostUrlService.class, postUrlService);
        map.put(BoardUrlService.class, boardUrlService);
        map.put(AccountUrlService.class, accountUrlService);
        map.put(PostUrlController.class, postUrlController);
        map.put(BoardUrlController.class, boardUrlController);
        map.put(AccountUrlController.class, accountUrlController);
        try {
            boardRepository.add(new Board("1"));
            boardRepository.add(new Board("2"));
            postRepository.add(new Post("11", "11", 1L, 1L));
            postRepository.add(new Post("11", "11", 1L, 2L));
            Account account = new Account("admin", "admin", "admin", "admin");
            account.setAccountType(AccountType.ADMIN);
            accountRepository.add(account);
            accountRepository.add(new Account("123", "123", "1@1", "keeke"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T get(Class<T> clazz) {
        Object instance = map.get(clazz);
        if (instance == null) {
            throw new IllegalArgumentException();
        }
        return clazz.cast(instance);
    }
}
