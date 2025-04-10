// Add these lines to the .csproj file
// <PackageReference Include="Azure.AI.Inference" Version="1.0.0-beta.2" />
// <PackageReference Include="Azure.Identity" Version="1.13.2" />
using static System.Environment;
using System.Text.Json; 
using Azure;
using Azure.AI.Inference;
using Azure.Core;
using Azure.Identity;

var endpoint = GetEnvironmentVariable("AZURE_INFERENCE_SDK_ENDPOINT") ?? "<uri>";
var uri = new Uri(endpoint);

// Set the AZURE_CLIENT_ID, AZURE_CLIENT_SECRET, and AZURE_TENANT_ID environment variables.
TokenCredential credential = new DefaultAzureCredential();

var model = GetEnvironmentVariable("DEPLOYMENT_NAME") ?? "DeepSeek-V3";
var client = new ChatCompletionsClient(uri, credential, new AzureAIInferenceClientOptions());

var requestOptions = new ChatCompletionsOptions()
{
  Messages = {
    new ChatRequestSystemMessage("You are a helpful assistant."), //ventana de contexto
    new ChatRequestUserMessage("What are 3 things to visit in Seattle?") //consulta
  },
  MaxTokens = 1000,
  Model = model
};
  
Response<ChatCompletions> response = client.Complete(requestOptions);

Console.WriteLine(JsonSerializer.Serialize(response, new JsonSerializerOptions { WriteIndented = true }));