package com.baeldung.spring.ai.ollamachatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelpDeskRequest {
    @JsonProperty("prompt_message")
    String promptMessage;

    @JsonProperty("history_id")
    String historyId;

    public String getPromptMessage() {
        return promptMessage;
    }

    public String getHistoryId() {
        return historyId;
    }
}
