import { createContext, useState } from "react";
import { LoginFunc } from "../services/Api";


export const Global = createContext();

export default function GlobalConte({children}){      
    const [isLogin, setLogin] = useState(false);

    async function MidLogin(obj) {
        let res = await LoginFunc(obj);
        if (!res.ok) {
            setLogin(false);
        }
        else {
            setLogin(true);
        }
        return res.json();
    }

    return(
        <Global.Provider value={{ isLogin, MidLogin}}>
            {children}
        </Global.Provider>
    )
}