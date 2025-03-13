import { createContext, useState } from "react";
import { LoginFunc } from "../services/Api";

export const Global = createContext();

export default function GlobalConte({children}){      
    const [isLogin, setLogin] = useState(false);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const [confirmar, setConfirmar] = useState(false);

    async function MidLogin(obj) {
        let res = await LoginFunc(obj);
        if (!res.ok) {
            setLogin(false);
        }
        else {
            setLogin(true);
        }
        return res;
    }

    return(
        <Global.Provider value={{ isLogin, MidLogin, setLogin, loading, setLoading, setMessage, message, confirmar, setConfirmar}}>
            {children}
        </Global.Provider>
    )
}