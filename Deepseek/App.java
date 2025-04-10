import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;

public final class App {
     /**
     * @param args Unused. Arguments to the program.
     */
    /* 
    * Add the following section to the pom.xml file
    <dependencies
       <dependency>
         <groupId>com.azure</groupId>
         <artifactId>azure-ai-inference</artifactId>
         <version>1.0.0-beta.1</version>
       </dependency>
       <dependency>
         <groupId>com.azure</groupId>
         <artifactId>azure-identity</artifactId>
         <version>1.13.3</version>
       </dependency>
    </dependencies>
    */
    public static void main(String[] args) {
      
    String endpoint = Optional.ofNullable(System.getenv("AZURE_INFERENCE_SDK_ENDPOINT")).orElse("<uri>");;
    // Set the AZURE_CLIENT_ID, AZURE_CLIENT_SECRET, and AZURE_TENANT_ID environment variables
    TokenCredential credential = new DefaultAzureCredentialBuilder().build();
    String model = Optional.ofNullable(System.getenv("DEPLOYMENT_NAME")).orElse("DeepSeek-V3");

    ChatCompletionsClient client = new ChatCompletionsClientBuilder()
        .credential(credential)
        .endpoint(endpoint)
        .buildClient();

    List<ChatRequestMessage> chatMessages = Arrays.asList(
        new ChatRequestSystemMessage("You are a helpful assistant."),
        new ChatRequestUserMessage("What are 3 things to visit in Seattle?")
    );

    ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
    chatCompletionsOptions.setModel(model);

      
    ChatCompletions completions = client.complete(chatCompletionsOptions);
    try {
      System.out.println(completions.toJsonString());
    } catch (Exception e) {
      System.out.printf("Exception: %s%n", e.getMessage());
    }

  }
}