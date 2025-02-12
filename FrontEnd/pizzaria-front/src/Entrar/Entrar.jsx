import React, { useState, createContext, useContext } from "react";
import Login from "../Login/Login";
import imagePizzaBanner from "../assets/Images/pablo-pacheco-D3Mag4BKqns-unsplash.jpg";
import style from "./Entrar.module.css";
import Cadastro from "../Cadastro/Cadastro";

export const EntrarPage = createContext();

export default function Entrar(){
    const [login, setLogin] = useState(true);

    return(
        <section className={style.containerEntrarPage}>
            <section className={style.containerImage}>
                <img src={imagePizzaBanner} alt="Imagem de uma Pizza" />
            </section>
            <section className={style.containerForm}>
                <h1>The Pizza Manager</h1>
                <EntrarPage.Provider value={{login, setLogin}}>
                    {login ? <Login /> : <Cadastro />}
                </EntrarPage.Provider> 
                <p>
                    {
                        login ? "Nao possui conta? " : "ja possui conta? "
                    }
                </p>
                <p>{
                    login ? <a className={style.cadastrolink} onClick={(e) => {e.preventDefault();setLogin(false);}}>Cadastre-se</a> :

                    <a className={style.cadastrolink} onClick={(e) => { e.preventDefault(); setLogin(true); }}>Login</a>}</p>
            </section>
        </section>
    )
}