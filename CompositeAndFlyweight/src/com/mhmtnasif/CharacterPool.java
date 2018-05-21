package com.mhmtnasif;

import java.util.HashMap;

public class CharacterPool {

    private static final CharacterPool CHARACTER_POOL = new CharacterPool();
    private static HashMap<java.lang.Character, Character> characters = new HashMap<>();

    private CharacterPool() {
    }

    public static final CharacterPool getCharacterPoolInstance() {
        return CHARACTER_POOL;
    }

    public static Character getCharacter(char c) {
        Character character = null;
        String temp = String.valueOf(c).toLowerCase();
        if (characters.containsKey(temp.charAt(0))) {
            character = characters.get(temp.charAt(0));
        } else {
            character = new Character(temp.charAt(0));
            characters.put(c, character);
        }
        return character;
    }

}
