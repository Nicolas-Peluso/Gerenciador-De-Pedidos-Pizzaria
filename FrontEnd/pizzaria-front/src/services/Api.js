const url = "http://localhost:9000/";

export async function LoginFunc(obj){
    try{
            const response = await fetch(url+"Login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
                body: JSON.stringify(obj)
        });
        
        return response;
    } catch (Erro){
        return false;
    }
}


export async function Cadastrar(obj) {
    try {
        const response = await fetch(url + "Cadastro", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(obj)});
        const result = await response.json();
        return result;
    } catch (Exception) {
        console.log(Exception);
    }
    return true;
}