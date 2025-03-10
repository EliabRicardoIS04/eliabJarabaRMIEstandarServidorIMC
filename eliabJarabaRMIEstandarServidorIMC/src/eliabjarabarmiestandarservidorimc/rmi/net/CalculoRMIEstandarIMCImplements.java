
package eliabjarabarmiestandarservidorimc.rmi.net;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import lib.ClassDatosIMC;
import lib.interfaceRClassDatosIMC;


public class CalculoRMIEstandarIMCImplements extends UnicastRemoteObject implements interfaceRClassDatosIMC{
        
        public  ClassDatosIMC datosimc;
    
    public CalculoRMIEstandarIMCImplements() throws RemoteException{
        super();
    }
       
    public static void main( String []args) throws AlreadyBoundException{
        try {
           String name = "interfaceRClassDatosIMC";
           interfaceRClassDatosIMC OServicio =new CalculoRMIEstandarIMCImplements();
           interfaceRClassDatosIMC stub = (interfaceRClassDatosIMC) UnicastRemoteObject.exportObject(OServicio,0);
            Registry registry = LocateRegistry.getRegistry(1099);       
           registry.rebind(name, stub);
           System.out.println("El servidor esta funcionando bien" );
       } catch (Exception ex) {
           System.out.println("Error de url mal formada: " + ex.getMessage());
           ex.printStackTrace();
        System.out.println("El servidor no funciona" );

       }
   }
    
    @Override
    public ClassDatosIMC CalcularIMC (ClassDatosIMC datosimc) throws RemoteException{
        float resultado = 0;

        if (datosimc.getPeso() <= 0 || datosimc.getAltura() <= 0) {
            datosimc.setInterpretacion("ERROR: El peso y la altura deben ser mayor que 0");
            return datosimc;
        } else {
            resultado = datosimc.getPeso() / (datosimc.getAltura() * datosimc.getAltura());
            datosimc.setResultado(resultado);
            if (resultado < 18.5) {
                datosimc.setInterpretacion(" Debes consultar de un medico, tu peso es muy bajo ");
            } else if (resultado >= 18.5 && resultado <= 24.9) {
                datosimc.setInterpretacion("  Estan bien de peso  ");
            } else if (resultado >= 24.9 && resultado <= 29.9) {
                datosimc.setInterpretacion("  Debes bajar un poco de peso  ");
            } else {
                datosimc.setInterpretacion("  Debes consultar de un medico, tu peso es muy alto ");
            }
            return datosimc;
        }
    }
        
  
    
}
