package com.gg.gop.dto;

import java.time.LocalDateTime;

public class BoardDto {
    private int b_bno; // PK
    private String b_title;
    private String username; // 글쓴이의 사용자 이름 (닉네임)
    private String b_contents;
    private int viewcount; // 조회수
    private LocalDateTime b_date; // 작성 날짜와 시간
    private boolean like; //좋아요



    public BoardDto() {
    }

    public BoardDto(int b_bno, String b_title, String username, String b_contents, int viewcount, LocalDateTime b_date) {
        this.b_bno = b_bno;
        this.b_title = b_title;
        this.username = username;
        this.b_contents = b_contents;
        this.viewcount = viewcount;
        this.b_date = b_date;
    }

    public int getB_bno() {
        return b_bno;
    }

    public String getB_title() {
        return b_title;
    }

    public String getUsername() {
        return username;
    }

    public String getB_contents() {
        return b_contents;
    }

    public int getViewcount() {
        return viewcount;
    }

    public LocalDateTime getB_date() {
        return b_date;
    }

    public void setB_bno(int b_bno) {
        this.b_bno = b_bno;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setB_contents(String b_contents) {
        this.b_contents = b_contents;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public void setB_date(LocalDateTime b_date) {
        this.b_date = b_date;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "b_bno=" + b_bno +
                ", b_title='" + b_title + '\'' +
                ", username='" + username + '\'' +
                ", b_contents='" + b_contents + '\'' +
                ", viewcount=" + viewcount +
                ", b_date=" + b_date +
                '}';
    }
}
