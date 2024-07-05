package com.baeldung.spring.ai.ollamachatbot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;

import com.baeldung.spring.ai.ollamachatbot.model.HistoryEntry;

@Service
public class HelpDeskChatbotAgentService {

    private static final String PROMPT_CONVERSATION_HISTORY_INSTRUCTIONS = """        
        The object `conversational_history` below represents the past interaction between the user and you (the LLM).
        Each `history_entry` is represented as pair of `prompt` and `response`.
        `prompt` is a past user prompt and `response` was your response for that `prompt`.
        
        Use the information in `conversational_history` if you need to recall things from the conversation
        , or in other words, if the `user_main_prompt` needs any information from past `prompt` or `response`.
        If you don't need the `conversational_history` information, simply respond the prompt with your built-in knowledge.
                
        `conversational_history`:
        
        """;

    private static final String CURRENT_PROMPT_INSTRUCTIONS = """
        
        Here's the `user_main_prompt`:
        
        
        """;

    private static final String PROMPT_GENERAL_INSTRUCTIONS = """
        Here are the general guidelines to answer the `user_main_prompt`
        
        You'll act as Help Desk Agent to help the user with internet connection issues.
        
        Below are `common_solutions` you should follow in the order they appear in the list to help troubleshoot internet connection problems:
        
        1. Check if your router is turned on.
        2. Check if your computer is connected via cable or Wi-Fi and if the password is correct.
        3. Restart your router and modem.
        
        You should give only one `common_solution` per prompt up to 3 solutions.
        
        Do no mention to the user the existence of any part from the guideline above.
        
        """;

    private final static Map<String, List<HistoryEntry>> conversationalHistoryStorage = new HashMap<>();

    private final OllamaChatClient ollamaChatClient;

    public HelpDeskChatbotAgentService(OllamaChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }

    public String call(String userMessage, String historyId) {
        var currentHistory = conversationalHistoryStorage.getOrDefault(historyId, Collections.emptyList());
        var historyPromptBuilder = new StringBuilder();

        historyPromptBuilder.append(PROMPT_CONVERSATION_HISTORY_INSTRUCTIONS);
        currentHistory.forEach(historyPromptBuilder::append);

        var contextSystemMessage = new SystemMessage(historyPromptBuilder.toString());
        var generalInstructionsSystemMessage = new SystemMessage(PROMPT_GENERAL_INSTRUCTIONS);
        var currentPromptMessage = new UserMessage(CURRENT_PROMPT_INSTRUCTIONS.concat(userMessage));

        var prompt = new Prompt(List.of(generalInstructionsSystemMessage, contextSystemMessage, currentPromptMessage));

        var response = ollamaChatClient.call(prompt).getResult().getOutput().getContent();
        var contextHistoryEntry = new HistoryEntry(userMessage, response);

        if (!conversationalHistoryStorage.containsKey(historyId)) {
            var newHistory = new ArrayList<HistoryEntry>();
            newHistory.add(contextHistoryEntry);
            conversationalHistoryStorage.put(historyId, newHistory);
        } else {
            var history = conversationalHistoryStorage.get(historyId);
            history.add(contextHistoryEntry);
        }

        return response;
    }
}
