class program
{

    static void Main(string[] args)
    {
        try
        {
            ChatCommun.AzureAISearchClass _azureAISearchClass = new ChatCommun.AzureAISearchClass();
            //Create index
            //_azureAISearchClass.CreateIndex();
            //Upload document
            //_azureAISearchClass.UploadDocument();
            //search
            //string _propmt = "Necesito un hotel que se llama twin";
            string _propmt = "twin";
            //ChatCommun.AzureAISearchClass _azureAISearchClass = new ChatCommun.AzureAISearchClass();
            string _resultado = _azureAISearchClass.SearchDocumentOpen(_propmt);
            Console.WriteLine($"Aquí el resultado : {_resultado}");
            Console.WriteLine("Finaliza con exito");
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error : " + ex.Message);
        }
        Console.ReadLine();
    }


}




