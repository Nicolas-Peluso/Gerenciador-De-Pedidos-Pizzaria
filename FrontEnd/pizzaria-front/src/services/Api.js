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
        return response;
    } catch (Exception) {
        console.log(Exception);
    }
    return true;
}

export async function BuscarItens(fitro, page) {

    let token = localStorage.getItem("token");
    if (token === undefined || token === null) {
        throw new Error();
    }
    
    try {
        const response = await fetch(url + "itens/" + "filtro=" + fitro +"/limite="+ page,{
            method: 'GET',
            headers: {
                'Authorization': 'Bearer '+token
            }
        });

        const result = await response.json();
        return result;
    } catch (Exception) {
        console.log(Exception)
    }
}

export async function CadastrarItem(obj) {
    let token = localStorage.getItem("token");
    if (token === undefined || token === null) {
        throw new Error();
    }

    try {
        const response = await fetch(url + "CadastrarItem", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
            },
            body: JSON.stringify(obj)
        });
        let res = await response.json();
        return res;

    } catch (Exception) {
        console.log(Exception);
    }
}

export async function DeletarItem(obj){
    let token = localStorage.getItem("token");
    if (token === undefined || token === null) {
        throw new Error();
    }

    try {
        const request = await fetch(url + "DeletarItem", {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Bearer '+token
            },
            body: JSON.stringify(obj)
        });
        const result = await request.json();
        console.log(result);
    } catch (error) {
        console.log(error)
    }
}