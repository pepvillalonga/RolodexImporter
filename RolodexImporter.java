import java.io.*;

public class RolodexImporter {

    private static final String CSV_FILE_PATH = "writable/contacts.csv";
    private static final String CSV_HEADER = "Nombre,Telefono,Email";
    private static final String EXIT_COMMAND = "salir";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            initializeCsvFile();

            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║     ROLODEX IMPORTER - Digitalizador de Contactos      ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("Bienvenido al importador de contactos Rolodex.");
            System.out.println("Introduce los datos de cada contacto.");
            System.out.println("Escribe 'exit' en el campo Nombre para salir.");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();

            while (true) {
                System.out.print("Nombre completo: ");
                String name = reader.readLine();

                if (name == null || name.trim().equalsIgnoreCase(EXIT_COMMAND)) {
                    System.out.println();
                    System.out.println("─────────────────────────────────────────────────────────");
                    System.out.println("¡Gracias por usar Rolodex Importer!");
                    System.out.println("Contactos guardados en: " + CSV_FILE_PATH);
                    System.out.println("─────────────────────────────────────────────────────────");
                    break;
                }

                if (name.trim().isEmpty()) {
                    System.out.println("!!! Error: El nombre no puede estar vacío.");
                    System.out.println();
                    continue;
                }

                System.out.print("Número de teléfono: ");
                String phone = reader.readLine();

                if (phone == null) {
                    phone = "";
                }

                System.out.print("Dirección de email: ");
                String email = reader.readLine();

                if (email == null) {
                    email = "";
                }

                try {
                    appendToCSV(name.trim(), phone.trim(), email.trim());
                    System.out.println("$ Contacto agregado exitosamente!");
                    System.out.println();
                } catch (IOException e) {
                    System.err.println("!!! Error al guardar el contacto: " + e.getMessage());
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer la entrada: " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el lector: " + e.getMessage());
            }
        }
    }

    private static void initializeCsvFile() throws IOException {
        File csvFile = new File(CSV_FILE_PATH);
        File parentDir = csvFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!csvFile.exists() || csvFile.length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false))) {
                writer.write(CSV_HEADER);
                writer.newLine();
                writer.flush();
            }
        }
    }

    private static void appendToCSV(String name, String phone, String email) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String escapedName = escapeCsvField(name);
            String escapedPhone = escapeCsvField(phone);
            String escapedEmail = escapeCsvField(email);

            String csvLine = escapedName + "," + escapedPhone + "," + escapedEmail;

            writer.write(csvLine);
            writer.newLine();
            writer.flush();

        }
    }

    private static String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }

        boolean needsEscaping = field.contains(",") ||
                field.contains("\"") ||
                field.contains("\n") ||
                field.contains("\r");

        if (needsEscaping) {
            String escaped = field.replace("\"", "\"\"");
            return "\"" + escaped + "\"";
        }

        return field;
    }
}