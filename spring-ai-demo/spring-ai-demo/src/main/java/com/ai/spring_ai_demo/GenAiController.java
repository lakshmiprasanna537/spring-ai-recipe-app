package com.ai.spring_ai_demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class GenAiController {

   private final ChatService chatService;

   private final ImageService imageService;

   private final RecipeService recipeService;

    public GenAiController(ChatService chatService,ImageService imageService,RecipeService recipeService){
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

   @GetMapping("/ask-ai")    
    public String getResponse(@RequestParam String prompt){

        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("/image-ai")    
    public List<String> getImage(HttpServletResponse response,
         @RequestParam String prompt,
        @RequestParam(defaultValue = "dall-e-2") String model,
    @RequestParam(defaultValue = "hd") String quality,
@RequestParam(defaultValue = "1") int n,
@RequestParam(defaultValue = "1024") int height,
@RequestParam(defaultValue = "1024") int width) throws IOException{

        ImageResponse imageResponse = imageService.generateImage(prompt,quality,n,width,height);
       List<String> imageUrls= imageResponse.getResults().stream().map(result->result.getOutput().getUrl())
        .collect(Collectors.toList());
        return imageUrls;
       //String url=  imageResponse.getResult().getOutput().getUrl();
        //response.sendRedirect(url);
    }


    @GetMapping("/create-recipe")    
    public String createRecipe(@RequestParam  String ingredients,
                                    @RequestParam(defaultValue = "any")  String cuisine,
                                    @RequestParam(defaultValue = "none")  String dietaryRestrictions
    ){
        return  recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
        }
}
