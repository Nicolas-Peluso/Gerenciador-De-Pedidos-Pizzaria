import React from 'react';
import style from './Input.module.css';

function Input({type, placheholder, name, ...any}) {
    return (
        <input type={type} placeholder={placheholder} name={name} className={style.input} {...any}/>
    );
}

export default Input;