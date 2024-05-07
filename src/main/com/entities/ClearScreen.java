package main.com.entities;

public class ClearScreen {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void centralizarTexto(String texto) {
        int larguraTerminal = 80; // Largura padrão do terminal
        int espacosAntes = (larguraTerminal - texto.length()) / 2;
        String espacos = " ".repeat(espacosAntes);
        System.out.println(espacos + texto);
    }
}
