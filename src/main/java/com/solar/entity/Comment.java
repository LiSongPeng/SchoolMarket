package com.solar.entity;

import java.util.Date;

/**
 * @author LiHuiBo
 */
public class Comment {
    public static final int UNDER_REVIEW = 1;
    public static final int REJECTED = 0;
    public static final int PASSED = 2;
    private String id;
    private String content;
    private Date time;
    private int status;
    private Product product;
    private User user;

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
