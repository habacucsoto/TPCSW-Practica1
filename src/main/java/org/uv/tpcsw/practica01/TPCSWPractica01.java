package org.uv.tpcsw.practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uv.tpcsw.practica01.dao.ConexionBD;
import org.uv.tpcsw.practica01.dao.DAOEmpleadoConcreto;
import org.uv.tpcsw.practica01.dao.EmpleadoPojo;
import org.uv.tpcsw.practica01.dao.TransaccionDB;
import org.uv.tpcsw.practica01.inyeccion.*;


public class TPCSWPractica01 {

    public static void main(String[] args) {
        
        ConexionBD conexion = ConexionBD.getInstance();
        EmpleadoPojo pojo = new EmpleadoPojo();
        pojo.setClave("1123");
        pojo.setNombre("Habacuc");
        pojo.setDireccion("Av 1");
        pojo.setTelefono("272");
        TransaccionDB<EmpleadoPojo> transaction = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="insert into empleados (clave, nombre, direccion, telefono) values"
                            + " (?,?,?,?)";
                    
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    pst.setString(2, pojo.getNombre());
                    pst.setString(3, pojo.getDireccion());
                    pst.setString(4, pojo.getTelefono());
                    return pst.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(TPCSWPractica01.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        conexion.execute(transaction);
        
        pojo.setClave("1123"); 
        pojo.setNombre("Habacuc Modificado");
        pojo.setDireccion("Nueva dirección");
        pojo.setTelefono("123456");

        TransaccionDB<EmpleadoPojo> transactionUpdate = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "UPDATE empleados SET nombre = ?, direccion = ?, telefono = ? WHERE clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getNombre());
                    pst.setString(2, pojo.getDireccion());
                    pst.setString(3, pojo.getTelefono());
                    pst.setString(4, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    Logger.getLogger(TPCSWPractica01.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        conexion.execute(transactionUpdate);
//        
        pojo.setClave("01");

        TransaccionDB<EmpleadoPojo> transactionDelete = new TransaccionDB<EmpleadoPojo>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "DELETE FROM empleados WHERE clave = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, pojo.getClave());
                    return pst.executeUpdate() > 0;
                } catch (SQLException ex) {
                    Logger.getLogger(TPCSWPractica01.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        conexion.execute(transactionDelete);

//        MensajeController controller = new MensajeController();
//        SaludoConcreto saludo = new SaludoConcreto();
//        DespedidaConcreto despedida = new DespedidaConcreto();
//        IMensajeID msg = new IMensajeID() {
//            @Override
//            public void imprimir() {
//                System.out.println("Otro mensaje ID");
//            }
//        };
//        guardar, modificar y borrar
//        controller.mostrar(msg);
        
//        DAOEmpleadoConcreto dao = new DAOEmpleadoConcreto();
//        EmpleadoPojo empleado = new EmpleadoPojo();
//        empleado.setClave("10");
//        empleado.setNombre("Roberto");
//        empleado.setDireccion("AV. 1");
//        empleado.setTelefono("272 708 9364");
//        
//        dao.save(empleado);
        
        /**
            Singleton s1 = Singleton.getInstance();
            Singleton s2 = Singleton.getInstance();
            System.out.println(s1);
            System.out.println(s2);
            
            
//          Singleton s1 = new Singleton();
//          Singleton s2 = new Singleton();
//          System.out.println(s1);
//          System.out.println(s2);


//           IMensaje msgv = new Saludo2();
//           msgv.imprimir();
//           
//           msgv = new Despedida2();
//           msgv.imprimir();
//           
//           //Lanmda
//           msgv = () -> {
//               System.out.println("Otro mensaje...");
//           };
//           msgv.imprimir();
        
//        Mensaje msgV=null;
//        
//        msgV = new Saludo();
//        
//        msgV.imprimir();
//        
//        msgV = new Despedida();
//        
//        msgV.imprimir();
//        
//        msgV=new Mensaje(){
//            @Override
//            protected void msg(){
//                System.out.println("Otro mensaje");
//            }
//        };
//        msgV.imprimir();
//        
        
//        MensajeConcreto msg=null;
//        msg = new MensajeConcreto("Otro");
//        msg.imprimir();
//        System.out.println("Hello World!");
* */
    }
    
}
