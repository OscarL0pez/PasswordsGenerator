import java.security.SecureRandom;

public class GeneradorDeContraseñas {

    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String CARACTERES_ESPECIALES = "!@#$%^&*()-_=+<>?";

    public static String generarContraseña(int longitud) {
        if (longitud < 4) {
            throw new IllegalArgumentException("La longitud debe ser al menos 4 para incluir todos los tipos de caracteres.");
        }

        String todosLosCaracteres = MAYUSCULAS + MINUSCULAS + NUMEROS + CARACTERES_ESPECIALES;
        SecureRandom random = new SecureRandom();
        StringBuilder contraseña = new StringBuilder();

        // Asegurarse de incluir al menos un carácter de cada tipo
        contraseña.append(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        contraseña.append(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        contraseña.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        contraseña.append(CARACTERES_ESPECIALES.charAt(random.nextInt(CARACTERES_ESPECIALES.length())));

        // Completar el resto de la contraseña con caracteres aleatorios
        for (int i = 4; i < longitud; i++) {
            contraseña.append(todosLosCaracteres.charAt(random.nextInt(todosLosCaracteres.length())));
        }

        // Mezclar la contraseña para hacerla más aleatoria
        return mezclarCaracteres(contraseña.toString());
    }

    private static String mezclarCaracteres(String input) {
        char[] caracteres = input.toCharArray();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < caracteres.length; i++) {
            int indexAleatorio = random.nextInt(caracteres.length);
            char temp = caracteres[i];
            caracteres[i] = caracteres[indexAleatorio];
            caracteres[indexAleatorio] = temp;
        }
        return new String(caracteres);
    }

    public static void main(String[] args) {
        int longitudDeseada = 12; // Cambia esto para ajustar la longitud de la contraseña
        String contraseña = generarContraseña(longitudDeseada);
        System.out.println("Contraseña generada: " + contraseña);
    }
}
