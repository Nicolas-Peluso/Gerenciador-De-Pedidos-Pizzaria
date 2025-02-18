import React, { useContext } from "react";
import style from "./Header.module.css";
import icon from "../assets/Icons/caixa-de-pizza Color.png"
import { Global } from "../Context/GlobalContext";
import iconSair from "../assets/Icons/sair.png"
import { NavLink } from "react-router";

export default function Header(){
    const { isLogin, setLogin } = useContext(Global);

    return (
        <header>
            <section className={style.container}>
                <section className={style.containerIcon}>
                    <img src={icon} alt="imagem de uma caixa de pizza" />
                </section>
                <section className={style.optionsBtn}>
                    {
                        isLogin ? <ul>
                                    <li>
                                        <button>Pedidos</button>
                                    </li>
                                    <li className={style.btnSair}>
                                        <NavLink to="/entrar" onClick={() => setLogin(false)}><button>Sair <img src={iconSair} alt="Sair icone" /></button></NavLink>
                                    </li>
                                </ul> 
                                : 
                                null
                    }
                </section>
            </section>
        </header>
    );
}