import React, { useContext, useState } from "react";
import style from "./Login.module.css";
import Input from "../input/Input";
import { Login } from "../services/Api";

export default function(){
    const [verSenha, setVerSenha] = useState(false);
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    return(
            <form>
                <Input type={"email"} placheholder={"Email"} name={"email"} required onChange={
                    e => setEmail(e.target.value)
                }/>
                <div className={style.versenha}>
                    <label htmlFor="verSenha" id="labelVerSenha">mostrar senha</label>
                    <input type="checkbox" name="verSenha" id="verSenha" onChange={() => setVerSenha(!verSenha)} />
                </div>
            <Input type={verSenha ? "text" : "password"} placheholder={"Senha"} name={"senha"} required onChange={
                e => setSenha(e.target.value)
            } />
                <button className={style.btnLogin} onClick={(e) => {
                    e.preventDefault();
                    console.log(senha);
                    console.log(email);

                    
                    Login(email, senha);
                }}>Login</button>
            </form>
    );
}