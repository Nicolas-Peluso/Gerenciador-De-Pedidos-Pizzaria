const urls = "http://viacep.com.br/ws/";

export async function findCep(cep){
    console.log(urls + cep + "/json");
    
    try{
        const response = await fetch(urls + cep + "/json", {
            method: "GET"
        });
        const re = await response.json();
        return re;
    } catch(e){
        console.log(e);
    }
}