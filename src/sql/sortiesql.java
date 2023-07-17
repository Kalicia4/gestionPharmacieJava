
package sql;
import java.sql.*;
import javax.swing.JOptionPane;

public class sortiesql extends connexionsql {
    java.sql.Statement st;
    ResultSet rs;
    
    public ResultSet listeRuptureStock(){
       
        try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select * from medicament where stock < 5";
            rs= st.executeQuery(sql);
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }
    
    public ResultSet recetteAccumule(String date){
        
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "SELECT SUM((medicament.prix_unitaire*achat.nbr)) AS somme FROM medicament JOIN achat ON medicament.nummedoc = achat.nummedoc WHERE achat.dateachat ='"+date+"'";
            rs= st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
        
        return rs;
    }
    
    public ResultSet medicamentLePlusVendu(String date){
        
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "select achat.nummedoc,medicament.design, sum(nbr) as quantite from achat join medicament on achat.nummedoc = medicament.nummedoc where dateachat = '"+date+"' group by achat.nummedoc, medicament.design order by quantite DESC limit 5;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
        
        return rs;
    }
    
    ///////// REQUETE POUR PDF //////////
    
    public ResultSet tableauFacture(String date, String nomCli){
        
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "SELECT medicament.design, medicament.prix_unitaire, SUM(nbr) AS nombre, (SUM(nbr) * (medicament.prix_unitaire)) AS total FROM achat JOIN medicament ON achat.nummedoc = medicament.nummedoc where dateachat='"+date+"' and nomclient='"+nomCli+"' GROUP BY medicament.design,medicament.prix_unitaire;";
            rs= st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
        
        return rs;
    }
    
    public ResultSet sommeTotal(String date, String nomCli){
        
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "SELECT SUM(total) AS somme_total FROM (SELECT SUM(achat.nbr * medicament.prix_unitaire) AS total FROM achat JOIN medicament ON achat.nummedoc = medicament.nummedoc where dateachat='"+date+"' and nomclient='"+nomCli+"') AS sub_query";
            rs= st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
        
        return rs;
    }
    
    /// FIN REQUETE PDF ///
    
    /// REQUETE POUR HISTOGRAMME ///
    public ResultSet histogrammeRecette(){
        
         try{
            
            Connection connexion = connecter();
            st = connexion.createStatement();
            String sql = "SELECT SUM((medicament.prix_unitaire*achat.nbr)) AS somme,EXTRACT (MONTH FROM achat.dateachat) AS mois  FROM medicament JOIN achat ON medicament.nummedoc = achat.nummedoc GROUP BY mois ORDER BY mois DESC LIMIT 5";
            rs= st.executeQuery(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur", "message",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        }
        
        return rs;
    }
    
}
