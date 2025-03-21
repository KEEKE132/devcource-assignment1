package board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static Long idCounter = 1L;

    private final Long id;
    private String title;
    private List<Long> postIds;
    private LocalDateTime createdAt;
    private Long writerId = null;

    public Board(String title) {
        this.id = idCounter++;
        this.title = title;
        this.postIds = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Board(String title, Long writerId) {
        this(title);
        if (writerId != null) {
            this.writerId = writerId;
        }
    }

    public void addPost(Long id) {
        postIds.add(id);
    }

    public void removePost(Long id) {
        postIds.remove(id);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getWriterId() {
        return writerId;
    }
}
