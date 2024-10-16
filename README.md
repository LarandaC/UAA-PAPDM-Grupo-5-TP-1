# Aplicación Android de Registro de Productos

Trabajo Practico del Primer Parcial de la materia Programación Avanzada para Dispositivos Móviles.

**Profesor:** Lic. Diego Bonnin

### ⭐ Integrantes

> [!NOTE]
> - Leticia Aranda
> - Lucas Cubilla
> - Orlando Romero De La Cruz
> - Jacqueline Gini
> - Francisco Salinas
> - José Benítez

## 🚀 Cómo Ejecutar la Aplicación

Sigue estos pasos para ejecutar la aplicación en tu entorno local:

### Prerrequisitos

> [!IMPORTANT]
> Antes de comenzar, asegúrate de tener instalados los siguientes elementos:
> - [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versión 11 o superior)
> - [Android Studio](https://developer.android.com/studio) (última versión)}
> - [Gradle](https://gradle.org/install/) (opcional, si no usas Android Studio)

### 1. Clonar el Repositorio

Clona el repositorio en tu máquina local utilizando el siguiente comando:

   ```bash
   git clone https://github.com/LarandaC/UAA-PAPDM-Grupo-5-TP-1.git
  ```

### 2. Abrir el Proyecto en Android Studio

- Abre Android Studio.
- Selecciona File > Open... y elige la carpeta del proyecto que acabas de clonar.

### 3. Instalar Dependencias

> [!TIP]
> - Si es la primera vez que abres el proyecto, Android Studio descargará automáticamente las dependencias. 
> - Si necesitas hacerlo manualmente, ve a View > Tool Windows > Gradle y selecciona Refresh en el panel de Gradle.

### 4. Configurar un Dispositivo

Puedes ejecutar la aplicación en un emulador o en un dispositivo físico.

    Emulador:
        Ve a Tools > Devide Manager
        Crea un nuevo dispositivo virtual si no tienes uno.
        Inicia el emulador.

    Dispositivo físico:
        Asegúrate de que la depuración USB esté habilitada en tu dispositivo.
        Conecta tu dispositivo a la computadora.
        Elige al dispositivo.
        Inicia el emulador.

### 5. Ejecutar la Aplicación

  Selecciona el dispositivo (emulador o físico) en la barra de herramientas de Android Studio.
  Haz clic en el botón de Run (el icono de triángulo verde) o presiona Shift + F10.

### 6. Verificar la Aplicación

Una vez que la aplicación se compile e inicie, deberías poder verla en el dispositivo seleccionado. 

## 📱 Uso de la aplicación
> [!NOTE]
> El tema de la aplicación cambia según el tema establecido en el celular.
> - En la pantalla principal aparecerá la lista de las propiedadees, en caso de que no haya propiedades, un mensaje.
> - Al apretar en el botón 'Añadir propiedad', llevará a la pantalla para agregar propiedad.
> - Ingresar todos los datos, junto a una URL válida.
> - Apretar en 'Guardar'.
> - Volver a la pantalla anterior con el botón 'Atras'.
> - Se visualizará la lista de propiedades o el mensaje.
> - Si quiere eliminar una propiedad, apretar el botón rojo que aparece en la parte de abajo de cada propiedad.

## 📝 Estructura del proyecto
```plaintext
.
├── app
│   ├── **build.gradle** 📦                — *Configuración de Gradle específica del módulo de la app*
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── primerparcial  — *Paquete con las clases del proyecto* 💻
│   │   │   ├── kotlin
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── primerparcial  — *Clases Kotlin principales* 📚
│   │   │   ├── res
│   │   │   │   ├── layout 🖼️              — *Archivos XML de diseño de la interfaz gráfica*
│   │   │   │   ├── values 🎨              — *Recursos como strings, colors, y styles*
│   │   │   │   └── drawable 🖼️            — *Archivos gráficos, íconos e imágenes*
│   │   │   ├── **AndroidManifest.xml** 📜 — *Archivo que define los componentes de la app*
│   └── test                                
├── **build.gradle**                       — *Configuración general de Gradle* 📦
├── gradle                                 
│   ├── wrapper
│   └── **gradle-wrapper.properties**
├── **gradle.properties**                  — *Configuración global de Gradle* ⚙️
├── **settings.gradle**                    — *Archivo de configuración de los módulos* 📋
└── **README.md**                          — *Documentación del proyecto* 📄

```
