import ModelClient from "@azure-rest/ai-inference";
import { DefaultAzureCredential } from "@azure/identity";

const client = new ModelClient(
  ({}).AZURE_INFERENCE_SDK_ENDPOINT ?? "<uri>",
  // Set the AZURE_CLIENT_ID, AZURE_CLIENT_SECRET, and AZURE_TENANT_ID environment variables
  new DefaultAzureCredential()
);

var messages = [
  { role: "system", content: "You are an helpful assistant" },
  { role: "user", content: "What are 3 things to see in Seattle?" },
];

var response = await client.path("chat/completions").post({
  body: {
    messages: messages,
    max_tokens: 1000,
    model: ({}).DEPLOYMENT_NAME ?? "DeepSeek-V3",
  },
});

console.log(JSON.stringify(response));