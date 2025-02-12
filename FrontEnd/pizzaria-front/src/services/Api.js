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
        console.log(result);
    }catch(Exception){
        console.log(Exception);
    }
}