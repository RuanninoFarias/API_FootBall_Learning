package br.com.meli.Futebol.enums;

import java.util.Arrays;

public enum BrazilState {
    AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;

    public static boolean isValidState(String state) {
        return Arrays.stream(BrazilState.values())
                .anyMatch(validState -> validState.name().equalsIgnoreCase(state));
    }

}
