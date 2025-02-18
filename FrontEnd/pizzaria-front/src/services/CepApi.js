const urls = "http://viacep.com.br/ws/";

export async function findCep(cep){    
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