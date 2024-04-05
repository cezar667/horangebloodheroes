package com.processo.horangebloodheroes.exception;

public class HeroOutOfCombatException extends Exception{
    public HeroOutOfCombatException(){
        super();
    }

    public HeroOutOfCombatException(String msg){
        super(msg);
    }
}
