
package com.bhavanichandra.bisync.model;

import java.util.ArrayList;
import java.util.List;

public class SlackModalMessage {

    private String type;
    private CommonModel title;
    private CommonModel submit;
    private CommonModel close;
    private List<Block> blocks = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public SlackModalMessage() {
    }

    /**
     * 
     * @param submit
     * @param blocks
     * @param type
     * @param title
     * @param close
     */
    public SlackModalMessage(String type, CommonModel title, CommonModel submit, CommonModel close, List<Block> blocks) {
        super();
        this.type = type;
        this.title = title;
        this.submit = submit;
        this.close = close;
        this.blocks = blocks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommonModel getTitle() {
        return title;
    }

    public void setTitle(CommonModel title) {
        this.title = title;
    }

    public CommonModel getSubmit() {
        return submit;
    }

    public void setSubmit(CommonModel submit) {
        this.submit = submit;
    }

    public CommonModel getClose() {
        return close;
    }

    public void setClose(CommonModel close) {
        this.close = close;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

}
