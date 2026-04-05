import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalCuenta {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Cuenta> listaCuentas = new ArrayList<>();
        Cuenta cuentaActual = null;
        int opcion = 0;
        
        do {
            System.out.println("\n=== MENU PRINCIPAL - CUENTAS ===");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Conocer la cantidad de cuentas creadas");
            System.out.println("3. Listar cuentas");
            System.out.println("4. Seleccionar cuenta actual");
            System.out.println("5. Asignar el nombre del cuenta habiente (de la cuenta actual)");
            System.out.println("6. Depositar (en la cuenta actual)");
            System.out.println("7. Retirar (de la cuenta actual)");
            System.out.println("8. Consultar saldo (de la cuenta actual)");
            System.out.println("9. Consultar estado de la cuenta (actual)");
            System.out.println("10. Salir");
            System.out.println("================================");
            System.out.print("Ingrese su opcion: ");
            
            String linea = teclado.nextLine().trim();
            try {
                opcion = Integer.parseInt(linea);
            } catch (Exception e) {
                opcion = 0;
            }
            
            switch (opcion) {
                case 1:
                    System.out.print("Desea ingresar nombre del cuenta habiente ahora? (s/n): ");
                    String respuesta = teclado.nextLine().trim().toLowerCase();
                    
                    if (respuesta.equals("s")) {
                        System.out.print("Nombre del cuenta habiente: ");
                        String nombre = teclado.nextLine();
                        System.out.print("Saldo inicial: ");
                        double saldo = Double.parseDouble(teclado.nextLine().trim());
                        Cuenta nueva = new Cuenta(nombre, saldo);
                        listaCuentas.add(nueva);
                        System.out.println("Cuenta creada con exito: " + nueva.getCodCuenta());
                    } else {
                        System.out.print("Saldo inicial: ");
                        double saldo = Double.parseDouble(teclado.nextLine().trim());
                        Cuenta nueva = new Cuenta(saldo);
                        listaCuentas.add(nueva);
                        System.out.println("Cuenta creada con exito (nombre pendiente): " + nueva.getCodCuenta());
                    }
                    break;
                    
                case 2:
                    System.out.println("Cantidad total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                    
                case 3:
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas todavia.");
                    } else {
                        System.out.println("\nLISTADO DE CUENTAS:");
                        for (Cuenta c : listaCuentas) {
                            System.out.println(c.toString());
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;
                    
                case 4:
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas para seleccionar.");
                        break;
                    }
                    System.out.print("Ingrese codigo de cuenta (ej: cta-1): ");
                    String codBuscado = teclado.nextLine().trim();
                    cuentaActual = null;
                    for (Cuenta c : listaCuentas) {
                        if (c.getCodCuenta().equals(codBuscado)) {
                            cuentaActual = c;
                            break;
                        }
                    }
                    if (cuentaActual != null) {
                        System.out.println("Cuenta actual seleccionada: " + cuentaActual.getCodCuenta());
                    } else {
                        System.out.println("Codigo de cuenta no encontrado.");
                    }
                    break;
                    
                case 5:
                    if (cuentaActual == null) {
                        System.out.println("Primero debe seleccionar una cuenta actual (opcion 4).");
                    } else {
                        System.out.print("Nuevo nombre del cuenta habiente: ");
                        String nuevoNombre = teclado.nextLine();
                        cuentaActual.setNombreCuentaHabiente(nuevoNombre);
                        System.out.println("Nombre asignado correctamente.");
                    }
                    break;
                    
                case 6:
                    if (cuentaActual == null) {
                        System.out.println("Primero debe seleccionar una cuenta actual (opcion 4).");
                    } else {
                        System.out.print("Monto a depositar: ");
                        double montoDep = Double.parseDouble(teclado.nextLine().trim());
                        double nuevoSaldo = cuentaActual.depositar(montoDep);
                        System.out.println("Deposito realizado. Saldo actual: " + nuevoSaldo);
                    }
                    break;
                    
                case 7:
                    if (cuentaActual == null) {
                        System.out.println("Primero debe seleccionar una cuenta actual (opcion 4).");
                    } else {
                        System.out.print("Monto a retirar: ");
                        double montoRet = Double.parseDouble(teclado.nextLine().trim());
                        double nuevoSaldo = cuentaActual.retirar(montoRet);
                        System.out.println("Saldo actual: " + nuevoSaldo);
                    }
                    break;
                    
                case 8:
                    if (cuentaActual == null) {
                        System.out.println("Primero debe seleccionar una cuenta actual (opcion 4).");
                    } else {
                        System.out.println("Saldo actual: " + cuentaActual.getSaldo());
                    }
                    break;
                    
                case 9:
                    if (cuentaActual == null) {
                        System.out.println("Primero debe seleccionar una cuenta actual (opcion 4).");
                    } else {
                        System.out.println(cuentaActual.toString());
                    }
                    break;
                    
                case 10:
                    System.out.println("Gracias por usar el sistema de cuentas.");
                    break;
                    
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
            
        } while (opcion != 10);
        
        teclado.close();
    }
}