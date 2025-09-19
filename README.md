[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3L-UTKfi)
# **🏛️ Ejercicio 5 — Evaluación automatizada de solicitudes de crédito**

---

## **📚 Instrucciones para los estudiantes**

### **🔧 Configuración inicial**

1. **Acepta la asignación** en GitHub Classroom usando el enlace proporcionado por tu profesor.
2. **Clona el repositorio** en tu computadora local:
   ```bash
   git clone [URL-de-tu-repositorio]
   cd template_evaluacion_credito
   ```
3. **Abre el proyecto** en tu IDE favorito (IntelliJ IDEA, Eclipse, VS Code).
4. **Verifica** que Maven esté instalado ejecutando: `mvn --version`

### **📝 Tu tarea**

Debes completar la implementación de la clase `EvaluacionCredito.java` que se encuentra en `src/main/java/org/example/`. Esta clase debe:

1. **Declarar los atributos necesarios** (están especificados en el contexto de negocio).
2. **Implementar el constructor** con todos los parámetros necesarios.
3. **Implementar los métodos requeridos**:
   - `calcularTasaMensual(double tasaNominalAnual)` 
   - `calcularCuotaMensual(double tasaNominalAnual, int plazoMeses)`
   - `evaluarAprobacion(double tasaNominalAnual, int plazoMeses)`
   - Getters y setters para todos los atributos

### **⚠️ Importante**
- **NO modifiques** los nombres de los métodos ni sus parámetros.
- **NO modifiques** la clase de pruebas `EvaluacionCreditoTest.java`.
- La GUI (`EvaluacionCreditoGUI.java`) ya está implementada y funcionará cuando completes correctamente la clase principal.

---

## **📜 Contexto de negocio**

La entidad financiera **FinAurora** desea **automatizar** la evaluación de créditos de consumo.

### **📊 Datos del crédito**
Cada solicitud de crédito debe registrar:
- **Nombre del solicitante** (String)
- **Ingresos mensuales** del solicitante (double)
- **Número de créditos activos** (int)
- **Puntaje de crédito** (int, rango: 0–1000)
- **Valor del crédito solicitado** (double)
- **Tiene codeudor** (boolean)

### **🔢 Parámetros de simulación**
- **Tasa Nominal Anual (TNA)**: Porcentaje anual de interés
- **Plazo en meses**: Duración del crédito

---

## **📏 Reglas de negocio**

El sistema debe evaluar automáticamente si aprobar o rechazar el crédito:

### **Perfil Bajo (Puntaje < 500)**
- ❌ **Rechazo automático** sin importar otras condiciones

### **Perfil Medio (500 ≤ Puntaje ≤ 700)**
- ✅ **Aprobado** si:
  - Tiene codeudor **Y**
  - La cuota mensual ≤ 25% de los ingresos
- ❌ **Rechazado** en cualquier otro caso

### **Perfil Alto (Puntaje > 700 Y Créditos activos < 2)**
- ✅ **Aprobado** si la cuota mensual ≤ 30% de los ingresos
- ❌ **Rechazado** si la cuota excede el 30% de los ingresos

### **Nota**: Si el cliente tiene puntaje > 700 pero 2 o más créditos activos, no aplica para el perfil alto.

---

## **🧮 Fórmulas de cálculo**

### **Tasa mensual**
```
Tasa mensual = TNA / 12
```

### **Cuota mensual (Método francés)**
```
         M × im × (1 + im)^n
Cuota = ----------------------
          (1 + im)^n - 1

Donde:
- M = Monto del crédito solicitado
- im = Tasa mensual / 100 (para convertir a decimal)
- n = Plazo en meses
```

**Caso especial**: Si la tasa es 0%, la cuota = Monto / Plazo

---

## **🧪 Pruebas automáticas**

Tu código será evaluado con **15 pruebas unitarias** que verifican:
- Cálculo correcto de tasa mensual (6 pts)
- Cálculo correcto de cuota con y sin intereses (13 pts)
- Aplicación correcta de reglas de negocio (56 pts)
- Casos límite y validaciones (25 pts)

### **Para ejecutar las pruebas localmente:**
```bash
# Ejecutar todas las pruebas
mvn clean test

# Ejecutar una prueba específica
mvn test -Dtest=EvaluacionCreditoTest#testCalcularTasaMensual
```

---

## **💻 Probar la interfaz gráfica**

Una vez implementada la clase `EvaluacionCredito`:
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="edu.eam.ingesoft.logica.credito.Main"
```

---

## **📤 Entrega**

1. **Implementa** la clase `EvaluacionCredito.java` completa.
2. **Verifica** que todas las pruebas pasen localmente.
3. **Haz commit** de tus cambios:
   ```bash
   git add .
   git commit -m "Implementación de EvaluacionCredito"
   git push origin main
   ```
4. **GitHub Classroom** ejecutará automáticamente las pruebas y asignará tu calificación.

---

## **🎯 Criterios de evaluación**

| Componente | Puntos |
|------------|--------|
| Cálculo de tasa mensual | 6 |
| Cálculo de cuota con tasa | 7 |
| Cálculo de cuota sin tasa | 6 |
| Rechazo perfil bajo | 7 |
| Aprobación perfil alto | 7 |
| Rechazo perfil alto por cuota | 7 |
| Perfil medio sin codeudor | 7 |
| Perfil medio con codeudor | 7 |
| Perfil medio rechazado por cuota | 7 |
| Casos límite (500 y 700) | 12 |
| Múltiples créditos activos | 7 |
| Getters y Setters | 6 |
| Valores extremos | 12 |
| **TOTAL** | **100** |

---

## **❓ Consejos**

1. **Lee cuidadosamente** las reglas de negocio antes de implementar.
2. **Prueba tu código** frecuentemente con `mvn test`.
3. **Usa el JavaDoc** de los métodos como guía.
4. **Revisa los tests** si tienes dudas sobre el comportamiento esperado.
5. **No olvides** los casos especiales (tasa 0%, límites de puntaje).

---

## **🆘 ¿Necesitas ayuda?**

- Revisa los mensajes de error de las pruebas, son muy descriptivos.
- Consulta con tu profesor o compañeros.
- Recuerda que la GUI te ayudará a visualizar si tu implementación funciona correctamente.

---

## **📋 Estructura del proyecto**

```
template_evaluacion_credito/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               ├── EvaluacionCredito.java    # ⚠️ DEBES COMPLETAR ESTA CLASE
│   │               ├── EvaluacionCreditoGUI.java # GUI (no modificar)
│   │               └── Main.java                 # Clase principal (no modificar)
│   └── test/
│       └── java/
│           └── org/
│               └── example/
│                   └── EvaluacionCreditoTest.java # Tests (no modificar)
├── pom.xml                                        # Configuración Maven (no modificar)
└── README.md                                      # Este archivo
```

---

## **🎓 Objetivos de aprendizaje**

Al completar este ejercicio habrás aprendido a:

1. **Identificar y modelar** atributos de una entidad de negocio.
2. **Implementar lógica de negocio** con reglas complejas.
3. **Aplicar fórmulas matemáticas** en programación.
4. **Trabajar con porcentajes** y cálculos financieros.
5. **Manejar casos especiales** y validaciones.
6. **Usar pruebas unitarias** para verificar tu código.
7. **Trabajar con Git y GitHub** para control de versiones.

¡Buena suerte! 🚀