package post;

import java.time.LocalDateTime;

public class Post {
    private static Long idCounter = 1L;

    private final Long id;
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createdAt;

    public Post(String title, String content) {
        this.id = idCounter++;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Post(String title, String content, Long boardId) {
        this(title, content);
        this.boardId = boardId;
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

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("번 게시글").append("\n");
        sb.append("제목: ").append(title).append("\n");
        sb.append("내용: ").append(content).append("\n");
        System.out.println(sb);
    }
}
