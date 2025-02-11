import React, { useState, createContext, useContext } from "react";

export default function Entrar(){
    const EntrarPage = createContext();
    
    return(
        <section className="Container">
            <section className="ContainerImage">

            </section>
            <EntrarPage.Provider>
            
            </EntrarPage.Provider>
        </section>
    )
}