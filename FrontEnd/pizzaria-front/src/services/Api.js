const url = "http://localhost:9000/";

export async function Login(email, senha){
    try{
            const response = await fetch(url+"Login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email,  senha })
        });

        const result = await response.json();
        return result;
    }catch(Exception){
        console.log(Exception);
    }
    return true;
}


/*
     
 {
 "nome": "Realiza",
 "cargo": "gerente",
 "limiteSaborPizza": 2,
 "email": "Nicolaspeluso111@gmail.com",
 "senha": "12345678A",
 "nomePizzaria": "Asas do vento 2",
 "endereco": "rua salvador marcovich, Jd santa angela, 230",
 "telefone": "+55 32 657492-32113",
}
 
 */

export async function Cadastrar(nome, cargo, limiteSaborPizza, email, senha, nomePizzaria, endereco, telefone) {
    try {
        const response = await fetch(url + "Cadastro", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nome: "Realiza",
                cargo: "gerente",
                limiteSaborPizza: 2,
                email: "Nicolaspeluso111@gmail.com",
                senha: "12345678A",
                nomePizzaria: "Asas do vento 2",
                endereco: "rua salvador marcovich, Jd santa angela, 230",
                telefone: "+55 32 657492-32113"})
        });
        const result = await response.json();
        return result;
    } catch (Exception) {
        console.log(Exception);
    }
    return true;
}