import React, { useState, createContext, useContext } from "react";
import Login from "../Login/Login";
import imagePizzaBanner from "../assets/Images/pablo-pacheco-D3Mag4BKqns-unsplash.jpg";
import style from "./Entrar.module.css";
import Cadastro from "../Cadastro/Cadastro";
import { Global } from "../Context/GlobalContext";

export const EntrarPage = createContext();

export default function Entrar(){
    const [loginGoTo, setLoginGoTo] = useState(true);
    const {message, setLoading} = useContext(Global);

    function validarEmail(email, e) {
        let regEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;        
        if (!regEmail.test(email)) {
            e.target.style.borderColor = "red";
            return e;
        }
        return true;
    }

    function HandleClickBtn(e, obj) {
                setLoading(true);

                for(const prop in obj){
                    if (obj[prop] === undefined || obj[prop] === "" || obj[prop] === null){
                        return false;
                    }
                }
    
                if (validarEmail(obj["email"], e) !== true){
                    return false;
                };

                return true;
            }

    return(
        <section className={style.containerEntrarPage}>
            <section className={style.containerImage}>
                <img src={imagePizzaBanner} alt="Imagem de uma Pizza" />
            </section>
            <section className={style.containerForm}>
                <h1>The Pizza Manager</h1>
                <EntrarPage.Provider value={{HandleClickBtn}}>
                    {loginGoTo ? <Login /> : <Cadastro />}
                </EntrarPage.Provider> 
                {message.length !== 0 ? <p style={{ color: "red" }}>{message}</p> : null}
                <p>
                    {
                        loginGoTo ? "Nao possui conta? " : "ja possui conta? "
                    }
                </p>
                <p>{
                    loginGoTo ? <button className={style.cadastrolink} onClick={(e) => { e.preventDefault(); setLoginGoTo(false);}}><span>Cadastre-se</span></button> :
                        <button className={style.cadastrolink} onClick={(e) => { e.preventDefault(); setLoginGoTo(true); }}><span>Login</span></button>}
                </p>
            </section>
                
        </section>
    )
}