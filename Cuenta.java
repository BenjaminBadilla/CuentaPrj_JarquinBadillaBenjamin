import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta {
    
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    
    private static int cantCuentasCreadas = 0;
    
    // Constructor con 2 parámetros (el principal)
    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        this.saldo = pSaldo;
        cantCuentasCreadas++;
        this.codCuenta += cantCuentasCreadas;
        
        // Fecha de creación
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        this.fechaCreacion = formatoFecha.format(fecha);
        
        this.cantDepositosRealizados = 0;
        this.cantRetirosExitososRealizados = 0;
    }
    
    // Constructor con 1 parámetro (llama al de 2 parámetros)
    public Cuenta(double pSaldo) {
        this("", pSaldo);   // ← Aquí se evita la duplicación
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta() {
        return codCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public double depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            cantDepositosRealizados++;
        }
        return saldo;
    }
    
    public boolean validarRetiro(double monto) {
        return saldo >= monto && monto > 0;
    }
    
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }
    
    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }
    
    public String toString() {
        String nombre = nombreCuentaHabiente.isEmpty() 
                        ? "PENDIENTE DE ASIGNAR" 
                        : nombreCuentaHabiente;
        
        return "CUENTA: " + codCuenta + "\n" +
               "Nombre: " + nombre + "\n" +
               "Saldo: " + saldo + "\n" +
               "Fecha creacion: " + fechaCreacion + "\n" +
               "Depositos: " + cantDepositosRealizados + "\n" +
               "Retiros exitosos: " + cantRetirosExitososRealizados;
    }
}