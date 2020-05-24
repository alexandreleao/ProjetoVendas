
package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;


public class Utilitarios {

    //Metodo limparcampos
    public void LimpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component : components){
            if(component instanceof JTextField){
                ((JTextField)component).setText(null);
            }
        }
    }
    
    /* public void LimpaTela(JTable container){
        Component components[] = container.getComponents();
        for(Component component : components){
            if(component instanceof JTable){
                ((JTable)component).setText(null);
            }
        }
    }*/
}
