public class Empleado {

    private String id;
    private String nombreCompleto;
    private float horasTrabajadas;
    private float tarifaHora;
    private float deduccionPorcentaje;

    public Empleado(String id, String nombreCompleto, float horasTrabajadas, float tarifaHora, float deduccionPorcentaje) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaHora = tarifaHora;
        this.deduccionPorcentaje = deduccionPorcentaje;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public float getTarifaHora() {
        return tarifaHora;
    }

    public float getDeduccionPorcentaje() {
        return deduccionPorcentaje;
    }

    public float calcularSalarioBruto() {
        return tarifaHora * horasTrabajadas;
    }

    public float calcularSalarioNeto() {
        float salarioNeto = calcularSalarioBruto() * (1 - deduccionPorcentaje / 100);
        return salarioNeto;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombreCompleto + "\nSalario Bruto: " + calcularSalarioBruto() + "\nSalario Neto: " + calcularSalarioNeto();
    }
}