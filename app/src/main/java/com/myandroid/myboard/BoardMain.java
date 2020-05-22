package com.myandroid.myboard;

public class BoardMain {
    String boardTitle;
    String userName;
    String boardTS;
    String boardContent;

    public BoardMain(String boardTitle, String userName, String boardTS) {
        this.boardTitle = boardTitle;
        this.userName = userName;
        this.boardTS = boardTS;
    }

    public BoardMain(String boardTitle, String userName, String boardTS, String boardContent) {
        this.boardTitle = boardTitle;
        this.userName = userName;
        this.boardTS = boardTS;
        this.boardContent = boardContent;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBoardTS() {
        return boardTS;
    }

    public void setBoardTS(String boardTS) {
        this.boardTS = boardTS;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }
}
