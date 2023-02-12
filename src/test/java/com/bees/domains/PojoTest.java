package com.bees.domains;

public interface PojoTest {
    public static final String MSG_ERRO = "O campo %s está vazio, null ou é diferente do esperado.";

    public void init();
    public void should_Return_Success_Then_OnSetter();
    public void should_Return_Success_Then_Constructor();
}
