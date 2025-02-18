import React, { useContext } from "react";
import style from "./Header.module.css";
import icon from "../assets/Icons/caixa-de-pizza Color.png"
import { Global } from "../Context/GlobalContext";

export default function Header(){
    const {isLogin} = useContext(Global);

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
                                        <button>Dashborad</button>
                                    </li>
                                    <li>
                                        <button>Pedidos</button>
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