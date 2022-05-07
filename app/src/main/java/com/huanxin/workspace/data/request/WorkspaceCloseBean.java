package com.huanxin.workspace.data.request;

public class WorkspaceCloseBean {
    /**
     * id
     */
    private String id;
    /**
     * ticketResultState
     */
    private Integer ticketResultState;
    /**
     * ticketResultContent
     */
    private String ticketResultContent;
    /**
     * ticketResultImages
     */
    private String ticketResultImages;
    /**
     * ticketResultVideo
     */
    private String ticketResultVideo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTicketResultState() {
        return ticketResultState;
    }

    public void setTicketResultState(Integer ticketResultState) {
        this.ticketResultState = ticketResultState;
    }

    public String getTicketResultContent() {
        return ticketResultContent;
    }

    public void setTicketResultContent(String ticketResultContent) {
        this.ticketResultContent = ticketResultContent;
    }

    public String getTicketResultImages() {
        return ticketResultImages;
    }

    public void setTicketResultImages(String ticketResultImages) {
        this.ticketResultImages = ticketResultImages;
    }

    public String getTicketResultVideo() {
        return ticketResultVideo;
    }

    public void setTicketResultVideo(String ticketResultVideo) {
        this.ticketResultVideo = ticketResultVideo;
    }
}
