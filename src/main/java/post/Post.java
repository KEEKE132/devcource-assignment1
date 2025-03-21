package post;

import java.time.LocalDateTime;

public class Post {
    private static Long idCounter = 1L;

    private final Long id;
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long writerId;

    public Post(String title, String content) {
        this.id = idCounter++;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Post(String title, String content, Long boardId) {
        this(title, content);
        this.boardId = boardId;
    }

    public Post(String title, String content, Long boardId, Long writerId) {
        this(title, content, boardId);
        if (writerId != null) {
            this.writerId = writerId;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(String title, String content) {
        if (title != null && title != "") {
            setTitle(title);
        }
        if (content != null && content != "") {
            setContent(content);
        }
        updatedAt = LocalDateTime.now();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("번 게시글").append("\n");
        sb.append("제목: ").append(title).append("\n");
        sb.append("내용: ").append(content).append("\n");
        System.out.println(sb);
    }
}
