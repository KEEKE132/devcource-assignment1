package url;

public class Session {
    private Long sessionId = null;

    public Session() {
    }

    public Session(Long sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isSigned() {
        return sessionId != null;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

}
