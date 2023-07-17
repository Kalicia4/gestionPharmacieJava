/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaloina
 */
public class crudentree extends connexionsql{
    java.sql.Statement st;
    ResultSet rs;
    
    public void inserer(String numMedoc, Integer stockEntree, String dateEntree){
        try{

            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "insert into entree values(0,'"+numMedoc+"','"+stockEntree+"','"+dateEntree+"');";
            st.execute(sql);
            st.close();
            connexion.close();
            JOptionPane.showMessageDialog(null, "l'enregistrement est effectué", "message",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "l'enregistrement n'est pas effectué", "message",JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
    }
    
    public ResultSet itemComboBox(){
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select nummedoc from medicament;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }
    
    public ResultSet affichageDonnes(){
       
        try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select * from entree;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }
    
    public void actualiser(String num ,String numM ,Integer stockE, String dateEntree ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "update entree set nummedoc='"+numM+"', stockentree='"+stockE+"', dateentree='"+dateEntree+"'  where numentree='"+num+"';";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "modification effectuée", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
     public void updatestocksuppr(Integer stockEntree, String numMedoc){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = stock  - '"+stockEntree+"' where nummedoc ='"+numMedoc+"' ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "base de donnée mis à jour", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur add", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void supprimer(String numE, String numM, Integer stockE, String dateE ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "DELETE FROM entree WHERE numentree='"+numE+"' AND nummedoc='"+numM+"' AND stockentree='"+stockE+"' AND dateentree='"+dateE+"'";
            int message =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer ?", "message", JOptionPane.YES_NO_OPTION);
            
            if(message == 0){
                st.executeUpdate(sql);
                updatestocksuppr(stockE, numM);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } 
    
    public void updatestock(Integer stockEntree, String numMedoc ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = stock  + '"+stockEntree+"' where nummedoc ='"+numMedoc+"' ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "base de donnée mis à jour", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur add", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } 
    
   
    public void updatestockmodif(Integer ancien, Integer nouvel, String numMedoc){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = (stock  + ("+nouvel+" - "+ancien+")) where nummedoc ='"+numMedoc+"' ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "base de donnée mis à jour", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur add", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public ResultSet stockverif(String numM){
       
        try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select stock from medicament where nummedoc='"+numM+"';";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }
}
