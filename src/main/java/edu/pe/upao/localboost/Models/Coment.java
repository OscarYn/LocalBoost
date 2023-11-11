package edu.pe.upao.localboost.Models;

public class Coment {
    private String comment;
    private User user;

    public Coment(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
