import React, { useContext, useEffect, useState } from "react";
import style from "./Login.module.css";
import Input from "../input/Input";
import { Login } from "../services/Api";

export default function(){
    const [verSenha, setVerSenha] = useState(false);
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [loading, setLoading] = useState(false);
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
                <button className={style.btnLogin} style={{color: loading ? "grey" : "black"}} disabled={loading} onClick={
                        async (e) => {
                            e.preventDefault();
                            setLoading(true);
                        try{
                            await Login(email, senha);
                        }finally{
                            setLoading(false);
                        }
                }}>Login</button>
            </form>
    );
}