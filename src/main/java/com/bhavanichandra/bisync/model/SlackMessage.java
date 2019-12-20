package com.bhavanichandra.bisync.model;

import java.util.List;

public class SlackMessage {

    private List<Block> blocks;

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
