import { useState, useEffect } from "react";

export default function Signup(){
    return(
        <>
<form>
    <legend>Signup</legend>
    <label for="username">username</label>
    <input type="text" id="username">username</input>
    <label for="password">password</label>
    <input type="password" id="password"></input>
</form>
        </>
    )
}