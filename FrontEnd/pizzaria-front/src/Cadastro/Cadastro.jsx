import React, { useState } from "react";
import Input from "../input/Input";
import style from "./Cadastro.module.css";

export default function Cadastro(){

    const [verSenha, setVerSenha] = useState();
    const [senha, setSenha] = useState();

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
    return(
        <form>
            <Input type={"text"} name={"nome"} placheholder={"Nome"} required/>
            <Input type={"text"} name={"cargo"} placheholder={"Cargo"} required />
            <Input type={"number"} name={"limiteSabor"} placheholder={"limiteDeSabor"} required min="0" max="5" onChange={
                e => {
                    if (e.target.value > 5 || e.target.value < 0){
                        e.target.style.borderColor = "red";
                    }else{
                        e.target.style.borderColor = "black";
                    }
                }
            }/>
            <Input type={"email"} name={"email"} placheholder={"Email"} required />
            <div className={style.versenha}>
                <label htmlFor="verSenha" id="labelVerSenha">mostrar senha</label>
                <input type="checkbox" name="verSenha" id="verSenha" onChange={() => setVerSenha(!verSenha)} />
            </div>
            <Input type={verSenha ? "text" : "password"} name={"senha"} placheholder={"Senha"} required onChange={
                e => setSenha(e.target.value)
            }/>
            
            <Input type={verSenha ? "text" : "password"} name={"senha2"} placheholder={"Confirmar Senha"} required onChange={
                e => {
                    e.target.value != senha ? e.target.style.borderColor = "red" : e.target.style.borderColor = "black";
                }
            }/>

            <Input type={"text"} name={"cep"} placheholder={"Cep Do Restaurante"} required />
            
            <Input type={"text"} name={"nomeRua"} placheholder={"Nome Rua"} required />
            <Input type={"number"} name={"numeroCasa"} placheholder={"Numero da Loja"} required />
            <Input type={"text"} name={"bairro"} placheholder={"Nome do bairro"} required />

            <button>Cadastrar</button>
        </form>
    );
}