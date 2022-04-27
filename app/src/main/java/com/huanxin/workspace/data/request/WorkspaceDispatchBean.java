package com.huanxin.workspace.data.request;

public class WorkspaceDispatchBean {

    /**
     * id
     */
    private Integer id;
    /**
     * engineerId
     */
    private Integer engineerId;
    /**
     * ticketRecommend
     */
    private String ticketRecommend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Integer engineerId) {
        this.engineerId = engineerId;
    }

    public String getTicketRecommend() {
        return ticketRecommend;
    }

    public void setTicketRecommend(String ticketRecommend) {
        this.ticketRecommend = ticketRecommend;
    }
}
