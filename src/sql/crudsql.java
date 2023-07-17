
package sql;
import java.sql.*;
import javax.swing.JOptionPane;


public class crudsql extends connexionsql {
    java.sql.Statement st;
    ResultSet rs;
    
    public void inserer(String design, Integer pu){
        try{

            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "insert into medicament values(0,'"+design+"','"+pu+"',0);";
            st.execute(sql);
            st.close();
            connexion.close();
            JOptionPane.showMessageDialog(null, "l'enregistrement est effectué", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "l'enregistrement n'est pas effectué", "message",JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
    }
    
    public ResultSet affichageDonnes(){
       
        try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select * from medicament;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }
    
    public void actualiser(String num,String design, Integer pu ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "update medicament set design='"+design+"', prix_unitaire='"+pu+"' where nummedoc='"+num+"';";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "modification effectuée", "message",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void supprimer(String num,String design, Integer pu ){
        try{
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "DELETE FROM medicament WHERE nummedoc = '"+num+"' AND design='"+design+"' AND prix_unitaire='"+pu+"'";
            int message =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment modifier", "message", JOptionPane.YES_NO_OPTION);
            
            if(message == 0){
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "vous venez de supprimer une ligne");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public ResultSet recherche(String design){
       
        try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select * from medicament where design like '%" + design +"%'";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return rs;
    }
    
}
