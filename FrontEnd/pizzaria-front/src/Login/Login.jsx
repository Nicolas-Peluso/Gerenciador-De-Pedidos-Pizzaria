import React, { useContext, useState } from "react";
import style from "./Login.module.css";
import Input from "../input/Input";
import { EntrarPage } from "../Entrar/Entrar";
import { Global } from "../Context/GlobalContext";

export default function Login(){
    const [verSenha, setVerSenha] = useState(false);
    const [email, setEmail] = useState("");
    const [emailElement, setEmailElement] = useState();
    const [senha, setSenha] = useState("");

    const { HandleClickBtn, setLoading, loading, setMessage} = useContext(EntrarPage);
    const { MidLogin } = useContext(Global);
    return(
            <form onChange={e => {setMessage(""); setLoading(false)}}>
            <Input type={"email"} placheholder={"Email"} name={"email"} required onChange={e => { setEmail(e.target.value); setEmailElement(e); e.target.style.borderColor = "black"; }} value={email}/>
                <div className={style.versenha}>
                    <label htmlFor="verSenha" id="labelVerSenha">mostrar senha</label>
                    <input type="checkbox" name="verSenha" id="verSenha" onChange={() => setVerSenha(!verSenha)} />
                </div>
            <Input type={verSenha ? "text" : "password"} placheholder={"Senha"} name={"senha"} required onChange={e => setSenha(e.target.value)} value={senha} />

                <button className={style.btnLogin} style={{color: loading ? "grey" : "black"}} disabled={loading} onClick={
                        async (e) => {
                            e.preventDefault();

                            let obj = {email, senha};
                            let res = HandleClickBtn(emailElement, obj);
                            
                            if (!res){
                                setMessage("Verifique as informações de login");
                            }else{
                                try{
                                    const req = await MidLogin(obj);
                                    if (req.Message !== undefined && req.Message.length !== 0){
                                        setMessage(req.Message);
                                    }
                                    else{

                                    }
                                } catch(e){
                                    console.log(e);
                                }
                                console.log("login");
                            }
                }}>Login</button>
            </form>
    );
}