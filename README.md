# 📇 Rolodex Importer

Digitalizador de contactos por consola que permite importar y almacenar información de contactos en formato CSV.

## 📋 Descripción

Rolodex Importer es una aplicación Java de línea de comandos diseñada para facilitar la digitalización de contactos físicos. El programa permite ingresar información de contactos de manera interactiva y los guarda automáticamente en un archivo CSV estructurado.

## ✨ Características

- **Interfaz interactiva por consola** con formato visual atractivo
- **Almacenamiento automático** en formato CSV
- **Validación de datos** para evitar entradas vacías
- **Escapado correcto** de caracteres especiales en CSV
- **Creación automática** de directorios y archivos necesarios
- **Proceso continuo** hasta que el usuario decida salir

## 🚀 Requisitos

- Java JDK 8 o superior
- Sistema operativo: Windows, Linux o macOS

## 📦 Instalación

1. Clona o descarga el archivo `RolodexImporter.java`
2. Compila el programa:
```bash
javac RolodexImporter.java
```

## 💻 Uso

### Ejecución del programa

```bash
java RolodexImporter
```

### Flujo de trabajo

1. El programa te dará la bienvenida y mostrará las instrucciones
2. Introduce los datos solicitados:
   - **Nombre completo**: Campo obligatorio
   - **Número de teléfono**: Campo opcional
   - **Dirección de email**: Campo opcional
3. Después de cada contacto, el sistema confirmará que se guardó correctamente
4. Para salir, escribe `salir` en el campo de nombre

### Ejemplo de uso

```
╔════════════════════════════════════════════════════════╗
║     ROLODEX IMPORTER - Digitalizador de Contactos      ║
╚════════════════════════════════════════════════════════╝

Bienvenido al importador de contactos Rolodex.
Introduce los datos de cada contacto.
Escribe 'exit' en el campo Nombre para salir.
─────────────────────────────────────────────────────────

Nombre completo: Juan Pérez
Número de teléfono: +34 612 345 678
Dirección de email: juan.perez@email.com
$ Contacto agregado exitosamente!

Nombre completo: salir

─────────────────────────────────────────────────────────
¡Gracias por usar Rolodex Importer!
Contactos guardados en: writable/contacts.csv
─────────────────────────────────────────────────────────
```

## 📁 Estructura de archivos

El programa genera la siguiente estructura:

```
.
├── RolodexImporter.java
├── RolodexImporter.class
└── writable/
    └── contacts.csv
```

## 📊 Formato del archivo CSV

El archivo generado (`writable/contacts.csv`) tiene la siguiente estructura:

```csv
Nombre,Telefono,Email
Juan Pérez,+34 612 345 678,juan.perez@email.com
María García,+34 623 456 789,maria.garcia@email.com
```

### Características del formato CSV

- **Encabezado**: Nombre, Telefono, Email
- **Separador**: Coma (`,`)
- **Escapado automático**: Los campos que contienen comas, comillas o saltos de línea se escapan correctamente según el estándar RFC 4180

## 🔧 Funcionalidades técnicas

### Inicialización del archivo CSV

El programa verifica y crea automáticamente:
- El directorio `writable/` si no existe
- El archivo `contacts.csv` con encabezados si no existe

### Validación de datos

- **Nombre**: Campo obligatorio, no puede estar vacío
- **Teléfono y Email**: Campos opcionales

### Manejo de caracteres especiales

El programa implementa escapado correcto para:
- Comas en los campos
- Comillas dobles
- Saltos de línea
- Retornos de carro

## ⚠️ Manejo de errores

El programa maneja las siguientes situaciones:
- Nombres vacíos (muestra mensaje de error y solicita de nuevo)
- Errores de lectura/escritura de archivos
- Interrupciones del flujo de entrada (Ctrl+D o EOF)
