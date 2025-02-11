import { createContext } from "react";


export const Global = createContext();

export default function GlobalConte({children}){      
    
    return(
        <Global.Provider value={{te: "teste"}}>
            {children}
        </Global.Provider>
    )
}