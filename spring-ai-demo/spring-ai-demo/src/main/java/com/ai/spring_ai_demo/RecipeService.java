package com.ai.spring_ai_demo;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

@Service 
public class RecipeService {

     
     private ChatModel chatModel;

    public RecipeService(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
        String cuisine, String dietaryRestrictions){
            var template = """
                    I want to create a recipe using the following ingredients: {ingredients}.
                    The cuisine type i prefers is {cuisine}.
                     Please consider the following dietaryRestrictions :{dietaryRestrictions}
                     Please provide me with a detailed recipe including title, list of ingredients, andcooking instruction
                    """;

                    PromptTemplate promptTemplate= new PromptTemplate(template);
            Map<String,Object> params = Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions",dietaryRestrictions
            );

            Prompt prompt = promptTemplate.create(params);
           return chatModel.call(prompt).getResult().getOutput().getText();
                   
    }
}
