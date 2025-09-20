package edu.eam.ingesoft.logica.credito;

/**
 * Clase que representa una evaluación de crédito para la entidad financiera FinAurora.
 * Permite calcular cuotas mensuales y evaluar la aprobación de créditos según reglas de negocio.
 */
public class EvaluacionCredito {

    private String nombreSolicitante;
    private double ingresosMensuales;
    private int numeroCreditosActivos;
    private int puntajeCredito;
    private double valorCreditoSolicitado;
    private boolean tieneCodedor;

    /**
     * Constructor de la clase EvaluacionCredito.
     *
     * @param nombreSolicitante Nombre completo del solicitante del crédito
     * @param ingresosMensuales Ingresos mensuales del solicitante en pesos
     * @param numeroCreditosActivos Cantidad de créditos activos que tiene el solicitante
     * @param puntajeCredito Puntaje crediticio del solicitante (0-1000)
     * @param valorCreditoSolicitado Monto del crédito solicitado en pesos
     * @param tieneCodedor Indica si el solicitante cuenta con un codeudor
     */
    public EvaluacionCredito(String nombreSolicitante, double ingresosMensuales,
                             int numeroCreditosActivos, int puntajeCredito,
                             double valorCreditoSolicitado, boolean tieneCodedor) {
        this.nombreSolicitante = nombreSolicitante;
        this.ingresosMensuales = ingresosMensuales;
        this.numeroCreditosActivos = numeroCreditosActivos;
        this.puntajeCredito = puntajeCredito;
        this.valorCreditoSolicitado = valorCreditoSolicitado;
        this.tieneCodedor = tieneCodedor;
    }

    /**
     * Calcula la tasa de interés mensual a partir de la tasa nominal anual.
     *
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @return Tasa mensual en porcentaje
     */
    public double calcularTasaMensual(double tasaNominalAnual) {
        return tasaNominalAnual / 12.0;
    }

    /**
     * Calcula la cuota mensual del crédito usando la fórmula de amortización francesa.
     * Formula: Cuota = M * (im * (1+im)^n) / ((1+im)^n - 1)
     *
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return Valor de la cuota mensual en pesos
     */
    public double calcularCuotaMensual(double tasaNominalAnual, int plazoMeses) {
        double tasaMensual = calcularTasaMensual(tasaNominalAnual) / 100.0; // convertir a decimal
        if (tasaMensual == 0) {
            return valorCreditoSolicitado / plazoMeses;
        }
        return (valorCreditoSolicitado * tasaMensual * Math.pow(1 + tasaMensual, plazoMeses)) /
                (Math.pow(1 + tasaMensual, plazoMeses) - 1);
    }

    /**
     * Evalúa si el crédito debe ser aprobado según las reglas de negocio:
     * - Perfil bajo (puntaje < 500): Rechazo automático
     * - Perfil medio (500 ≤ puntaje ≤ 700): Requiere codeudor y cuota ≤ 25% de ingresos
     * - Perfil alto (puntaje > 700 y < 2 créditos): Cuota ≤ 30% de ingresos
     *
     * @param tasaNominalAnual Tasa nominal anual en porcentaje
     * @param plazoMeses Plazo del crédito en meses
     * @return true si el crédito es aprobado, false si es rechazado
     */
    public boolean evaluarAprobacion(double tasaNominalAnual, int plazoMeses) {
        double cuotaMensual = calcularCuotaMensual(tasaNominalAnual, plazoMeses);

        // Perfil bajo → rechazo automático
        if (puntajeCredito < 500) {
            return false;
        }

        // Perfil medio → 500 ≤ puntaje ≤ 700
        if (puntajeCredito <= 700) {
            return tieneCodedor && (cuotaMensual <= ingresosMensuales * 0.25);
        }

        // Perfil alto → puntaje > 700 y menos de 2 créditos
        if (numeroCreditosActivos < 2) {
            return cuotaMensual <= ingresosMensuales * 0.30;
        }

        // Si no cumple ninguna regla → rechazo
        return false;
    }

    // --------- GETTERS Y SETTERS ---------

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public double getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(double ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public int getNumeroCreditosActivos() {
        return numeroCreditosActivos;
    }

    public void setNumeroCreditosActivos(int numeroCreditosActivos) {
        this.numeroCreditosActivos = numeroCreditosActivos;
    }

    public int getPuntajeCredito() {
        return puntajeCredito;
    }

    public void setPuntajeCredito(int puntajeCredito) {
        this.puntajeCredito = puntajeCredito;
    }

    public double getValorCreditoSolicitado() {
        return valorCreditoSolicitado;
    }

    public void setValorCreditoSolicitado(double valorCreditoSolicitado) {
        this.valorCreditoSolicitado = valorCreditoSolicitado;
    }

    public boolean isTieneCodedor() {
        return tieneCodedor;
    }

    public void setTieneCodedor(boolean tieneCodedor) {
        this.tieneCodedor = tieneCodedor;
    }

    // --------- MAIN PARA PRUEBAS ---------
    public static void main(String[] args) {
        EvaluacionCredito cliente1 = new EvaluacionCredito(
                "Ana Pérez", 2000000, 1, 450, 10000000, false);

        EvaluacionCredito cliente2 = new EvaluacionCredito(
                "Luis Gómez", 3000000, 2, 650, 15000000, true);

        EvaluacionCredito cliente3 = new EvaluacionCredito(
                "María López", 5000000, 1, 750, 20000000, false);

        double tasaAnual = 24.0;
        int plazo = 36;

        System.out.println("Cliente: " + cliente1.getNombreSolicitante() +
                " → Aprobado: " + cliente1.evaluarAprobacion(tasaAnual, plazo));

        System.out.println("Cliente: " + cliente2.getNombreSolicitante() +
                " → Aprobado: " + cliente2.evaluarAprobacion(tasaAnual, plazo));

        System.out.println("Cliente: " + cliente3.getNombreSolicitante() +
                " → Aprobado: " + cliente3.evaluarAprobacion(tasaAnual, plazo));
    }
}