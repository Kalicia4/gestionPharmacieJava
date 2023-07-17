
package sql;
import java.sql.*;
import javax.swing.JOptionPane;

public class crudachat extends connexionsql {
    java.sql.Statement st;
    ResultSet rs;
    
    public void inserer(String numM, String nomCli, Integer n, String dateA){
        try{

            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "insert into achat values(0,'"+numM+"','"+nomCli+"','"+n+"','"+dateA+"');";
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
            String sql = "select * from achat;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }
    
    public void actualiser(String numA ,String numM ,String nomCli ,Integer nb, String dateA ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "update achat set nummedoc='"+numM+"', nomclient='"+nomCli+"', nbr='"+nb+"', dateachat='"+dateA+"'  where numachat='"+numA+"';";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "modification effectuée", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
     public void initstock(String numMedoc, Integer quantite){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = stock  + '"+quantite+"' where nummedoc ='"+numMedoc+"' ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"base de donnée mis à jour", "message", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } 
    
    public void supprimer(String numA, String numM, String nomCli, Integer n, String dateA ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "DELETE FROM achat WHERE numachat='"+numA+"' AND nummedoc='"+numM+"' AND nomclient='"+nomCli+"' AND nbr='"+n+"'  AND dateachat='"+dateA+"'";
            
            int message = JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer?", "message", JOptionPane.YES_NO_OPTION);
            
            if(message == 0){
            
                st.executeUpdate(sql);
                initstock( numM, n);
            }
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } 
    
    public void achatstock(String numMedoc, Integer quantite){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = stock  - '"+quantite+"' where nummedoc ='"+numMedoc+"' ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "base de donnée mis à jour", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void updatestockmodif(Integer ancien, Integer nouvel, String numMedoc){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql ="update medicament set stock = (stock  - ("+nouvel+" - "+ancien+")) where nummedoc ='"+numMedoc+"' ";
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
